package hadeel.com.kittycity.Connection;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import hadeel.com.kittycity.Model.Kitty;
import hadeel.com.kittycity.Model.User;

public class Connection {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    //This method used for check if used logging in infor are match/correct.
    public boolean checkLogin(String email,
                              String password){
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");


        //....................................................................................................

        return false;
    }


    //This method to insert the user as he register for the first time with no kitties.
    public void insertUser(String username,
                           String email,
                           String password){

        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        DatabaseReference newRef = databaseReference.child("User").push();
        newRef.child("username").setValue(username);
        newRef.child("email").setValue(email);
        newRef.child("password").setValue(password);
    }


    /*

    public void insertUser2(String username,
                           String email,
                           String password,
                           ArrayList<Kitty> kitties){

        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        databaseReference.child("User").child("username").setValue(username);
        databaseReference.child("User").child("email").setValue(email);
        databaseReference.child("User").child("password").setValue(password);
        databaseReference.child("User").child("kitties").child("name").setValue(kitties.get(0).getName());
        databaseReference.child("User").child("kitties").child("id").setValue(kitties.get(0).getId());
        databaseReference.child("User").child("kitties").child("path").setValue(kitties.get(0).getPath());

    }*/

    public void checkIfUserAlreadyExisits(String email){
        //.......................................................................................................
    }

 /*   public void testDatabase(){
        Kitty kitty = new Kitty();
        kitty.setName("Soso");
        kitty.setId("02");
        kitty.setPath("02");
        ArrayList<Kitty> kitties = new ArrayList<>();
        kitties.add(kitty);

        User user = new User();
        user.setUsername("Sara");
        user.setEmail("sara@gmail.com");
        user.setPassword("0000");
        user.setKitties(kitties);

        insertUser2("Sara","sara@gmail.com", "0000", kitties);
    }*/
}
