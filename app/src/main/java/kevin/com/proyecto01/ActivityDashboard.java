package kevin.com.proyecto01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import kevin.com.proyecto01.view.fragment.ActivityPrueba;
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
                    break;

                case R.id.homeFragment:
                    fragment = new Fragment1();
                    item.setChecked(mState);
                    break;
                case R.id.search:
                    fragment = new Fragment3();
                    item.setChecked(mState);
                    break;


                case R.id.chats:
                    Intent intent = new Intent(getApplicationContext(), ActivityPrueba.class);
                    startActivity(intent);
                    /*fragment = new Fragment4();*/
                    item.setChecked(mState);
                    break;

            }

            if(fragment != null){
                setSupportManager(fragment);
            }


            return false;
        }
    };

    private BottomNavigationView navView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerDash, new Fragment1())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void setSupportManager(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerDash, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    private static final int INTERVALO = 2000;  //2 segundos para salir
    private long tiempoPrimerClick;

    @Override
    public void onBackPressed() {

        if(tiempoPrimerClick+ INTERVALO > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
        }else{
            navView.setSelectedItemId(R.id.homeFragment);
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
        }

        tiempoPrimerClick = System.currentTimeMillis();

    }
}
