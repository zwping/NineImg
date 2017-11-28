package win.zwping.nineimg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import win.zwping.nineimg_lib.DisplayNineImgActivity;
import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.i.DisplayNineImgLoaderInterface;
import win.zwping.nineimg_lib.i.NineImgLoaderInterface;
import win.zwping.nineimg_lib.i.OnEmptyItemClickListener;
import win.zwping.nineimg_lib.i.OnItemClickListener;
import win.zwping.plib.frame.review.PImageView;

public class MainActivity extends AppCompatActivity {

    private NineImg nineImg;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            View decoderView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decoderView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            //or ?
////            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NineImg.setNineImgLoader(new NineImgLoaderInterface() {
            @Override
            public void displayImage(Context context, NineImg.ViewHolder holder, String url) {
                Glide.with(context).load(url).asBitmap().centerCrop().into((ImageView) holder.getView(R.id.nine_img_img));
            }

            @Override
            public View createView(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nine_img_layout, parent, false);
            }
        });

        DisplayNineImgActivity.setLoaderInterface(new DisplayNineImgLoaderInterface() {
            @Override
            public void displayImage(Context context, String url, ImageView imageView) {
                Glide.with(context).load(url).asBitmap().centerCrop().into(imageView);
            }

            @Override
            public ImageView createView(Context context) {
                return new ImageView(context);
            }

            @Override
            public void saveReturn(String imgUrl) {
                Toast.makeText(MainActivity.this, "保存成功" + imgUrl, Toast.LENGTH_SHORT).show();
            }
        });

        nineImg = findViewById(R.id.nine_img);
        recyclerView = findViewById(R.id.recycler);

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2662232333,1061247545&fm=27&gp=0.jpg");
        }
//        nineImg.setAutoSize(true);
//        nineImg.setList(list);

//        lists.add(list);
        for (int i = 0; i < 30; i++) {
            ArrayList<String> list1 = new ArrayList<>();
            for (int i1 = 0; i1 < (new Random().nextInt(8) + 1); i1++) {
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
//        NineImg.setNineImgLoader(new NineImgInterface.NineImgLoader());
//        DisplayNineImgActivity.setLoaderInterface(new NineImgInterface.DisplayNineImgLoader());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }

    public void OnClick(View view) {
        recyclerView.scrollToPosition(0);
        lists = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ArrayList<String> list1 = new ArrayList<>();
            for (int i1 = 0; i1 < new Random().nextInt(8) + 1; i1++) {
                list1.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2662232333,1061247545&fm=27&gp=0.jpg");
            }
            lists.add(list1);
        }
        adapter.notifyDataSetChanged();

//        ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < new Random().nextInt(2) + 1; i++) {
//            list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2662232333,1061247545&fm=27&gp=0.jpg");
//        }
//        nineImg.setList(list);

    }

    private List<ArrayList<String>> lists = new ArrayList<>();

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_nine_img, parent, false);
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
            holder.nineImg.setList(getResources().getDisplayMetrics().widthPixels, lists.get(position))
                    .setAutoDisplayNineImg(true, MainActivity.this)
                    .setAutoSize(true)
//                    .setMaxItem(9)
                    .setAddPlusItem(true, 6, new NineImg.OnPlusItemLoadListener() {
                        @Override
                        public void onPlusItemLoad(Context context, RecyclerView.ViewHolder viewHolder) {
                            viewHolder.itemView.findViewById(R.id.exit).setVisibility(View.GONE);
                            ImageView plusItem = viewHolder.itemView.findViewById(R.id.nine_img_img);
                            plusItem.setImageResource(R.mipmap.error_picture);
                            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "111111111111111111", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
//                    .setOnItemClickListener(new OnItemClickListener() {
//                        @Override
//                        public void onItemClick(final RecyclerView.ViewHolder viewHolder, final ArrayList<String> data) {
//                            ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
//                            viewGroup.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    holder.nineImg.remove(viewHolder.getAdapterPosition());
//                                }
//                            });
//                            viewGroup.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    holder.nineImg.displayNineImg(data, viewHolder.getAdapterPosition());
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onEmptyItemClick() {
//                            System.out.println("点空布局了");
//                        }
//                    })
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

    int num;


}
