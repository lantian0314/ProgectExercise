package com.example.view;

import android.R;
import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CashDialog extends FrameLayout {

	private static final int ID_title = 1;
	private static final int ID_content = 2;
	private static final int ID_Cancel = 3;
	private static final int ID_Delete = 4;
	private static final int ID_titleLineLayout = 5;
	private static final int ID_buttonLineLayout = 6;
	private static final int ID_buttonUPLayout = 7;
	private static final int ID_buttonCenterLineLayout = 8;
	private static final int ID_titleLayout = 9;
	private static final int ID_contentView = 10;
	private static final int ID_deleteView = 11;
	private static final int ID_cancelView = 12;
	private static final int ID_buttonView = 13;
	private Context mContext;
	private RelativeLayout relativeLayout;
	private TextView Title;
	private TextView content;
	private Button CancelButton;
	private Button DeleteButton;
	private WindowManager mWindowManager = null;
	private RelativeLayout mView = null;
	private Boolean isShown = false;
	private int buttonTextSize = 16;
	private int conentTextSize = 18;
	private int titleTextSize = 20;
	private Display display;// �����Ļ�ķֱ���
	private RelativeLayout buttonView;
	private int titleWidth = 0;
	private int buttonHeight = 0;

	public CashDialog(Context context) {
		super(context);
		mContext = context;
	}

	public void showPopWindow(Context context) {
		isShown = true;
		WindowManager.LayoutParams params = initLayout(context);
		mView = setView(context);
		mWindowManager.addView(mView, params);

	}

	private WindowManager.LayoutParams initLayout(Context context) {
		// ��ȡӦ�õ�Context
		mContext = context.getApplicationContext();
		// ��ȡWindowManager
		mWindowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		display = mWindowManager.getDefaultDisplay();// �����Ļ�ķֱ���
		titleWidth = (int) (display.getWidth() * 0.85);
		buttonHeight = (int) (display.getHeight() * 0.09);

		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				(int) (display.getWidth() * 0.85), LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
				| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		params.alpha = 1F;
		params.format = 1;
		params.verticalMargin = 0.05F;
		params.gravity = android.view.Gravity.CENTER;
		return params;
	}

	@SuppressLint("ResourceAsColor")
	private RelativeLayout setView(Context context) {
		relativeLayout = new RelativeLayout(mContext);
		relativeLayout.setBackgroundColor(Color.rgb(232, 232, 232));// ����ɫ
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		relativeLayout.setLayoutParams(lp);
		// ����Ŀ��
		relativeLayout.addView(titleLayout());

		// �����·��ĺ���
		relativeLayout.addView(titleBlowLine());

		// ���ݵĿ��
		relativeLayout.addView(contentLayout());

		relativeLayout.addView(buttonLayout());

		// ��ť�Ϸ��ĺ���
		relativeLayout.addView(buttonUPLine());

		// ��ť�м��һ������
		relativeLayout.addView(buttonCenterLine());

		return relativeLayout;
	}

	@SuppressLint("NewApi") private RelativeLayout buttonLayout() {
		RelativeLayout buttonView = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, buttonHeight);
		buttonView.setId(ID_buttonView);
		buttonParams.addRule(RelativeLayout.BELOW, ID_contentView);
		buttonView.setLayoutParams(buttonParams);

		DeleteButton = new Button(mContext);
		DeleteButton.setText("ɾ��");// ���ð�ť���ı�
		DeleteButton.setId(ID_Delete);// ���ð�ť��ID
		DeleteButton.setTextColor(0xff000000);// �����������ɫ
		DeleteButton.setTextSize(buttonTextSize);// ���ð�ť����Ĵ�С
		// ����ɾ����ť��ѡ����--ע��ѡ������Χ�Ĵ�С ��Χ�ں���
		DeleteButton.setBackground(newSelector(mContext,
				Color.rgb(232, 232, 232), Color.rgb(213, 213, 213),
				Color.rgb(213, 213, 213)));
		// QueryButton.setEnabled(false);
		// ����ɾ����ť�ļ����¼�
		DeleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "ɾ���ɹ�", 0).show();
				hidePopupWindow();
			}
		});
		RelativeLayout.LayoutParams LP4 = new RelativeLayout.LayoutParams(
				titleWidth / 2, buttonHeight);
		LP4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		DeleteButton.setLayoutParams(LP4);
		buttonView.addView(DeleteButton);

		// ����ȡ����ť���������
		CancelButton = new Button(mContext);
		CancelButton.setText("ȡ��");// ȡ����ť���ı�
		CancelButton.setId(ID_Cancel);// ȡ����ť��ID
		CancelButton.setTextColor(0xff000000);// �����������ɫ
		CancelButton.setTextSize(buttonTextSize);// ȡ����ť����Ĵ�С
		// ����ȡ����ť��ѡ����--ע��ѡ������Χ�Ĵ�С ��Χ�ں���
		CancelButton.setBackground(newSelector(mContext,
				Color.rgb(232, 232, 232), Color.rgb(213, 213, 213),
				Color.rgb(213, 213, 213)));
		// DeleteButton.setEnabled(false);
		// ����ȡ����ť�ļ����¼�
		CancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "ȡ��", 0).show();
				hidePopupWindow();
			}
		});
		RelativeLayout.LayoutParams LP5 = new RelativeLayout.LayoutParams(
				titleWidth / 2, buttonHeight);
		LP5.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		CancelButton.setLayoutParams(LP5);
		buttonView.addView(CancelButton);
		return buttonView;
	}

	/**
	 * �ڱ����·���һ����
	 */
	private RelativeLayout titleBlowLine() {
		RelativeLayout lineLayout = new RelativeLayout(mContext);
		lineLayout.setId(ID_titleLineLayout);
		RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 1);
		lineLayout.setBackgroundColor(Color.rgb(104, 143, 172));// ������ɫ
		lineParams.addRule(RelativeLayout.BELOW, ID_titleLayout);
		lineLayout.setLayoutParams(lineParams);
		return lineLayout;
	}

	/**
	 * �ڰ�ť�Ϸ���һ������
	 */
	private RelativeLayout buttonUPLine() {
		RelativeLayout lineLayout = new RelativeLayout(mContext);
		lineLayout.setId(ID_buttonLineLayout);
		RelativeLayout.LayoutParams buttonlineParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 1);
		lineLayout.setBackgroundColor(Color.rgb(213, 213, 213));// ��ɫ
		buttonlineParams.addRule(RelativeLayout.ABOVE, ID_buttonView);
		lineLayout.setLayoutParams(buttonlineParams);
		return lineLayout;
	}

	/**
	 * ��������ť�м��һ������
	 */
	private RelativeLayout buttonCenterLine() {
		RelativeLayout lineLayout = new RelativeLayout(mContext);
		lineLayout.setId(ID_buttonCenterLineLayout);
		RelativeLayout.LayoutParams buttonlineParams = new RelativeLayout.LayoutParams(
				1, buttonHeight);
		lineLayout.setBackgroundColor(Color.rgb(213, 213, 213));
		buttonlineParams.addRule(RelativeLayout.CENTER_HORIZONTAL,
				ID_buttonView);
		buttonlineParams.addRule(RelativeLayout.BELOW, ID_buttonLineLayout);
		lineLayout.setLayoutParams(buttonlineParams);
		return lineLayout;
	}

	/**
	 * �����ı��Ĳ���
	 */
	private RelativeLayout contentLayout() {
		RelativeLayout contentView = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		contentParams.setMargins((int) (titleWidth * 0.06),
				(int) (buttonHeight * 0.25), (int) (titleWidth * 0.06),
				(int) (buttonHeight * 0.5));
		contentView.setId(ID_contentView);
		contentParams.addRule(RelativeLayout.BELOW, ID_titleLineLayout);
		contentParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		contentView.setLayoutParams(contentParams);
		contentView.setBackgroundColor(Color.rgb(232, 232, 232));// ���ݵı���ɫ

		content = new TextView(mContext);
		content.setText("ɾ������Ϣ�����ָܻ�����ȷ��Ҫɾ����?ɾ������Ϣ�����ָܻ�����ȷ��Ҫɾ����?");// ����content�е������ı�
		content.setId(ID_content);// ����content��ID
		content.setTextColor(Color.rgb(36, 36, 36));// �����������ɫ
		content.setTextSize(conentTextSize);// ��������Ĵ�С
		RelativeLayout.LayoutParams LP2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// �����ڱ�����·������Һͱ�����ұ߶���
		// LP2.addRule(RelativeLayout.BELOW, ID_titleLineLayout);
		// LP2.addRule(RelativeLayout.ALIGN_RIGHT, ID_title);
		content.setLayoutParams(LP2);
		contentView.addView(content);
		return contentView;
	}

	/**
	 * ����Ĳ���
	 */
	private RelativeLayout titleLayout() {
		int titleHeight = 0;
		titleHeight = (int) (display.getHeight() * 0.08);
		RelativeLayout titleLayout = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
				titleWidth, titleHeight);
		titleParams.leftMargin = (int) (titleWidth * 0.1);
		titleLayout.setId(ID_titleLayout);
		titleLayout.setLayoutParams(titleParams);

		Title = new TextView(mContext);
		Title.setText("ɾ��");// ���ñ�����ı�
		Title.setId(ID_title);// ���ñ����ID
		Title.setTextColor(Color.rgb(68, 152, 214));// �����������ɫ
		Title.setTextSize(titleTextSize);// ���ñ�������Ĵ�С
		RelativeLayout.LayoutParams LP1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// ����λ�ڲ��ֵ����Ϸ�
		// LP1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		LP1.addRule(RelativeLayout.ALIGN_LEFT);
		LP1.addRule(RelativeLayout.CENTER_VERTICAL, ID_titleLayout);
		Title.setLayoutParams(LP1);
		titleLayout.addView(Title);
		return titleLayout;
	}

	/**
	 * ����д��ѡ����
	 * 
	 * @param context
	 *            ������
	 * @param idNormal
	 *            ������ʾ��ID
	 * @param idPressed
	 *            ����ȥ��ʾID
	 * @param idFocused
	 *            �н���ʱID
	 * @return
	 */
	public static StateListDrawable newSelector(Context context, int idNormal,
			int idPressed, int idFocused) {
		StateListDrawable drawable = new StateListDrawable();
		Drawable pressed = new ColorDrawable(idPressed);
		Drawable focused = new ColorDrawable(idPressed);
		Drawable normal = new ColorDrawable(idNormal);
		drawable.addState(new int[] { android.R.attr.state_pressed,
				android.R.attr.state_enabled }, pressed);
		drawable.addState(new int[] { android.R.attr.state_focused,
				android.R.attr.state_enabled }, focused);
		drawable.addState(new int[] { android.R.attr.state_enabled }, normal);
		drawable.addState(new int[] {}, normal);
		return drawable;
	}

	/**
	 * ���ش���ķ���
	 */
	private void hidePopupWindow() {
		if (isShown && null != mView) {
			mWindowManager.removeView(mView);
			isShown = false;
		}
	}
}
