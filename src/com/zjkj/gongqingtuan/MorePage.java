package com.zjkj.gongqingtuan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author qinst 首页
 */
public class MorePage extends Fragment {

	private WebView mWebView;

	private FragmentActivity mActivity;
	
	public static MorePage newInstance(){
		MorePage morePage = new MorePage();
		return morePage;
	}

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		mWebView = new WebView(mActivity);

		LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		mWebView.setLayoutParams(lp);

		return mWebView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://zyj.8jon.com/Appmobile/search/index");
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub

				view.loadUrl(url);
				return true;
			}
		});
	}
	
	public String getCurrentUrl() {
		return mWebView.getUrl();
	}

	/**
	 * @return 获取是否能够返回
	 */
	public boolean canGoBack() {
		return mWebView != null && mWebView.canGoBack();
	}

	/**
	 * 返回上一页
	 */
	public void goBack() {
		if (mWebView != null) {
			mWebView.goBack();
		}
	}

}
