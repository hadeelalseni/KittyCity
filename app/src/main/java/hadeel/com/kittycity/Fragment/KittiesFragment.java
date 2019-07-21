package hadeel.com.kittycity.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hadeel.com.kittycity.Adapter.Adapter;
import hadeel.com.kittycity.Model.Kitty;
import hadeel.com.kittycity.R;


public class KittiesFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference kitty_table;
    RecyclerView kitty_rv;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;

    public KittiesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_kitties, container, false);
        new AsyncTask().execute();
        // Inflate the layout for this fragment
        return rootView;
    }
    class AsyncTask extends android.os.AsyncTask<Void, Void, ArrayList<Kitty>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Kitty> doInBackground(Void... voids) {
            database = FirebaseDatabase.getInstance();
            kitty_table = database.getReference("kitty");
            final ArrayList<Kitty> kitties = new ArrayList<>();
            kitty_table.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                    Kitty kitty = data.getValue(Kitty.class);
                    kitties.add(kitty);
                    }
                    callRecyclerViewAndAdapter(kitties);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return kitties;
        }

        private void callRecyclerViewAndAdapter(ArrayList<Kitty> kitties) {
            kitty_rv = (RecyclerView) getView().findViewById(R.id.kitties_rv);
            adapter = new Adapter(getActivity(), kitties);
            kitty_rv.setAdapter(adapter);
            layoutManager = new LinearLayoutManager(getActivity());
            kitty_rv.setLayoutManager(layoutManager);

        }

    }

}
