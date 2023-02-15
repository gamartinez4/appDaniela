package com.example.appdaniela.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appdaniela.R
import com.example.appdaniela.databinding.FragmentDetailBinding
import com.example.appdaniela.models.DetailsUI
import com.example.appdaniela.viewModels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
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


        binding.erasePost.setOnClickListener {
            detailsViewModel.deleteFoodFromFoodList(args.gitItemArg.id)
        }
    }

    private fun startObserver() {
        detailsViewModel.uiModel.observe(viewLifecycleOwner, uiModelObserver)
    }

    private fun handleUi(detailsUI: DetailsUI){
        if(detailsUI.goBack)findNavController().popBackStack()
    }

}