package kevin.com.proyecto01.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
public class Repositorios extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorRepositorios mAdaptadorRepositorios;
    ArrayList<RepositoriosModel> mListaRepositorios;
    public Repositorios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_repositorios, container, false);
      mRecyclerView = view.findViewById(R.id.recy_repositorios);
      iniciarRecycler();
      getDatos();
      return view;
    }

    public void iniciarRecycler(){
        mListaRepositorios = new ArrayList<>();
        mAdaptadorRepositorios = new AdaptadorRepositorios(mListaRepositorios,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdaptadorRepositorios);
    }

    public void getDatos(){

        mListaRepositorios.add(new RepositoriosModel("<html>","5","4"));
        mListaRepositorios.add(new RepositoriosModel("PHP","14","8"));
        mListaRepositorios.add(new RepositoriosModel("html avanzado","100","6"));
        mListaRepositorios.add(new RepositoriosModel("Programacion en android","45","2"));
    }

}
