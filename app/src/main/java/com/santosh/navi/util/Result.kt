package com.santosh.navi.util

enum class STATE {
    SUCCESS,
    ERROR,
    INPROGRESS
}

data class Result<out T>(val state: STATE, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(STATE.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T? = null): Result<T> {
            return Result(STATE.ERROR, data, message)
        }

        fun <T> inProgress(): Result<T> {
            return Result(STATE.INPROGRESS, null, null)
        }
    }
}