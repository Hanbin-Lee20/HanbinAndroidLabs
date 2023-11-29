package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import Algonquin.cst2355.lee.R;
import Algonquin.cst2355.lee.databinding.ActivityMainBinding;

/**
 * This contains login screen with validate function
 * @author HanbinLee
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This holds the text
     */
    private TextView tv = null;

    /**
     * This holds the button
     */
    private Button bt = null;

    /**
     * This holds the edit text
     */
    private EditText et = null;

    protected RequestQueue queue = null;

    /**
     * Entry point for retrieving weather info
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.forecastButton.setOnClickListener( cli -> {
        String cityName = binding.cityTextField.getText().toString();

            String url = null;
            try {
                url = "https://api.openweathermap.org/data/2.5/weather?q="
                                + URLEncoder.encode(cityName, "UTF-8")
                                + "&appid=c73fc4616c26eeaf96ff734b52a3b6e3&units=metric";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {

                    try {
                        JSONObject coord = response.getJSONObject( "coord" );
                        JSONArray weatherArray = response.getJSONArray ( "weather" );
                        JSONObject position0 = weatherArray.getJSONObject(0);

                        int vis = response.getInt("visibility");
                        String name = response.getString( "name" );

                        String description = position0.getString("description");
                        String iconName = position0.getString("icon");

                        JSONObject mainObject = response.getJSONObject( "main" );
                        double current = mainObject.getDouble("temp");
                        double min = mainObject.getDouble("temp_min");
                        double max = mainObject.getDouble("temp_max");
                        int humidity = mainObject.getInt("humidity");


                        ImageRequest imgReq = new ImageRequest("http://openweathermap.org/img/w/01d.png" + iconName + ".png", new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
//                                binding.icon.setImageBitmap(bitmap);
                                Bitmap image = bitmap;

                                FileOutputStream fOut = null;
                                try {
                                    fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                                    image.compress(Bitmap.CompressFormat.PNG,100, MainActivity.this.openFileOutput(
                                            iconName + ".png", Activity.MODE_PRIVATE) );
                                    fOut.flush();
                                    fOut.close();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        }, 1024, 1024, ImageView.ScaleType.CENTER, null,
                                (error ) -> {
                                    int i = 0;

                                });

                        queue.add(imgReq);

                        runOnUiThread( (  )  -> {

                            binding.temp.setText("The current temperature is " + current);
                            binding.temp.setVisibility(View.VISIBLE);

                            binding.minTemp.setText("The min temperature is " + min);
                            binding.minTemp.setVisibility(View.VISIBLE);

                            binding.maxTemp.setText("The max temperature is " + max);
                            binding.maxTemp.setVisibility(View.VISIBLE);

                            binding.humitidy.setText("The humitidy is " + humidity);
                            binding.humitidy.setVisibility(View.VISIBLE);

                            binding.icon.setVisibility(View.VISIBLE);

                            binding.description.setText("The description is " + description);
                            binding.description.setVisibility(View.VISIBLE);

                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },

                error -> {
                    int i = 0;
            });

            queue.add(request);


        });

    }




    };




















