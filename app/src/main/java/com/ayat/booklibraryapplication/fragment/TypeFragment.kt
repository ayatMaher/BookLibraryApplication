package com.ayat.booklibraryapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayat.booklibraryapplication.adapter.TypeAdapter
import com.ayat.booklibraryapplication.R
import com.ayat.booklibraryapplication.databinding.FragmentTypeBinding
import com.ayat.booklibraryapplication.model.Item
import com.ayat.booklibraryapplication.model.Type
import com.google.gson.Gson

class TypeFragment : Fragment() {
    lateinit var binding: FragmentTypeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTypeBinding.inflate(inflater, container, false)
        val arr = ArrayList<Item>()
        arr.add(Item(R.drawable.thecast, "The Cast"))
        arr.add(Item(R.drawable.thethief, "The Thief"))
        arr.add(Item(R.drawable.web, "Charlotte is Web"))
        arr.add(Item(R.drawable.itends, "It Ends With Us"))
        arr.add(Item(R.drawable.theland, "The Land Of OZ"))
        val arr1 = ArrayList<Item>()
        arr1.add(Item(R.drawable.sword, "Sword and Scimitar"))
        arr1.add(Item(R.drawable.seeking, "Seeking Allah"))
        arr1.add(Item(R.drawable.secrets, "Secrets Of Divine Love"))
        arr1.add(Item(R.drawable.women, "In the Land of Invisible Women"))
        arr1.add(Item(R.drawable.islamic, "The Islamic Enlightenment"))
        val arr2 = ArrayList<Item>()
        arr2.add(Item(R.drawable.thousand, "A Thousand Splendid Suns"))
        arr2.add(Item(R.drawable.life, "A Life Of Pi"))
        arr2.add(Item(R.drawable.rising, "Esperanza Rising"))
        arr2.add(Item(R.drawable.night, "At Night All Blood Is Black"))
        arr2.add(Item(R.drawable.mistry, "A Fine Balance"))
        val arr3 = ArrayList<Item>()
        arr3.add(Item(R.drawable.ken, "The Pillars of the Earth"))
        arr3.add(Item(R.drawable.army, "An Army at Dawn"))
        arr3.add(Item(R.drawable.kate, "The Rose Code"))
        arr3.add(Item(R.drawable.hilary, "Bring Up the Bodies"))
        arr3.add(Item(R.drawable.lisa, "Eternal"))
        val data = ArrayList<Type>()
        data.add(Type("Novels", arr))
        data.add(Type("Religious", arr1))
        data.add(Type("Cultural", arr2))
        data.add(Type("Historical", arr3))
        val typeAdapter =
            TypeAdapter(requireActivity(), data, object : TypeAdapter.OnClickListener {
                override fun onClick(position: Int) {
                    val categoryFragment = CategoryFragment()
                    val arguments = Bundle()
                    arguments.putString("name", data[position].typeName)
                    categoryFragment.arguments = arguments
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, categoryFragment).addToBackStack(null)
                        .commit()
                }
            })
        binding.rvType.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvType.adapter = typeAdapter
        return binding.root

    }


}