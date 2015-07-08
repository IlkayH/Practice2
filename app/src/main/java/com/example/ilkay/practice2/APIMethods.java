package com.example.ilkay.practice2;

/**
 * Created by ilkay on 08/07/15.
 */

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Callback;


public interface APIMethods {

    @GET("/get/curators.json")
    void getCurators(
            @Query("api_key") String key, Callback<Curator> cb
    );



}
