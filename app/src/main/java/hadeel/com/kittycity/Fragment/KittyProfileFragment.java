package hadeel.com.kittycity.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.R;
import hadeel.com.kittycity.Widget.WidgetInfo;

import static android.content.Context.MODE_PRIVATE;

public class KittyProfileFragment extends Fragment {
    String name, image;
    TextView kitty_tv;
    ImageView kitty_iv;
    FloatingActionButton buy;
    Button addWidgetBtn;

    public KittyProfileFragment() {
        // Required empty public constructor
    }

    public static KittyProfileFragment newInstance(String param1, String param2) {
        KittyProfileFragment fragment = new KittyProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        name = b.getString("name");
        image = b.getString("image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitty_profile, container, false);

        kitty_tv = (TextView) view.findViewById(R.id.kitty_name_profile);
        kitty_iv = (ImageView) view.findViewById(R.id.kitty_image_profile);
        kitty_tv.setText(name);
        addWidgetBtn = (Button) view.findViewById(R.id.add_wiget_btn);
        buy = (FloatingActionButton) view.findViewById(R.id.buy_kitty_fab);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.currentUser == null){
                    Toast.makeText(getActivity()," You have to login first to buy.", Toast.LENGTH_LONG);
                }
                if(Common.currentUser!=null){
                    System.out.println("Kitty Profile Fragment: "+ Common.currentUser.getUsername());
                    final Bundle b = new Bundle();
                    b.putParcelable("User", Common.currentUser);
                    Fragment fragment = null;
                    fragment = new GameFragment();
                    fragment.setArguments(b);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.framelayout, fragment).commit();
                }
                /*else{
                    Toast.makeText(getActivity()," You have to login first to buy.", Toast.LENGTH_LONG);
                }*/
                Toast.makeText(getActivity()," You have to login first to buy.", Toast.LENGTH_LONG);



            }
        });
        Picasso.get().load(image).placeholder(R.drawable.ic_cat).into(kitty_iv);
        addWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(WidgetInfo.kittyNameWidget, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(WidgetInfo.kittyNameWidget, name);
                editor.apply();
                Toast.makeText(getActivity(), "Widget Added successfully", Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


}
