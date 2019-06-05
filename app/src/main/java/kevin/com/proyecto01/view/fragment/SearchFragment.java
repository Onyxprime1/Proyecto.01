package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.adaptadores.AdaptadorUsuarios;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.Notificacion;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    RecyclerView mRecyclerUsuarios;
    AdaptadorUsuarios mAdaptadorUsuarios;
    ArrayList<ModeloLogin> mListaUsuarios;
    FirebaseAuth firebaseAuth;


    private boolean solicitudEnviadaGlobal;


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

                if(dataSnapshot.exists()){
                    for (DataSnapshot datauser : dataSnapshot.getChildren()) {

                        solicitudEnvidada(datauser);

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

    private void solicitudEnvidada(final DataSnapshot dataSnapshotU) {

        solicitudEnviadaGlobal = false;

        final DatabaseReference reference = Util.getmDatabase().getReference();
        reference.child("Notificaciones").child(dataSnapshotU.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for (DataSnapshot data : dataSnapshot.getChildren()){

                        Log.e(TAG, "emisor "+firebaseAuth.getUid());


                            Notificacion notificacion = data.getValue(Notificacion.class);

                            Log.e(TAG,"***************"+ notificacion.getEmisor()+"************************");

                            if(!firebaseAuth.getUid().equals(notificacion.getEmisor())){
                                //No ha sido enviada la amistad
                                ModeloLogin usuarios = dataSnapshotU.getValue(ModeloLogin.class);

                                usuarios.setIdUser(dataSnapshotU.getKey());


                                if (!usuarios.getIdUser().equals(firebaseAuth.getUid()) && notificacion.getEsAmigo().equals("No")) {
                                    cargarListaUsuarios(usuarios);
                                }
                            }else{
                                //Ya ha sido enviada la solicitud
                                Log.e(TAG, "Ya fue enviada a " + notificacion.getReceptor());
                            }

                    }
                }else{

                    ModeloLogin usuarios = dataSnapshotU.getValue(ModeloLogin.class);

                    usuarios.setIdUser(dataSnapshotU.getKey());


                    if (!usuarios.getIdUser().equals(firebaseAuth.getUid())) {
                        cargarListaUsuarios(usuarios);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void cargarListaUsuarios(ModeloLogin usuarios) {
        mListaUsuarios.add(usuarios);
        mAdaptadorUsuarios.notifyDataSetChanged();
    }

    public void iniciarRecycler() {
        mRecyclerUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerUsuarios.setHasFixedSize(true);
        mAdaptadorUsuarios = new AdaptadorUsuarios(mListaUsuarios, getContext());
        mRecyclerUsuarios.setAdapter(mAdaptadorUsuarios);
    }


}


