package com.example.ootsuk;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void ,Void,Void> {
   String data="";
   String singleParsed="";
   String dataParsed="";


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute( aVoid );
        MainActivity.data.setText( this.dataParsed );
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL("https://turingo-api.herokuapp.com/api/v2/questions/?format=json&lang=en&page=1");
           HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputstream =httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader( new InputStreamReader( inputstream ) );
            String line="";
            while(null != line){
              line= bufferedReader.readLine();
              data= data+line;
            }
            JSONArray JA =new JSONArray(data);
            for(int i = 0; i<JA.length(); i++){
                JSONObject JO =(JSONObject) JA.get( i);
                 singleParsed= "ID:"+ JO.get( "id") + "\n"+
                               " Text:"+ JO.get("text");
                dataParsed=dataParsed+singleParsed;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
