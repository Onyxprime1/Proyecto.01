package kevin.com.proyecto01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class Acounnt extends AppCompatActivity {

    Toolbar mytoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acounnt);
        showToolbar(getResources().getString(R.string.bartoo), true);
    }

    public void showToolbar(String tittle, boolean upButton) {
        mytoolbar = findViewById(R.id.toolbars);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
