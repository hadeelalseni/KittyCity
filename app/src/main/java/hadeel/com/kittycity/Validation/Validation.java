package hadeel.com.kittycity.Validation;

import android.util.Patterns;

public class Validation {

    public boolean signUpValidation(String name, String email, String password){

        boolean flag = true;
        if(name.isEmpty() || name.length() < 3){
            //this.name.setError("Error! name is empty or has less than 3 charcter.");
            flag = false;
        }
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //this.email.setError("Error! email is empty or not an email.");
            flag = false;
        }
        if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            //this.email.setError("Error! password is empty and must be between 4 and 10 chars.");
            flag = false;
        }
        return flag;
    }

    public boolean loginValidation(String email, String password){
        boolean flag = true;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //this.email.setError("Error! email is empty or not an email.");
            flag = false;
        }
        if(password.isEmpty() || password.length() < 4 || password.length() > 10){
            //this.email.setError("Error! password is empty and must be between 4 and 10 chars.");
            flag = false;
        }
        return flag;
    }
}

