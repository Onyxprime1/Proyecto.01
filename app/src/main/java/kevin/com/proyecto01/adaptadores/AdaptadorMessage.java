package kevin.com.proyecto01.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.modelos.ChatsModel;
import kevin.com.proyecto01.modelos.MessageModel;
import kevin.com.proyecto01.view.fragment.Chat;

public class AdaptadorMessage extends RecyclerView.Adapter<AdaptadorMessage.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private ArrayList<MessageModel> mListaMessage;
    Context context;
    private String imagenUrl;
    int position=0;

    FirebaseUser fuser;

    public AdaptadorMessage() {
    }

    public AdaptadorMessage(ArrayList<MessageModel> mListaMessage, Context context, String imagenUrl) {
        this.mListaMessage = mListaMessage;
        this.context = context;
        this.imagenUrl = imagenUrl;
    }

    @NonNull
    @Override
    public AdaptadorMessage.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       if (i == MSG_TYPE_RIGHT) {
           View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item_right, viewGroup, false);
           AdaptadorMessage.ViewHolder holder = new AdaptadorMessage.ViewHolder(view);
           return holder;
       }else{
           View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item_left, viewGroup, false);
           AdaptadorMessage.ViewHolder holder = new AdaptadorMessage.ViewHolder(view);
           return holder;
       }
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorMessage.ViewHolder viewHolder, int i) {
        MessageModel message =  mListaMessage.get(i);
        viewHolder.show_msg.setText(message.getMensaje());
        Glide.with(context).load(imagenUrl).into(viewHolder.mImgPerfil);
    }

    @Override
    public int getItemCount() {
        return mListaMessage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgPerfil;
        private TextView show_msg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgPerfil = itemView.findViewById(R.id.fotoPerfil);
            show_msg = itemView.findViewById(R.id.show_message);

        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mListaMessage.get(position).getEmisor().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }

    }
}