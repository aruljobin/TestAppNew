package com.projects.aruljobin.testapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aruljobin on 27/07/18.
 */

public interface GetDataService {

    //@GET("/photos")
    @GET("/alertmobile/getallpopularalerts")
    Call<List<ManageItems>> getAll(@Query("userId") String userId,@Query("latitude") String latitude,@Query("longitude") String longitude);
}
