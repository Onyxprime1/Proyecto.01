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
import kevin.com.proyecto01.login.ModeloLogin;

public class AdaptadorAmigos extends RecyclerView.Adapter<AdaptadorAmigos.ViewHolder> {

    private ArrayList<ModeloLogin> mListaAmigos;
    private Context context;

    public AdaptadorAmigos(ArrayList<ModeloLogin> mListaAmigos, Context context) {
        this.mListaAmigos = mListaAmigos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_amigos,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.bind(mListaAmigos.get(i), context);

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

            mFotoAmigo = itemView.findViewById(R.id.civ_fotoAmigoP);
            mNombreAmigo = itemView.findViewById(R.id.txt_nombreAmigoP);

        }

        public void bind(ModeloLogin modeloLogin, Context context) {

            Glide.with(context).load(modeloLogin.getUrlImage()).into(mFotoAmigo);
            mNombreAmigo.setText(modeloLogin.getNombre());
        }
    }
}