@file:Suppress("NAME_SHADOWING")

package com.chrrissoft.amazingkeyboard

import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.os.Binder
import android.os.IBinder
import android.view.View
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.ViewTreeSavedStateRegistryOwner
import java.util.*


class IMEService : InputMethodService(),
        LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {

        private val binder = LocalBinder()

        fun sendText() = currentInputConnection.commitText("hello", 1)

        inner class LocalBinder : Binder() {
            fun getService(): IMEService = this@IMEService
        }

        fun myOnBind(intent: Intent): IBinder {
            return binder
        }

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

        //SaveStateRegestry Methods

        private val savedStateRegistry = SavedStateRegistryController.create(this)

        override fun getSavedStateRegistry(): SavedStateRegistry =
            savedStateRegistry.savedStateRegistry

    }
