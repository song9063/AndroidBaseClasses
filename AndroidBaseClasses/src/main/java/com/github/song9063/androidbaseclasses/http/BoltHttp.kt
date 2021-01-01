package com.github.song9063.androidbaseclasses.http

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

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

}