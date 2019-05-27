package kevin.com.proyecto01.view.fragment.SubFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import kevin.com.proyecto01.modelos.ChatsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Chats_SubFragment extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;

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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("noobspace-50f69");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mListaChats.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatsModel user = snapshot.getValue(ChatsModel.class);

                    assert user != null;
                    assert firebaseUser != null;
                    if (!user.getNombre().equals(firebaseUser.getDisplayName())) {
                        cargarListaChats(firebaseUser);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //cargarListaChats();
                }
            }
        };

        initRecycler();

        return view;
    }

    public void cargarListaChats(FirebaseUser user) {
        mListaChats.add(new ChatsModel(" ", user.getDisplayName(), user.getPhoneNumber(), "28/03/2018", 2));
        //mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "28/03/2018", 2));
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
