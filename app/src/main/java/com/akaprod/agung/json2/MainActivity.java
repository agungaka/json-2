package com.akaprod.agung.json2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasil = (TextView) findViewById(R.id.hasil);

        String address = "https://owlbot.info/api/v1/dictionary/owl?format=json";

        new requestData().execute(address);
    }

    private class requestData extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {

            String returnValue = null;
            StringBuilder sb = null;
            URL obj = null;
            try{
                obj = new URL(strings[0]);
                HttpURLConnection con = (HttpsURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null){
                    sb.append(inputLine);
                }
                in.close();
            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            hasil.setText(s);
        }
    }
}
