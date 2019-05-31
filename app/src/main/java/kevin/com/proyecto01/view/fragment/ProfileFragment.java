package kevin.com.proyecto01.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.adaptadores.Adaptador;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;
import kevin.com.proyecto01.adaptadores.TabsAdapter;
import kevin.com.proyecto01.login.MainActivity;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.tabsPerfil.Repositorios;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    public static final int GALLERY_INTENT = 1;
    private SharedPreferences preferences;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private CircleImageView mfotoPerfil;
    private TextView mNombrePerfil;
    private ImageView imgFondo;
    private TabsAdapter mTabsAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private StorageReference storageReference;
    private FloatingActionButton mButtonSubirFoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment2, container, false);
        storageReference = FirebaseStorage.getInstance().getReference();
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


        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mfotoPerfil = view.findViewById(R.id.foto);
        imgFondo = view.findViewById(R.id.fondo);
        mNombrePerfil = view.findViewById(R.id.userFragmen2);
        mTabLayout = view.findViewById(R.id.tablayout_perfil);
        mViewPager = view.findViewById(R.id.viewPager_perfil);
        mButtonSubirFoto = view.findViewById(R.id.fab_subir_foto);
        mButtonSubirFoto.setOnClickListener(this);
        insertNestedFragment();
        initComponentes();
        obtenerDatosUsuario();
        showToolbar("", view);

    }

    public void insertNestedFragment() {
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.viewPager_perfil, new Repositorios()).commit();
    }

    public void showToolbar(String tittle, View view) {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        Toolbar mytoolbar = view.findViewById(R.id.toolbar3);
        activi.setSupportActionBar(mytoolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void obtenerDatosUsuario(){
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        reference = Util.getmDatabase().getReference();
        reference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot datauser : dataSnapshot.getChildren()){
                    ModeloLogin login = datauser.getValue(ModeloLogin.class);
                    assert login != null;
                    assert firebaseUser != null;

                    if (login.getCorreo().equals(firebaseUser.getEmail())){
                        mNombrePerfil.setText(login.getNombre());
                        Log.e("dato", login.getNombre());
                       // Glide.with(getContext()).load(firebaseUser.getPhotoUrl()).into(mfotoPerfil);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        mNombrePerfil.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).into(mfotoPerfil);
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

    @Override
    public void onClick(View v) {
        Intent abriGaleria = new Intent(Intent.ACTION_PICK);
        abriGaleria.setType("image/*");
        startActivityForResult(abriGaleria,GALLERY_INTENT);

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK);{
            Uri uri = data.getData();
            StorageReference filePath = storageReference.child("fotosUsuarios").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Se subio la foto exitosamente", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/
}
