package com.example.dmytroberezhnyi_vdatatesttask.presentation.base.extension

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setText(text: CharSequence) {
    editText?.setText(text)
}

fun TextInputLayout.getText(): String {
    return editText?.text.toString()
}