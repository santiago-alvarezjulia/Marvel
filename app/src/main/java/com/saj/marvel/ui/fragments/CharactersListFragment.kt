package com.saj.marvel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.saj.marvel.databinding.FragmentCharactersListBinding
import com.saj.marvel.ui.adapters.CharactersAdapter
import com.saj.marvel.ui.adapters.CharactersItemDecoration
import com.saj.marvel.viewModels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    @Inject
    lateinit var charactersAdapter: CharactersAdapter

    @Inject
    lateinit var itemDecoration: CharactersItemDecoration

    private val charactersViewModel: CharactersViewModel by viewModels()

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
        charactersViewModel.charactersLiveData.observe(viewLifecycleOwner, {
            charactersAdapter.setData(it)
        })
    }

    private fun setUpCharactersAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.charactersList.layoutManager = layoutManager
        charactersAdapter.setHasStableIds(true)
        binding.charactersList.adapter = charactersAdapter
        binding.charactersList.addItemDecoration(itemDecoration)
        binding.charactersList.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}