package com.schuster.composecleanarchitecture.presentation.feature

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schuster.composecleanarchitecture.presentation.model.PostUiModel
import com.schuster.composecleanarchitecture.utils.TestTags
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun postScreen_showsShimmer_whenStatusIsLoading() {
        composeTestRule.setContent {
            PostScreenContent(
                uiStateValue = PostUiState(status = PostStatus.Loading),
                snackbarHostState = remember { SnackbarHostState() },
                onIntent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TestTags.LOADING)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun postScreen_showsPostDetails_whenStatusIsSuccess() {
        val postData = PostUiModel(
            postId = "1",
            id = "100",
            email = "compose@test.com",
            name = "Android Developer",
            comment = "Compose Clean Architecture Test"
        )

        composeTestRule.setContent {
            PostScreenContent(
                uiStateValue = PostUiState(status = PostStatus.Success(data = postData)),
                snackbarHostState = remember { SnackbarHostState() },
                onIntent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TestTags.COMMENT_TEXT)
            .assertIsDisplayed()
            .assertTextEquals("COMENTARIO: Compose Clean Architecture Test")

        composeTestRule
            .onNodeWithText("EMAIL: compose@test.com")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("NOME: Android Developer")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("ID: 100")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("POST ID: 1")
            .assertIsDisplayed()
    }

    @Test
    fun postScreen_showsErrorScreen_whenStatusIsError() {
        val errorMessage = "Recurso não encontrado"

        composeTestRule.setContent {
            PostScreenContent(
                uiStateValue = PostUiState(status = PostStatus.Error(message = errorMessage)),
                snackbarHostState = remember { SnackbarHostState() },
                onIntent = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()
    }

    @Test
    fun postScreen_clickingSearchIcon_dispatchesOnSearchIntent() {
        var dispatchedIntent: PostIntent? = null

        composeTestRule.setContent {
            PostScreenContent(
                uiStateValue = PostUiState(textSearch = "42"),
                snackbarHostState = remember { SnackbarHostState() },
                onIntent = { intent -> dispatchedIntent = intent }
            )
        }

        composeTestRule
            .onNodeWithTag(TestTags.CLICK_SEARCH)
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        assertEquals(PostIntent.OnSearch, dispatchedIntent)
    }
}
