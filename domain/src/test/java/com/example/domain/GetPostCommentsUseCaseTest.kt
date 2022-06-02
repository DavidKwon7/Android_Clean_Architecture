package com.example.domain

import app.cash.turbine.test
import com.example.common.extensions.Resource
import com.example.domain.repository.Repository
import com.example.domain.usecase.GetPostCommentsUseCase
import com.example.domain.utils.MainCoroutineRule
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
import java.lang.Exception
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class GetPostCommentsUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var getPostCommentsUseCase: GetPostCommentsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPostCommentsUseCase = GetPostCommentsUseCase(
            repository = repository,
            dispatcher = mainCoroutineRule.dispatcher
        )
    }

    @Test
    fun test_get_post_comments_success() = runBlockingTest {
        val comments = TestDataGenerator.generatePostComments()
        val commentsFlow = flowOf(Resource.Success(comments))

        coEvery { repository.getPostComments(any()) } returns commentsFlow

        val result = getPostCommentsUseCase.execute(1)
        result.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).containsExactlyElementsIn(comments)
            expectComplete()
        }
        coEvery { repository.getPostComments(any()) }

    }

    @Test
    fun test_get_post_comments_fail() = runBlockingTest {
        val errorFlow = flowOf(Resource.Error(Exception()))
        coEvery { repository.getPostComments(any()) } returns errorFlow
        val result = getPostCommentsUseCase.execute(1)
        result.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }
        coVerify { repository.getPostComments(any()) }
    }

    @Test
    fun test_get_post_comments_fail_with_empty_param() = runBlockingTest {
        val errorFlow = flowOf(Resource.Error(Exception()))
        coEvery { repository.getPostComments(any()) } returns errorFlow

        val result = getPostCommentsUseCase.execute(null)
        result.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Error).exception.message
            Truth.assertThat(expected).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat(expectedData).isEqualTo("PostId can not be null")
            expectComplete()
        }

        coVerify(exactly = 0) { repository.getPostComments(any()) }

    }
}