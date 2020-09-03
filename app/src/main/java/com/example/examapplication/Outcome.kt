package com.example.examapplication

sealed class Outcome<T>(var  status: Status) {
    data class Progress<T>(var loading: Boolean) : Outcome<T>(Status.LOADING)
    data class Success<T>(var data: T) : Outcome<T>(Status.SUCCESS)
    data class Failure<T>(var e: Throwable) : Outcome<T>(Status.FAILURE)

    companion object {
        fun <T> loading(isLoading: Boolean): Outcome<T> =
            Progress(isLoading)

        fun <T> success(data: T): Outcome<T> =
            Success(data)

        fun <T> failure(e: Throwable): Outcome<T> =
            Failure(e)
    }

    enum class Status {
        SUCCESS, FAILURE, LOADING
    }
}