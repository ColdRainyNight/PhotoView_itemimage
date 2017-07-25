package com.photoview_itemimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


public class ImageActivity extends AppCompatActivity {

    private ImageView image_two;
    private TextView text_title;
    private PhotoView mPhotoView;

    // private List<Data.DataBean.ComicsBean> list = new ArrayList<>();

//    private HackyViewPager hackyView;
//    private List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        image_two = (ImageView) findViewById(R.id.imageView);
        text_title = (TextView) findViewById(R.id.text_title);

        Intent it = getIntent();
        String tit = it.getStringExtra("title");
        text_title.setText(tit);
        String image = it.getStringExtra("image");
        Glide.with(this).load(image).into(image_two);

        //int i =image_two.getId();
        // Uri u = Uri.parse(image);
        // Log.d("aaa",i+"");

       // mPhotoView = (PhotoView) findViewById(R.id.photo_view);
        // mPhotoView.setImageResource();

//        images = new ArrayList<>();
//        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4037836875,1323314356&fm=200&gp=0.jpg");
//        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4037836875,1323314356&fm=200&gp=0.jpg");
//        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4037836875,1323314356&fm=200&gp=0.jpg");
//
//        hackyView.setAdapter(new PagerAdapter() {
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//
//                PhotoView photoView = new PhotoView(container.getContext());
//                Glide.with(container.getContext()).load(images.get(position)).into(photoView);
//                container.addView(photoView);
//                return photoView;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((View)object);
//
//            }
//
//            @Override
//            public int getCount() {
//                return images.size();
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view==object;
//            }
//        });
    }
}
