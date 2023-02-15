package fr.hug0cr.collectme.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hug0cr.collectme.common.snackbar.SnackbarManager
import fr.hug0cr.collectme.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import fr.hug0cr.collectme.model.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class CollectMeViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}