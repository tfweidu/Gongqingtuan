package com.zjkj.gongqingtuan;

import com.zjkj.tools.utils.diglog.SmallDialogFragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author qinst 首页
 */
public class HomePage extends Fragment {

	private final static String TAG = "com.pocketcampus.tools.utils.diglog.SmallDialogFragment";

	private WebView mWebView;

	private FragmentActivity mActivity;

	private boolean blockLoadingNetworkImage = false;

	private boolean isFirstOpen = true;

	private String tag = "";

	public static HomePage newInstance() {
		HomePage hompage = new HomePage();
		return hompage;
	}

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		mActivity = getActivity();

		tag = this.getTag();
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

		if (isFirstOpen) {
			SmallDialogFragment.createBuilder(mActivity, getFragmentManager())
					.setToast("loading...").show().getArguments()
					.putString("bussinessTag", tag);
		}

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://zyj.8jon.com/Appmobile");

		mWebView.getSettings().setRenderPriority(RenderPriority.HIGH);

		mWebView.getSettings().setBlockNetworkImage(true);

		blockLoadingNetworkImage = true;

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub

				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				if (isFirstOpen) {
					((DialogFragment) getFragmentManager().findFragmentByTag(
							TAG)).dismiss();
					isFirstOpen = false;
				}

			}
		});

		mWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub

				if (newProgress >= 100) {

					if (blockLoadingNetworkImage) {
						mWebView.getSettings().setBlockNetworkImage(false);
						blockLoadingNetworkImage = false;

					}
				}
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
