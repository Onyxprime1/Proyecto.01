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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.AdaptadorChats;
import kevin.com.proyecto01.modelos.ChatsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;
    Toolbar mToolbar;

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
        showToolbar("", view);
        mListaChats = new ArrayList<>();
        cargarListaChats();
        initRecycler();
        return view;

    }

    public void gui(View view) {
        fotoPerfil = view.findViewById(R.id.fperfil);
        mRecyclerView = view.findViewById(R.id.recycler_chats);
        usuario = view.findViewById(R.id.username);
        mToolbar = view.findViewById(R.id.toolbar_chat);
    }

    public void cargarListaChats() {
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "28/03/2018", 2));
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "14/03/2019", 2));
    }

    public void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorChats = new AdaptadorChats(mListaChats, getContext());
        mRecyclerView.setAdapter(mAdaptadorChats);

    }

    public void showToolbar(String tittle, View view) {

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
