package kevin.com.proyecto01.view.fragment.SubFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.AdaptadorChats;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.ChatsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Chats_SubFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;
    String TAG = "ChatFragment";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    public Chats_SubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_fragment_chats, container, false);

        gui(view);

        mListaChats = new ArrayList<>();

        //cargarListaChats();


        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ModeloLogin login = dataSnapshot.getValue(ModeloLogin.class);

                if(dataSnapshot.exists()) {
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        ChatsModel user = data.getValue(ChatsModel.class);
                        cargarListaChats(user);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mListaChats.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatsModel chat =  snapshot.getValue(ChatsModel.class);

                    cargarListaChats(chat);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        /*firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    cargarListaChats(user);
                }
            }
        };*/


        initRecycler();
        return view;
    }

    public void cargarListaChats(ChatsModel user) {
        mListaChats.add(new ChatsModel(user.getImagenPerfil(), user.getNombre(), user.getMensaje(), "28/03/2018", 2));
        mAdaptadorChats.notifyDataSetChanged();

    }

    public void gui(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_chats);
    }

    public void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorChats = new AdaptadorChats(mListaChats, getContext());
        mRecyclerView.setAdapter(mAdaptadorChats);

    }
   /* @Override
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
*/


}
