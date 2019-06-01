package kevin.com.proyecto01.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.Notificacion;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.mNombreUsuario.setText(mListaUsuarios.get(i).getNombre());
        //Glide.with(context).load(mListaUsuarios.get(i).getUrlFotoAmigo()).into(viewHolder.mFotoUsuario);
        
        
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Hola mundo", Toast.LENGTH_SHORT).show();

                DatabaseReference reference = Util.getmDatabase().getReference();

                FirebaseAuth user = FirebaseAuth.getInstance();

                String receptor = mListaUsuarios.get(i).getIdUser();

                Notificacion notificacion = new Notificacion(user.getUid(), receptor, "No");


                Map<String, String> noti = new HashMap<>();
                noti.put("Emisor", notificacion.getEmisor());
                noti.put("Receptor", notificacion.getReceptor());
                noti.put("EsAmigo", notificacion.getEsAmigo());

                reference.child("Notificiones").child(receptor).push().setValue(noti);


            }
        });
        

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
        ImageView mFotoUsuario;
        TextView mNombreUsuario;
        MaterialButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFotoUsuario = itemView.findViewById(R.id.civ_fotoUsuario);
            mNombreUsuario = itemView.findViewById(R.id.txt_nombreUsuario);
            button = itemView.findViewById(R.id.btn_enviarSolicitud);
        }
    }
}
