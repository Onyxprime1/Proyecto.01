package kevin.com.proyecto01.tabsPerfil;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.adaptadores.AdaptadorAmigos;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.Amigo;
import kevin.com.proyecto01.modelos.AmigosModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Amigos extends Fragment {

    public static final int NUMERO_COLUMNAS = 3;
    private static final String TAG = "Amigos";
    private RecyclerView mRecyclerAmigos;
    private AdaptadorAmigos mAdaptadorAmigos;
    private ArrayList<ModeloLogin> mListaAmigos;

    private FirebaseUser fuser;

    public Amigos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);
        mRecyclerAmigos = view.findViewById(R.id.recy_amigos);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = Util.getmDatabase().getReference().child("Amigos").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mListaAmigos.clear();

                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()) {

                        Amigo amigo = data.getValue(Amigo.class);
                        cargarAmigos(amigo);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        iniciarRecycler();
        return view;
    }

    private void cargarAmigos(Amigo amigo) {

        DatabaseReference reference = Util.getmDatabase().getReference();
        reference.child("Usuarios").child(amigo.getIdAmigo()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ModeloLogin amigo = dataSnapshot.getValue(ModeloLogin.class);

                    mListaAmigos.add(amigo);
                    mAdaptadorAmigos.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void iniciarRecycler() {
        mListaAmigos = new ArrayList<>();
        mRecyclerAmigos.setLayoutManager(new GridLayoutManager(getContext(), NUMERO_COLUMNAS));
        mRecyclerAmigos.setHasFixedSize(true);
        mAdaptadorAmigos = new AdaptadorAmigos(mListaAmigos, getContext());
        mRecyclerAmigos.setAdapter(mAdaptadorAmigos);

    }


}