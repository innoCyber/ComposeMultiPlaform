package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.example.project.pdf.presentation.pdflist.PdfListScreenRoot
import org.example.project.pdf.presentation.pdflist.PdfListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PdfListScreenRoot (
        viewModel = remember { PdfListViewModel() },
        onPdfClick = {}
    )
}