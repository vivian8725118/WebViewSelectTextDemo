package com.bossturban.webviewmarker.sample.demos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class BtPopWindowLikeNum extends PopupWindow implements OnClickListener {
	private final TextView mRootView;
	private Context context;

	public BtPopWindowLikeNum(Context context, String text, final View view) {
		super();
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRootView = (TextView) inflater.inflate(R.layout.popup, null);
		mRootView.setText(text);
		this.setContentView(mRootView);
		this.setWidth(LayoutParams.WRAP_CONTENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setTouchable(true);
		this.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

		// 点击空白区域让pop消失
		this.setOutsideTouchable(true);
		// this.setFocusable(true);
		mRootView.setOnClickListener(this);
	}

	/**
	 * 第一次的时候，没有显示出来过，所以要添加手动measure，否则getWidth是0，则第一次显示pop的位置会偏
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public int getWidth(int width, int height) {
		int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
		int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
		mRootView.measure(widthSpec, heightSpec);
		return mRootView.getMeasuredWidth();
	}

	public int getHeight(int width, int height) {
		int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
		int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
		mRootView.measure(widthSpec, heightSpec);
		return mRootView.getMeasuredHeight();
	}

	public int getLeft() {
		return mRootView.getLeft();
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
	}

}
