package com.threedee.coco.ui.main

import com.darkwater.alfred.adapter.AdapterViewModel
import com.darkwater.alfred.adapter.common.text.TextViewModel
import com.darkwater.alfred.adapter.common.text.TextViewStyle
import com.darkwater.alfred.extensions.letAll
import com.darkwater.alfred.injection.scopes.ActivityScope
import com.darkwater.alfred.providers.DimensionProvider
import com.darkwater.alfred.style.BaseStyle
import com.threedee.coco.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainPresenter
@Inject
constructor(
    private var view: MainContract.View?,
    private var interactor: MainContract.Interactor?,
    private var dimensionProvider: DimensionProvider?
) : MainContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun initialize() {
        letAll(view, interactor, dimensionProvider) { view, interactor, dimensionProvider ->
            interactor.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cells ->
                    val viewModels: MutableList<AdapterViewModel> = mutableListOf()

                    val baseStyle = BaseStyle(
                        paddings = arrayListOf(
                            dimensionProvider.getDimenionPixelSize(R.dimen.margin_medium),
                            dimensionProvider.getDimenionPixelSize(R.dimen.margin_medium),
                            dimensionProvider.getDimenionPixelSize(R.dimen.margin_medium),
                            dimensionProvider.getDimenionPixelSize(R.dimen.margin_medium)
                        )
                    )

                    viewModels.add(
                        TextViewModel(
                            text = "${cells.size} items found for year 2018 within 200KM of Toronto",
                            style = TextViewStyle(layoutBaseStyle = baseStyle)
                        )
                    )

                    viewModels.addAll(cells.map { cell ->
                        TextViewModel(
                            id = cell.id.hashCode() and 0xfffffff,
                            text = "LAT: ${cell.lat}\nLON: ${cell.lon}\nFF_CO2: ${cell.ff_co2}\nIB_CO2: ${cell.ib_co2}",
                            style = TextViewStyle(layoutBaseStyle = baseStyle)
                        )
                    })

                    view.showView(viewModels)
                }, {
                    view.showView(
                        listOf(
                            TextViewModel(
                                text = "Something went wrong: ${it.localizedMessage}"
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
        dimensionProvider = null
    }
}