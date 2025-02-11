package com.example.pm01app.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pm01app.R;

public class Menu extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);


        ImageButton registros,registrar;

        registrar = (ImageButton)findViewById(R.id.ibtnRegistrar);
        registros = (ImageButton)findViewById(R.id.ibtnRegistros);

        registrar.setOnClickListener(v -> {
            Intent intent = new Intent(this,ActivityPrincipal.class);
            this.startActivity(intent);
        });

        registros.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}