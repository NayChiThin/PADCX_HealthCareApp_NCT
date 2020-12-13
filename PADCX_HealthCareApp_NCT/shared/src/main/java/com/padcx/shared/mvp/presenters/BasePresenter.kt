package com.padcx.shared.mvp.presenters

import com.padcx.shared.mvp.views.BaseView

interface BasePresenter<V: BaseView> {
    fun initPresenter(view:V)
}