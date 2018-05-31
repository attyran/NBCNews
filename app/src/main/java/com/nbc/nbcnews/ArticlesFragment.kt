package com.nbc.nbcnews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_articles.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ArticlesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val itemOnClick: (View, Int, Int) -> Unit = { view, position, type ->
            // todo
        }

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_articles, container, false)

        view.rv_articles.layoutManager = LinearLayoutManager(view.context)
        NewsManager.instance.articleAdapter = ArticleAdapter(NewsManager.instance.articlesList, view.context, itemOnClick)
        view.rv_articles.adapter = NewsManager.instance.articleAdapter
        return view
    }
}
