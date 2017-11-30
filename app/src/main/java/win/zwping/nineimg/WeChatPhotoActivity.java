package win.zwping.nineimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import win.zwping.nineimg_lib.DisplayNineImgActivity;
import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.i.OnChildItemClickListener;
import win.zwping.nineimg_lib.i.OnEmptyItemClickListener;
import win.zwping.nineimg_lib.i.OnItemClickListener;

public class WeChatPhotoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;

    private List<ArrayList<String>> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_photo);
        recyclerView = findViewById(R.id.recycler);

        NineImg.setNineImgLoader(new NineImgInterface.WeChatNineImgLoader());
        DisplayNineImgActivity.setLoaderInterface(new NineImgInterface.WeChatDisplayNineImgLoader());
//        NineImg.setNineImgLoader(new NineImgInterface.SelectPicNineImgLoader());
//        DisplayNineImgActivity.setLoaderInterface(new NineImgInterface.SelectPicDisplayNineImgLoader());

        getList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }


    public void OnClick(View view) {
        recyclerView.scrollToPosition(0);
        getList();
        adapter.notifyDataSetChanged();
    }

    private void getList() {
        lists = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ArrayList<String> list1 = new ArrayList<>();
            for (int i1 = 0; i1 < new Random().nextInt(8) + 1; i1++) {
                if (new Random().nextBoolean()) {
                    list1.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2662232333,1061247545&fm=27&gp=0.jpg");
                } else if (new Random().nextBoolean()) {
                    list1.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
                } else {
                    list1.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big.png");
                }
            }
            lists.add(list1);
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view1 = LayoutInflater.from(WeChatPhotoActivity.this).inflate(R.layout.item_nine_img, parent, false);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("跳转");
                }
            });
            return new ViewHolder(view1);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.textView.setText(holder.getAdapterPosition() + "---");
            holder.nineImg
                    .setWidth(getResources().getDisplayMetrics().widthPixels)
                    .setList(lists.get(position))
                    .setClickAutoDisplayNineImg(true, WeChatPhotoActivity.this)
                    .setAutoSize(true)
                    .setMaxItem(9)
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(final RecyclerView.ViewHolder viewHolder, final ArrayList<String> data) {
                            holder.nineImg.displayNineImg(data, viewHolder.getAdapterPosition());
                        }
                    })
                    .setOnEmptyItemClickListener(new OnEmptyItemClickListener() {
                        @Override
                        public void onEmptyItemClick() {
                            System.out.println("空点击");
                        }
                    })
                    .init();
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private NineImg nineImg;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            nineImg = itemView.findViewById(R.id.nine_img);
            textView = itemView.findViewById(R.id.text);
        }

    }
}
