package com.example.ultima.ui.home

import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.ultima.MyService
import com.example.ultima.R
import java.util.*


class HomeFragment : Fragment() {
    var playbackServiceIntent: Intent? = null
    var calendar = Calendar.getInstance()
    private lateinit var vista: View
    private var buttonStop: Button? = null
    private var buttonPlay: Button? = null
    private var mPlayer: MediaPlayer? = null
    private var texto: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vista = inflater.inflate(R.layout.fragment_home, container, false)
        buttonStop = vista.findViewById(R.id.buttonStop) as Button
        buttonPlay = vista.findViewById(R.id.buttonPlay) as Button
        texto=vista.findViewById(R.id.text_home)
        mPlayer = MediaPlayer()
        buttonPlay!!.tag = 1
        buttonPlay!!.setOnClickListener { startService() }
        buttonStop!!.tag = 1
        buttonStop!!.setOnClickListener { stopService() }
        playbackServiceIntent = Intent(activity, MyService::class.java)
        texto?.setText("no ayy")
        return vista

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.title = "Audiophileradio"
    }

    private fun stopService() {
        activity!!.stopService(Intent(activity, MyService::class.java))
    }

    private fun startService() {
    }
}