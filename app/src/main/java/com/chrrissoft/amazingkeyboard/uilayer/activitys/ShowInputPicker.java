package com.chrrissoft.amazingkeyboard.uilayer.activitys;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputMethodManager;

public class ShowInputPicker {
    public void showPicker(Context ctx) {
        ((InputMethodManager) ctx.getSystemService(InputMethodService.INPUT_METHOD_SERVICE))
                .showInputMethodPicker();
    }
}
