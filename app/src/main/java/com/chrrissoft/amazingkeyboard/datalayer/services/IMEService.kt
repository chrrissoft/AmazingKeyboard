package com.chrrissoft.amazingkeyboard.datalayer.services

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.core.AndroidKeyboardView

class IMEService : InputMethodService(),
    LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {

    enum class ShiftState() { SHIFTED, NOT_SHIFT, SHIFT }

    private var _shiftState = MutableLiveData(ShiftState.SHIFT)


    val shiftState get() = _shiftState


    override fun onCreateInputView(): View {


        val view = AndroidKeyboardView(this)

        window!!.window!!.decorView.let { decorView ->
            ViewTreeLifecycleOwner.set(decorView, this)
            ViewTreeViewModelStoreOwner.set(decorView, this)
            ViewTreeSavedStateRegistryOwner.set(decorView, this)
        }
        view.let { view ->
            ViewTreeLifecycleOwner.set(view, this)
            ViewTreeViewModelStoreOwner.set(view, this)
            ViewTreeSavedStateRegistryOwner.set(view, this)
        }

        return view
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)

    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
    }


    fun enableShift() {
        _shiftState.value = ShiftState.SHIFT
    }

    fun disableShift() {
        _shiftState.value = ShiftState.NOT_SHIFT
    }

    fun enableShifted() {
        _shiftState.value = ShiftState.SHIFTED
    }


    fun onKey(text: CharSequence, index: Int?) {
        currentInputConnection.commitText(text, 1)
    }

    fun doneText() {
        currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_GO)
    }

    fun deleteText() {
        val selectedText = currentInputConnection?.getSelectedText(0)
        if (selectedText != null) {
            currentInputConnection.commitText("", 1)
        } else {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }
    }


    //Lifecycle Methods

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

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

    //SaveStateRegistry Methods

    private val savedStateRegistry = SavedStateRegistryController.create(this)

    override fun getSavedStateRegistry(): SavedStateRegistry =
        savedStateRegistry.savedStateRegistry
}
