package kevin.com.proyecto01.Database;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kevin.com.proyecto01.Database.Entidad.PostEntity;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;

public class NuevoPostDialogFragment extends DialogFragment {

    private static final String TAG = "NuevoPostDialogFragment";
    private NuevoPostDialogViewModel mViewModel;

    private FirebaseUser user;
    private String id_user;

    private List<PostEntity> listaPosts;

    private FirebaseDatabase database;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        database = Util.getmDatabase();

        listaPosts = new ArrayList<>();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nuevo post");
        builder.setMessage("¿Qué quieres comunicar?");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.nuevo_post_dialog_fragment, null);

        final EditText editTitlePost = view.findViewById(R.id.tema_post);
        final EditText editMessagePost = view.findViewById(R.id.message_post);
        ImageView imageImage = view.findViewById(R.id.imageImage);
        ImageView imageDocument = view.findViewById(R.id.imageDocuments);

        builder.setView(view);



        builder.setPositiveButton("Publicar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String titulo = editMessagePost.getText().toString();
                String mensaje = editTitlePost.getText().toString();

                if(entradasValidas(titulo, mensaje)){
                    mViewModel = ViewModelProviders.of(getActivity()).get(NuevoPostDialogViewModel.class);

                    id_user = user.getUid();

                    Log.e(TAG, id_user );

                    PostEntity postEntity = new PostEntity(titulo, mensaje, id_user, "", "", "");


                    upModelToFirebase(postEntity);


                    mViewModel.insertarPost(postEntity);

                    dialog.dismiss();
                }

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        return builder.create();
    }

    private void upModelToFirebase(final PostEntity postEntity) {

        final DatabaseReference reference = database.getReference();


        reference.child("Posts").setValue(id_user);


        listaPosts.add(postEntity);

        reference.child("Post_Content").child(id_user).setValue(listaPosts);

    }

    private boolean entradasValidas(String titulo, String mensaje) {

        if(titulo.length() > 0){

           return true;
        }else if(mensaje.length() > 0){
            return true;
        }

        return false;
    }
}
