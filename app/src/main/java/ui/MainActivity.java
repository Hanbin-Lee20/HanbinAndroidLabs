package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Clock;

import Algonquin.cst2355.lee.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    private MainViewModel model;
    private MainViewModel checkedChangeListener;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());


            model.editString.observe(this, s ->  {
                variableBinding.textview.setText("Your edit text has: " + s);
            });

        });

        model.isSelected.observe(this, selected -> {
            variableBinding.checkbox.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            variableBinding.radio.setChecked(selected);


            CharSequence text = "The value is now:" + selected;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this , text, duration);
            toast.show();
        });

        variableBinding.checkbox.setOnCheckedChangeListener( (checkbox, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );

        variableBinding.switch1.setOnCheckedChangeListener( (switch1, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );

        variableBinding.radio.setOnCheckedChangeListener( (radio, isChecked) -> {
            model.isSelected.postValue(isChecked);
        } );


        variableBinding.myimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "You just clicked image!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        variableBinding.myimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int width = view.getWidth();
                int height = view.getHeight();

                Context context = getApplicationContext();
                CharSequence text = "The width = " + width + " and height = " + height;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });


    }


    };




















