package com.photoview_itemimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


public class ImageActivity extends AppCompatActivity {

   // private ImageView image_two;
    private TextView text_title;
    private PhotoView mPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

      //  image_two = (ImageView) findViewById(R.id.imageView);
        text_title = (TextView) findViewById(R.id.text_title);

        Intent it = getIntent();
        String tit = it.getStringExtra("title");
        text_title.setText(tit);
        String image = it.getStringExtra("image");
       // Glide.with(this).load(image).into(image_two);

        mPhotoView = (PhotoView) findViewById(R.id.photo_view);
        Glide.with(this).load(image).into(mPhotoView);

    }
}
