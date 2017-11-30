package win.zwping.nineimg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void OnClick1(View view) {
        startActivity(new Intent(this, WeChatPhotoActivity.class));
    }

    public void OnClick2(View view) {
        startActivity(new Intent(this, SelectPhotoActivity.class));
    }
}
