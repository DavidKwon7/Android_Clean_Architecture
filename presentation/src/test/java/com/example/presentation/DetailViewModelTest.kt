package com.example.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.test.filters.SmallTest
import com.example.domain.usecase.GetPostCommentsUseCase
import com.example.presentation.mapper.CommentDomainUiMapper
import com.example.presentation.utils.MainCoroutineRule
import com.example.presentation.vm.DetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class DetailViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var getPostCommentsUseCase: GetPostCommentsUseCase

    private val commentMapper = CommentDomainUiMapper()
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        detailViewModel = DetailViewModel(
            savedStateHandle = savedStateHandle,
            getPostCommentsUseCase = getPostCommentsUseCase,
            commentMapper = commentMapper
        )
    }
}