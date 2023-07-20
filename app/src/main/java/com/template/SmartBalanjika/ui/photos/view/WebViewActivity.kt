package com.template.SmartBalanjika.ui.photos.view


import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.DownloadListener
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.template.SmartBalanjika.R
import com.template.SmartBalanjika.databinding.ActivityWebViewBinding
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val profileName=intent.getStringExtra("Username")
        val pdfname=intent.getStringExtra("pdfname")
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.getSettings().setUseWideViewPort(true)
        webView.getSettings().setBuiltInZoomControls(true)

        webView.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val request = DownloadManager.Request(
                Uri.parse(url)
            )
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) //Notify client once download is completed!
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                pdfname.toString()+".pdf"
            )
            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            Toast.makeText(
                applicationContext,
                applicationContext?.getResources()?.getString(R.string.Downloading_File),  //To notify the Client that the file is being downloaded
                Toast.LENGTH_LONG
            ).show()
        })
        if (profileName != null) {
            webView.loadUrl(profileName)
        }
    }
}