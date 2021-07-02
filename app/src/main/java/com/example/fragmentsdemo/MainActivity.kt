package com.example.fragmentsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private lateinit var blankFragment : Fragment
    private lateinit var playerListFragment : Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PlayersListFragment>(R.id.fragment_container_playerList)
            add<PlayerDetailsFragment>(R.id.fragment_container_playerDetail)
        }
    }
}