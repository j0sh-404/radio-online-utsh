package com.example.ultima.ui.gallery

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieAnimationView
import com.example.ultima.MyService
import com.example.ultima.R
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers.Main


class GalleryFragment : Fragment() {
    lateinit var error:ImageView
    lateinit var msj_error:TextView
    lateinit var  facebook:WebView
    private var galleryViewModel: GalleryViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView = root.findViewById<TextView>(R.id.text_gallery)

        facebook=root.findViewById(R.id.webFace)
        error=root.findViewById(R.id.no_conection)
        msj_error=root.findViewById(R.id.textView3)
        error.visibility = View.GONE
        msj_error.visibility = View.GONE
        facebook.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        conectFacebook()

        return root
    }

    private val networkStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
                context: Context,
                intent: Intent
        ) {
            val manager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = manager.activeNetworkInfo

        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
    }
    private fun conectFacebook() {
        if( isOnline( this@GalleryFragment.requireActivity())){

            getActivity()?.let { Toasty.info(it, "Conectando a facebook..", 400, false).show() }
            facebook.loadUrl("https://www.facebook.com/radioutshturadio/")
        }else{
            getActivity()?.let { Toasty.error(it, "Verifica tu conexión",800).show() }
        }

    }



}


