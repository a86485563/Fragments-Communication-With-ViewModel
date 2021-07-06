package com.example.listview_livedata.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.listview_livedata.model.Player
import com.example.listview_livedata.model.PlayersRepository


class PlayerViewModel : ViewModel() {
    val selectedPlayer = MutableLiveData<String>()

     val repository = PlayersRepository()

//    init {
//        selectedPlayer.value="Lebron James"
//    }

    fun selectPlayer(playerName: String) {
        selectedPlayer.value = playerName
    }


    fun getPlayerList(): MutableList<String> {
        return repository.getPlayers()
    }

    fun getPlayerDetails(): Player? {
        return repository.getPlayerDetails(selectedPlayer.value?:"Lebron James")
    }
}