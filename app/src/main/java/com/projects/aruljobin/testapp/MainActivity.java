package com.projects.aruljobin.testapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*import eu.amirs.JSON;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;*/

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ArrayList<ManageItems> mItems=new ArrayList<>();
    private ItemListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Set adapter for the RecyclerView

        // Set LayoutManager for the RecyclerView
        //mLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //OkHttpClass okHttpClass=new OkHttpClass();
        //okHttpClass.execute();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //Call<List<ManageItems>> call = service.getAllPhotos();
        Call<List<ManageItems>> call = service.getAll("623","13.0827","80.2707");
        call.enqueue(new Callback<List<ManageItems>>() {
            @Override
            public void onResponse(Call<List<ManageItems>> call, Response<List<ManageItems>> response) {
                Log.e("Res",response.message()+":"+response.raw().toString());
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ManageItems>> call, Throwable t) {
                Log.e("CHK",t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateDataList(List<ManageItems> body) {
        //Log.e("Val",body.getUserName()+":"+body.getUserCity());
        mAdapter = new ItemListAdapter(this, body); // space
        mRecyclerView.setAdapter(mAdapter);
        Log.e("chk","yes pull");

    }

    /*public class OkHttpClass extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... strings) {

            String url="http://35.164.28.104:8080/alertmobile/getallpopularalerts?userId=623&latitude=13.0827&longitude=80.2707";
            String token="044f910c-f202-482c-9119-72509e6f838a";

            OkHttpClient client = new OkHttpClient();
            client.followSslRedirects();

            Log.e("URL", url);

            final Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer "+token)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);

                        }
                    });

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    Log.e("RES", response.toString());
                    if (!response.isSuccessful()) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);

                            }
                        });

                        throw new IOException("Unexpected code " + response);
                    } else {
                        // do something wih the result
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String res = null;
                                try {
                                    res = response.body().string();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                progressBar.setVisibility(View.GONE);

                                Log.e("Success", res);

                                try {
                                    JSONObject jsonObject = new JSONObject(res);
                                    JSONObject jsonStatus = jsonObject.optJSONObject("status");
                                    Log.e("Val",jsonStatus.toString()+":"+jsonStatus.getString("status")+":"+jsonStatus.getString("msg"));

                                    JSONArray jsonArray = jsonObject.getJSONArray("_entity");

                                    for (int i=0; i<jsonArray.length(); i++){
                                        JSONObject jsonEntity = jsonArray.getJSONObject(i);

                                        JSONObject jsonUser = jsonEntity.getJSONObject("userId");

                                        //Log.e("CHK",jsonUser.toString());
                                        Log.e("Name",jsonUser.getString("firstName")+" "+jsonUser.getString("lastName"));

                                        ManageItems item = new ManageItems();
                                        item.setUserName(jsonUser.getString("firstName")+" "+jsonUser.getString("lastName"));
                                        item.setUserCity(jsonUser.getString("city"));
                                        item.setCommentCount(jsonEntity.getString("commentCount"));
                                        item.setCreatedOn(jsonEntity.getString("created_On"));

                                        mItems.add(item);
                                    }

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                *//*JSON json = new JSON(res);

                                String firstTag = json.key("_entity").index(0).stringValue();
                                String val = json.key("userId").stringValue();
                                Log.e("firstTag",firstTag);
                                Log.e("firstTag",val);
*//*
                                //Picasso.with(MainActivity.this).load().into(image_big);

                            }
                        });

                    }
                }
            });

            return null;
        }
    }*/

}
