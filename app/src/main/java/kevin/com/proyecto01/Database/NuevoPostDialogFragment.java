package kevin.com.proyecto01.Database;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Date;

import kevin.com.proyecto01.Database.Entidad.PostEntity;
import kevin.com.proyecto01.R;

public class NuevoPostDialogFragment extends DialogFragment {

    private NuevoPostDialogViewModel mViewModel;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nuevo post");
        builder.setMessage("¿Qué quieres comunicar?");


        LayoutInflater inflater = getActivity().getLayoutInflater();
       /* final View view = inflater.inflate(R.layout.nuevo_post_dialog_fragment, null);

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
                    mViewModel.insertarPost(new PostEntity(titulo, mensaje, "", "", ""));

                    dialog.dismiss();
                }

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/



        return builder.create();
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
