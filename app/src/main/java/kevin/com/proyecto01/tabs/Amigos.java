package kevin.com.proyecto01.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.AdaptadorAmigos;
import kevin.com.proyecto01.modelos.AmigosModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Amigos extends Fragment {

    public static final int NUMERO_COLUMNAS = 3;
    private RecyclerView mRecyclerAmigos;
    private AdaptadorAmigos mAdaptadorAmigos;
    private ArrayList<AmigosModel> mListaAmigos;

    public Amigos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_amigos, container, false);
       mRecyclerAmigos = view.findViewById(R.id.recy_amigos);
       iniciarRecycler();
       getDatos();
       return view;
    }

    public void iniciarRecycler(){
        mListaAmigos = new ArrayList<>();
        mRecyclerAmigos.setLayoutManager(new GridLayoutManager(getContext(), NUMERO_COLUMNAS));
        mRecyclerAmigos.setHasFixedSize(true);
        mAdaptadorAmigos = new AdaptadorAmigos(mListaAmigos,getContext());
        mRecyclerAmigos.setAdapter(mAdaptadorAmigos);

    }

    public void getDatos(){

        mListaAmigos.add(new AmigosModel("",""));
        mListaAmigos.add(new AmigosModel(" ",""));
        mListaAmigos.add(new AmigosModel(" ",""));
    }

}
