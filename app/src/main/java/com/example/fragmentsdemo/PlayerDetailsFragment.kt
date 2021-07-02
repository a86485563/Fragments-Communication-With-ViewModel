package com.example.fragmentsdemo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.listview_livedata.model.Player
import com.example.listview_livedata.viewmodel.PlayerViewModel


class PlayerDetailsFragment : Fragment() {

    private lateinit var name: TextView
    private lateinit var team: TextView
    private lateinit var age: TextView
    private lateinit var rank: TextView
//    private val viewModel: PlayerViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //處裡資料
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.player_details, container, false)

        val viewModel = ViewModelProvider(requireActivity()).get(PlayerViewModel::class.java)
        viewModel.selectedPlayer.observe(viewLifecycleOwner,
            { item: String? -> displayDetails(viewModel.getPlayerDetails(item)!!) })

        name = view.findViewById(R.id.name);
        age = view.findViewById(R.id.age);
        team = view.findViewById(R.id.team);
        rank = view.findViewById(R.id.rank);
        return view
    }

    fun displayDetails(player: Player) {
        name.text = player.name
        age.text = "" + player.age
        team.setText(player.team)
        rank.text = "" + player.rank
    }


}