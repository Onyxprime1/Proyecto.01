package kevin.com.proyecto01.adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kevin.com.proyecto01.Database.Entidad.ProgramerEntity;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.view.Recibir;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    ArrayList<ProgramerEntity> lista;
    int resouce;
    Activity context;

    public Adaptador(ArrayList<ProgramerEntity> lista, int resouce, Activity context) {
        this.lista = lista;
        this.resouce = resouce;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(resouce, viewGroup,false);
        ViewHolder holder = new ViewHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        viewHolder.bind(lista.get(position), context);


        ImageView img = viewHolder.img;
        viewHolder.texto1.setText(lista.get(position).getUserName());
        viewHolder.texto2.setText(lista.get(position).getTime());
        viewHolder.texto3.setText(lista.get(position).getLike());
        Glide.with(context).load(lista.get(position).getImagen()).into(img);


        if(lista.get(position).isFavorito()){
            viewHolder.favorite.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
            lista.get(position).setFavorito(true);
        }

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Recibir.class);
                intent.putExtra("titulo",lista.get(position).getTime());
                intent.putExtra("nombre",lista.get(position).getUserName());
                intent.putExtra("like",lista.get(position).getLike());
                intent.putExtra("img",lista.get(position).getImagen());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView texto1;
        TextView texto2;
        TextView texto3;
        ImageButton favorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imagen1);
            texto1 = itemView.findViewById(R.id.userNameCard);
            texto2 = itemView.findViewById(R.id.timecard);
            texto3 = itemView.findViewById(R.id.likeNumber);
            favorite = itemView.findViewById(R.id.likeCheck);
        }

        public void bind(final ProgramerEntity programerEntity, Activity context) {

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(programerEntity.isFavorito()){
                        favorite.setBackgroundResource(R.drawable.ic_favorite_border_white_24dp);
                        programerEntity.setFavorito(false);
                    }else{
                        favorite.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                        programerEntity.setFavorito(true);
                    }
                }
            });
        }
    }

}
