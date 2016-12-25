package com.lance.lytoast;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.lance.widget.LYToast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.text).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LYToast.makeText(MainActivity.this, "测克大厦积分卡拿到手就浪费空间打开飞机打你卡上解放了扩大减肥徕卡等肌肤蜡的咖啡苏打绿咖啡就挨打了水库附近的那块放大身份试", LYToast.LENGTH_LONG);
				
			}
		});
		
		//3946714889
		//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sinaweibo://")));
		
	}

}
