package com.recipes.app.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.recipes.R
import com.recipes.app.adapter.RecipeAdapter
import com.recipes.app.home.viewmodel.HomeViewModel
import com.recipes.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin


class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    val viewModel = HomeViewModel(fetchRecipes = getKoin().get())

    private val recipeAdapter by lazy { RecipeAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchAllPosts()
        clickedButtons()
    }


    private fun fetchAllPosts() {
        viewModel.fetchPostsByXML()
        lifecycleScope.launch {
            viewModel.recipes.collect { products ->
                if (products?.recipeModel?.isEmpty() == true) {
                    Toast.makeText(
                        requireContext(), "not found", Toast.LENGTH_SHORT
                    ).show()
                    recipeAdapter.differ.submitList(emptyList())
                } else {
                    recipeAdapter.differ.submitList(products?.recipeModel?.toList())
                    binding.rV.apply {
                        adapter = recipeAdapter
                        layoutManager =
                            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        addItemDecoration(
                            DividerItemDecoration(
                                requireContext(), DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                }
            }
        }
    }

    private fun clickedButtons() {
        binding.fabDetailsUser.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }
}