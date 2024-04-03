package com.example.sharedviewmodel_kotlin_jetpackcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.sharedviewmodel_kotlin_jetpackcomponent.databinding.FragmentSecondBinding
import com.example.sharedviewmodel_kotlin_jetpackcomponent.viewmodel.SharedViewModel
import androidx.lifecycle.observe

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        lifecycleScope.launchWhenStarted {
            viewModel.selectedItemId.collect { itemId ->
                // Do something with the selected item id
                binding.itemDetailTextView.text = "Item Detail: $itemId"
                Log.v("@@",""+itemId)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.items.collect { items ->
                Log.v("@@",""+items.toString())
                // Do something with the list of items
                // For example, update UI or refresh RecyclerView
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}