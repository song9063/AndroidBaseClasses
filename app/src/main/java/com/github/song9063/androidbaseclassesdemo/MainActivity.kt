package com.github.song9063.androidbaseclassesdemo

import android.os.Bundle
import com.github.song9063.androidbaseclasses.activities.BoltBaseActivity

class MainActivity : BoltBaseActivity() {

    companion object {
        const val PREF_KEY = "PREF_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataManager(PREF_KEY)


    }
}