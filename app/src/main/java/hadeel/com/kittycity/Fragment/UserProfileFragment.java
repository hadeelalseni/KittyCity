package hadeel.com.kittycity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.R;

public class UserProfileFragment extends Fragment {
    TextView user_profile_name;
    AppCompatButton myKittiesBtn, settingsBtn;
    public UserProfileFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        user_profile_name = (TextView) view.findViewById(R.id.user_name_profile);
        user_profile_name.setText("Hello "+ Common.currentUser.getUsername());

        myKittiesBtn = (AppCompatButton) view.findViewById(R.id.my_kitties_btn);
        myKittiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = null;
                fragment = new MyKittiesFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.framelayout, fragment).commit();
            }
        });

        return view;
    }

}
