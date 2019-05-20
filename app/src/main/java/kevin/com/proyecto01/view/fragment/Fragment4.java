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
import kevin.com.proyecto01.adaptadores.AdaptadorChats;
import kevin.com.proyecto01.modelos.ChatsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;
    Toolbar mToolbar;
    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_fragment4, container, false);
       showToolbar("Chats",view);
       mRecyclerView = view.findViewById(R.id.recycler_chats);
       mListaChats = new ArrayList<>();
       cargarListaChats();
       initRecycler();
       return view;
    }

    public void cargarListaChats(){
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2018",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","14/03/2019",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/01/2019",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
        mListaChats.add(new ChatsModel(" ","Carlos","Hola","28/03/2017",2));
    }

    public void initRecycler(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorChats = new AdaptadorChats(mListaChats,getContext());
        mRecyclerView.setAdapter(mAdaptadorChats);

    }
    public void showToolbar(String tittle,View view) {

        AppCompatActivity activi = (AppCompatActivity) getActivity();
        mToolbar = view.findViewById(R.id.toolbars);
        activi.setSupportActionBar(mToolbar);
        activi.getSupportActionBar().setTitle(tittle);
        activi.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

}
