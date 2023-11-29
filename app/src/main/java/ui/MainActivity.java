package ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        String stringURL = null;

            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(cityName, "UTF-8")
                        + "&appid=c73fc4616c26eeaf96ff734b52a3b6e3&units=metric";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            JSONArray weatherArray = response.getJSONArray("weather");

                            int vis = response.getInt("visibility");
                            
                        }catch() {

                        }
                    })


        });

    }




    };




















