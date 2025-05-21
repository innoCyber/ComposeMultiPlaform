package org.example.project.pdf.presentation.pdflist

import org.example.project.pdf.domain.Pdf

sealed interface PdfListAction {
    data class OnSearchQueryChanged(val query: String): PdfListAction
    data class OnPdfClicked(val pdf: Pdf): PdfListAction
    data class OnTabSelected(val index: Int): PdfListAction
}