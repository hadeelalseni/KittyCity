package hadeel.com.kittycity.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.Model.Kitty;
import hadeel.com.kittycity.Model.User;
import hadeel.com.kittycity.R;
import hadeel.com.kittycity.UI.SignupActivity;
import hadeel.com.mathquestionslib.GetCoin;

public class GameFragment extends Fragment {
    TextView question;
    Button choice1, choice2, choice3;
    public GameFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        final View view = inflater.inflate(R.layout.fragment_game, container, false);

        question = (TextView) view.findViewById(R.id.question_tv);
        choice1 = (Button) view.findViewById(R.id.choice1);
        choice2 = (Button) view.findViewById(R.id.choice2);
        choice3 = (Button) view.findViewById(R.id.choice3);


        final GetCoin getCoin = new GetCoin();
        int n1 = getCoin.getNumberOne();
        int n2 = getCoin.getNumberTwo();
        int correctAnswer = n1+n2;
        question.setText(n1+"  +  "+n2);
        choice1.setText(correctAnswer+3+"");
        choice2.setText(correctAnswer-1+"");
        choice3.setText(correctAnswer+"");

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_LONG).show();
            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_LONG).show();
            }
        });

        final int random = 1 + (int) (Math.random() * ((9 - 1)+1));
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.choosedKitty != 0){
                    System.out.println("Chooooooooooooooooooosed kittty : "+ Common.choosedKitty);
                }else{
                    //Min + (int)(Math.random() * ((Max - Min) + 1))
                    User user = Common.currentUser;
                    ArrayList<Integer> kittiesId = user.getKitties();
                    kittiesId.add(random);
                    user.setKitties(kittiesId);
                    table_user.child(user.getEmail()).removeValue();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
