package com.saj.marvel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.saj.marvel.databinding.FragmentCharacterDetailBinding
import com.saj.marvel.models.Character
import com.saj.marvel.ui.imageManager.ImageManager
import com.saj.marvel.viewModels.CharacterSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    @Inject
    lateinit var imageManager: ImageManager

    private val characterSharedViewModel: CharacterSharedViewModel by activityViewModels()

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterSharedViewModel.characterLiveData.observe(viewLifecycleOwner, {
            updateCharacterUI(it)
        })

        binding.closeDetail.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateCharacterUI(character: Character) {
        binding.characterName.text = character.name
        binding.characterDescription.text = character.description
        imageManager.loadImage(this, binding.characterThumbnail, character.thumbnail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}