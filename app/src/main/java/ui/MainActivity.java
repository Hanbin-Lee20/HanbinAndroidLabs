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

import org.w3c.dom.Text;

import java.time.Clock;

import Algonquin.cst2355.lee.R;
import Algonquin.cst2355.lee.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {
//    private MainViewModel model;
//    private MainViewModel checkedChangeListener;
//    private ActivityMainBinding variableBinding;

    /**
     * This holds the text at the center of the screen
     */
    private TextView tv = null;

    /**
     * This holds the button to proceed login
     */
    private Button bt = null;

    /**
     * This holds the edit text for accepting password
     */
    private EditText et = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.loginText);
        bt = findViewById(R.id.loginButton);

        bt.setOnClickListener( click -> {
            String password = et.getText().toString();

            boolean isComplex = checkPasswordComplexity(password);
        });

        };

    /**
     * This function checks whether password is complex enough or not.
     * @param pw The String object that we are checking
     * @return Returns true if the password is complex.
     */
    boolean checkPasswordComplexity(String pw){

        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        int duration = Toast.LENGTH_SHORT;

        for(int i = 0; i < pw.length(); i++){
            char c = pw.charAt(i);

            if(!Character.isUpperCase(c)) {

                Toast.makeText(this, "Your password does not have an upper case letter", duration);// Say that they are missing an upper case letter;
                return false;
            }

            else if(!Character.isLowerCase(c)) {
                Toast.makeText(this, "Your password does not have have a lower case", duration); // Say that they are missing a lower case letter;
                return false;
            }

            else if( !Character.isDigit(c)) {
                Toast.makeText(this, "Your password does not have have a number", duration); // Say that they are missing a lower case letter;
                return false;
            }

            else if(!Character.isSpecial) {}

        }


                return true; //only get here if they're all true
        }




    }

    /**
     *
     * @param c
     * @return
     */
    boolean isSpecialCharacter(char c){
        switch(c){
        case '#':
        case '?':
        case '*':
        case '$':
        case '%':
        case '&':
        case '@':
        case '!':
            return true;
        default:
            return false;
    }



    /**
     * javadoc
     * @param text
     * @return
     */
//        private boolean isComplexEnough(String text) {
//
//            boolean result = false;
//            boolean foundUpperCase = false, foundLowerCase = false;
//            for (int i = 0; i < text.length(); i++) {
//                char c = text.charAt(i);
//                if (Character.isUpperCase(c)) {
//                    foundUpperCase = true;
//                } else if (Character.isLowerCase(c))
//                    foundLowerCase = true;
//
//            }
//            return false;
//
//        }

    };
























