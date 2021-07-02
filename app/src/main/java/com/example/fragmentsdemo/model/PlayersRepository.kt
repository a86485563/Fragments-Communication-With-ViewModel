package com.example.listview_livedata.model


class PlayersRepository {
    private  lateinit var players : MutableList<String>
    private lateinit var playerDetails: HashMap<String, Player>

    fun getPlayers(): MutableList<String>{
//            if(players.isNullOrEmpty()){
//                players =mutableListOf("Lebron James","AD","KD","James Harden")
//            }
        players = mutableListOf("Lebron James","AD","KD","James Harden")    ;
        return players
    }

    fun getPlayerDetails(name: String?): Player? {
            createPlayerDetailsMap()
        return playerDetails.get(name)
    }

    private fun createPlayerDetailsMap(){
        var player1 = Player("Lebron James", 37, "Laker", 8)
        var player2 = Player("AD", 29, "Laker", 8)
        var player3 = Player("KD", 30, "Nets", 3)
        var player4 = Player("James Harden", 30, "Nets", 3)
        playerDetails = hashMapOf("Lebron James" to player1,"AD" to player2,"KD" to player3,"James Harden" to player4)
    }
}