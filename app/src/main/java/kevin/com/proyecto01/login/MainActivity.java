package kevin.com.proyecto01.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

import kevin.com.proyecto01.ActivityDashboard;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.Util.Util;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SharedPreferences preferences;

    private EditText editUsername;
    private EditText editPassword;
    private TextView texto3;
    private ModeloLogin login;
    private SignInButton button2;
    private Button button;
    private FirebaseAuth authGoogle; //google
    private FirebaseAuth authUserPass; //correo
    private FirebaseAuth.AuthStateListener googleListener;
    private GoogleApiClient mGoogleSignInClient;
    private static int RC_SING_IN = 101;
    private static final String TAG = "MainActivityLog";
    String bandera = "";
    String bandera2 = "";

    private CheckBox checky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExixts();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        gui();
        authGoogle = FirebaseAuth.getInstance();
        authUserPass = FirebaseAuth.getInstance();

        inicializeGoogleAccount();

    }

    private void setCredentialsIfExixts() {
        String email = Util.getUserMailPrefs(preferences);
        String pass = Util.getUserPassPrefs(preferences);

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            Log.i(TAG, "Email: " + email);
            Log.i(TAG, "Email: " + pass);
            startActivity(new Intent(getApplicationContext(), ActivityDashboard.class));
            finish();
        }
    }

    private void inicializeGoogleAccount() {
        googleListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    if (bandera.equals("google")) {
                        goMainScreen();
                    }

                }
            }
        };
    }

    public void gui() {
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        texto3 = findViewById(R.id.txtdonhaveAccunt);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button = findViewById(R.id.login);
        button.setOnClickListener(this);
        checky = findViewById(R.id.checkBox);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                bandera = "google";
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
                startActivityForResult(intent, RC_SING_IN);
                break;
            case R.id.login:
                bandera2 = "userPass";
                login();
                break;
        }
    }

    public void login() {
        login = new ModeloLogin();
        login.setCorreo(editUsername.getText().toString());
        login.setContracena(editPassword.getText().toString());

        if (logn(login.getCorreo(), login.getContracena())) {
            singnIn(login.getCorreo(), login.getContracena());
        }
    }


    public void registro(View v) {
        startActivity(new Intent(getApplication(), Acounnt.class));
    }


    protected void singnIn(final String email, final String pass) {

        authUserPass.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                button.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    FirebaseUser user2 = authUserPass.getCurrentUser();
                    if (user2.isEmailVerified()) {

                        goMainWithEmail(email, pass);

                    } else {
                        Toast.makeText(MainActivity.this, "la cuenta no ha sido iniciada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error al iniciar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void goMainWithEmail(String email, String pass) {

        Toast.makeText(MainActivity.this, "Iniciando...", Toast.LENGTH_SHORT).show();
        if (checky.isChecked()) {
            saveOnPreferences(email, pass);
            startActivity(new Intent(getApplicationContext(), ActivityDashboard.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), ActivityDashboard.class));
            finish();
        }

    }

    private boolean logn(String email, String pass) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Correo inválido, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValidPass(pass)) {
            Toast.makeText(this, "Tu contraseña es de al menos 6 caracteres, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidPass(String pass) {
        return pass.length() >= 6;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void saveOnPreferences(String email, String pass) {


        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("pass", pass);
        editor.apply();
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

        button2.setVisibility(View.GONE);
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        authGoogle.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                button2.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    FirebaseUser user = authGoogle.getCurrentUser();
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
        Intent intent = new Intent(getApplicationContext(), ActivityDashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        authGoogle.addAuthStateListener(googleListener);

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleSignInClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
            goMainScreen();
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        /*
        if (googleListener != null){
            authGoogle.removeAuthStateListener(googleListener);
        }
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        //authGoogle.addAuthStateListener(googleListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //authGoogle.addAuthStateListener(googleListener);
    }
}
