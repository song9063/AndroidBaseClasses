package com.github.song9063.androidbaseclasses.http

import android.content.Context
import android.graphics.Bitmap
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class BoltHttp(context: Context) {
    private val mContext = context

    // onResp(urlString, responseObject, Success, CustomTag)
    // onError(urlString, errorString, CustomTag)
    fun httpPost(strUrl: String, params: JSONObject,
                 onResp: (String, JSONObject, Boolean, Int) -> (Boolean),
                 onError: (String, String, Int) -> (Boolean),
                 extHeaders: HashMap<String, String>,
                 nCustomTag: Int = 0){

        val jsonObjReq = object : JsonObjectRequest(
            Request.Method.POST, strUrl, params,
            Response.Listener<JSONObject> { resp ->
                onResp(strUrl, resp, resp != null, nCustomTag)
            },
            Response.ErrorListener { error ->
                onError(strUrl, error.toString(), nCustomTag)
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                return extHeaders
                /*val headers = HashMap<String, String>()
                headers.put("X-API-KEY",DLConst.HTTP_API_KEY)
                return headers*/
            }
        }
        BoltVolleyQueue.getInstance(mContext).addToRequestQueue(jsonObjReq)
    }

    fun httpPut(strUrl: String, params: JSONObject,
                onResp: (String, JSONObject, Boolean, Int) -> (Boolean),
                onError: (String, String, Int) -> (Boolean),
                extHeaders: HashMap<String, String>,
                nCustomTag: Int = 0){

        val jsonObjReq = object : JsonObjectRequest(
                Request.Method.PUT, strUrl, params,
                Response.Listener<JSONObject> { resp ->
                    onResp(strUrl, resp, resp != null, nCustomTag)
                },
                Response.ErrorListener { error ->
                    onError(strUrl, error.toString(), nCustomTag)
                }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                return extHeaders
                /*val headers = HashMap<String, String>()
                headers.put("X-API-KEY",DLConst.HTTP_API_KEY)
                return headers*/
            }
        }
        BoltVolleyQueue.getInstance(mContext).addToRequestQueue(jsonObjReq)
    }

    fun awsS3PutImage(strUrl: String, bitmap: Bitmap,
                      strFileExtension: String = "jpg",
                      onResp: (String, String, Boolean, Int) -> (Boolean),
                      onError: (String, String, Int) -> (Boolean), nCustomTag: Int){
        val req = object : StringRequest(
            Request.Method.PUT, strUrl,
            Response.Listener<String> { resp ->
                onResp(strUrl, resp, resp.isNotEmpty(), nCustomTag)
            },
            Response.ErrorListener { error ->
                onError(strUrl, error.toString(), nCustomTag)
            }
        ){
            override fun getBody(): ByteArray {
                return bitmap.toByteArray()
            }
            override fun getHeaders(): MutableMap<String, String> {
                val s3Headers = HashMap<String,String>()
                s3Headers.put("Content-Type", "image/jpg")
                return s3Headers
            }
        }
        BoltVolleyQueue.getInstance(mContext).addToRequestQueue(req)
    }
    fun Bitmap.toByteArray():ByteArray{
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG,100,this)
            return toByteArray()
        }
    }

}