package com.chrrissoft.amazingkeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.ViewTreeSavedStateRegistryOwner


class IMEService : InputMethodService(), LifecycleOwner,
    ViewModelStoreOwner, SavedStateRegistryOwner {

    override fun onCreateInputView(): View {
        val view = AndroidKeyboardView(this)
        window!!.window!!.decorView.let { decorView ->
            ViewTreeLifecycleOwner.set(decorView, this)
            ViewTreeViewModelStoreOwner.set(decorView, this)
            ViewTreeSavedStateRegistryOwner.set(decorView, this)
        }
        view.let { v: AndroidKeyboardView ->
            ViewTreeLifecycleOwner.set(v, this)
            ViewTreeViewModelStoreOwner.set(v, this)
            ViewTreeSavedStateRegistryOwner.set(v, this)
        }
        return view
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
    }

    fun sendText(text: CharSequence, newCursorPosition: Int) {
        val focusedTextField = currentInputConnection // get null
        focusedTextField.commitText(text, newCursorPosition)
    }

    // Lifecycle Methods

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    private fun handleLifecycleEvent(event: Lifecycle.Event) =
        lifecycleRegistry.handleLifecycleEvent(event)

    override fun onCreate() {
        super.onCreate()
        savedStateRegistry.performRestore(null)
        handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onDestroy() {
        super.onDestroy()
        handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    //ViewModelStore Methods
    private val store = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore = store

    //SaveStateRegestry Methods

    private val savedStateRegistry = SavedStateRegistryController.create(this)

    override fun getSavedStateRegistry(): SavedStateRegistry = savedStateRegistry.savedStateRegistry
}

