package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun onIncrement()
    fun onDecrement()
}
