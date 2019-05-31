package kevin.com.proyecto01.view.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import kevin.com.proyecto01.R;
//import kevin.com.proyecto01.adaptadores.AdaptadorMessage;
//import kevin.com.proyecto01.modelos.ChatsModel;
//import kevin.com.proyecto01.modelos.MessageModel;

public class Chat extends AppCompatActivity {

/*
    private Toolbar mToolbarChat;
    private CircleImageView perfil;
    private TextView nombre;
    Intent intent;

    private FloatingActionButton enviar;
    private EditText mensaje;

    AdaptadorMessage adaptadorMessage;
    ArrayList<MessageModel> messageModels;

    RecyclerView mRecyclerView;

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
        final String id = intent.getStringExtra("id");
        final Calendar currentTime = Calendar.getInstance();
        currentTime.set(Calendar.HOUR_OF_DAY, 0);


        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ChatsModel user = data.getValue(ChatsModel.class);
                    assert user != null;
                    if (user.getNombre().equals(username)) {
                        nombre.setText(user.getNombre());
                    }

                    readMessage(fuser.getUid(), id, user.getImagenPerfil());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mensaje.getText().toString().equals("")) {
                    sendMenssage(fuser.getUid(), id , mensaje.getText().toString(), currentTime.toString());
                }

                mensaje.setText("");
            }
        });
        initRecycler();

    }

    private void readMessage(final String emisor, final String receptor, final String imagenUrl) {
        messageModels = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageModels.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    MessageModel msg = data.getValue(MessageModel.class);
                    if (msg.getReceptor().equals(receptor) && msg.getEmisor().equals(emisor) ||
                            msg.getReceptor().equals(emisor) && msg.getEmisor().equals(receptor)) {
                        messageModels.add(msg);
                    }
                    adaptadorMessage = new AdaptadorMessage(messageModels, getApplicationContext(), imagenUrl);
                    mRecyclerView.setAdapter(adaptadorMessage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sendMenssage(String emisor, String receptor, String mensaje, String hora) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Map<String, String> chatMap = new HashMap<>();
        chatMap.put("emisor", emisor);
        chatMap.put("receptor", receptor);
        chatMap.put("mensaje", mensaje);
        chatMap.put("hora", "");

        reference.child("Chats").push().setValue(chatMap);
    }

    public void gui() {
        perfil = findViewById(R.id.perfil);
        nombre = findViewById(R.id.nombre);

        enviar = findViewById(R.id.btn_enviar);
        mensaje = findViewById(R.id.edt_message);

        mToolbarChat = findViewById(R.id.toolbar_chat);
        mRecyclerView = findViewById(R.id.recy_chat);
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

    public void initRecycler() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
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
*/
}