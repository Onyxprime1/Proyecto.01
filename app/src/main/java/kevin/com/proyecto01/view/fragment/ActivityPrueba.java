package kevin.com.proyecto01.view.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.modelos.ChatsModel;
import kevin.com.proyecto01.view.fragment.SubFragments.Chats_SubFragment;
import kevin.com.proyecto01.view.fragment.SubFragments.Groups_SubFragment;

public class ActivityPrueba extends AppCompatActivity {

    Toolbar mToolbar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private CircleImageView fotoPerfil;
    private TextView usuario;

    private TabLayout tabOp;
    private ViewPager viewPg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        gui();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    setUserData(user);
                }

            }
        };
        showToolbar("");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Chats_SubFragment(), "Chats");
        viewPagerAdapter.addFragment(new Groups_SubFragment(), "Grupos");
        viewPg.setAdapter(viewPagerAdapter);
        tabOp.setupWithViewPager(viewPg);

    }

    public void gui() {
        fotoPerfil = findViewById(R.id.fperfil);
        usuario = findViewById(R.id.username);
        mToolbar = findViewById(R.id.toolbar_chat);
        tabOp = findViewById(R.id.tab_layout);
        viewPg = findViewById(R.id.view_pager);
    }


    public void showToolbar(String tittle) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    private void setUserData(FirebaseUser user) {
        usuario.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).into(fotoPerfil);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titulo;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titulo = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String tit) {
            fragments.add(fragment);
            titulo.add(tit);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titulo.get(position);
        }
    }
}
