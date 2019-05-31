package kevin.com.proyecto01.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.modelos.RepositoriosModel;

public class AdaptadorRepositorios extends RecyclerView.Adapter<AdaptadorRepositorios.ViewHolder> {

    private ArrayList<RepositoriosModel> mListaRepositorios;
    Context context;

    public AdaptadorRepositorios(ArrayList<RepositoriosModel> mListaRepositorios, Context context) {
        this.mListaRepositorios = mListaRepositorios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_repositorios,viewGroup,false);
        ViewHolder holder = new  ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mTitulo.setText(mListaRepositorios.get(i).getTitulo());
        viewHolder.mVistas.setText(mListaRepositorios.get(i).getVistas());
        viewHolder.mPuntos.setText(mListaRepositorios.get(i).getPuntos());
    }

    @Override
    public int getItemCount() {
        return mListaRepositorios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitulo;
        private TextView mVistas;
        private TextView mPuntos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitulo = itemView.findViewById(R.id.txt_titulo);
            mVistas = itemView.findViewById(R.id.txt_vistas);
            mPuntos = itemView.findViewById(R.id.txt_puntos);

        }
    }
}