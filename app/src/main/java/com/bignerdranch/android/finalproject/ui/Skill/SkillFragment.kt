package com.bignerdranch.android.finalproject.ui.Skill

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.finalproject.OwnFood
import com.bignerdranch.android.finalproject.R
import com.bignerdranch.android.finalproject.Skill
import com.bignerdranch.android.finalproject.databinding.FragmentSkillBinding
import java.util.*

private const val TAG = "SkillFragment"
private const val ARG_SKILL_ID = "skill_id"

class SkillFragment : Fragment() {

    private lateinit var skillViewModel: SkillViewModel
    private var _binding: FragmentSkillBinding? = null

    interface Callbacks {
        fun onSkillSelected(SkillId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var skillRecyclerView: RecyclerView
    private var adapter: SkillFragment.SkillAdapter? = SkillAdapter(emptyList())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        skillViewModel =
            ViewModelProvider(this).get(SkillViewModel::class.java)

        _binding = FragmentSkillBinding.inflate(inflater, container, false)


        val view = inflater.inflate(R.layout.fragment_ownrecipe, container, false)
        skillRecyclerView =
            view.findViewById(R.id.ownrecipe_recycler_view) as RecyclerView
        skillRecyclerView.layoutManager = LinearLayoutManager(context) //Creates a vertical LinearLayoutManager
        skillRecyclerView.adapter = adapter //
        val root: View = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //register observer to liveData instance. Do something when liveData changes
        skillViewModel.skillLiveData.observe(
            viewLifecycleOwner,
            Observer { skills ->
                skills?.let {
                    Log.i(TAG, "Got skills ${skills.size}")
                    updateUI(skills)
                }
            })
    }

    private fun updateUI(skills: List<Skill>) {
        adapter = SkillAdapter(skills) //create new adapter
        skillRecyclerView.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_skill_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_skill -> {
                val skill= Skill()
                skillViewModel.addskill(skill)
                callbacks?.onSkillSelected(skill.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private inner class SkillHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var skill: Skill
        //list item's data
        private val dateTextView: TextView = itemView.findViewById(R.id.skill_date)
        private val skillTextView: TextView = itemView.findViewById(R.id.skill_name)

        init {
            itemView.setOnClickListener(this) //make your ViewHolder the OnClickListener for its View
        }
        //bind data to the match view
        fun bind(skill: Skill) {
            this.skill = skill
            dateTextView.text = this.skill.date.toString()
            skillTextView.text = this.skill.skillname
        }

        override fun onClick(v: View) {
            callbacks?.onSkillSelected(skill.id)
        }
    }

    private inner class SkillAdapter(var skills: List<Skill>) : RecyclerView.Adapter<SkillHolder>() {
        //create the view
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : SkillHolder {
            //the individual item
            val view = layoutInflater.inflate(R.layout.list_item_skill, parent, false)
            return SkillHolder(view)
        }
        //bind the data
        override fun onBindViewHolder(holder: SkillHolder, position: Int) {
            val skill = skills[position]
            holder.bind(skill)
        }
        //total number of views
        override fun getItemCount() = skills.size
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance(skillId: UUID): SkillFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SKILL_ID, skillId)
            }
            return SkillFragment().apply {
                arguments = args
            }
        }
    }
}