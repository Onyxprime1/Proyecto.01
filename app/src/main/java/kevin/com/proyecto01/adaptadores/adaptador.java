package kevin.com.proyecto01.adaptadores;

import android.app.Activity;
import android.content.Intent;
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
import kevin.com.proyecto01.view.Recibir;

public class adaptador extends RecyclerView.Adapter<adaptador.ViewHolder> {

    ArrayList<modelo> lista;
    int resouce;
    Activity context;

    public adaptador(ArrayList<modelo> lista, int resouce, Activity context) {
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        ImageView img = viewHolder.img;
        viewHolder.texto1.setText(lista.get(i).getUserName());
        viewHolder.texto2.setText(lista.get(i).getTime());
        viewHolder.texto3.setText(lista.get(i).getLike());
        Glide.with(context).load(lista.get(i).getImagen()).into(img);

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Recibir.class);
                intent.putExtra("titulo",lista.get(i).getTime());
                intent.putExtra("nombre",lista.get(i).getUserName());
                intent.putExtra("like",lista.get(i).getLike());
                intent.putExtra("img",lista.get(i).getImagen());
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imagen1);
            texto1 = itemView.findViewById(R.id.userNameCard);
            texto2 = itemView.findViewById(R.id.timecard);
            texto3 = itemView.findViewById(R.id.likeNumber);
        }
    }

}
