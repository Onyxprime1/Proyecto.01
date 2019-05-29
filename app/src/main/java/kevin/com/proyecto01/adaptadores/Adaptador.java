package kevin.com.proyecto01.adaptadores;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kevin.com.proyecto01.Database.Entidad.PostEntity;
import kevin.com.proyecto01.Database.NuevoPostDialogViewModel;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.view.Recibir;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    List<PostEntity> lista;
    int resouce;
    Activity context;

    private NuevoPostDialogViewModel viewModel;

    public Adaptador(List<PostEntity> lista, int resouce, Activity context) {
        this.lista = lista;
        this.resouce = resouce;
        this.context = context;

        //viewModel = ViewModelProviders.of((AppCompatActivity)context).get(NuevoPostDialogViewModel.class);
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

        viewHolder.bind(lista.get(position), context, viewModel);

        if(lista.get(position).isFavorite()){
            viewHolder.favorite.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
            lista.get(position).setFavorite(true);
        }

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Recibir.class);
                intent.putExtra("nombre",lista.get(position).getTitulo());
                intent.putExtra("like",lista.get(position).getMensaje());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setNuevoPost(List<PostEntity> postEntities) {
        this.lista= postEntities;
        notifyDataSetChanged();
    }

    public void removeItem(String title) {
        for(int i = 0; i < lista.size(); i++){

            if(title.equals(lista.get(i).getTitulo())){
                lista.remove(i);
                notifyItemRemoved(i);
                notifyDataSetChanged();
            }
        }
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtTitleCardPost;
        TextView txtMessageCardPost;
        TextView texto3;
        ImageButton favorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imagen1);
            txtTitleCardPost = itemView.findViewById(R.id.titleCardPost);
            txtMessageCardPost = itemView.findViewById(R.id.messageCardPost);
            favorite = itemView.findViewById(R.id.likeCheck);
        }

        public void bind(final PostEntity post, Activity context, final NuevoPostDialogViewModel viewModel) {


            txtTitleCardPost.setText(post.getTitulo());
            txtMessageCardPost.setText(post.getMensaje());


            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(post.isFavorite()){
                        favorite.setBackgroundResource(R.drawable.ic_favorite_border_white_24dp);
                        post.setFavorite(false);
                    }else{
                        favorite.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
                        post.setFavorite(true);
                    }

                    viewModel.actualizarPost(post);

                }
            });
        }
    }

}
