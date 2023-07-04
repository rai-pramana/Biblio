package com.progmob_d_kelompok_8.biblio.adminuser;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmob_d_kelompok_8.biblio.R;
import com.progmob_d_kelompok_8.biblio.database.DatabaseHelper;
import com.progmob_d_kelompok_8.biblio.tool.Session;

public class PreviewImageActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Session session;
    ImageView ivImg;
    private FloatingActionButton bt_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_image);

        session = new Session(PreviewImageActivity.this);
        db = new DatabaseHelper(PreviewImageActivity.this);

        ivImg = findViewById(R.id.iv_preview);
        bt_close = findViewById(R.id.bt_close);

        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showData();
    }

    private void showData(){
        Cursor cursor = null;

        if (session.getFrom().equals("DetailBookActivity")){
            cursor = db.getBookImage(session.getBookIdDetail());
        } else if (session.getFrom().equals("DetailUserActivity")) {
            cursor = db.getUserImage(session.getUserIdDetail());
        } else if (session.getFrom().equals("AdminProfileFragment")
                || session.getFrom().equals("UserProfileFragment")) {
            cursor = db.getUserImage(session.getUserId());
        }

        cursor.moveToFirst();

        byte[] gambarSampul = cursor.getBlob(0);

        if (gambarSampul != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(gambarSampul,0, gambarSampul.length);
            ivImg.setImageBitmap(bitmap);
        }

        cursor.close();
        db.close();


    }
}