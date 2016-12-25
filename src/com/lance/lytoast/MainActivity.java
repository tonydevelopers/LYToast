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
				LYToast.makeText(MainActivity.this, "��˴��û��ֿ��õ��־��˷ѿռ�򿪷ɻ����㿨�Ͻ������������⿨�ȼ������Ŀ����մ��̿��ȾͰ�����ˮ�⸽�����ǿ�Ŵ������", LYToast.LENGTH_LONG);
				
			}
		});
		
		//3946714889
		//startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sinaweibo://")));
		
	}

}
