package kevin.com.proyecto01.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.Adaptador;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;
import kevin.com.proyecto01.adaptadores.TabsAdapter;
import kevin.com.proyecto01.login.MainActivity;
import kevin.com.proyecto01.tabsPerfil.Repositorios;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private SharedPreferences preferences;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private CircleImageView circle;
    private TextView texto;
    private ImageView img;
    private TabsAdapter mTabsAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment2, container, false);
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), 0, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                } else {
                    goLogInScreen();
                }
            }
        };
        circle = view.findViewById(R.id.foto);
        img = view.findViewById(R.id.fondo);
        texto = view.findViewById(R.id.userFragmen2);
        setHasOptionsMenu(true);
        showToolbar("", view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mTabLayout = view.findViewById(R.id.tablayout_perfil);
        mViewPager = view.findViewById(R.id.viewPager_tabs);
        insertNestedFragment();
        initComponentes();

    }

    public void insertNestedFragment() {
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.viewPager_tabs, new Repositorios()).commit();
    }

    public void showToolbar(String tittle, View view) {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        Toolbar mytoolbar = view.findViewById(R.id.toolbar3);
        activi.setSupportActionBar(mytoolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        activi.getMenuInflater().inflate(R.menu.item_exit, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //return  ;
    }


    //********************************************************************************************************************************
    private void setUserData(FirebaseUser user) {
        texto.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).into(circle);
    }
//********************************************************************************************************************************

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void goLogInScreen() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                logOut();
                //startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    public void logOut() {
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    removeSharedPreferences();
                    goLogInScreen();
                }
            }
        });

        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        goLogInScreen();
                    }
                }
        );
    }

    private void removeSharedPreferences() {
        preferences.edit().clear().apply();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }
    public void initComponentes (){
        mTabsAdapter = new TabsAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTabsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
