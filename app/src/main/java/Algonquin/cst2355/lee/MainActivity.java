package Algonquin.cst2355.lee;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import Algonquin.cst2355.lee.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "In OnCreate() - Loading Widgets");

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener( click -> {


            File mySandbox = getFilesDir();
            Intent newPage = new Intent(MainActivity.this, SecondActivity.class);
            String emailInput = binding.email.getText().toString();
            newPage.putExtra("LoginEmail", emailInput);


            String emailAddress = binding.email.getText().toString();
            SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor myEditor = prefs.edit();

            myEditor.putString("LoginName", emailAddress);
            prefs.getString("Email address", "");
            prefs.getString("LoginName", emailAddress);

            myEditor.apply();


            startActivity(newPage);

        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "The application is now visible on screen.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "The application is now responding to user input");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG,"The application no longer responds to user input");



    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"The application is no longer visible.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"Any memory used by the application is freed.");
    }


}