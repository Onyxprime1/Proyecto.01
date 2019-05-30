package kevin.com.proyecto01.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.modelos.AmigosModel;

public class AdaptadorAmigos extends RecyclerView.Adapter<AdaptadorAmigos.ViewHolder> {

    private ArrayList<AmigosModel> mListaAmigos;
    Context context;

    public AdaptadorAmigos(ArrayList<AmigosModel> mListaAmigos, Context context) {
        this.mListaAmigos = mListaAmigos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_usuarios,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mNombreAmigo.setText(mListaAmigos.get(i).getNombreAmigo());
        //Glide.with(context).load(mListaAmigos.get(i).getUrlFotoAmigo()).into(viewHolder.mFotoAmigo);

    }

    @Override
    public int getItemCount() {
       return mListaAmigos.size();


    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mFotoAmigo;
        private TextView mNombreAmigo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFotoAmigo = itemView.findViewById(R.id.civ_fotoAmigo);
            mNombreAmigo = itemView.findViewById(R.id.txt_nombreAmigo);

        }
    }
}