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
import kevin.com.proyecto01.modelos.AmigosModel;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder> {

    private ArrayList<ModeloLogin> mListaUsuarios;
    Context context;

    public AdaptadorUsuarios( ArrayList<ModeloLogin> mListaUsuarios, Context context) {
        this.mListaUsuarios = mListaUsuarios;
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

        viewHolder.mNombreUsuario.setText(mListaUsuarios.get(i).getNombre());
        //Glide.with(context).load(mListaUsuarios.get(i).getUrlFotoAmigo()).into(viewHolder.mFotoUsuario);

    }

    @Override
    public int getItemCount() {
        if (mListaUsuarios.size() != 0) {
            return mListaUsuarios.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mFotoUsuario;
        private TextView mNombreUsuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFotoUsuario = itemView.findViewById(R.id.civ_fotoUsuario);
            mNombreUsuario = itemView.findViewById(R.id.txt_nombreUsuario);
        }
    }
}
