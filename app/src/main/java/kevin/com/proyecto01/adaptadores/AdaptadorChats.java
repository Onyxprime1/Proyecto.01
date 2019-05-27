package kevin.com.proyecto01.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.modelos.ChatsModel;

public class AdaptadorChats extends RecyclerView.Adapter<AdaptadorChats.ViewHolder> {

    private ArrayList<ChatsModel> mListaChats;
    Context context;

    public AdaptadorChats() {
    }

    public AdaptadorChats(ArrayList<ChatsModel> mListaChats, Context context) {
        this.mListaChats = mListaChats;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_chats,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mNombre.setText(mListaChats.get(i).getNombre());
        viewHolder.mMensaje.setText(mListaChats.get(i).getMensaje());
        viewHolder.mFecha.setText(mListaChats.get(i).getFecha());
        viewHolder.mNumero.setText(String.valueOf(mListaChats.get(i).getNumero()));

    }

    @Override
    public int getItemCount() {
        return mListaChats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImgPerfil;
        private TextView mNombre;
        private TextView mMensaje;
        private TextView mFecha;
        private TextView mNumero;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgPerfil = itemView.findViewById(R.id.img_perfil);
            mNombre = itemView.findViewById(R.id.txt_username);
            mMensaje = itemView.findViewById(R.id.txt_mensaje);
            mFecha = itemView.findViewById(R.id.txt_fecha);
            mNumero = itemView.findViewById(R.id.txt_numero);

        }
    }
}
