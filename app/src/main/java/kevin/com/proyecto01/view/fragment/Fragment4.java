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

public class Fragment4 extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;
    Toolbar mToolbar;

    CircleImageView fotoPerfil;
    TextView username;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

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
                    username.setText(user.getDisplayName());
                    Glide.with(getContext()).load(user.getPhotoUrl()).into(fotoPerfil);
                }
            }
        };
        setHasOptionsMenu(true);
        showToolbar();
        mListaChats = new ArrayList<>();
        cargarListaChats();
        initRecycler();
        return view;
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

    public void showToolbar() {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        activi.setSupportActionBar(mToolbar);
        activi.getSupportActionBar().setTitle("");
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void gui(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_chats);
        fotoPerfil = view.findViewById(R.id.fperfil);
        username = view.findViewById(R.id.username);
        mToolbar = view.findViewById(R.id.toolbar_chat);
    }


    public void cargarListaChats() {
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "28/03/2018", 2));
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "14/03/2019", 2));
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "28/01/2019", 2));
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "28/03/2017", 2));
    }

    public void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorChats = new AdaptadorChats(mListaChats, getContext());
        mRecyclerView.setAdapter(mAdaptadorChats);
    }

}
