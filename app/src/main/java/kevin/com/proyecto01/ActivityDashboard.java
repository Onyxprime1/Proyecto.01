package kevin.com.proyecto01;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import kevin.com.proyecto01.view.fragment.Fragment1;
import kevin.com.proyecto01.view.fragment.Fragment2;
import kevin.com.proyecto01.view.fragment.Fragment3;
import kevin.com.proyecto01.view.fragment.Fragment4;

public class ActivityDashboard extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            boolean mState = !item.isChecked();

            switch (item.getItemId()) {

                case R.id.profile:
                    fragment = new Fragment2();
                    item.setChecked(mState);
                    Toast.makeText(ActivityDashboard.this, "Perfil", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.home:
                    fragment = new Fragment1();
                    item.setChecked(mState);
                    Toast.makeText(ActivityDashboard.this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.search:
                    fragment = new Fragment3();
                    item.setChecked(mState);
                    Toast.makeText(ActivityDashboard.this, "Búsqueda", Toast.LENGTH_SHORT).show();
                    break;


                case R.id.chats:
                    fragment = new Fragment4();
                    item.setChecked(mState);
                    Toast.makeText(ActivityDashboard.this, "Chats", Toast.LENGTH_SHORT).show();
                    break;

            }

            if(fragment != null ){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerDash, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }




            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerDash, new Fragment1())
                .commit();

        navView.setSelectedItemId(R.id.home);

    }

}
