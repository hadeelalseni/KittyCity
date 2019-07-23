package hadeel.com.kittycity.Validation;

import android.util.Patterns;
import android.widget.Toast;

import hadeel.com.kittycity.UI.SignupActivity;

public class Validation {

    public boolean signUpValidation(String name, String email, String password){

        boolean flag = true;
        if(name.isEmpty() || name.length() < 3){
            //Toast.makeText(SignupActivity.this, "Name is empty or has less than 3 char.", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if(email.isEmpty() || !Patterns.PHONE.matcher(email).matches()){
            flag = false;
        }
        if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            flag = false;
        }
        return flag;
    }

    public boolean loginValidation(String email, String password){
        boolean flag = true;
        if(email.isEmpty() || !Patterns.PHONE.matcher(email).matches()){
            flag = false;
        }
        if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            flag = false;
        }
        return flag;
    }
}

