package com.chrrissoft.amazingkeyboard.datalayer.services

import android.inputmethodservice.InputMethodService
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import com.chrrissoft.amazingkeyboard.uilayer.keyboard.AndroidKeyboardView

class IMEService : InputMethodService(),
    LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {

    private var _isShifted = MutableLiveData(false)
    private var _isShift = MutableLiveData(true)
    private var _IMEAcction = MutableLiveData(2)


    val isShifted: LiveData<Boolean> get() = _isShifted
    val isShift: LiveData<Boolean> get() = _isShift
    val IMEAcction: LiveData<Int?> get() = _IMEAcction




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

        if (attribute != null) {
            when ( attribute.inputType and InputType.TYPE_MASK_CLASS ) {
                 InputType.TYPE_TEXT_VARIATION_URI -> {
                     Log.d("T", "TYPE_TEXT_VARIATION_URI")
                     _IMEAcction.value = EditorInfo.IME_ACTION_SEARCH
                 }
                InputType.TYPE_CLASS_PHONE -> {
                    Log.d("T", "TYPE_CLASS_PHONE")
                    _IMEAcction.value = EditorInfo.IME_ACTION_GO
                }
                InputType.TYPE_CLASS_NUMBER -> {
                    Log.d("T", "TYPE_CLASS_NUMBER")
                    _IMEAcction.value = EditorInfo.IME_ACTION_DONE
                }
                InputType.TYPE_CLASS_TEXT -> {
                    _IMEAcction.value = EditorInfo.IME_ACTION_PREVIOUS
                    Log.d("T", "TYPE_CLASS_TEXT")
                }
            }
        }

    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
    }


    fun enableShift() { _isShift.value = true }
    fun disableShift() { _isShift.value = false }

    fun enableShifted() { _isShifted.value = true }
    fun disableShifted() { _isShifted.value = false }


    fun sendText(text: CharSequence) {
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
