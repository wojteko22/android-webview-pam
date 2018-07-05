package com.rusoko.pam

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        setButtons()

        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(this), "android")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript: (function() { document.getElementById('video').play(); document.getElementById('video').addEventListener('click', function() {android.showToast(\"Kliknięto!\");}); })();")
            }
        }

        webView.loadUrl("file:///android_asset/movie/video.html")
    }

    private fun setButtons() {
        button1.setOnClickListener {
            webView.loadUrl("file:///android_asset/shrek/index.html")
        }

        button2.setOnClickListener {
            webView.loadUrl("file:///android_asset/animation/index.html")
        }

        button3.setOnClickListener {
            webView.loadUrl("file:///android_asset/movie/video.html")
        }

        buttonOn.setOnClickListener {
            webView.post {
                webView.loadUrl("javascript: (function() { document.getElementById('video').play(); })();")
            }
        }

        buttonOff.setOnClickListener {
            webView.post {
                webView.loadUrl("javascript: (function() { document.getElementById('video').pause(); })();")
            }
        }
    }

    inner class WebAppInterface(private var mContext: Context) {

        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }
}
