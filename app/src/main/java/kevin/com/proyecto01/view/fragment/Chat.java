package kevin.com.proyecto01.view.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import kevin.com.proyecto01.R;

public class Chat extends AppCompatActivity {

    private Toolbar mToolbarChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mToolbarChat = findViewById(R.id.toolbar_chat);
        setSupportActionBar(mToolbarChat);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
