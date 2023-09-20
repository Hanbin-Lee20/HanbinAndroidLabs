package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import Algonquin.cst2355.lee.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

//        variableBinding.textview.setText(model.editString);

        variableBinding.mybutton.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());


            model.editString.observe(this, s ->  {
                variableBinding.textview.setText("Your edit text has: " + s);
            });

        });


        //Inside the method onCreate
//        TextView mytext = variableBinding.textview;
//        Button btn = variableBinding.mybutton;
//        EditText myedit = variableBinding.myedittext;
//
//        String editString = myedit.getText().toString();
//        btn.setOnClickListener(   (View v) ->  mytext.setText("Your edit text has: " + editString)    );








    }



}

















