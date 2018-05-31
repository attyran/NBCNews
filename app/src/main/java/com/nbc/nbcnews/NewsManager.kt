package com.nbc.nbcnews

class NewsManager private constructor() {
    private object Holder { val INSTANCE = NewsManager() }

    var articlesList: ArrayList<MainActivity.Item> = ArrayList()
    var articleAdapter: ArticleAdapter? = null

    companion object {
        val instance: NewsManager by lazy { Holder.INSTANCE }
    }
}