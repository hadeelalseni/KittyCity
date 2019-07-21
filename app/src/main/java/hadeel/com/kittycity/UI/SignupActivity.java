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
import java.util.ArrayList;
import hadeel.com.kittycity.Model.User;
import hadeel.com.kittycity.R;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, password;
    private AppCompatButton signubBtn;
    private TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name      = (EditText) findViewById(R.id.input_name);
        email     = (EditText) findViewById(R.id.input_email);
        password  = (EditText) findViewById(R.id.input_password);
        signubBtn = (AppCompatButton) findViewById(R.id.btn_signup);
        linkLogin = (TextView) findViewById(R.id.link_login);

        final FirebaseDatabase database    = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
                progressDialog.setMessage("Wait please...");
                progressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(email.getText().toString()).exists()){
                            progressDialog.dismiss();
                            Toast.makeText(SignupActivity.this, "user already in database", Toast.LENGTH_LONG).show();
                        }else{
                            ArrayList<Integer> firstKitty = new ArrayList<>();
                            firstKitty.add(01);
                            progressDialog.dismiss();
                            User user = new User();
                            user.setPassword(password.getText().toString());
                            user.setUsername(name.getText().toString());
                            user.setKitties(firstKitty);
                            table_user.child(email.getText().toString()).setValue(user);
                            Toast.makeText(SignupActivity.this, "Sign up OK :)", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SignupActivity.this, "Something wrong happen, try again.", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
