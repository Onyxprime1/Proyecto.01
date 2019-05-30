package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import kevin.com.proyecto01.adaptadores.Adaptador;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;
import kevin.com.proyecto01.adaptadores.AdaptadorAmigos;
import kevin.com.proyecto01.modelos.AmigosModel;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    RecyclerView mRecyclerUsuarios;
    AdaptadorAmigos mAdaptadorUsuarios;
    ArrayList<AmigosModel> mListaUsuarios;
    FirebaseAuth firebaseAuth;
    AmigosModel usuarios;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
       mRecyclerUsuarios = view.findViewById(R.id.recyUsuarios);
        // Cargar la lista de Usuarios
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mListaUsuarios.clear();

                for (DataSnapshot datauser : dataSnapshot.getChildren()){
                    usuarios = datauser.getValue(AmigosModel.class);
                    assert usuarios != null;
                    assert firebaseUser != null;
                    if (!usuarios.getNombreAmigo().equals(firebaseUser.getDisplayName())){
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

    public void cargarListaUsuarios(AmigosModel usuarios){
        mListaUsuarios.add(new AmigosModel(usuarios.getUrlFotoAmigo(),usuarios.getNombreAmigo()));
    }

    public void iniciarRecycler(){
        mRecyclerUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerUsuarios.setHasFixedSize(true);
        //mAdaptadorUsuarios.onCreateViewHolder(viewGroup,0);
        mAdaptadorUsuarios = new AdaptadorAmigos(mListaUsuarios,getContext());
        mRecyclerUsuarios.setAdapter(mAdaptadorUsuarios);
    }




}
