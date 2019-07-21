package hadeel.com.kittycity.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hadeel.com.kittycity.Adapter.Adapter;
import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.Model.Kitty;
import hadeel.com.kittycity.R;

public class MyKittiesFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference user_table, kitty_table;
    RecyclerView my_kitty_rv;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<Integer> userKitties;

    public MyKittiesFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_kitties, container, false);

        new AsyncTask().execute();
        // Inflate the layout for this fragment
        return view;
    }

    class AsyncTask extends android.os.AsyncTask<Void, Void, ArrayList<Kitty>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Kitty> doInBackground(Void... voids) {
            userKitties = Common.currentUser.getKitties();

            database = FirebaseDatabase.getInstance();
            kitty_table = database.getReference("kitty");

            final ArrayList<Kitty> kitties = new ArrayList<>();

            //System.out.println("BEFORE FOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");
            for(int i = 0; i< userKitties.size(); i++){
                final int test = userKitties.get(i);
                //System.out.println("Inside FOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR");

                kitty_table.orderByChild("01").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Kitty kitty = dataSnapshot.getValue(Kitty.class);
                        //System.out.println("KIIIITTTTTTTTTIITITIITITIYYYYYYYYYYYYYYYYY: "+kitty.getName());
                        //System.out.println("KEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEY" + dataSnapshot.getKey());
                        if(dataSnapshot.getKey().equals("0"+test)){
                            //System.out.println("THIS WILL BE PRINTED ONCE< YES HADEEL YOU ARE EXCELLANT PROGMMER ^_^ BIG LOVE YOU.");
                            kitties.add(kitty);
                        }
                        callRecyclerViewAndAdapter(kitties);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            return kitties;
        }


        private void callRecyclerViewAndAdapter(ArrayList<Kitty> kitties) {
            my_kitty_rv = (RecyclerView) getView().findViewById(R.id.my_kitties_rv);
            adapter = new Adapter(getActivity(), kitties);
            my_kitty_rv.setAdapter(adapter);
            layoutManager = new LinearLayoutManager(getActivity());
            my_kitty_rv.setLayoutManager(layoutManager);
        }

    }

}
