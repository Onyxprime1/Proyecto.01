package kevin.com.proyecto01.tabsPerfil;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.AdaptadorRepositorios;
import kevin.com.proyecto01.modelos.RepositoriosModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favoritos extends Fragment {

    public static final int NUMERO_DE_COLUMNAS = 2;
    private AdaptadorRepositorios mAdaptadorRepositorios;
    private RecyclerView mRecyclerView;
    private ArrayList<RepositoriosModel> mListaRepFavoritos;
    private CardView mCarViewFavoritos;
    public Favoritos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        mRecyclerView = view.findViewById(R.id.recy_favoritos);
        iniciarRecycler();
        getDatos();
        return view;
    }

    public void iniciarRecycler(){
        mListaRepFavoritos = new ArrayList<>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),NUMERO_DE_COLUMNAS));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorRepositorios = new AdaptadorRepositorios(mListaRepFavoritos,getContext());
        mRecyclerView.setAdapter(mAdaptadorRepositorios);
    }

    public void getDatos(){

        mListaRepFavoritos.add(new RepositoriosModel("","",""));
        mListaRepFavoritos.add(new RepositoriosModel("<html>","5","4"));
        mListaRepFavoritos.add(new RepositoriosModel("PHP","14","8"));
        mListaRepFavoritos.add(new RepositoriosModel("html avanzado","100","6"));
    }
}