package universal.appfactory.aeroindia2023.exhibitors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import universal.appfactory.aeroindia2023.R

class HallStallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall_stall)

        val hall = intent.getStringExtra("Hall")
        val webView = findViewById<WebView>(R.id.webView)

        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)

        val url = when (hall) {
            "Hall A" -> "https://aeroindia.gov.in/booked-category/hall-A"
            "Hall B" -> "https://aeroindia.gov.in/booked-category/hall-B"
            "Hall C" -> "https://aeroindia.gov.in/booked-category/hall-C"
            "Hall D" -> "https://aeroindia.gov.in/booked-category/hall-D"
            "Hall E" -> "https://aeroindia.gov.in/booked-category/hall-E"
            "Hall F" -> "https://aeroindia.gov.in/booked-category/hall-F"
            else -> "https://aeroindia.gov.in/booked-category/hall-G"
        }
        webView.loadUrl(url)

    }
}