package com.bignerdranch.android.finalproject.ui.OwnRecipe

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.finalproject.OwnFood
import com.bignerdranch.android.finalproject.R
import java.util.*


private const val TAG = "OwnRecipeFragment"
private const val ARG_OWN_ID = "Own_id"

class OwnRecipeFragment : Fragment()   {
    interface Callbacks {
        fun onOwnSelected(OwnId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var ownRecyclerView: RecyclerView
    private var adapter: OwnAdapter? = OwnAdapter(emptyList())

    private val ownViewModel: OwnRecipeViewModel by lazy {
        ViewModelProviders.of(this).get(OwnRecipeViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //the recycler view
        val view = inflater.inflate(R.layout.fragment_ownrecipe, container, false)
        ownRecyclerView =
            view.findViewById(R.id.match_recycler_view) as RecyclerView
        ownRecyclerView.layoutManager = LinearLayoutManager(context) //Creates a vertical LinearLayoutManager
        ownRecyclerView.adapter = adapter //bind the adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //register observer to liveData instance. Do something when liveData changes
        ownViewModel.ownLiveData.observe(
            viewLifecycleOwner,
            Observer { ownfoods ->
                ownfoods?.let {
                    Log.i(TAG, "Got favorites ${ownfoods.size}")
                    val ownId: UUID = arguments?.getSerializable(ARG_OWN_ID) as UUID
                    ownViewModel.loadOwn(ownId)
                    updateUI(ownfoods)
                }
            })
    }

    private fun updateUI(ownFoods: List<OwnFood>) {
        adapter = OwnAdapter(ownFoods) //create new adapter
        ownRecyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_ownrecipe_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_ownrecipe -> {
                val own= OwnFood()
                ownViewModel.addown(own)
                callbacks?.onOwnSelected(own.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private inner class OwnHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var ownFood: OwnFood
        //list item's data
        private val dateTextView: TextView = itemView.findViewById(R.id.food_date2)
        private val foodnameTextView: TextView = itemView.findViewById(R.id.food_name2)

        init {
            itemView.setOnClickListener(this) //make your ViewHolder the OnClickListener for its View
        }
        //bind data to the match view
        fun bind(favoriteFood: OwnFood) {
            this.ownFood = favoriteFood
            dateTextView.text = this.ownFood.date.toString()
            foodnameTextView.text = this.ownFood.foodname
        }

        override fun onClick(v: View) {
            callbacks?.onOwnSelected(ownFood.id)
        }
    }

    private inner class OwnAdapter(var ownFoods: List<OwnFood>) : RecyclerView.Adapter<OwnHolder>() {
        //create the view
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : OwnHolder {
            //the individual item
            val view = layoutInflater.inflate(R.layout.list_item_ownrecipe, parent, false)
            return OwnHolder(view)
        }
        //bind the data
        override fun onBindViewHolder(holder: OwnHolder, position: Int) {
            val match = ownFoods[position]
            holder.bind(match)
        }
        //total number of views
        override fun getItemCount() = ownFoods.size
    }

    companion object {
        fun newInstance(favoriteId: UUID): OwnRecipeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_OWN_ID, favoriteId)
            }
            return OwnRecipeFragment().apply {
                arguments = args
            }
        }
    }
}