package kevin.com.proyecto01.view.fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
import kevin.com.proyecto01.modelos.ChatsModel;

public class Chat extends AppCompatActivity {

    private Toolbar mToolbarChat;
    private CircleImageView perfil;
    private TextView nombre;
    Intent intent;


    private FirebaseUser fuser;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        gui();
        toolbar();

        intent = getIntent();
        final String username = intent.getStringExtra("username");

        //Toast.makeText(this, "->" +username, Toast.LENGTH_SHORT).show();

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ChatsModel user = data.getValue(ChatsModel.class);
                    assert user != null;
                    if (user.getNombre().equals(username)) {
                        nombre.setText(user.getNombre());
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void gui() {
        perfil = findViewById(R.id.perfil);

        nombre = findViewById(R.id.nombre);
        mToolbarChat = findViewById(R.id.toolbar_chat);
    }

    public void toolbar() {
        setSupportActionBar(mToolbarChat);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarChat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ver_perfil:
                Toast.makeText(this, "ver perfil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.archivos:
                Toast.makeText(this, "ver archivos", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(this, "ver archivos", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
