package com.example.view;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CashPager extends Activity {

	private RelativeLayout relativeLayout;
	private Button button;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		relativeLayout = new RelativeLayout(this);

		button = new Button(getApplicationContext());
		button.setText("窗体展示");
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new CashDialog(getApplicationContext()).showPopWindow(
						getApplicationContext(), "删除",
						"删除的信息将不能恢复,你确定要删除吗?删除的信息将不能恢复,你确定要删除吗?", "删除", "取消");
			}
		});
		RelativeLayout.LayoutParams LP1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		LP1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		LP1.addRule(RelativeLayout.CENTER_HORIZONTAL);
		relativeLayout.setBackgroundColor(Color.GRAY);
		relativeLayout.addView(button, LP1);
		setContentView(relativeLayout);

	}
}
