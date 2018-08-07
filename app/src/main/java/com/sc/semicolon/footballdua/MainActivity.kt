package com.sc.semicolon.footballdua

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sc.semicolon.footballdua.Fragment.PrevFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.fragment
import com.sc.semicolon.footballdua.Fragment.FavoriteFragment
import com.sc.semicolon.footballdua.Fragment.NextFragment


class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                pindahFragmentPrev()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                pindahFragmentNext()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                pindahFavorite()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pindahFragmentPrev()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    fun pindahFragmentPrev(){
        val transaction = manager.beginTransaction()
        val fragment = PrevFragment()
        transaction.replace(R.id.ly,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun pindahFragmentNext(){
        val transaction = manager.beginTransaction()
        val fragment = NextFragment()
        transaction.replace(R.id.ly,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun pindahFavorite(){
        val transaction = manager.beginTransaction()
        val fragment = FavoriteFragment()
        transaction.replace(R.id.ly,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
