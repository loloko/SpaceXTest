package com.fernando.spacex.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.spacex.R
import com.fernando.spacex.adapter.RocketAdapter
import com.fernando.spacex.databinding.FragmentRocketListBinding
import com.fernando.spacex.extension.createLoadingPopup
import com.fernando.spacex.extension.isNetworkAvailable
import com.fernando.spacex.extension.snackBar
import com.fernando.spacex.helpers.CellClickListener
import com.fernando.spacex.helpers.MarginItemDecoration
import com.fernando.spacex.helpers.ResultResource
import com.fernando.spacex.model.RocketModel
import com.fernando.spacex.viewmodel.RocketListViewModel
import com.fernando.spacex.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RocketListFragment @Inject constructor() : DaggerFragment(), CellClickListener {

    private lateinit var viewModel: RocketListViewModel
    private lateinit var binding: FragmentRocketListBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: RocketAdapter

    private val loadingPopup by lazy {
        requireContext().createLoadingPopup()
    }

    // View initialization logic
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // View binding
        binding = FragmentRocketListBinding.inflate(layoutInflater)

        // ViewModel
        viewModel = ViewModelProvider(this, providerFactory).get(RocketListViewModel::class.java)
        setHasOptionsMenu(true)

        return binding.root
    }

    // Post view initialization logic
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVariables()
        subscribeObservers()

        // Fetch all rockets
        if (requireContext().isNetworkAvailable(binding.recyclerRockets))
            viewModel.getRockets()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_rocket_list, menu)

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        // Listener for searching rocket by name (Searching View)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    // Initialize variables
    private fun initVariables() {

        // Init recycler
        adapter.setListener(this)
        binding.recyclerRockets.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerRockets.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.recycler)))
        binding.recyclerRockets.adapter = adapter

        // When user swipe to refresh the recycler
        binding.swipeRefresh.setOnRefreshListener {
            if (requireContext().isNetworkAvailable(binding.recyclerRockets)) {
                viewModel.getRockets()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun subscribeObservers() {
        viewModel.rocketResultObserver().observe(viewLifecycleOwner) { data ->
            when (data) {
                is ResultResource.Loading -> {
                    loadingPopup.show()
                }
                is ResultResource.Success -> {
                    loadingPopup.dismiss()

                    // Update adapter
                    data.data?.let { adapter.setRockets(it) }
                }
                is ResultResource.NotFound -> {
                    loadingPopup.dismiss()
                    binding.recyclerRockets.snackBar(R.string.not_found_rockets, true)
                }
                is ResultResource.Error -> {
                    loadingPopup.dismiss()
                    binding.recyclerRockets.snackBar(data.msg, isWarning = true)
                }
            }
        }
    }

    override fun onCellClickListener(rocket: RocketModel) {
        val directions = RocketListFragmentDirections.navigateToRocketDetail(rocket)
        findNavController().navigate(directions)
    }
}