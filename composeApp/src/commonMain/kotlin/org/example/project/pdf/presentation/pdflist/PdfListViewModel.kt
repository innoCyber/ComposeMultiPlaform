package org.example.project.pdf.presentation.pdflist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PdfListViewModel: ViewModel() {
    private val _state = MutableStateFlow(PdfListState())
    val state = _state.asStateFlow()

    fun onAction(action: PdfListAction){
        when(action){
            is PdfListAction.OnPdfClicked -> TODO()
            is PdfListAction.OnSearchQueryChanged -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is PdfListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }
}