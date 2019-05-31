package kevin.com.proyecto01.view.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kevin.com.proyecto01.Database.Entidad.PostEntity;
import kevin.com.proyecto01.Database.NuevoPostDialogFragment;
import kevin.com.proyecto01.Database.NuevoPostDialogViewModel;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.adaptadores.Adaptador;
import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    private DatabaseReference mPostReference;
    private FirebaseUser user;


    private ValueEventListener postListener;


    private FloatingActionButton fab;

    private RecyclerView recy;
    private Adaptador adaptador;
    private Toolbar mytoolbar;

    private List<PostEntity> postEntities;

    private NuevoPostDialogViewModel viewModel;
    private String TAG = "InicioFragment";

    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        postEntities = new ArrayList<>();

        fab = view.findViewById(R.id.fab);


        mPostReference = Util.getmDatabase().getReference()
                .child("Posts").child(user.getUid());


        showToolbar("home", view);
        recy = view.findViewById(R.id.recy);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                NuevoPostDialogFragment dialogFragment = new NuevoPostDialogFragment();
                dialogFragment.show(fragmentManager, "NuevoPostDialogFragment");
            }
        });


        mPostReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.exists()){
                    PostEntity postByUser = dataSnapshot.getValue(PostEntity.class);
                    postEntities.add(postByUser);

                    adaptador = new Adaptador(postEntities, R.layout.card_picture, getActivity());
                    recy.setAdapter(adaptador);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    PostEntity postElimiado = dataSnapshot.getValue(PostEntity.class);
                    adaptador.removeItem(postElimiado.getId());
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void lanzarViewModel() {
        viewModel = ViewModelProviders.of(getActivity())
                .get(NuevoPostDialogViewModel.class);

        viewModel.getAllPosts().observe(getActivity(), new Observer<List<PostEntity>>() {
            @Override
            public void onChanged(@Nullable List<PostEntity> postEntities) {
                adaptador.setNuevoPost(postEntities);
            }
        });

    }

    public void showToolbar(String tittle, View view) {

        AppCompatActivity activi = (AppCompatActivity) getActivity();
        mytoolbar = view.findViewById(R.id.toolbars);
        activi.setSupportActionBar(mytoolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}
