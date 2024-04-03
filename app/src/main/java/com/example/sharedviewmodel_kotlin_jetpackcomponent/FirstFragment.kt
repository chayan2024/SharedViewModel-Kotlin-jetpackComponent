package com.example.sharedviewmodel_kotlin_jetpackcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sharedviewmodel_kotlin_jetpackcomponent.adapter.ItemAdapter
import com.example.sharedviewmodel_kotlin_jetpackcomponent.databinding.FragmentFirstBinding
import com.example.sharedviewmodel_kotlin_jetpackcomponent.model.Item
import com.example.sharedviewmodel_kotlin_jetpackcomponent.viewmodel.SharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstBinding.inflate(inflater, container, false)

        val items = listOf(Item(1, "Item 1"), Item(2, "Item 2"), Item(3, "Item 3"))
        viewModel.updateData(items, null)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ItemAdapter(items) { itemId ->
            viewModel.updateData(items, itemId)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recyclerView.adapter = adapter

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}