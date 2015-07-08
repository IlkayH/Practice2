package com.example.ilkay.practice2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import retrofit.RestAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MainActivity extends ActionBarActivity {
    private static final String API_URL = "http://freemusicarchive.org/api";
    private static final String API_KEY = "-------";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        textView = (TextView) findViewById(R.id.textView);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .build();
        APIMethods methods = restAdapter.create(APIMethods.class);

        Callback callback = new Callback() {
            @Override
            public void success(Object o, Response response) {
                Curator curators = (Curator) o;
                textView.setText(curators.title + "\n\n");
                for (Curator.Dataset dataset : curators.dataset) {
                    textView.setText(textView.getText() + dataset.curator_title +
                            " - " + dataset.curator_tagline + "\n");
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        };

       //
       methods.getCurators(API_KEY, callback);
    }


    /*private class BackgroundTask extends AsyncTask<Void, Void,
            Curator> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .build();
        }

        @Override
        protected Curator doInBackground(Void... params) {
            APIMethods methods = restAdapter.create(APIMethods.class);
            Curator curators = methods.getCurators(API_KEY);

            return curators;
        }

        @Override
        protected void onPostExecute(Curator curators) {
            textView.setText(curators.title + "\n\n");
            for (Curator.Dataset dataset : curators.dataset) {
                textView.setText(textView.getText() + dataset.curator_title +
                        " - " + dataset.curator_tagline + "\n");
            }
        }
    }*/
}
