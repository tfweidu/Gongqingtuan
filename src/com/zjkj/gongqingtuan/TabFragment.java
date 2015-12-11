package com.zjkj.gongqingtuan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author qinst tab fragment
 *
 */
public class TabFragment extends Fragment {

	private FragmentActivity mActivity;
	private View rootView;

	private observerInterface mObserver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		rootView = inflater.inflate(R.layout.tablayout, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initView();
	}

	private View tab01;
	private View tab02;
	private View tab03;
	private View tab04;

	private TextView text01;
	private TextView text02;
	private TextView text03;
	private TextView text04;

	private void initView() {
		// TODO Auto-generated method stub

		tab01 = rootView.findViewById(R.id.layout_tab01);
		tab02 = rootView.findViewById(R.id.layout_tab02);
		tab03 = rootView.findViewById(R.id.layout_tab03);
		tab04 = rootView.findViewById(R.id.layout_tab04);

		text01 = (TextView) rootView.findViewById(R.id.text_tab01);
		text02 = (TextView) rootView.findViewById(R.id.text_tab02);
		text03 = (TextView) rootView.findViewById(R.id.text_tab03);
		text04 = (TextView) rootView.findViewById(R.id.text_tab04);

		tab01.setOnClickListener(mClickListener);
		tab02.setOnClickListener(mClickListener);
		tab03.setOnClickListener(mClickListener);
		tab04.setOnClickListener(mClickListener);

	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			int index = 0;

			if (view == tab01) {
				index = 0;

			} else if (view == tab02) {
				index = 1;

			} else if (view == tab03) {
				index = 2;

			} else if (view == tab04) {
				index = 3;
			}
			changeTab(index);

		}
	};

	/**
	 * @author qinst
	 * tab选项观察者
	 *
	 */
	public static interface observerInterface {

		public void onChangeTab(int index);
	}

	public void setTabObserverInterface(observerInterface mInterface, int index) {

		this.mObserver = mInterface;
		changeTab(index);

	}

	/**
	 * @param index
	 *            更换tab
	 */
	private void changeTab(int index) {
		setViewState(index);
		if (mObserver == null) {
			return;
		}

		mObserver.onChangeTab(index);

	}

	/**
	 * 更换聚焦效果
	 * 
	 * @param index
	 */
	private void setViewState(int index) {
		final View imageTab01 = rootView.findViewById(R.id.image_tab01);
		final View imageTab02 = rootView.findViewById(R.id.image_tab02);
		final View imageTab03 = rootView.findViewById(R.id.image_tab03);
		final View imageTab04 = rootView.findViewById(R.id.image_tab04);
		// ---
		imageTab01.setSelected(false);
		imageTab02.setSelected(false);
		imageTab03.setSelected(false);
		imageTab04.setSelected(false);
		// --
		switch (index) {
		case 0:
			imageTab01.setSelected(true);
			if(text01==null||text02==null||text03==null||text04==null){
				return;
			}
			text01.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color_black));
			text02.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text03.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text04.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			break;
		case 1:
			imageTab02.setSelected(true);
			if(text01==null||text02==null||text03==null||text04==null){
				return;
			}
			text01.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text02.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color_black));
			text03.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text04.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			break;
		case 2:
			imageTab03.setSelected(true);
			if(text01==null||text02==null||text03==null||text04==null){
				return;
			}
			text01.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text02.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text03.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color_black));
			text04.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			break;
		case 3:
			imageTab04.setSelected(true);
			if(text01==null||text02==null||text03==null||text04==null){
				return;
			}
			text01.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text02.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text03.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color));
			text04.setTextColor(mActivity.getResources().getColor(
					R.color.gq10_color_black));
			break;

		}
	}

}
