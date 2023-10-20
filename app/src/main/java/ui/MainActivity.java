package ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Algonquin.cst2355.lee.R;

/**
 * This contains login screen with validate function
 * @author HanbinLee
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

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

    /**
     * Entry point for launching password screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.loginText);
        bt = findViewById(R.id.loginButton);

        bt.setOnClickListener(click -> {
            String password = et.getText().toString();

            boolean isComplex = checkPasswordComplexity(password);

            if(isComplex){
                tv.setText("Your password meets the requirements");
            }else{
                tv.setText("You shall not pass!");
            }
        });



    }

    /**
     * This method validates the password value contains:
     * Uppercase, lowercase, number and special characters.
     * @param pw
     * @return returns true when password string passes every conditions.
     */
    boolean checkPasswordComplexity(String pw) {

        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        int duration = Toast.LENGTH_SHORT;

        for (char c : pw.toCharArray()) {
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            Toast.makeText(this, "Your password is missing an uppercase letter.", duration).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Your password is missing a lowercase letter.", duration).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this, "Your password is missing a number.", duration).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "Your password is missing a special character.", duration).show();
            return false;
        } else {
            return true; // Only gets here if all conditions are met
        }

    }

    /**
     * This method validates whether the password contains special characters or not.
     * @param c
     * @return returns true when the password string contains one of the characters specified below
     */
    boolean isSpecialCharacter(char c) {
        switch (c) {
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

    }



    };




















