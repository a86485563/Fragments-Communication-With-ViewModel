package com.example.fragmentsdemo
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.listview_livedata.model.PlayersRepository
import com.example.listview_livedata.viewmodel.PlayerViewModel


class PlayersListFragment : Fragment() {

    private lateinit var lv: ListView
    private val playerRepo  = PlayersRepository()
//    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var  viewModel : PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.palyers, container, false)
        lv = view.findViewById<ListView>(R.id.players_lv)

        return view

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)
        var adapter  = this.context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1, viewModel.getPlayerList()) };
        lv.adapter=adapter

        lv.onItemClickListener =
            AdapterView.OnItemClickListener { adapter: AdapterView<*>?, itemView: View, pos: Int, id: Long ->
                val tv = itemView as TextView
                Toast.makeText(this.context, tv.text.toString(), Toast.LENGTH_SHORT).show()
                if(viewModel.selectedPlayer.value != tv.text.toString()){
                    viewModel.selectPlayer(tv.text.toString())
                }
            }
    }

    //    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//        //create listvie adapter
//        var adapter  = this.context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1, viewModel.getPlayerList()) };
//        lv.adapter=adapter
////
//        lv.onItemClickListener =
//            AdapterView.OnItemClickListener { adapter: AdapterView<*>?, itemView: View, pos: Int, id: Long ->
//                val tv = itemView as TextView
//                Toast.makeText(this.context, tv.text.toString(), Toast.LENGTH_SHORT).show()
//                viewModel.selectPlayer(tv.text.toString())
//            }
//    }
}