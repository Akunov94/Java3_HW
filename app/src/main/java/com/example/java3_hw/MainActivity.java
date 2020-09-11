package com.example.java3_hw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button btnOpen;
    private TextView textView;
    private ImageView imageView;
    String photo;
    public static int REQUEST_CODE = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        init();
    }

    private void init() {
        btnOpen = findViewById(R.id.btn_activity2);
        textView = findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageView_EditText.class);
                intent.putExtra(ImageView_EditText.KEY, "Activity2");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            String text = data.getStringExtra(ImageView_EditText.KEY);
            photo = data.getStringExtra("img");
            textView.setText(text);
            Glide.with(this).load(photo).into(imageView);
        }
    }

    public void sendGmail(View view) {
        String message = textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plane");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra("photo", photo);
        Intent chosenIntent = Intent.createChooser(intent, "Выберите мессенджер");
        startActivity(chosenIntent);
    }
}

