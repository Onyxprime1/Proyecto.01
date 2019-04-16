package kevin.com.proyecto01.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.adaptadores.adaptador;
import kevin.com.proyecto01.adaptadores.modelo;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {

    RecyclerView recy3;
    adaptador adapt;
    GridLayoutManager gridLayoutManager;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        recy3 = view.findViewById(R.id.recy3);
       gridLayoutManager = new GridLayoutManager(view.getContext(),2);
        recy3.setHasFixedSize(true);
        recy3.setLayoutManager(gridLayoutManager);
        adapt = new adaptador(listade(),R.layout.card_picture,getActivity());
        recy3.setAdapter(adapt);
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


}
