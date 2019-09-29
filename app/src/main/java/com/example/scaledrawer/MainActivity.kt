package com.example.scaledrawer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        this.toolbar.setNavigationOnClickListener {
            this.drawer_layout.openDrawer(GravityCompat.START)
        }

        this.drawer_layout.also {
            it.drawerElevation = 0f
            it.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            it.setScrimColor(Color.TRANSPARENT)
            it.addDrawerListener(this.drawerListener)
        }
    }

    // region ActionBarDrawerToggle

    private val drawerListener by lazy {
        object : ActionBarDrawerToggle(this, this.drawer_layout, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                this@MainActivity.content.also {
                    val scaleFactor = 6f
                    it.translationX = drawerView.width * slideOffset
                    it.scaleX = 1 - (slideOffset / scaleFactor)
                    it.scaleY = 1 - (slideOffset / scaleFactor)
                }
            }
        }
    }

    // endregion
}
