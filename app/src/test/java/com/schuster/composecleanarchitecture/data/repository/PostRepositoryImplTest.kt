package com.schuster.composecleanarchitecture.data.repository

import com.schuster.composecleanarchitecture.data.api.PostApiService
import com.schuster.composecleanarchitecture.data.model.PostResponse
import com.schuster.composecleanarchitecture.domain.model.DomainResult
import com.schuster.composecleanarchitecture.domain.model.Post
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class PostRepositoryImplTest {

    private val api: PostApiService = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository = PostRepositoryImpl(api, testDispatcher)

    @Test
    fun `getPost should return Success when api call succeeds`() = runTest {
        val postResponse = PostResponse(
            postId = 1,
            id = 5,
            email = "test@domain.com",
            name = "Test User",
            comment = "Comment content"
        )
        coEvery { api.getPost(5) } returns postResponse

        val result = repository.getPost(5)

        coVerify(exactly = 1) { api.getPost(5) }
        assertTrue(result is DomainResult.Success)
        val expectedData = Post(
            postId = 1,
            id = 5,
            email = "test@domain.com",
            name = "Test User",
            comment = "Comment content"
        )
        assertEquals(expectedData, (result as DomainResult.Success).data)
    }

    @Test
    fun `getPost should return Error when api throws IOException`() = runTest {
        coEvery { api.getPost(10) } throws IOException("No internet")

        val result = repository.getPost(10)

        coVerify(exactly = 1) { api.getPost(10) }
        assertTrue(result is DomainResult.Error)
        assertEquals("Network error. Please check your connection.", (result as DomainResult.Error).message)
    }
}
