package com.example.appdaniela.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdaniela.MainActivity
import com.example.appdaniela.R
import com.example.appdaniela.adapters.CommentAdapter
import com.example.appdaniela.adapters.PagingLoadStateAdapter
import com.example.appdaniela.databinding.FragmentDetailBinding
import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.models.DetailsUI
import com.example.appdaniela.models.PostDto
import com.example.appdaniela.viewModels.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val adapter =  CommentAdapter()

    private val pagingDataObserver = Observer<PagingData<CommentDto>> { handlePagingData(it) }
    private val uiModelObserver = Observer<DetailsUI> { handleUi(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserver()
        detailsViewModel.selectedValue.value = args.gitItemArg
        detailsViewModel.setPagingCommentData(args.gitItemArg)
        detailsViewModel.getUserFromUserId(args.gitItemArg.userId)

        binding.recyclerComment.layoutManager = LinearLayoutManager(requireContext())
        with(adapter){
            binding.recyclerComment.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
        }

        binding.favouriteBoton.setOnClickListener {
            detailsViewModel.setFavouriteValueForPost()
        }

        binding.erasePost.setOnClickListener {
            detailsViewModel.deletePostFromPostList(args.gitItemArg.id)
        }
    }

    private fun handlePagingData(pagingData: PagingData<CommentDto>){
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitData(pagingData)
        }
    }

    private fun startObserver() {
        detailsViewModel.pagingData.observe(viewLifecycleOwner, pagingDataObserver)
        detailsViewModel.uiModel.observe(viewLifecycleOwner, uiModelObserver)
    }

    private fun handleUi(detailsUI: DetailsUI){
        if(detailsUI.goBack)findNavController().popBackStack()
    }

}