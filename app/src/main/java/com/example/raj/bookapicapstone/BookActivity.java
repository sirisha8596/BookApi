package com.example.raj.bookapicapstone;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BookActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    String str="https://www.googleapis.com/books/v1/volumes?q=";
    String bnamme;
    public  static final String bkey="bookname";
    List<BookModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        recyclerView=findViewById(R.id.recycler);
        progressBar=findViewById(R.id.progress);
        Intent intent=getIntent();
        bnamme=intent.getStringExtra(bkey);
        list=new ArrayList<>();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
           list.clear();
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        } else {
           list.clear();
           recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recyclerView.setAdapter(new BookAdapter(this,list));
        new  BookTask().execute();
    }
    public  class  BookTask extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(str+bnamme);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()){
                    return scanner.next();
                }
                else {
                    return  null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            try {
                    JSONObject jsonObject=new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("items");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("volumeInfo");
                            String title = jsonObject2.getString("title");
                            JSONObject jsonObject3 = jsonObject2.getJSONObject("imageLinks");
                            String image = jsonObject3.getString("thumbnail");
                            list.add(new BookModel(title, image));
                        }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
