package kevin.com.proyecto01.tabsPerfil;


import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.adaptadores.AdaptadorNotificacion;
import kevin.com.proyecto01.modelos.Notificacion;

/**
 * A simple {@link Fragment} subclass.
 */
public class Historial extends Fragment {

    private static final String TAG = "HistorialTab";
    private AdaptadorNotificacion adaptadorNotificacion;
    private RecyclerView recyclerView;
    private List<Notificacion> listaNotificaciones;


    public Historial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        bindUIElements(view);
        DatabaseReference reference = Util.getmDatabase().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        reference.child("Notificaciones").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                listaNotificaciones.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Notificacion notificacion = data.getValue(Notificacion.class);
                    listaNotificaciones.add(notificacion);
                    adaptadorNotificacion.notifyDataSetChanged();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        iniciarRecycler();


        return view;


    }

    private void iniciarRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adaptadorNotificacion = new AdaptadorNotificacion(listaNotificaciones, R.layout.cardview_notificacion_layout, getActivity());
        recyclerView.setAdapter(adaptadorNotificacion);
    }

    private void bindUIElements(View view) {

        listaNotificaciones = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recy_historial);

    }

}
