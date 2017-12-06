package win.zwping.nineimg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import win.zwping.nineimg_lib.NineImg;

public class MainActivity extends AppCompatActivity {

    private NineImg nineImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nineImg = findViewById(R.id.nine);


        ArrayList<String> list = new ArrayList<>();
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        nineImg.setNineImgLoader(new NineImgInterface.WeChatNineImgLoader()).setList(list).init();
    }


    public void OnClick1(View view) {
        startActivity(new Intent(this, WeChatPhotoActivity.class));
    }

    public void OnClick2(View view) {
        startActivity(new Intent(this, SelectPhotoActivity.class));
    }
}
