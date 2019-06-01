package kevin.com.proyecto01.adaptadores;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;
import kevin.com.proyecto01.login.ModeloLogin;
import kevin.com.proyecto01.modelos.Notificacion;

public class AdaptadorNotificacion extends RecyclerView.Adapter<AdaptadorNotificacion.ViewHolder> {

    private static final String TAG = "AdaptadorNotificacion";
    private List<Notificacion> notificaciones;
    private int layout;
    private Activity activity;


    public AdaptadorNotificacion(List<Notificacion> notificaciones, int layout, Activity activity) {
        this.notificaciones = notificaciones;
        this.layout = layout;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(activity).inflate(layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(notificaciones.get(position), activity);
    }

    @Override
    public int getItemCount() {
        return notificaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameInvitation;
        CircleImageView imgUserInvitation;
        MaterialButton btnAcceptInvitation;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameInvitation = itemView.findViewById(R.id.txtUserInvitation);
            imgUserInvitation = itemView.findViewById(R.id.img_userInvitation);
            btnAcceptInvitation = itemView.findViewById(R.id.btn_aceptarInvitation);
        }

        public void bind(final Notificacion notificacion, final Activity activity) {

            DatabaseReference reference = Util.getmDatabase().getReference();

            reference.child("Usuarios").child(notificacion.getEmisor()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        ModeloLogin amigo = dataSnapshot.getValue(ModeloLogin.class);

                        txtNameInvitation.setText(amigo.getNombre());
                        Glide.with(activity).load(amigo.getUrlImage()).into(imgUserInvitation);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




            btnAcceptInvitation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Aceptar invitación
                    //Quitar de Notificaciones al usuario actual
                    //Pasar al usuario a la lista de amigos

                }
            });

        }
    }
}
