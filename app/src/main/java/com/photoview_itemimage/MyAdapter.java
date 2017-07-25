package com.photoview_itemimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    private List<Data.DataBean.ComicsBean> lsit;
    private Context mContext;
    ImageLoader imageloader;
    DisplayImageOptions options;

    public MyAdapter(Context context, List<Data.DataBean.ComicsBean> lsit) {
        mContext = context;
        this.lsit = lsit;
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);
        //将configuration配置到imageloader中
        imageloader = ImageLoader.getInstance();
        imageloader.init(configuration);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

    }

    @Override
    public int getCount() {
        return lsit.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item, null);
            holder = new ViewHolder();
            holder.image_im = (ImageView) view.findViewById(R.id.image_im);
            holder.txt_title = (TextView) view.findViewById(R.id.txt_title);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Data.DataBean.ComicsBean bean = lsit.get(i);
        holder.txt_title.setText(bean.getLabel_text());

        // 图片加载不出来给个默认的图片
        holder.image_im.setImageResource(R.mipmap.ic_launcher);
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(mContext);
        ImageLoader image = ImageLoader.getInstance();
        image.init(configuration);
        image.displayImage(bean.getCover_image_url(), holder.image_im);
        return view;
    }

    class ViewHolder {
        ImageView image_im;
        TextView txt_title;
    }
}
