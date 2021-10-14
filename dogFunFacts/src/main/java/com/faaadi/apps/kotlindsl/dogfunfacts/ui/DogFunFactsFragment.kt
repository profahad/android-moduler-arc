package com.faaadi.apps.kotlindsl.dogfunfacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faaadi.apps.kotlindsl.dogfunfacts.R
import com.faaadi.apps.kotlindsl.dogfunfacts.models.DogFactsResModel
import com.faaadi.apps.networkcore.handlers.ResourceState
import com.faaadi.apps.networkcore.utils.CoreUtility
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dog_fun_facts.*


@AndroidEntryPoint
class DogFunFactsFragment : Fragment() {

    private val viewModel by viewModels<DogFactsViewModel>()
    private lateinit var dogFunFactsAdapter: DogFunFactsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dog_fun_facts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDogFunFacts()
        setUpObservers()
    }

    private fun getDogFunFacts() {
        viewModel.getDogFunFacts("10")
    }

    private fun setUpObservers() {


        viewModel.dogFunFactsResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResourceState.Success -> {
                    CoreUtility.printLog(TAG, "Inside_dogFunFactsResponse_success ${Gson().toJson(it)}")
                    viewModel.loadingVisibility.value = View.GONE
                    it.data?.also { facts->
                        setUpDogFactsRecyclerView(facts)
                    }
                }
                is ResourceState.Error -> {
                    viewModel.loadingVisibility.value = View.GONE
                    CoreUtility.printLog(TAG, "Inside_dogFunFactsResponse_error ${it.message}")
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
                is ResourceState.Loading -> {
                    viewModel.loadingVisibility.value = View.VISIBLE
                }
            }
        })


        viewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
            if (it == View.VISIBLE)
                dogLoaderView.visibility = View.VISIBLE
            else
                dogLoaderView.visibility = View.GONE
        })


    }

    private fun setUpDogFactsRecyclerView(facts: ArrayList<DogFactsResModel>) {
        dogLoaderView.visibility = View.GONE
        rvDogFunFacts.apply {
            visibility = View.VISIBLE
            adapter = DogFunFactsAdapter(facts)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    companion object{
        private const val TAG = "DogFunFactsFragment"
    }
}