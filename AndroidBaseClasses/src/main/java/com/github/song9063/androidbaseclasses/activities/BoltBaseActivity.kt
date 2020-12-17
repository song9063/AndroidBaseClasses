package com.github.song9063.androidbaseclasses.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.song9063.androidbaseclasses.utils.BoltDataManager

open class BoltBaseActivity : AppCompatActivity(){
    var boltDataManager: BoltDataManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    fun initDataManager(prefName: String){
        boltDataManager = BoltDataManager(this, prefName)
    }

    fun openURL(strUrl: String) {
        val uri = Uri.parse(strUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intent.putExtras(b)
        startActivity(intent)
    }

    fun openGoogleMapBrowser(strLat: String, strLng: String, strZoomLevel: String){
        val strUrl = "https://www.google.com/maps/@${strLat},${strLng},${strZoomLevel}z"
        openURL(strUrl)
    }

    fun openGoogleMapRouteBrowser(strStartLat: String, strStartLng: String,
                                  strEndLat: String, strEndLng: String){
        var strUrl = "https://www.google.com/maps/dir/?api=1&origin=${strStartLat},${strStartLng}"
        strUrl += "&destination=${strEndLat},${strEndLng}&travelmode=driving"
        openURL(strUrl)
    }

    fun phoneCall(strPhoneNumber: String){
        openURL("tel:${strPhoneNumber}")
    }
}