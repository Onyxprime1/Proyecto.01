package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.adaptador;
import kevin.com.proyecto01.adaptadores.modelo;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    RecyclerView recy2;
    kevin.com.proyecto01.adaptadores.adaptador adaptador;
    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment2, container, false);
        showToolbar("",view);
        recy2 = view.findViewById(R.id.recy2);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recy2.setLayoutManager(manager);
        adaptador = new adaptador(listade(),R.layout.card_picture,getActivity());
        recy2.setAdapter(adaptador);
        return view;
    }

    public ArrayList<modelo> listade(){
        ArrayList<modelo>listo = new ArrayList<>();
        listo.add(new modelo("http://conrumboaningunaparte.com/wp-content/uploads/2017/07/leon-blanco-y-negro-183361-2.jpg","kevin","infinito","10000"));
        listo.add(new modelo("http://s1.1zoom.me/big0/512/Lions_462377.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://us.123rf.com/450wm/huettenhoelscher/huettenhoelscher1606/huettenhoelscher160600268/60807044-le%C3%B3n-en-blanco-y-negro.jpg?ver=6","kevin","infinito","10000"));
        listo.add(new modelo("https://ae01.alicdn.com/kf/HTB1OSOeNFXXXXbsXFXXq6xXFXXXD/Env-o-Gratuito-de-Una-Pieza-de-Papel-Tapiz-de-Le-n-Real-Gato-Salvaje-Caliente.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://www.dhresource.com/0x0s/f2-albu-g4-M01-E3-B5-rBVaEFoTsMKAL_kXAAD5kUIds5c887.jpg/cartel-de-le-n-blanco-y-negro-decoraci-n.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://desenio.es/bilder/artiklar/zoom/2909_1.jpg","kevin","infinito","10000"));
        listo.add(new modelo("https://http2.mlstatic.com/cuadro-moderno-leon-blanco-y-negro-5-piezas-114x185cm-D_NQ_NP_820304-MLM25894387403_082017-F.jpg","kevin","infinito","10000"));

        return listo;
    }

    public void showToolbar(String tittle,View view) {
        AppCompatActivity activi = (AppCompatActivity) getActivity();
        Toolbar mytoolbar = view.findViewById(R.id.toolbar3);
        activi.setSupportActionBar(mytoolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}
