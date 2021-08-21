package com.saj.marvel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saj.marvel.R
import com.saj.marvel.databinding.FragmentCharactersListBinding
import com.saj.marvel.ui.adapters.CharacterDiffItemCallback
import com.saj.marvel.ui.adapters.CharactersPagingAdapter
import com.saj.marvel.ui.adapters.ListItemDecoration
import com.saj.marvel.ui.imageManager.ImageManager
import com.saj.marvel.viewModels.CharacterSharedViewModel
import com.saj.marvel.viewModels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    @Inject
    lateinit var imageManager: ImageManager

    @Inject
    lateinit var itemDecoration: ListItemDecoration

    private val charactersViewModel: CharactersViewModel by activityViewModels()
    private val characterSharedViewModel: CharacterSharedViewModel by activityViewModels()

    private lateinit var charactersPagingAdapter: CharactersPagingAdapter

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCharactersAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            charactersViewModel.charactersFlow.collectLatest { pagingData ->
                charactersPagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setUpCharactersAdapter() {
        charactersPagingAdapter = CharactersPagingAdapter(imageManager, {
            characterSharedViewModel.setCharacter(it)
            navigateToDetailScreen()
        }, CharacterDiffItemCallback())

        val layoutManager = LinearLayoutManager(activity)
        binding.charactersList.layoutManager = layoutManager
        binding.charactersList.adapter = charactersPagingAdapter
        binding.charactersList.addItemDecoration(itemDecoration)
        binding.charactersList.setHasFixedSize(true)
    }

    private fun navigateToDetailScreen() {
        findNavController().navigate(R.id.actionToCharactersDetailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}