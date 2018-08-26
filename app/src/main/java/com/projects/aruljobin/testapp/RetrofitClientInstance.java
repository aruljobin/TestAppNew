package com.projects.aruljobin.testapp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aruljobin on 27/07/18.
 */

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://35.164.28.104:8080";
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            final String token="044f910c-f202-482c-9119-72509e6f838a";
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                                          @Override
                                          public okhttp3.Response intercept(Chain chain) throws IOException {
                                              Request original = chain.request();

                                              Request request = original.newBuilder()
                                                      .header("Authorization", "Bearer "+token)
                                                      .method(original.method(), original.body())
                                                      .build();

                                              return chain.proceed(request);
                                          }
                                      });

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }




    //Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).client(httpClient.build()).build();

}
