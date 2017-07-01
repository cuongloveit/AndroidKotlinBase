package com.androidtmc.android_kotlin_base.utils

/**
 * Created by cuong on 6/30/17.
 */
object CheckException {
    fun checkArgument(expression: Boolean) {
        if (!expression) {
            throw IllegalArgumentException()
        }
    }

    fun checkArgument(expression: Boolean, errorMessage: Any?) {
        if (!expression) {
            throw IllegalArgumentException(errorMessage.toString())
        }
    }

    fun checkState(expression: Boolean) {
        if (!expression) {
            throw IllegalStateException()
        }
    }

    fun checkState(expression: Boolean, errorMessage: Any?) {
        if (!expression) {
            throw IllegalStateException(errorMessage.toString())
        }
    }

    fun <T> checkNotNull(reference: T?): T {
        if (reference != null) {
            return reference
        } else {
            throw NullPointerException()
        }
    }

    fun <T> checkNotNull(reference: T?, errorMessage: Any?): T {
        if (reference != null) {
            return reference
        } else {
            throw NullPointerException(errorMessage.toString())
        }
    }
}