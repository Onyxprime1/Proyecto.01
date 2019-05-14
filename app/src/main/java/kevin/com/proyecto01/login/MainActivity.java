package kevin.com.proyecto01.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    EditText texto1;
    EditText texto2;
    TextView texto3;
    ModeloLogin login;
    SignInButton button2;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener fAuthStateListener;
    private GoogleApiClient mGoogleSignInClient;
    private static int RC_SING_IN = 11;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        gui();
        firebaseAuth = FirebaseAuth.getInstance();
        mfirebaseAuth = FirebaseAuth.getInstance();
        fAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    goMainScreen();
                }else {

                }
            }
        };

    }
    public void  gui(){
        texto1 = findViewById(R.id.username);
        texto2 = findViewById(R.id.password);
        texto3 = findViewById(R.id.txtdonhaveAccunt);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    //***************************************************************************************************************************//
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
            mfirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mfirebaseAuth.getCurrentUser();
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


    //*****************************************************************************************************************************************//

   @Override
    public void onClick(View v) {

       Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
       startActivityForResult(intent, RC_SING_IN);
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(fAuthStateListener);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SING_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            firebaseAuthWithGoogle(result.getSignInAccount());
        } else {
            Toast.makeText(this, "no se puede abrir", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {

        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                button2.setVisibility(View.VISIBLE);

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    updateUI(null);
                }

            }
        });
    }

    private void updateUI(Object o) {

    }

    private void goMainScreen() {
        Intent intent = new Intent(this, Container.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fAuthStateListener != null){
            firebaseAuth.removeAuthStateListener(fAuthStateListener);
        }
    }
}
