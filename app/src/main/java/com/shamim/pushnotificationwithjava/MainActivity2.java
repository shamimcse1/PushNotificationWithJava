package com.shamim.pushnotificationwithjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private TextView tiileTv,body;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tiileTv = findViewById(R.id.title);
        body = findViewById(R.id.body);
        imageView = findViewById(R.id.image);
        String title;
        String massage;
        String url;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            massage = extras.getString("massage");
            url = extras.getString("image");
            Toast.makeText(this, ""+title+"--------"+massage, Toast.LENGTH_SHORT).show();

            tiileTv.setText(title);
            body.setText(massage);

            Log.d("Data",""+title+"--------"+massage);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}