package com.bignerdranch.android.finalproject.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.finalproject.R
import com.bignerdranch.android.finalproject.databinding.FragmentHomeBinding
import com.bignerdranch.android.finalproject.ui.Favorite.FavoriteFragment
import com.bignerdranch.android.finalproject.ui.OwnRecipe.OwnRecipeFragment


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var OwnRecipe: Button
    private lateinit var Favorite: Button

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        OwnRecipe = view.findViewById(R.id.own_recipe)
        Favorite = view.findViewById(R.id.favorite)

/*        OwnRecipe.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = activity
                ?.getSupportFragmentManager()?.beginTransaction()!!
            fragmentTransaction.replace(R.id.ownrecipe_recycler_view, OwnRecipeFragment()  )
            fragmentTransaction.commit()
        }

        Favorite.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = activity
                ?.getSupportFragmentManager()?.beginTransaction()!!
            fragmentTransaction.replace(R.id.favorite_recycler_view, FavoriteFragment() )
            fragmentTransaction.commit()
        }*/

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}