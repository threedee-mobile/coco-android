package com.threedee.coco.ui.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darkwater.alfred.adapter.AdapterViewModel
import com.darkwater.alfred.adapter.ViewHolderAdapter
import com.darkwater.alfred.extensions.registerCommonViewHolders
import com.darkwater.alfred.ui.BaseActivity
import com.threedee.coco.R
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    private var presenter: MainContract.Presenter? = null

    private lateinit var recyclerView: RecyclerView
    private var adapter: ViewHolderAdapter = object : ViewHolderAdapter() {
        // No-op
    }.apply {
        registerCommonViewHolders()
    }

    @Inject
    fun inject(
        presenter: MainContract.Presenter?
    ) {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        presenter?.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.cleanUp()
    }

    override fun showView(viewModels: List<AdapterViewModel>) {
        adapter.addViewModels(viewModels)
    }

    override fun clearView() {
        adapter.removeAllViewModels()
    }
}
