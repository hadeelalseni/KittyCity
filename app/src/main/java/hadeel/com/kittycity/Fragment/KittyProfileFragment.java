package hadeel.com.kittycity.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.squareup.picasso.Picasso;

import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.R;

public class KittyProfileFragment extends Fragment {
    String name, image;
    TextView kitty_tv;
    ImageView kitty_iv;
    FloatingActionButton buy;

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
        buy = (FloatingActionButton) view.findViewById(R.id.buy_kitty_fab);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.choosedKitty = 03;
                Fragment fragment = null;
                fragment = new GameFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.framelayout, fragment).commit();

            }
        });
        Picasso.get().load(image).placeholder(R.drawable.ic_cat).into(kitty_iv);

        // Inflate the layout for this fragment
        return view;
    }


}
