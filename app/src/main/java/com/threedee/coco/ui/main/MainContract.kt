package com.threedee.coco.ui.main

import com.darkwater.alfred.viper.BaseContract
import com.threedee.coco.service.Cell
import io.reactivex.Single

interface MainContract {

    interface View : BaseContract.View {

    }

    interface Interactor : BaseContract.Interactor {
        fun getData(): Single<List<Cell>>
    }

    interface Presenter : BaseContract.Presenter {

    }

    interface Router : BaseContract.Router {

    }
}