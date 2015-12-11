package com.zjkj.gongqingtuan;

import java.util.ArrayList;

import com.zjkj.gongqingtuan.TabFragment.observerInterface;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * @author qinst
 * 
 *         主界面
 *
 */
public class MainActivity extends FragmentActivity {

	private TabFragment tab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		urlList.add("http://zyj.8jon.com/Appmobile".trim());
		urlList.add("http://zyj.8jon.com/Appmobile/qnfw/lists".trim());
		urlList.add("http://zyj.8jon.com/Appmobile/Login/login".trim());
		urlList.add("http://zyj.8jon.com/Appmobile/search/index".trim());

		int index = 0;
		final FragmentManager fm = getSupportFragmentManager();

		tab = (TabFragment) fm.findFragmentById(R.id.gq10_tabs_fragment);
		tab.setTabObserverInterface(new observerInterface() {

			@Override
			public void onChangeTab(int index) {
				// TODO Auto-generated method stub
				getFrament(index);
				currentIndex = index;
			}
		}, index);

	}

	private HomePage homeFragment;
	private ServicePage serviceFragment;
	private MePage loginFragment;
	private MorePage moreFragment;

	private int currentIndex;

	/**
	 * @param index
	 *            获取相应的fraagment
	 */
	private void getFrament(int index) {

		final FragmentManager fm = getSupportFragmentManager();

		FragmentTransaction ft = fm.beginTransaction();

		String tag = "";

		Fragment fragCurrent = null;
		Fragment frag01 = null;
		Fragment frag02 = null;
		Fragment frag03 = null;

		switch (index) {
		case 0:
			tag = "index=0";
			if (homeFragment == null) {
				homeFragment = HomePage.newInstance();
			}
			fragCurrent = homeFragment;
			frag01 = serviceFragment;
			frag02 = loginFragment;
			frag03 = moreFragment;
			break;
		case 1:
			tag = "index=1";
			if (serviceFragment == null) {
				serviceFragment = ServicePage.newInstance();
			}
			fragCurrent = serviceFragment;
			frag01 = homeFragment;
			frag02 = loginFragment;
			frag03 = moreFragment;
			break;
		case 2:
			tag = "index=2";
			if (loginFragment == null) {
				loginFragment = MePage.newInstance();
			}
			fragCurrent = loginFragment;
			frag01 = homeFragment;
			frag02 = serviceFragment;
			frag03 = moreFragment;
			break;
		case 3:
			tag = "index=3";
			if (moreFragment == null) {
				moreFragment = MorePage.newInstance();
			}
			fragCurrent = moreFragment;
			frag01 = homeFragment;
			frag02 = serviceFragment;
			frag03 = loginFragment;
			break;
		default:
			return;
		}
		if (fm.findFragmentByTag(tag) == null) {
			ft.add(R.id.gq10_tab_fragment_container, fragCurrent, tag);
		} else {
			ft.show(fragCurrent);
		}
		if (frag01 != null) {
			ft.hide(frag01);
		}
		if (frag02 != null) {
			ft.hide(frag02);
		}
		if (frag03 != null) {
			ft.hide(frag03);
		}
		ft.commit();

		return;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		switch (currentIndex) {

		case 0:
			if (homeFragment != null
					&& urlList.contains(homeFragment.getCurrentUrl().trim())) {
				exit();
				return;
			}
			if (homeFragment != null && homeFragment.canGoBack()) {
				homeFragment.goBack();
			}
			break;
		case 1:
			if (homeFragment != null
					&& urlList.contains(homeFragment.getCurrentUrl().trim())) {
				exit();
				return;
			}
			if (serviceFragment != null && serviceFragment.canGoBack()) {
				serviceFragment.goBack();
			}
			break;
		case 2:
			if (loginFragment != null
					&& urlList.contains(loginFragment.getCurrentUrl().trim())) {
				exit();
				return;
			}
			if (loginFragment != null && loginFragment.canGoBack()) {
				loginFragment.goBack();
			}
			break;
		case 3:
			if (moreFragment != null
					&& urlList.contains(moreFragment.getCurrentUrl().trim())) {
				exit();
				return;
			}
			if (moreFragment != null && moreFragment.canGoBack()) {
				moreFragment.goBack();
			}
			break;
		default:

			break;

		}
	}

	/**
	 * 首页URL列表
	 */
	private ArrayList<String> urlList = new ArrayList<>();

	/**
	 * 点击返回键控制handler
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	/**
	 * 标示是否第一次点击
	 */
	private static boolean isExit = false;

	/**
	 * 退出程序
	 */
	private void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出志愿聚",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

}
