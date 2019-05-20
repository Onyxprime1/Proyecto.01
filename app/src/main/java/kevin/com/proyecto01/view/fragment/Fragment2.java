package kevin.com.proyecto01.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.adaptador;
import kevin.com.proyecto01.adaptadores.modelo;
import kevin.com.proyecto01.login.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    RecyclerView recy2;
    kevin.com.proyecto01.adaptadores.adaptador adaptador;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private CircleImageView circle;
    private TextView texto;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment2, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(),0, this )
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
        img  = view.findViewById(R.id.fondo);
        texto = view.findViewById(R.id.userFragmen2);
        setHasOptionsMenu(true);
        showToolbar("",view);
        recy2 = view.findViewById(R.id.recy2);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy2.setLayoutManager(manager);
        adaptador = new adaptador(listade(),R.layout.card_picture,getActivity());
        recy2.setAdapter(adaptador);
        return view;
    }

    public ArrayList<modelo> listade(){
        ArrayList<modelo>listo = new ArrayList<>();
        listo.add(new modelo("http://conrumboaningunaparte.com/wp-content/uploads/2017/07/leon-blanco-y-negro-183361-2.jpg","kevin","infinito","10000"));
        listo.add(new modelo("http://s1.1zoom.me/big0/512/Lions_462377.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://us.123rf.com/450wm/huettenhoelscher/huettenhoelscher1606/huettenhoelscher160600268/60807044-le%C3%B3n-en-blanco-y-negro.jpg?ver=6","kevin","infinito","10000"));
        listo.add(new modelo("https://ae01.alicdn.com/kf/HTB1OSOeNFXXXXbsXFXXq6xXFXXXD/Env-o-Gratuito-de-Una-Pieza-de-Papel-Tapiz-de-Le-n-Real-Gato-Salvaje-Caliente.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://www.dhresource.com/0x0s/f2-albu-g4-M01-E3-B5-rBVaEFoTsMKAL_kXAAD5kUIds5c887.jpg/cartel-de-le-n-blanco-y-negro-decoraci-n.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://desenio.es/bilder/artiklar/zoom/2909_1.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://http2.mlstatic.com/cuadro-moderno-leon-blanco-y-negro-5-piezas-114x185cm-D_NQ_NP_820304-MLM25894387403_082017-F.jpg","kevin","infinito","10000"));

        return listo;
    }


    public void showToolbar(String tittle,View view) {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        Toolbar mytoolbar = view.findViewById(R.id.toolbar3);
        activi.setSupportActionBar(mytoolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        activi.getMenuInflater().inflate(R.menu.item_exit,menu);
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
                    goLogInScreen();
                } else {
                    Toast.makeText(getActivity(), "nooo perro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
    }
}
