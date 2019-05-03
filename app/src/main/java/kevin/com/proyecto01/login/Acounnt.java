package kevin.com.proyecto01.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kevin.com.proyecto01.R;


public class Acounnt extends AppCompatActivity implements View.OnClickListener {

    Toolbar mytoolbar;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    FirebaseAuth mAuth;
    ModeloLogin modelo = new ModeloLogin();
    Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acounnt);
        showToolbar(getResources().getString(R.string.bartoo), true);
        mAuth = FirebaseAuth.getInstance();
        gui();
        mSend.setOnClickListener(this);
    }

    public void showToolbar(String tittle, boolean upButton) {
        mytoolbar = findViewById(R.id.toolbars);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public void gui(){
        editText1 = findViewById(R.id.email);
        editText2 = findViewById(R.id.user);
        editText3 = findViewById(R.id.lastname);
        editText4 = findViewById(R.id.pass);
        editText5 = findViewById(R.id.confirmpas);
        mSend = findViewById(R.id.joinUs);
    }

    public void onClick(View v){
        modelo.setCorreo(editText1.getText().toString());
        modelo.setNombre(editText2.getText().toString());
        modelo.setApellido(editText3.getText().toString());
        modelo.setContracena(editText4.getText().toString());
        modelo.setConfirmacion(editText5.getText().toString());
        switch (v.getId()){
            case R.id.joinUs:
                creatAccount(modelo.getCorreo(),modelo.getContracena());
                break;
        }
    }

    public void creatAccount(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    saveDisplayName();
                    Toast.makeText(Acounnt.this, "Usario creado", Toast.LENGTH_SHORT).show();
                    sendEmailVericatio();
                    startActivity(new Intent(Acounnt.this,MainActivity.class));
                }else {
                    showError("El usuario ya existe");
                }
            }
        });
    }

    public void showError(String msj){
        new AlertDialog.Builder(this)
                .setTitle("Registro Denegado")
                .setMessage(msj)
                .setPositiveButton("ok",null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void saveDisplayName(){
        SharedPreferences preferences = getSharedPreferences("usuario",0);
        preferences.edit().putString("username",modelo.getNombre()).apply();
    }
    protected void sendEmailVericatio(){
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                showError("se ha enviado un correo a tu cuenta");
            }
        });
    }
}
