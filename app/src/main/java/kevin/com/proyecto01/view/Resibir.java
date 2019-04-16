package kevin.com.proyecto01.view;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kevin.com.proyecto01.R;

public class Resibir extends AppCompatActivity {

    TextView texto1;
    TextView texto2;
    TextView texto3;
    ImageView img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resibir);
        showToolbar("", true);
        Bundle parametros = this.getIntent().getExtras();
        texto1 = findViewById(R.id.title3);
        texto2 = findViewById(R.id.like2);
        texto3 = findViewById(R.id.title3);
        img2 = findViewById(R.id.header);
        String uno =parametros.getString("nombre");
        String dos =parametros.getString("like");
        String tres =parametros.getString("titulo");
        String cuatro = parametros.getString("img");
        Glide.with(this).load(cuatro).into(img2);
        texto1.setText(uno);
        texto2.setText(dos);
        texto3.setText(tres);


    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar mytoolbar = findViewById(R.id.toolbar21);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout colla = findViewById(R.id.collapsing);

    }
}
