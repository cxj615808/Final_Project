package com.bignerdranch.android.finalproject.ui.Favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.finalproject.FavoriteFood
import com.bignerdranch.android.finalproject.R
import java.util.*


private const val TAG = "FavoriteFragment"
class FavoriteFragment : Fragment()  {

    interface Callbacks {
        fun onGameSelected(gameId: UUID)
    }
    private var callbacks: Callbacks? = null

    private lateinit var favoriteRecyclerView: RecyclerView
    private var adapter: FavoriteAdapter? = FavoriteAdapter(emptyList())

    private val favoriteViewModel: FavoriteViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
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
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        favoriteRecyclerView =
            view.findViewById(R.id.match_recycler_view) as RecyclerView
        favoriteRecyclerView.layoutManager = LinearLayoutManager(context) //Creates a vertical LinearLayoutManager
        favoriteRecyclerView.adapter = adapter //bind the adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //register observer to liveData instance. Do something when liveData changes
        favoriteViewModel.favoriteLiveData.observe(
            viewLifecycleOwner,
            Observer { favoritefoods ->
                favoritefoods?.let {
                    Log.i(TAG, "Got favorites ${favoritefoods.size}")
                    updateUI(favoritefoods)
                }
            })
    }

    private fun updateUI(favoriteFoods: List<FavoriteFood>) {
        adapter = FavoriteAdapter(favoriteFoods) //create new adapter
        favoriteRecyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class FavoriteHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var favoriteFood: FavoriteFood
        //list item's data
        private val dateTextView: TextView = itemView.findViewById(R.id.food_date1)
        private val foodnameTextView: TextView = itemView.findViewById(R.id.food_name1)

        init {
            itemView.setOnClickListener(this) //make your ViewHolder the OnClickListener for its View
        }
        //bind data to the match view
        fun bind(favoriteFood: FavoriteFood) {
            this.favoriteFood = favoriteFood
            dateTextView.text = this.favoriteFood.date.toString()
            foodnameTextView.text = this.favoriteFood.foodname
        }

        override fun onClick(v: View) {
            callbacks?.onGameSelected(favoriteFood.id)
        }
    }

    private inner class FavoriteAdapter(var favoriteFoods: List<FavoriteFood>) : RecyclerView.Adapter<FavoriteHolder>() {
        //create the view
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : FavoriteHolder {
            //the individual item
            val view = layoutInflater.inflate(R.layout.list_item_favorite, parent, false)
            return FavoriteHolder(view)
        }
        //bind the data
        override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
            val match = favoriteFoods[position]
            holder.bind(match)
        }
        //total number of views
        override fun getItemCount() = favoriteFoods.size
    }
}