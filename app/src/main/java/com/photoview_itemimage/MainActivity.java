package com.photoview_itemimage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity {

    private XListView xlistview;
    private List<Data.DataBean.ComicsBean> list;
    private MyAdapter adapter;
    private String urlPath = "http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3NzQyMjQwNjE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjEzIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6ImJpZ25veCIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjAsIiRzY3JlZW5faGVpZ2h0IjoxMjgwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTEsIiRzY3JlZW5fd2lkdGgiOjcyMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNoaW5hIE1vYmlsZSIsIiRtb2RlbCI6IlZQaG9uZSIsIiRhcHBfdmVyc2lvbiI6IjMuNi4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo2YWRkYzdhZTQ1MjUwMzY1Iiwib3JpZ2luYWxfaWQiOiJBOjZhZGRjN2FlNDUyNTAzNjUiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = msg.obj.toString();
            Gson gson = new Gson();
            Data b = gson.fromJson(result, Data.class);
            list.addAll(b.getData().getComics());
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlistview = (XListView) findViewById(R.id.xlistview);
        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);
        xlistview.setAdapter(adapter);
        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ListView 点击item传图片不需要判断
                //XlistView需要判断
                if (i > 0 && i < list.size()) {
                    Data.DataBean.ComicsBean bean = list.get(i - 1);
                    Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                    intent.putExtra("title", bean.getLabel_text());
                    intent.putExtra("image", bean.getCover_image_url());
                    startActivity(intent);
                }
            }
        });
        xlistview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlistview.stopRefresh();
                        xlistview.setRefreshTime(setData());//下啦刷新系统时间
                        xlistview.setPullLoadEnable(true);
                        xlistview.setPullRefreshEnable(true);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlistview.stopLoadMore();
                        initUrl();
                    }
                }, 1000);
            }
        });
        initUrl();
    }

    public String setData() {
        long lg = System.currentTimeMillis();
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(data);
        // xlv.setRefreshTime(setData());放在刷新的地方
        return time;
    }

    private void initUrl() {
        new Thread() {
            @Override
            public void run() {
                String result = HttpUtils.getUrlConnect(urlPath);
                Message msg = Message.obtain();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
