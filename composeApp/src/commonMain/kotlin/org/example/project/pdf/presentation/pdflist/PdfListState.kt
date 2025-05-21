package org.example.project.pdf.presentation.pdflist

import org.example.project.core.presentation.UiText
import org.example.project.pdf.domain.Pdf

data class PdfListState(
    val searchQuery: String = "Compose",
    val searchResults: List<Pdf> = pdfs,
    val favoritePdfs: List<Pdf> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage : UiText? = null
)

val pdfs = (1..50).map {
    Pdf(
        id = it.toString(),
        title = "Title $it",
        imageUrl = "https://test.com",
        authors = listOf("Chinua $it"),
        languages = emptyList(),
        description = "Description $it",
        firstPublishYear = "",
        averageRating = 4.89,
        ratingCount = 100,
        numPages = 100,
        numEditions = 5
    )
}