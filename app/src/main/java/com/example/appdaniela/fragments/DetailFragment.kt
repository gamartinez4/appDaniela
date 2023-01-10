package com.example.appdaniela.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdaniela.R
import com.example.appdaniela.adapters.PagingLoadStateAdapter
import com.example.appdaniela.databinding.FragmentDetailBinding
import com.example.appdaniela.databinding.FragmentIntroBinding
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.viewModels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

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
        detailsViewModel.selectedValue.value = args.gitItemArg
        binding.favouriteBoton.setOnClickListener {
            val selectedValue = detailsViewModel.selectedValue.value
            selectedValue!!.favourite = !selectedValue.favourite
            detailsViewModel.selectedValue.value = selectedValue
            detailsViewModel.setRoomModel(selectedValue.favourite,selectedValue.id)
        }
    }

}