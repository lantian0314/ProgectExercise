package com.example.view;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;

public class CashPager_Old extends Activity {

	private RelativeLayout relativeLayout;

	@SuppressWarnings("deprecation")
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		relativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams LP1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		relativeLayout.setBackgroundColor(R.color.background_light);
		setContentView(relativeLayout);
		Dialog dialog = new CashDialog_Old(this);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setBackgroundDrawableResource(R.color.white);
		//WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		// dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		// lp.x = 100; // 新位置X坐标
		// lp.y = 100; // 新位置Y坐标
		//lp.width = 800; // 宽度
		//lp.height = 800; // 高度
		// lp.alpha = 0.7f; // 透明度
		// dialogWindow.setAttributes(lp);
		dialog.show();
		//对话框的百分比设置要在show方法后面   否则无效
		WindowManager manager = getWindowManager();
		Display display = manager.getDefaultDisplay();// 获得屏幕的宽、高
		WindowManager.LayoutParams LP =dialogWindow.getAttributes();// 获得当前对话框的参数
		LP.width = (int) (display.getWidth() * 0.85);//宽设置为屏幕的0.85
		LP.height = (int) (display.getHeight() * 0.35);//高设置为屏幕的0.35
		dialogWindow.setAttributes(LP);
		
	}
}
