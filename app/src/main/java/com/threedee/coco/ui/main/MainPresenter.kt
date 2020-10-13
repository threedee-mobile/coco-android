package com.threedee.coco.ui.main

import com.darkwater.kraken.adapter.AdapterViewModel
import com.darkwater.kraken.adapter.common.text.TextViewModel
import com.darkwater.kraken.adapter.common.text.TextViewStyle
import com.darkwater.kraken.extensions.letAll
import com.darkwater.kraken.injection.scopes.ActivityScope
import com.darkwater.kraken.providers.DimensionProvider
import com.darkwater.kraken.style.BaseStyle
import com.threedee.coco.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
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
                .doOnSubscribe {
                    view.showLoading()
                }
                .subscribe({ data ->
                    view.stopLoading()
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
                            text = "CO2 Emissions Data from the ODIAC data set near Toronto, Canada:",
                            style = TextViewStyle(
                                layoutBaseStyle = baseStyle,
                                textAppearance = R.style.BaseTextAppearance_Title
                            )
                        )
                    )

                    data.forEach { d ->
                        viewModels.add(
                            TextViewModel(
                                text = d.month.toUpperCase(Locale.ROOT),
                                style = TextViewStyle(
                                    layoutBaseStyle = baseStyle,
                                    textAppearance = R.style.BaseTextAppearance_Month
                                )
                            )
                        )
                        viewModels.addAll(d.cells.map { cell ->
                            TextViewModel(
                                id = cell.id.hashCode() and 0xfffffff,
                                text = "LAT: ${cell.lat}\nLON: ${cell.lon}\nFF_CO2: ${cell.ff_co2}\nIB_CO2: ${cell.ib_co2}",
                                style = TextViewStyle(
                                    layoutBaseStyle = baseStyle,
                                    textAppearance = R.style.BaseTextAppearance_Cell
                                )
                            )
                        })
                    }

                    view.showView(viewModels)
                }, {
                    view.stopLoading()
                    view.showView(
                        listOf(
                            TextViewModel(
                                text = "Something went wrong, please try again."
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