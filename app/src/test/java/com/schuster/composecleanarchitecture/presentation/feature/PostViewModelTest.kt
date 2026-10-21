package com.schuster.composecleanarchitecture.presentation.feature

import com.schuster.composecleanarchitecture.R
import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post
import com.schuster.composecleanarchitecture.domain.usecase.GetPostUseCase
import com.schuster.composecleanarchitecture.presentation.mapper.toPresentation
import com.schuster.composecleanarchitecture.util.MainDispatcherRule
import com.schuster.composecleanarchitecture.utils.UiText
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetPostUseCase = mockk(relaxed = true)

    @Test
    fun `init should trigger initial search and set status to InputTextError when textSearch is blank`() = runTest {
        val viewModel = PostViewModel(useCase)

        val state = viewModel.uiState.value
        assertEquals("", state.textSearch)
        assertEquals(PostStatus.InputTextError, state.status)
    }

    @Test
    fun `processIntent OnValueChange should filter non digit characters`() = runTest {
        val viewModel = PostViewModel(useCase)

        viewModel.processIntent(PostIntent.OnValueChange("123abc45"))

        assertEquals("12345", viewModel.uiState.value.textSearch)
    }

    @Test
    fun `processIntent OnSearch with blank text should emit ShowSnackbar effect and set InputTextError`() = runTest {
        val viewModel = PostViewModel(useCase)

        viewModel.processIntent(PostIntent.OnValueChange(""))
        viewModel.processIntent(PostIntent.OnSearch)

        val effect = viewModel.uiEffect.first()
        assertTrue(effect is PostUiEffect.ShowSnackbar)
        val snackbarEffect = effect as PostUiEffect.ShowSnackbar
        assertTrue(snackbarEffect.message is UiText.StringResource)
        assertEquals(R.string.search_not_empty, (snackbarEffect.message as UiText.StringResource).resId)
        assertEquals(PostStatus.InputTextError, viewModel.uiState.value.status)
    }

    @Test
    fun `processIntent OnSearch with valid id success should update state to Success`() = runTest {
        val domainData = Post(
            postId = 1,
            id = 100,
            email = "test@example.com",
            name = "Test Name",
            comment = "Test Comment"
        )
        coEvery { useCase(100) } returns DomainResult.Success(domainData)

        val viewModel = PostViewModel(useCase)
        viewModel.processIntent(PostIntent.OnValueChange("100"))
        viewModel.processIntent(PostIntent.OnSearch)

        coVerify(exactly = 1) { useCase(100) }

        val currentState = viewModel.uiState.value
        assertTrue(currentState.status is PostStatus.Success)
        val successStatus = currentState.status as PostStatus.Success
        assertEquals(domainData.toPresentation(), successStatus.data)
    }

    @Test
    fun `processIntent OnSearch with valid id error should update state to Error`() = runTest {
        val errorMessage = "Erro de conexão com o servidor"
        coEvery { useCase(100) } returns DomainResult.Error(errorMessage)

        val viewModel = PostViewModel(useCase)
        viewModel.processIntent(PostIntent.OnValueChange("100"))
        viewModel.processIntent(PostIntent.OnSearch)

        coVerify(exactly = 1) { useCase(100) }

        val currentState = viewModel.uiState.value
        assertTrue(currentState.status is PostStatus.Error)
        val errorStatus = currentState.status as PostStatus.Error
        assertEquals(errorMessage, errorStatus.message)
    }

    @Test
    fun `processIntent OnClearSearch should clear textSearch and set status to Idle`() = runTest {
        val viewModel = PostViewModel(useCase)
        viewModel.processIntent(PostIntent.OnValueChange("123"))

        viewModel.processIntent(PostIntent.OnClearSearch)

        val state = viewModel.uiState.value
        assertEquals("", state.textSearch)
        assertEquals(PostStatus.Idle, state.status)
    }
}
