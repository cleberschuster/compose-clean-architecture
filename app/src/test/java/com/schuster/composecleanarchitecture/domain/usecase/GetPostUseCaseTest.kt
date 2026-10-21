package com.schuster.composecleanarchitecture.domain.usecase

import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post
import com.schuster.composecleanarchitecture.domain.repository.PostRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostUseCaseTest {

    private val repository: PostRepository = mockk()
    private val useCase: GetPostUseCase = GetPostUseCaseImpl(repository)

    @Test
    fun `invoke should return Success when repository succeeds`() = runTest {
        val expectedDomain = Post(
            postId = 1,
            id = 10,
            email = "user@test.com",
            name = "John Doe",
            comment = "Great post!"
        )
        coEvery { repository.getPost(10) } returns DomainResult.Success(expectedDomain)

        val result = useCase(10)

        assertEquals(DomainResult.Success(expectedDomain), result)
        coVerify(exactly = 1) { repository.getPost(10) }
    }

    @Test
    fun `invoke should return Error when repository fails`() = runTest {
        val expectedError = DomainResult.Error("Post não encontrado")
        coEvery { repository.getPost(99) } returns expectedError

        val result = useCase(99)

        assertEquals(expectedError, result)
        coVerify(exactly = 1) { repository.getPost(99) }
    }
}
