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
import hadeel.com.kittycity.Common.Common;
import hadeel.com.kittycity.Fragment.GameFragment;
import hadeel.com.kittycity.Fragment.KittiesFragment;
import hadeel.com.kittycity.Fragment.UserProfileFragment;
import hadeel.com.kittycity.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private KittiesFragment kittiesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.container);
        //setupViewPager(viewPager);

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
                        break;
                    case R.id.ic_cat:
                        //Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        //startActivity(intent);
                        Fragment gameFragment = null;
                        gameFragment = new GameFragment();
                        FragmentManager fm_ = getSupportFragmentManager();
                        fm_.beginTransaction().replace(R.id.framelayout, gameFragment).commit();
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
