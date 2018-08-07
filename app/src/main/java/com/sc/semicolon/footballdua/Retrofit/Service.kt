package com.sc.semicolon.footballdua.Retrofit

import com.sc.semicolon.footballdua.Model.Prev
import com.sc.semicolon.footballdua.Model.Response
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

/**
 * Created by cis on 27/07/2018.
 */
interface Service {

    @GET("eventspastleague.php")
    fun getPrev(@Query("id") id: String): Observable<Response>

    @GET("eventsnextleague.php")
    fun getNext(@Query("id") id: String): Observable<Response>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Observable<Response>
}