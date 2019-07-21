package hadeel.com.kittycity.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.Model.User;
import hadeel.com.kittycity.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private AppCompatButton loginBtn;
    private TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email      = (EditText) findViewById(R.id.input_email);
        password   = (EditText) findViewById(R.id.input_password);
        loginBtn   = (AppCompatButton) findViewById(R.id.btn_login);
        signupLink = (TextView) findViewById(R.id.link_signup);

        final FirebaseDatabase database    = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Wait please...");
                progressDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(email.getText().toString()).exists()){
                            progressDialog.dismiss();

                            User user = dataSnapshot.child(email.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(password.getText().toString())){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Common.currentUser = user;
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Wrong password.", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User not in database :(", Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "Something wrong happen, try again.", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
