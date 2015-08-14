package com.example.view;

import android.R;
import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.util.DisplayMetrics;
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
	private static final int ID_buttonCenterLineLayout = 7;
	private static final int ID_titleLayout = 8;
	private static final int ID_contentView = 9;
	private static final int ID_buttonView = 10;

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
	private int[] size;

	private String String_title = "";
	private String String_content = "";
	private String String_sure = "";
	private String String_cancle = "";

	public CashDialog(Context context) {
		super(context);
		mContext = context;
	}

	/**
	 * 
	 * @param context
	 *            当前上下文
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param sure
	 *            确认文字
	 * @param cancle
	 *            取消文字
	 */
	public void showPopWindow(Context context, String title, String content,
			String sure, String cancle) {
		isShown = true;
		this.String_title = title;
		this.String_content = content;
		this.String_sure = sure;
		this.String_cancle = cancle;
		WindowManager.LayoutParams params = initLayout(context);// 初始化参数设置
		mView = setView(context);// 获得整个页面布局的框架
		setClickListener();
		mWindowManager.addView(mView, params);

	}

	/**
	 * SystemAlertWindow 参数的初始化
	 * 
	 * @param context
	 * @return
	 */
	private WindowManager.LayoutParams initLayout(Context context) {
		// 获取应用的Context
		mContext = context.getApplicationContext();
		// 获取WindowManager
		mWindowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		// display = mWindowManager.getDefaultDisplay();// 获得屏幕的分辨率
		// 获得页面尺寸的大小
		size = getPageSize();
		// buttonHeight = (int) (display.getHeight() * 0.09);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				size[0], LayoutParams.WRAP_CONTENT,
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
		relativeLayout.setBackgroundColor(Color.rgb(232, 232, 232));// 背景色
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		relativeLayout.setLayoutParams(lp);

		// 标题的框架
		relativeLayout.addView(titleLayout());

		// 标题下方的横线
		relativeLayout.addView(titleBlowLine());

		// 内容的框架
		relativeLayout.addView(contentLayout());

		// 按钮的框架
		relativeLayout.addView(buttonLayout());

		// 按钮上方的横线
		relativeLayout.addView(buttonUPLine());

		// 按钮中间的一条竖线
		relativeLayout.addView(buttonCenterLine());

		return relativeLayout;
	}

	/**
	 * 对话框最下方的按钮布局
	 * 
	 * @return 返回相对布局
	 */
	@SuppressLint("NewApi")
	private RelativeLayout buttonLayout() {
		RelativeLayout buttonView = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				(int) (size[0] * 0.17));
		buttonView.setId(ID_buttonView);
		buttonParams.addRule(RelativeLayout.BELOW, ID_contentView);
		buttonView.setLayoutParams(buttonParams);
		// 下方是删除按钮的设置
		DeleteButton = new Button(mContext);
		DeleteButton.setText(String_sure);// 设置按钮的文本
		DeleteButton.setId(ID_Delete);// 设置按钮的ID
		DeleteButton.setTextColor(0xff000000);// 设置字体的颜色
		DeleteButton.setTextSize(buttonTextSize);// 设置按钮字体的大小
		// 设置删除按钮的选择器--注意选择器范围的大小 大范围在后面
		DeleteButton.setBackgroundDrawable(newSelector(mContext,
				Color.rgb(232, 232, 232), Color.rgb(213, 213, 213),
				Color.rgb(213, 213, 213)));
		// QueryButton.setEnabled(false);

		RelativeLayout.LayoutParams LP4 = new RelativeLayout.LayoutParams(
				size[0] / 2, (int) (size[0] * 0.17));
		LP4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		DeleteButton.setLayoutParams(LP4);
		buttonView.addView(DeleteButton);

		// 设置取消按钮的相关属性
		CancelButton = new Button(mContext);
		CancelButton.setText(String_cancle);// 取消按钮的文本
		CancelButton.setId(ID_Cancel);// 取消按钮的ID
		CancelButton.setTextColor(0xff000000);// 设置字体的颜色
		CancelButton.setTextSize(buttonTextSize);// 取消按钮字体的大小
		// 设置取消按钮的选择器--注意选择器范围的大小 大范围在后面

		CancelButton.setBackgroundDrawable(newSelector(mContext,
				Color.rgb(232, 232, 232), Color.rgb(213, 213, 213),
				Color.rgb(213, 213, 213)));

		// DeleteButton.setEnabled(false);
		RelativeLayout.LayoutParams LP5 = new RelativeLayout.LayoutParams(
				size[0] / 2, (int) (size[0] * 0.17));
		LP5.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		CancelButton.setLayoutParams(LP5);
		buttonView.addView(CancelButton);
		return buttonView;
	}

	private void setClickListener() {
		// 设置删除按钮的监听事件
		DeleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "删除成功", 0).show();
				hidePopupWindow();
			}
		});
		// 设置取消按钮的监听事件
		CancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "取消", 0).show();
				hidePopupWindow();
			}
		});
	}

	/**
	 * 在标题下方的一条线
	 */
	private RelativeLayout titleBlowLine() {
		RelativeLayout lineLayout = new RelativeLayout(mContext);
		lineLayout.setId(ID_titleLineLayout);
		RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 1);
		lineLayout.setBackgroundColor(Color.rgb(104, 143, 172));// 线条颜色
		lineParams.addRule(RelativeLayout.BELOW, ID_titleLayout);
		lineLayout.setLayoutParams(lineParams);
		return lineLayout;
	}

	/**
	 * 在按钮上方的一条横线
	 */
	private RelativeLayout buttonUPLine() {
		RelativeLayout lineLayout = new RelativeLayout(mContext);
		lineLayout.setId(ID_buttonLineLayout);
		RelativeLayout.LayoutParams buttonlineParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 1);
		lineLayout.setBackgroundColor(Color.rgb(213, 213, 213));// 灰色
		buttonlineParams.addRule(RelativeLayout.ABOVE, ID_buttonView);
		lineLayout.setLayoutParams(buttonlineParams);
		return lineLayout;
	}

	/**
	 * 在两个按钮中间的一条竖线
	 */
	private RelativeLayout buttonCenterLine() {
		RelativeLayout lineLayout = new RelativeLayout(mContext);
		lineLayout.setId(ID_buttonCenterLineLayout);
		RelativeLayout.LayoutParams buttonlineParams = new RelativeLayout.LayoutParams(
				1, (int) (size[0] * 0.17));
		lineLayout.setBackgroundColor(Color.rgb(213, 213, 213));
		buttonlineParams.addRule(RelativeLayout.CENTER_HORIZONTAL,
				ID_buttonView);
		buttonlineParams.addRule(RelativeLayout.BELOW, ID_buttonLineLayout);
		lineLayout.setLayoutParams(buttonlineParams);
		return lineLayout;
	}

	/**
	 * 内容文本的布局
	 */
	private RelativeLayout contentLayout() {
		RelativeLayout contentView = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams contentParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		contentView.setId(ID_contentView);
		contentParams.addRule(RelativeLayout.BELOW, ID_titleLineLayout);
		contentParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		contentView.setLayoutParams(contentParams);
		contentView.setBackgroundColor(Color.rgb(232, 232, 232));// 内容的背景色

		content = new TextView(mContext);
		content.setText(String_content);// 设置content中的内容文本
		content.setId(ID_content);// 设置content的ID
		content.setTextColor(Color.rgb(36, 36, 36));// 设置字体的颜色
		content.setTextSize(conentTextSize);// 设置字体的大小

		content.setPadding((int) (size[0] * 0.06), (int) (size[0] * 0.04),
				(int) (size[0] * 0.06), (int) (size[0] * 0.08));
		RelativeLayout.LayoutParams LP2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		content.setLayoutParams(LP2);
		contentView.addView(content);
		return contentView;
	}

	/**
	 * 标题的布局
	 */
	private RelativeLayout titleLayout() {
		int titleHeight = 0;
		titleHeight = (int) (size[0] * 0.17);// size[0]是屏幕分辨率的宽
		if (!isScreenVertical(mContext)) {// 屏幕垂直
			titleHeight = (int) (size[0] * 0.13);
		}
		RelativeLayout titleLayout = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
				size[0], titleHeight);
		titleParams.leftMargin = (int) (size[0] * 0.06);
		titleLayout.setId(ID_titleLayout);
		titleLayout.setLayoutParams(titleParams);

		Title = new TextView(mContext);
		Title.setText(String_title);// 设置标题的文本
		Title.setId(ID_title);// 设置标题的ID
		Title.setTextColor(Color.rgb(68, 152, 214));// 设置字体的颜色
		Title.setTextSize(titleTextSize);// 设置标题字体的大小
		RelativeLayout.LayoutParams LP1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		// 标题位于布局的左上方
		// LP1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		LP1.addRule(RelativeLayout.ALIGN_LEFT);
		LP1.addRule(RelativeLayout.CENTER_VERTICAL, ID_titleLayout);
		Title.setLayoutParams(LP1);
		titleLayout.addView(Title);
		return titleLayout;
	}

	/**
	 * 代码写的选择器
	 * 
	 * @param context
	 *            上下文
	 * @param idNormal
	 *            正常显示的ID
	 * @param idPressed
	 *            按下去显示ID
	 * @param idFocused
	 *            有焦点时ID
	 * @return
	 */
	public static StateListDrawable newSelector(Context context, int idNormal,
			int idPressed, int idFocused) {
		StateListDrawable drawable = new StateListDrawable();
		Drawable pressed = new ColorDrawable(idPressed);
		Drawable focused = new ColorDrawable(idFocused);
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
	 * 隐藏窗体的方法
	 */
	private void hidePopupWindow() {
		if (isShown && null != mView) {
			mWindowManager.removeView(mView);
			isShown = false;
		}
	}

	/**
	 * 获得屏幕的分辨率
	 * 
	 * @param context
	 * @return
	 */
	public int[] getScreenDisplay(Context context) {
		DisplayMetrics displayMetrics = context.getResources()
				.getDisplayMetrics();
		int display[] = { displayMetrics.widthPixels,
				displayMetrics.heightPixels };
		return display;
	}

	/**
	 * 判断屏幕是否为竖屏状态
	 * 
	 * @param context
	 * @return
	 */
	public boolean isScreenVertical(Context context) {
		Configuration config = context.getResources().getConfiguration();
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return false;
		} else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
			return true;
		} else if (config.hardKeyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
			return false;
		} else if (config.hardKeyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
			return true;
		}
		return true;
	}

	/**
	 * 获得页面尺寸的方法
	 * 
	 * @return
	 */
	private int[] getPageSize() {
		int[] size = getScreenDisplay(mContext);
		if (isScreenVertical(mContext)) {
			size[0] = (int) (size[0] * 0.9);
		} else {
			size[0] = (int) (size[0] * 0.73);
		}
		return size;
	}
}
