package com.bignerdranch.android.finalproject.ui.Skill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.finalproject.databinding.FragmentSkillBinding

class DashboardFragment : Fragment() {

    private lateinit var skillViewModel: SkillViewModel
    private var _binding: FragmentSkillBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        skillViewModel =
            ViewModelProvider(this).get(SkillViewModel::class.java)

        _binding = FragmentSkillBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        skillViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}