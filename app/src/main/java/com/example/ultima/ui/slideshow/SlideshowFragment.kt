package com.example.ultima.ui.slideshow

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ultima.R
import es.dmoral.toasty.Toasty

class SlideshowFragment : Fragment() {
    private lateinit var radio:WebView
    private var slideshowViewModel: SlideshowViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,

                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView = root.findViewById<TextView>(R.id.text_slideshow)
        slideshowViewModel!!.text.observe(this, Observer { s -> textView.text = s })

        radio=root.findViewById(R.id.webRRadio)
        val uri = Uri.parse("http://radio.utsh.edu.mx")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if( internetConnectionCheck( this@SlideshowFragment.requireActivity())){
            startActivity(intent)
            radio.visibility = View.VISIBLE
            radio.visibility = View.GONE
        }else{
            radio.visibility = View.GONE
            getActivity()?.let { Toasty.error(it, "Verifica tu conexión",300).show() }
        }
        return root
    }

    fun internetConnectionCheck(CurrentActivity: Activity): Boolean {
        var Connected = false
        val connectivity = CurrentActivity.applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i].state == NetworkInfo.State.CONNECTED) {

                Connected = true
            } else {

            }
        } else {


            Connected = false
        }
        return Connected
    }
}