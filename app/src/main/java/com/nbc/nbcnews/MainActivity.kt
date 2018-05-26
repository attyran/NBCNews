package com.nbc.nbcnews

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

import android.support.design.widget.TabLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val CONNECTON_TIMEOUT_MILLISECONDS = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetNewsAsyncTask().execute("http://msgviewer.nbcnewstools.net:9207/v1/query/curation/news/?size=2")

        setSupportActionBar(toolbar)
        configureTabLayout()
    }

    private fun configureTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText("Articles"))
        tab_layout.addTab(tab_layout.newTab().setText("Videos"))
        tab_layout.addTab(tab_layout.newTab().setText("Slideshows"))

        val adapter = TabPagerAdapter(supportFragmentManager, tab_layout.tabCount);
        pager.adapter = adapter

        pager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    inner class GetNewsAsyncTask : AsyncTask<String, Void, NewsResponse>() {
        override fun onPreExecute() {
            // Before doInBackground
        }

        override fun doInBackground(vararg urls: String): NewsResponse? {
            var urlConnection: HttpURLConnection? = null

            try {
                val url = URL(urls[0])

                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = CONNECTON_TIMEOUT_MILLISECONDS
                urlConnection.readTimeout = CONNECTON_TIMEOUT_MILLISECONDS

                var inString = streamToString(urlConnection.inputStream)
                inString = inString.substring(1, inString.length - 1)
//                println(inString)
                val moshi = Moshi.Builder().build()
                val newsAdapter = moshi.adapter(NewsResponse::class.java)
                val news = newsAdapter.fromJson(inString)
                return news
            } catch (e: Exception) {
                println(e)
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect()
                }
            }
            return null
        }

        override fun onPostExecute(news: NewsResponse?) {
            if (news == null) {
                return
            }
            // handle render
        }
    }

    data class NewsResponse(
            val id: String,
            val type: String,
            val header: String,
            val showMore: Boolean,
            val items: List<Item>
    )

    data class Item(
            val id: String,
            val type: String,
            val url: String,
            val headline: String,
            val published: String,
            val tease: String,
            val summary: String,
            val label: String,
            val breaking: Boolean
    )

    fun streamToString(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
    }
}
