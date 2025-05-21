package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.pdf.presentation.pdflist.PdfListScreen
import org.example.project.pdf.presentation.pdflist.PdfListState
import org.example.project.pdf.presentation.pdflist.pdfs


@Preview
@Composable
private fun PdfListScreenPreview() {
    PdfListScreen(
        state = PdfListState(searchResults = pdfs),
        onAction = {}
    )
}
