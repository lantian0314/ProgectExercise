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
		// lp.x = 100; // ��λ��X����
		// lp.y = 100; // ��λ��Y����
		//lp.width = 800; // ���
		//lp.height = 800; // �߶�
		// lp.alpha = 0.7f; // ͸����
		// dialogWindow.setAttributes(lp);
		dialog.show();
		//�Ի���İٷֱ�����Ҫ��show��������   ������Ч
		WindowManager manager = getWindowManager();
		Display display = manager.getDefaultDisplay();// �����Ļ�Ŀ���
		WindowManager.LayoutParams LP =dialogWindow.getAttributes();// ��õ�ǰ�Ի���Ĳ���
		LP.width = (int) (display.getWidth() * 0.85);//������Ϊ��Ļ��0.85
		LP.height = (int) (display.getHeight() * 0.35);//������Ϊ��Ļ��0.35
		dialogWindow.setAttributes(LP);
		
	}
}
