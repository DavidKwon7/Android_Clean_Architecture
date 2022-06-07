package com.example.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.extensions.Resource
import com.example.domain.usecase.GetPostsUseCase
import com.example.presentation.contract.MainContract
import com.example.presentation.mapper.PostDomainUiMapper
import com.example.presentation.utils.MainCoroutineRule
import com.example.presentation.utils.TestDataGenerator
import com.example.presentation.vm.MainViewModel
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var postUseCase: GetPostsUseCase

    private val postMapper = PostDomainUiMapper()
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mainViewModel = MainViewModel(
            savedStateHandle = savedStateHandle,
            postsUseCase = postUseCase,
            postMapper = postMapper
        )
    }

    /**
     *
     * Required:
    Flow<Resource<List<PostEntityModel>>>
    Found:
    Flow<Resource.Success<List<PostUiModel>>>
     */
    @Test
    fun test_fetch_posts_success() = runBlockingTest {
        val posts = TestDataGenerator.generatePosts()
        val postFlow = flowOf(Resource.Success(posts))

        coEvery { postUseCase.execute(null) } returns postFlow

        mainViewModel.uiState.test {
            mainViewModel.setEvnet(MainContract.Event.OnFetchPosts)
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    postsState = MainContract.PostsState.Idle,
                    selectedPost = null
                )
            )
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    postsState = MainContract.PostsState.Loading,
                    selectedPost = null
                )
            )
            val expected= expectItem()
            val expectedData = (expected.postsState as MainContract.PostsState.Success).posts
            Truth.assertThat(expected).isEqualTo(
                MainContract.State(
                    postsState = MainContract.PostsState.Success(postMapper.fromList(posts)),
                    selectedPost = null
                )
            )
            Truth.assertThat(expectedData).containsExactlyElementsIn(postMapper.fromList(posts))
            cancelAndConsumeRemainingEvents()
        }
        coVerify { postUseCase.execute(null) }
    }
}