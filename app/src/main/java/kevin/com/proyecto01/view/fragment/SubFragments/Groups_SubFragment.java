package kevin.com.proyecto01.view.fragment.SubFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class Groups_SubFragment extends Fragment {


    RecyclerView mRecyclerView;
    AdaptadorChats mAdaptadorChats;
    ArrayList<ChatsModel> mListaChats;

    public Groups_SubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_fragment_groups, container, false);

        gui(view);


        mListaChats = new ArrayList<>();
        cargarListaChats();
        initRecycler();


        return view;
    }

    public void cargarListaChats() {
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "28/03/2018", 2));
        mListaChats.add(new ChatsModel(" ", "Carlos", "Hola", "14/03/2019", 2));
    }

    public void gui(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_chats);
    }

    public void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdaptadorChats = new AdaptadorChats(mListaChats, getContext());
        mRecyclerView.setAdapter(mAdaptadorChats);

    }




}
