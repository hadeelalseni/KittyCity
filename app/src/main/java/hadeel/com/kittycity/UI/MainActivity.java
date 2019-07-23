package hadeel.com.kittycity.UI;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.Fragment.GameFragment;
import hadeel.com.kittycity.Fragment.KittiesFragment;
import hadeel.com.kittycity.Fragment.UserProfileFragment;
import hadeel.com.kittycity.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private KittiesFragment kittiesFragment;
    private static final String TAG = "MainActivity";
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Fragment fragment = null;
        fragment = new KittiesFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.framelayout, fragment).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_account:
                        if(Common.currentUser != null){
                            Fragment fragment = null;
                            fragment = new UserProfileFragment();
                            FragmentManager fm = getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.framelayout, fragment).commit();
                            break;
                        }else{
                            Intent intent2 = new Intent(MainActivity.this, SignupActivity.class);
                            startActivity(intent2);
                            break;
                        }
                }
                return false;
            }
        });

    }
}
