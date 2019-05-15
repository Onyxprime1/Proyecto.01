package kevin.com.proyecto01.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.view.fragment.Fragment1;
import kevin.com.proyecto01.view.fragment.Fragment2;
import kevin.com.proyecto01.view.fragment.Fragment3;

public class Container extends AppCompatActivity {

    BottomNavigationView boton;
    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        boton = findViewById(R.id.bottombar);
        boton.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        Fragment2 fragment2 = new Fragment2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container1, fragment2)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        viewIsAtHome = false;
                        Toast.makeText(Container.this, "estas buscando", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home:
                        Fragment1 fragment1 = new Fragment1();
                         getSupportFragmentManager().beginTransaction().replace(R.id.container1, fragment1)
                                 .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                 .addToBackStack(null).commit();
                        viewIsAtHome = true;
                        //Toast.makeText(Container.this, "estan en casa wey", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        Fragment3 fragment3 = new Fragment3();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container1, fragment3)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        viewIsAtHome = false;
                        break;
                }
                return  true;
            }
        });
        boton.setSelectedItemId(R.id.home);
    }
}
