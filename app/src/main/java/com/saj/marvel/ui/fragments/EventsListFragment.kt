package com.saj.marvel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.saj.marvel.databinding.FragmentEventsListBinding
import com.saj.marvel.ui.adapters.EventsAdapter
import com.saj.marvel.ui.adapters.ListItemDecoration
import com.saj.marvel.viewModels.EventsViewModel
import com.saj.marvel.viewModels.singleEvent.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventsListFragment : Fragment() {

    @Inject
    lateinit var eventsAdapter: EventsAdapter

    @Inject
    lateinit var itemDecoration: ListItemDecoration

    private val eventsViewModel: EventsViewModel by activityViewModels()

    private var _binding: FragmentEventsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentEventsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpEventsAdapter()

        eventsViewModel.listedEventsLiveData.observe(viewLifecycleOwner, {
            eventsAdapter.setData(it)
        })

        eventsViewModel.loadEventsErrorLiveData.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireActivity(), getString(it), Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUpEventsAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.eventsList.layoutManager = layoutManager
        eventsAdapter.setHasStableIds(true)
        binding.eventsList.adapter = eventsAdapter
        binding.eventsList.addItemDecoration(itemDecoration)
        binding.eventsList.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}