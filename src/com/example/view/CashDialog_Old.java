package com.example.view;

import java.io.IOException;
import java.nio.channels.Selector;

import android.R;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class CashDialog_Old extends Dialog {
	private static final int ID_title = 1;
	private static final int ID_content = 2;
	private static final int ID_Delete = 3;
	private static final int ID_Query = 4;
	private Context mContext;
	private RelativeLayout relativeLayout;
	private TextView Title;
	private TextView content;
	private Button DeleteButton;
	private Button QueryButton;
	private LinearLayout linearLayout;

	public CashDialog_Old(Context context) {
		super(context);
		mContext = context;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint({ "ResourceAsColor", "InlinedApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		relativeLayout = new RelativeLayout(mContext);
		relativeLayout.setBackgroundColor(R.color.background_light);
		Title = new TextView(mContext);
		Title.setText("ɾ��");
		Title.setId(ID_title);
		Title.setTextColor(Color.BLACK);// �����������ɫ
		Title.setTextSize(25);
		RelativeLayout.LayoutParams LP1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		LP1.setMargins(50, 0, 0, 30);// ���ñ������߾� �� �� �� ��
		LP1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		LP1.addRule(RelativeLayout.ALIGN_LEFT);
		relativeLayout.addView(Title, LP1);

		content = new TextView(mContext);
		content.setText("ɾ������Ϣ�����ܻظ�����ȷ��Ҫɾ����");
		content.setId(ID_content);
		content.setTextColor(Color.BLACK);// �����������ɫ
		content.setTextSize(18);
		RelativeLayout.LayoutParams LP2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		LP2.setMargins(30, 0, 0, 30);// �������ݵ���߾� �� �� �� ��
		LP2.addRule(RelativeLayout.BELOW, ID_title);
		LP2.addRule(RelativeLayout.ALIGN_RIGHT, ID_title);
		relativeLayout.addView(content, LP2);

		// �������Բ��ֶ���ɾ����ȷ�ϰ�ťռ��ͬ�ı���
		linearLayout = new LinearLayout(mContext);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams LP3 = new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		LP3.setMargins(10, 0, 10, 0);
		// ����ɾ����ť���������
		DeleteButton = new Button(mContext);
		DeleteButton.setText("ɾ��");
		DeleteButton.setId(ID_Delete);
		DeleteButton.setTextColor(Color.BLACK);// �����������ɫ
		DeleteButton.setTextSize(18);
		DeleteButton.setBackgroundDrawable(newSelector(mContext,
				R.color.holo_red_light, R.color.white,
				R.color.holo_green_light, R.color.holo_blue_light));
		DeleteButton.setEnabled(false);
		// DeleteButton.setTextColor(createColorStateList(R.color.black,
		// R.color.white, R.color.white));
		DeleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "ɾ��", 0).show();
			}
		});
		linearLayout.addView(DeleteButton, LP3);

		QueryButton = new Button(mContext);
		QueryButton.setText("ȷ��");
		QueryButton.setId(ID_Query);
		QueryButton.setTextColor(Color.BLACK);// �����������ɫ
		QueryButton.setTextSize(18);
		QueryButton.setBackgroundDrawable(newSelector(mContext,
				R.color.holo_red_light, R.color.white,
				R.color.holo_green_light, R.color.holo_blue_light));
		QueryButton.setEnabled(false);
		QueryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "ȷ��", 0).show();
			}
		});
		LinearLayout.LayoutParams LP4 = new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		LP4.setMargins(10, 0, 10, 0);
		linearLayout.addView(QueryButton, LP4);	
		// ������Բ��ֵ����� ����������ť���������Ե��·�
		RelativeLayout.LayoutParams LP5 = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LP5.addRule(RelativeLayout.BELOW, ID_content);
		relativeLayout.addView(linearLayout, LP5);

		setContentView(relativeLayout);
	}

	public static StateListDrawable newSelector(Context context, int idNormal,
			int idPressed, int idFocused, int idUnable) {
		StateListDrawable drawable = new StateListDrawable();
		Drawable normal = idNormal == -1 ? null : context.getResources()
				.getDrawable(idNormal);
		Drawable pressed = idPressed == -1 ? null : context.getResources()
				.getDrawable(idPressed);
		Drawable focused = idFocused == -1 ? null : context.getResources()
				.getDrawable(idFocused);
		Drawable unable = idUnable == -1 ? null : context.getResources()
				.getDrawable(idUnable);

		drawable.addState(new int[] { android.R.attr.state_enabled }, normal);
		drawable.addState(new int[] { android.R.attr.state_pressed,
				android.R.attr.state_enabled }, pressed);
		drawable.addState(new int[] { android.R.attr.state_focused,
				android.R.attr.state_enabled }, focused);
		drawable.addState(new int[] { android.R.attr.state_focused }, focused);
		drawable.addState(new int[] { android.R.attr.state_window_focused },
				unable);
		drawable.addState(new int[] {}, normal);
		return drawable;
	}

	/*
	 * public static ColorStateList createColorStateList(int normal, int
	 * pressed, int focused){ int[] colors = new int[] { pressed, focused,
	 * normal, focused, normal }; int[][] states = new int[5][]; states[0] = new
	 * int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
	 * states[1] = new int[] { android.R.attr.state_enabled,
	 * android.R.attr.state_focused }; states[2] = new int[] {
	 * android.R.attr.state_enabled }; states[3] = new int[] {
	 * android.R.attr.state_focused }; states[4] = new int[] {}; ColorStateList
	 * colorList = new ColorStateList(states, colors); return colorList; }
	 */

}
