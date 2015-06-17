package com.bossturban.webviewmarker.sample.demos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bossturban.webviewmarker.TextSelectionSupport;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity {
	private WebView mWebView;
	BtPopWindowLikeNum pop;
	private TextSelectionSupport mTextSelectionSupport;
	int height;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mWebView = (WebView) findViewById(R.id.webView);
		pop = new BtPopWindowLikeNum(getApplicationContext(), "点击复制", mWebView);
		// mTextSelectionSupport = TextSelectionSupport.support(this, mWebView, pop);
		mTextSelectionSupport = new TextSelectionSupport(this, mWebView);

		mTextSelectionSupport.setSelectionListener(new TextSelectionSupport.SelectionListener() {

			@Override
			public void startSelection() {
			}

			@Override
			public void selectionChanged(int top, int left, int right, int bottom, String text) {
				pop.dismiss();
				if (bottom - top == 0) {
					pop.showAtLocation(mWebView, Gravity.NO_GRAVITY, (right + left) / 2 - pop.getWidth(mWebView.getWidth(), mWebView.getHeight()) / 2, top);
				} else {
					pop.showAtLocation(mWebView, Gravity.NO_GRAVITY, ScreenSizeUtil.getScreenWidth(MainActivity.this) / 2 - pop.getWidth(mWebView.getWidth(), mWebView.getHeight()) / 2, top);
				}
			}

			@Override
			public void endSelection() {
			}

		});
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onScaleChanged(WebView view, float oldScale, float newScale) {
				mTextSelectionSupport.onScaleChanged(oldScale, newScale);
			}

		});

		mWebView.loadUrl("file:///android_asset/content.html");
	}
}
