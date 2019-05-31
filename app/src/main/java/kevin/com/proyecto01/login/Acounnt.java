package kevin.com.proyecto01.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;


public class Acounnt extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AccountActivityLog";
    private FirebaseDatabase mDatabase;
    private FirebaseAuth firebaseAuth;

    Toolbar mytoolbar;
    EditText editTextEmail;
    EditText editTextUser;
    EditText editTextApellido;
    EditText editTextPassword;
    EditText editTextConfPassword;
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


        mDatabase = Util.getmDatabase();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void showToolbar(String tittle, boolean upButton) {
        mytoolbar = findViewById(R.id.toolbars);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    public void gui() {
        editTextEmail = findViewById(R.id.email);
        editTextUser = findViewById(R.id.user);
        editTextApellido = findViewById(R.id.lastname);
        editTextPassword = findViewById(R.id.pass);
        editTextConfPassword = findViewById(R.id.confirmpas);
        mSend = findViewById(R.id.joinUs);
    }

    public void onClick(View v) {

        String user = editTextUser.getText().toString();
        String email = editTextEmail.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String password = editTextPassword.getText().toString();
        String confPassword = editTextConfPassword.getText().toString();

        if (user.length() > 0 && email.length() > 0 && apellido.length() > 0 && password.length() > 0 && confPassword.length() > 0) {

            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (password.equals(confPassword)) {
                    ModeloLogin modelo = new ModeloLogin(editTextUser.getText().toString(), editTextApellido.getText().toString(), editTextEmail.getText().toString());
                    creatAccount(modelo, password);
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El correo no es válido, intentar de nuevo", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor llenar todos los campos para poder registrarse", Toast.LENGTH_SHORT).show();
        }


    }

    public void creatAccount(final ModeloLogin newUser, String pass) {
        mAuth.createUserWithEmailAndPassword(newUser.getCorreo(), pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    saveDisplayName();

                    writeNewUser(newUser);

                    sendEmailVericatio();
                    startActivity(new Intent(Acounnt.this, MainActivity.class));
                    Toast.makeText(Acounnt.this, "Usario creado", Toast.LENGTH_SHORT).show();
                } else {
                    showError("El usuario ya existe");
                }
            }
        });
    }

    private void writeNewUser(ModeloLogin newUser) {
        String user_id = mAuth.getCurrentUser().getUid();

        DatabaseReference databaseReference = mDatabase.getReference();

        databaseReference.child("Usuarios").child(user_id).setValue(newUser);

    }

    public void showError(String msj) {
        new AlertDialog.Builder(this)
                .setTitle("Registro Denegado")
                .setMessage(msj)
                .setPositiveButton("ok", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void saveDisplayName() {
        SharedPreferences preferences = getSharedPreferences("usuario", 0);
        preferences.edit().putString("username", modelo.getNombre()).apply();
    }

    protected void sendEmailVericatio() {
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                showError("se ha enviado un correo a tu cuenta");
            }
        });
    }
}
