package com.threedee.coco.ui.main

import com.darkwater.kraken.viper.BaseContract
import com.threedee.coco.service.Data
import io.reactivex.Single

interface MainContract {

    interface View : BaseContract.View {
        fun showLoading()
        fun stopLoading()
    }

    interface Interactor : BaseContract.Interactor {
        fun getData(): Single<List<Data>>
    }

    interface Presenter : BaseContract.Presenter {

    }

    interface Router : BaseContract.Router {

    }
}