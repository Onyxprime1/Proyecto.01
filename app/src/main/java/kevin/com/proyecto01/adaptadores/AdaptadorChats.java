package kevin.com.proyecto01.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import kevin.com.proyecto01.modelos.ChatsModel;
import kevin.com.proyecto01.view.fragment.Chat;

public class  AdaptadorChats extends RecyclerView.Adapter<AdaptadorChats.ViewHolder> {

    private ArrayList<ChatsModel> mListaChats;
    Context context;
    int position = 0;

    public AdaptadorChats() {
    }

    public AdaptadorChats(ArrayList<ChatsModel> mListaChats, Context context) {
        this.mListaChats = mListaChats;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_chats, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Glide.with(context).load(mListaChats.get(i).getImagenPerfil()).into(viewHolder.mImgPerfil);
        //viewHolder.mId.setText(mListaChats.get(i).getId());
        viewHolder.mNombre.setText(mListaChats.get(i).getNombre());
        viewHolder.mMensaje.setText(mListaChats.get(i).getMensaje());
        viewHolder.mFecha.setText(mListaChats.get(i).getFecha());
        position = i;

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat.class);
                intent.putExtra("id", viewHolder.mId.getText().toString());
                intent.putExtra("username", viewHolder.mNombre.getText().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListaChats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgPerfil;
        private TextView mNombre;
        private TextView mMensaje;
        private TextView mFecha;
        private TextView mId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgPerfil = itemView.findViewById(R.id.img_perfil);
            mNombre = itemView.findViewById(R.id.txt_username);
            mMensaje = itemView.findViewById(R.id.txt_mensaje);
            mFecha = itemView.findViewById(R.id.txt_fecha);
            mId = itemView.findViewById(R.id.id);

        }
    }
}
