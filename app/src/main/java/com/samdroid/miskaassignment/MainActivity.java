package com.samdroid.miskaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_text_view);
        Button goButton = findViewById(R.id.go_button);

        requestQueue = Volley.newRequestQueue(this);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });


    }

    private void getInfo(){
        String url = "https://restcountries.eu/rest/v2/region/asia";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);

                                String name = object.getString("name");
                                String capital = object.getString("capital");
                                String flag = object.getString("flag");
                                String region = object.getString("region");
                                String subregion = object.getString("subregion");
                                String population = object.getString("population");
                                String borders = object.getString("borders");
                                String languages = object.getString("languages");

                                resultTextView.append("NAME: " + name + "\n" + "CAPITAL: " +capital + "\n" + "FLAG: " + flag + "\n" + "REGION: " +region + "\n" + "SUBREGION: " +subregion + "\n" + "POPULATION: " +population + "\n" + "BORDERS: " + borders + "\n" + "LANGUAGES: " + languages +"\n\n\n");
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}