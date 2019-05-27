package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.AdaptadorChats;
import kevin.com.proyecto01.modelos.ChatsModel;
import kevin.com.proyecto01.view.fragment.SubFragments.Chats_SubFragment;
import kevin.com.proyecto01.view.fragment.SubFragments.Groups_SubFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    Toolbar mToolbar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private CircleImageView fotoPerfil;
    private TextView usuario;

    private TabLayout tabOp;
    private ViewPager viewPg;


    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment4, container, false);

        gui(view);
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

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addFragment(new Chats_SubFragment(), "Chats");
        viewPagerAdapter.addFragment(new Groups_SubFragment(), "Grupos");
        viewPg.setAdapter(viewPagerAdapter);
        tabOp.setupWithViewPager(viewPg);


        return view;

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

    public void gui(View view) {
        fotoPerfil = view.findViewById(R.id.fperfil);
        usuario = view.findViewById(R.id.username);
        mToolbar = view.findViewById(R.id.toolbar_chat);
        tabOp = view.findViewById(R.id.tab_layout);
        viewPg = view.findViewById(R.id.view_pager);
    }


    public void showToolbar(String tittle) {

        AppCompatActivity activi = (AppCompatActivity) getActivity();
        activi.setSupportActionBar(mToolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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

}