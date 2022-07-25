package com.example.ultima

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.IBinder
import android.preference.PreferenceManager
import java.io.IOException

class MyService : Service(), OnCompletionListener {

    var mediaPlayer: MediaPlayer? = null
    private var STREAM_URL = "http://200.79.178.212:8090/test1.mp3"
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val radio = sharedPreferences.getString("station", "100")
        if (radio != null && radio == "100") STREAM_URL = "http://200.79.178.212:8090/test1.mp3"
        if (radio != null && radio == "200") STREAM_URL = "http://200.79.178.212:8090/test1.mp3"
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer!!.setDataSource(STREAM_URL)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer!!.setOnCompletionListener(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (!mediaPlayer!!.isPlaying) {
            try {
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(STREAM_URL)
                mediaPlayer!!.prepareAsync()
                mediaPlayer!!.setOnPreparedListener { mediaPlayer!!.start() }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
        }
        mediaPlayer!!.release()
    }

    override fun onCompletion(_mediaPlayer: MediaPlayer) {
        // terminar el evento  stopSelf()
        if ( mediaPlayer!!.isPlaying){
            mediaPlayer!!.setOnPreparedListener{mediaPlayer!!.start()}
        }else{
            stopSelf()
        }
    }

    override fun onUnbind(intent: Intent): Boolean {
        return super.onUnbind(intent)
    }
}