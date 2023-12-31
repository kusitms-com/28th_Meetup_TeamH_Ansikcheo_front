package com.example.meetup.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meetup.R
import com.example.meetup.adapter.StoreDetailMenuAdapter
import com.example.meetup.adapter.StoreListAdapter
import com.example.meetup.databinding.FragmentStoreDetailMenuBinding
import com.example.meetup.model.store.StoreDetailMenuResponseModel
import com.example.meetup.sharedPreference.MyApplication
import com.example.meetup.viewmodel.FoodMenuDetailViewModel
import com.example.meetup.viewmodel.StoreDetailMenuViewModel
import com.example.meetup.viewmodel.StoreListViewModel
import kotlin.concurrent.fixedRateTimer

class StoreDetailMenuFragment : Fragment() {

    private var _binding: FragmentStoreDetailMenuBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: StoreDetailMenuViewModel
    lateinit var menuViewModel: FoodMenuDetailViewModel

    private lateinit var storeDetailMenuAdapter: StoreDetailMenuAdapter

    lateinit var viewModel2: FoodMenuDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStoreDetailMenuBinding.inflate(inflater,container,false)
        val view = binding.root

        viewModel = ViewModelProvider(requireActivity()).get(StoreDetailMenuViewModel::class.java)
        menuViewModel = ViewModelProvider(requireActivity()).get(FoodMenuDetailViewModel::class.java)
        viewModel2 = ViewModelProvider(requireActivity())[FoodMenuDetailViewModel::class.java]

//        val store_detail_menu_list = ArrayList<StoreDetailMenuResponseModel>()
//
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu1","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu2","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu3","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu4","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu5","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu6","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu7","000000원"))
//        store_detail_menu_list.add(StoreDetailMenuResponseModel("a","menu8","000000원"))


        storeDetailMenuAdapter = StoreDetailMenuAdapter(ArrayList())

        binding.recyclerviewStoreDetailMenu.adapter = storeDetailMenuAdapter
        binding.recyclerviewStoreDetailMenu.layoutManager = GridLayoutManager(context,2)

        viewModel.getStoreMenu(requireContext(),MyApplication.preferences.getString("storeId","").toInt())

        viewModel.storeDetailMenuList.observe(viewLifecycleOwner){
            storeDetailMenuAdapter = StoreDetailMenuAdapter(it.result)
            binding.recyclerviewStoreDetailMenu.adapter = storeDetailMenuAdapter


            storeDetailMenuAdapter.itemClick = object  : StoreDetailMenuAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    MyApplication.foodId = it.result[position].id.toInt()

                    menuViewModel.getFoodMenuInfo(context!!, fragmentManager!!, MyApplication.foodId)
                }
            }
        }
        return view
    }


}