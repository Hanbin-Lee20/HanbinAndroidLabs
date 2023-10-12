package Algonquin.cst2355.lee;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Algonquin.cst2355.lee.databinding.ActivityMainBinding;
import Algonquin.cst2355.lee.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    String TAG = "SecondActivity";
    @Override //second page in app
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent fromPrevious = getIntent();
        String emailInput = fromPrevious.getStringExtra("LoginEmail");
        binding.welcomeText.setText("Welcome back " + emailInput);

        binding.callBtn.setOnClickListener( click -> {
            String phoneNumber = binding.editTextPhone.getText().toString();
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(call);
        });


            ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {

                        @Override
                        public void onActivityResult(ActivityResult result) {

                            if (result.getResultCode() == Activity.RESULT_OK) {

                                Intent data = result.getData();
                                Bitmap thumbnail = data.getParcelableExtra("data");
//                                binding.profileImage.setImageBitmap(thumbnail);

                                FileOutputStream fOut = null;
                                try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                                    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                    fOut.flush();
                                    fOut.close();
                                }catch (IOException e)

                                { e.printStackTrace();

                                }
                                int i =0;

                            }


                        }
                    });

        binding.changePic.setOnClickListener( click -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);
        });







    }
    public void onPause() {
        super.onPause();
        ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String phoneNumber = prefs.getString("phoneNumber", "");
        prefs.getString("phoneNumber", phoneNumber);
        binding.editTextPhone.setText(phoneNumber);



    }

}