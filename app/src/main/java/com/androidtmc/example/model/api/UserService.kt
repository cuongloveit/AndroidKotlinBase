package com.androidtmc.example.model.api

import com.androidtmc.example.model.entity.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by cuong on 7/2/17.
 */
interface UserService {

    @GET("key/value/{name}/{email}")
    fun createUser(@Path("name") name: String, @Path("email") email: String): Flowable<User>

}