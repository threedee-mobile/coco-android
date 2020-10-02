package com.threedee.coco.ui.main

import com.darkwater.alfred.adapter.common.text.TextViewModel
import com.darkwater.alfred.extensions.letAll
import com.darkwater.alfred.injection.scopes.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainPresenter
@Inject
constructor(
    private var view: MainContract.View?,
    private var interactor: MainContract.Interactor?
) : MainContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun initialize() {
        letAll(view, interactor) { view, interactor ->
            interactor.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    view.showView(
                        listOf(
                            TextViewModel(
                                text = "Welcome to COCO!"
                            )
                        )
                    )
                }, {
                    view.showView(
                        listOf(
                            TextViewModel(
                                text = "Welcome to COCO!"
                            )
                        )
                    )
                })
        }
    }

    override fun cleanUp() {
        disposable.clear()

        view = null
        interactor = null
    }
}