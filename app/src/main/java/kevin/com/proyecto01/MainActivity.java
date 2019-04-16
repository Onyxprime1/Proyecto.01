package kevin.com.proyecto01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import kevin.com.proyecto01.view.Container;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nopues(View view) {
        Intent intent = new Intent(getApplicationContext(),Acounnt.class);
        startActivity(intent);
    }

    public void tocalo(View v){
        startActivity(new Intent(getApplicationContext(), Container.class));
    }

}
