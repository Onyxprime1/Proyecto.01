package kevin.com.proyecto01.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import kevin.com.proyecto01.R;
import kevin.com.proyecto01.view.Container;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    EditText texto1;
    EditText texto2;
    TextView texto3;
    ModeloLogin login;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_SING_IN = 11;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto1 = findViewById(R.id.username);
        texto2 = findViewById(R.id.password);
        texto3 = findViewById(R.id.txtdonhaveAccunt);

        findViewById(R.id.button2).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        mAuth = FirebaseAuth.getInstance();

    }

    public void ingresar(View view) {
        login = new ModeloLogin();
        login.setCorreo(texto1.getText().toString());
        login.setContracena(texto2.getText().toString());
        singnIn(login.getCorreo(), login.getContracena());

    }

    public void registro(View v) {
        startActivity(new Intent(getApplication(), Acounnt.class));
    }

    protected void singnIn(String email, String pass) {
        if (!login.getCorreo().equals("")||!login.getContracena().equals("")){
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user.isEmailVerified()) {
                            Toast.makeText(MainActivity.this, "Iniciando...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Container.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "la cuenta no ha sido iniciada", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Error al iniciar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        getGoogleCuenta();
    }

    private void getGoogleCuenta() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SING_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SING_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                updateUI(null);
            }
        }
    }

    private void updateUI(Object o) {

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }
                    }
                });
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "No hay internet!", Toast.LENGTH_SHORT).show();
    }


}
