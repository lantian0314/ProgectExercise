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
				new CashDialog(getApplicationContext()).showPopWindow(getApplicationContext());
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
		
		
		/*Dialog dialog = new CashDialog(this);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setBackgroundDrawableResource(R.color.white);
		
		dialog.show();
		//对话框的百分比设置要在show方法后面   否则无效
		WindowManager manager = getWindowManager();
		Display display = manager.getDefaultDisplay();// 获得屏幕的宽、高
		WindowManager.LayoutParams LP =dialogWindow.getAttributes();// 获得当前对话框的参数
		LP.width = (int) (display.getWidth() * 0.85);//宽设置为屏幕的0.85
		LP.height = (int) (display.getHeight() * 0.35);//高设置为屏幕的0.35
		dialogWindow.setAttributes(LP);*/
		
	}
}
