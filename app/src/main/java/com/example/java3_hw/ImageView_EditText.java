package com.example.java3_hw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ImageView_EditText extends AppCompatActivity {
    public static final int RESULT_LOAD_IMAGE = 0;
    private static final int SELECT_IMAGE = 101;
    public static String KEY = "key";
    private ImageView imageView;
    String imgStr;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view__edit_text);
        init();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });

        Intent intent = new Intent();
        if (intent != null) {
            String text = intent.getStringExtra(KEY);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            imgStr = data.getData().toString();
            Glide.with(this).load(imgStr).into(imageView);
        }
    }

    private void init() {
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(KEY, text2);
                intent.putExtra("img", imgStr);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}


