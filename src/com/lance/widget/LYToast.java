package com.lance.widget;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

/**
 * 自定义系统Toast效果---淡入淡出效果
 * @author chengkai.gan
 *
 */
public class LYToast implements OnDismissListener {
	
	public static final int LENGTH_LONG = 5000;
	public static final int LENGTH_SHORT = 3000;
	
	private static final int ALPHA_DURATION = 300;

	private PopupWindow mPopup;
	
	private Activity mContext;
	private ViewGroup mDecordView;// 所有视图之上的容器视图
	private View mRootView;// 内容视图的容器
	private View mShadeView;// 弹出框上半透明阴影
	private View mPanel;

	private LYToast() {
		
	}
	
	private void initText(Activity context, CharSequence text, int duration) {
		this.mContext = context;
		mPopup = new PopupWindow(this.mContext);
		mDecordView = (ViewGroup) mContext.getWindow().getDecorView();
		
		mRootView = createView(context,text);

		mPopup.setWidth(LayoutParams.MATCH_PARENT);
		mPopup.setHeight(LayoutParams.MATCH_PARENT);
		mPopup.setFocusable(true);
		ColorDrawable clearColor = new ColorDrawable(Color.TRANSPARENT);
		mPopup.setBackgroundDrawable(clearColor);
		mPopup.setOnDismissListener(this);
		
		mPopup.setContentView(mRootView);
		
	}
	
	private void show() {
		mPopup.showAtLocation(mDecordView, Gravity.CENTER
				| Gravity.CENTER_HORIZONTAL, 0, 0);
		
		mPanel.startAnimation(createAlphaInAnimation());
	}
	
	private void popDismissDelay(int duration) {
		if(duration > LENGTH_LONG){
			duration = LENGTH_LONG;
		}else{
			duration = LENGTH_SHORT;
		}
		mPanel.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mPanel.startAnimation(createAlphaOutAnimation());

				mPanel.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						mPopup.dismiss();
					}
				}, ALPHA_DURATION);
			}
		}, duration);
		
	}
	
	public static void makeText(Activity context, CharSequence text, int duration) {
		LYToast toast = new LYToast();
		toast.initText(context, text, duration);
		toast.show();
		
		toast.popDismissDelay(duration);
	}

//	public static void makeCustomView(Context context, View curstomView, int duration){
//		
//	}
	
	private View createView(Activity context, CharSequence text) {
		FrameLayout parent = new FrameLayout(mContext);
		parent.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));
		mShadeView = new View(mContext);
		mShadeView.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));
		mShadeView.setBackgroundColor(Color.argb(0, 0, 0, 0));// 全透明

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		int margin = (int)(density(context) * 20);
		params.leftMargin = margin;
		params.rightMargin = margin;
		params.gravity = Gravity.CENTER;
		
		TextView toastView = new TextView(mContext);
		toastView.setTextSize(14);
		toastView.setTextColor(Color.WHITE);
		toastView.setText(text);
		toastView.setLayoutParams(params);
		int paddingHorizontal = (int)(20 * density(context));
		int paddingVertical = (int)(10 * density(context));
		toastView.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
		toastView.setGravity(Gravity.CENTER);
		
		toastView.setBackgroundColor(Color.argb(80, 0, 0, 0));
		
		mPanel = toastView;
		mPanel.setLayoutParams(params);
		mPanel.setOnClickListener(null);// 防止点击空白组件后消失

		parent.addView(mShadeView);
		parent.addView(mPanel);
		return parent;
	}
	
	private Animation createAlphaInAnimation() {
		AlphaAnimation anim = new AlphaAnimation(0, 1);
		anim.setDuration(ALPHA_DURATION);
		return anim;
	}

	private Animation createAlphaOutAnimation() {
		AlphaAnimation anim = new AlphaAnimation(1, 0);
		anim.setDuration(ALPHA_DURATION);
		anim.setFillAfter(true);
		return anim;
	}

	@Override
	public void onDismiss() {
		
	}
	
	private float density(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.density;
	}
	
}
