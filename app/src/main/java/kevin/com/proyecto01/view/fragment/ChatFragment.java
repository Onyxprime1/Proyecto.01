package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.AdaptadorChats;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.ChatsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;
    String TAG = "ChatFragment";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private CircleImageView fotoPerfil;
    private TextView usuario;


    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment4, container, false);

        gui(view);
        mListaChats = new ArrayList<>();
        //------------------------- DATOS DE USUARIO LOGEADO
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
        //------------------------- CARGAR USUARIOS

        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        reference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mListaChats.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ChatsModel user = data.getValue(ChatsModel.class);

                    assert user != null;
                    assert firebaseUser != null;
                    if (!user.getNombre().equals(firebaseUser.getDisplayName())) {
                        cargarListaChats(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        showToolbar("");


        initRecycler();
        return view;

    }

    public void cargarListaChats(ChatsModel user) {
        mListaChats.add(new ChatsModel(user.getImagenPerfil(),"id", user.getNombre(), user.getMensaje(), "28/03/2018"));
        mAdaptadorChats.notifyDataSetChanged();

    }


    public void gui(View view) {
        fotoPerfil = view.findViewById(R.id.fperfil);
        usuario = view.findViewById(R.id.username);
        mToolbar = view.findViewById(R.id.toolbar_chat);
        mRecyclerView = view.findViewById(R.id.recycler_chats);
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

    public void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorChats = new AdaptadorChats(mListaChats, getContext());
        mRecyclerView.setAdapter(mAdaptadorChats);

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