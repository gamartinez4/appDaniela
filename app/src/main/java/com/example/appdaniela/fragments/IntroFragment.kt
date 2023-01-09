package com.example.appdaniela.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.appdaniela.R
import com.example.appdaniela.databinding.FragmentIntroBinding
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.viewModels.IntroViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdaniela.MainActivity
import com.example.appdaniela.adapters.GitReposAdapter
import com.example.appdaniela.adapters.PagingLoadStateAdapter
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.models.ToNoDbModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroFragment : Fragment() {

    private val introViewModel:IntroViewModel by viewModel()

    private val adapter = GitReposAdapter()
    private val pagingDataObserver = Observer<PagingData<RoomModel>> { handlePagingData(it) }
    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerGitRepos.layoutManager = LinearLayoutManager(requireContext())
        with(adapter){
            binding.recyclerGitRepos.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            onClickFunction = {
                findNavController().navigate(IntroFragmentDirections.actionIntroToDetail(it.ToNoDbModel()))
            }
        }

        if((activity as MainActivity).fisrtEnter){
            introViewModel.getListRepost()
            (activity as MainActivity).fisrtEnter = false
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }
        startObserver()
    }

    private fun startObserver() {
        introViewModel.pagingData.observe(viewLifecycleOwner, pagingDataObserver)
    }

    private fun handlePagingData(pagingData: PagingData<RoomModel>){
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(pagingData)
        }
    }



}