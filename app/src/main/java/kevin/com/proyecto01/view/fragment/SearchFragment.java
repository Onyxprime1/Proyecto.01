package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.adaptadores.Adaptador;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;
import kevin.com.proyecto01.adaptadores.AdaptadorAmigos;
import kevin.com.proyecto01.adaptadores.AdaptadorUsuarios;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.AmigosModel;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    RecyclerView mRecyclerUsuarios;
    AdaptadorUsuarios mAdaptadorUsuarios;
    ArrayList<ModeloLogin> mListaUsuarios;
    FirebaseAuth firebaseAuth;



    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
       mRecyclerUsuarios = view.findViewById(R.id.recyUsuarios);

       mListaUsuarios = new ArrayList<>();
       firebaseAuth = FirebaseAuth.getInstance();

        // Cargar la lista de Usuarios

        mListaUsuarios = new ArrayList<>();

        DatabaseReference reference = Util.getmDatabase().getReference();
        reference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mListaUsuarios.clear();

                for (DataSnapshot datauser : dataSnapshot.getChildren()){
                    ModeloLogin usuarios = datauser.getValue(ModeloLogin.class);

                    Log.e(TAG, usuarios.getNombre());

                    usuarios.setIdUser(datauser.getKey());

                    assert usuarios != null;

                    if (!usuarios.getIdUser().equals(firebaseAuth.getUid())){
                        cargarListaUsuarios(usuarios);
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

    public void cargarListaUsuarios(ModeloLogin usuarios){
        mListaUsuarios.add(usuarios);
        mAdaptadorUsuarios.notifyDataSetChanged();
    }

    public void iniciarRecycler(){
        mRecyclerUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerUsuarios.setHasFixedSize(true);
        mAdaptadorUsuarios = new AdaptadorUsuarios(mListaUsuarios,getContext());
        mRecyclerUsuarios.setAdapter(mAdaptadorUsuarios);
    }


}


