package com.nbc.nbcnews

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return ArticlesFragment()
            1 -> return VideosFragment()
            2 -> return SlideshowsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}