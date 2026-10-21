package com.schuster.composecleanarchitecture.data.mapper

import com.schuster.composecleanarchitecture.data.model.PostResponse
import com.schuster.composecleanarchitecture.data.model.toDomain
import com.schuster.composecleanarchitecture.domain.model.ObjectDomain
import com.schuster.composecleanarchitecture.presentation.mapper.toPresentation
import com.schuster.composecleanarchitecture.presentation.model.ObjectPresentation
import org.junit.Assert.assertEquals
import org.junit.Test

class PostMapperTest {

    @Test
    fun `PostResponse toDomain should map non-null values correctly`() {
        val response = PostResponse(
            postId = 10,
            id = 20,
            email = "dev@clean.com",
            name = "Developer",
            comment = "Clean Architecture post"
        )

        val domain = response.toDomain()

        assertEquals(10, domain.postId)
        assertEquals(20, domain.id)
        assertEquals("dev@clean.com", domain.email)
        assertEquals("Developer", domain.name)
        assertEquals("Clean Architecture post", domain.comment)
    }

    @Test
    fun `PostResponse toDomain should handle null values with defaults`() {
        val response = PostResponse(
            postId = null,
            id = null,
            email = null,
            name = null,
            comment = null
        )

        val domain = response.toDomain()

        assertEquals(0, domain.postId)
        assertEquals(0, domain.id)
        assertEquals("", domain.email)
        assertEquals("", domain.name)
        assertEquals("", domain.comment)
    }

    @Test
    fun `ObjectDomain toPresentation should format values into strings for UI`() {
        val domain = ObjectDomain(
            postId = 42,
            id = 99,
            email = "ui@test.com",
            name = "Compose UI",
            comment = "Mapping to UI model"
        )

        val presentation = domain.toPresentation()

        val expected = ObjectPresentation(
            postId = "42",
            id = "99",
            email = "ui@test.com",
            name = "Compose UI",
            comment = "Mapping to UI model"
        )
        assertEquals(expected, presentation)
    }
}
