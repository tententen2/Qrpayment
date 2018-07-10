package com.gg.qrpayment.main.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gg.qrpayment.R
import com.gg.qrpayment.base.BaseFragment
import com.gg.qrpayment.one.view.OneFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment :BaseFragment() {
    var tabIcon = arrayListOf(
        R.drawable.money,
        R.drawable.transaction
    )

    override val layoutResourceId: Int = R.layout.fragment_main

    override fun setUp() {
        setupViewPager()
        setUpView()
        setUpTabIcon()
    }

    private fun setUpTabIcon() {
        tab_main.getTabAt(0)?.setIcon(tabIcon[0])
        tab_main.getTabAt(1)?.setIcon(tabIcon[1])
    }

    private fun setupViewPager() {
        var adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(OneFragment(),"ONE")
        adapter.addFrag(OneFragment(),"TWO")

        pager_main.adapter = adapter

    }

    private fun setUpView() {
        tab_main.setupWithViewPager(pager_main)
    }

}

class ViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    private var mFragmentList = arrayListOf<Fragment>()
    private var mFragmentTitleList = arrayListOf<String>()

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    fun addFrag(fragment: Fragment,title:String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}