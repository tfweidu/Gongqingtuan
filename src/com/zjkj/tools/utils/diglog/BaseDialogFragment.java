package com.zjkj.tools.utils.diglog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zjkj.gongqingtuan.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author qin 对话框基类
 *
 */
public abstract class BaseDialogFragment extends DialogFragment {

	private final static boolean isDebug = false;

	/**
	 * 对话框类型
	 */
	private String dialogType;
	/**
	 * 对话框TAG
	 */
	private String dialogTag;
	private String bussinessTag;

	private FragmentActivity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		activity = getActivity();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Bundle argsBundle = getArguments();

		dialogType = argsBundle.getString("dialogType");

		dialogTag = argsBundle.getString("typeTag");
		bussinessTag = argsBundle.getString("bussinessTag");

		if (isDebug) {
			Log.v("searchleftTAG", bussinessTag);
		}

		Dialog mDialog = new Dialog(activity, R.style.base_dialog_style);

		return mDialog;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Builder mBuilder = new Builder(activity, inflater, container);
		return build(mBuilder).createDialogView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	protected <T> List<T> getDialogLinstener(Class<T> listenerInterface) {

		// final MyFragment frag = (MyFragment) getTargetFragment();
		// final Fragment frag = (Fragment) getFragmentManager()
		// .findFragmentByTag(dialogTag);
		final Fragment frag = (Fragment) getFragmentManager()
				.findFragmentByTag(bussinessTag);

		ArrayList<T> list = new ArrayList<T>(2);

		if (frag != null && listenerInterface.isAssignableFrom(frag.getClass())) {
			list.add((T) frag);

			if (isDebug) {
				Log.v("getDialogLinstenerFragment", frag.getClass().getName());
			}

		}

		if (activity != null
				&& listenerInterface.isAssignableFrom(activity.getClass())) {
			list.add((T) activity);

			if (isDebug) {
				Log.v("getDialogLinstenerActivity", activity.getClass()
						.getName());
			}

		}

		return Collections.unmodifiableList(list);

	}

	// -----------------------------
	// -----------------------------

	protected abstract Builder build(Builder builder);

	// ---------------------------
	// ---------------------------

	protected static class Builder {

		// big dialog
		private byte[] mImageDrawableByte;
		private View.OnClickListener imageClickListener;

		// middle dialog
		private Button leftBn;
		private Button rightBn;
		private String titleTv;
		private String contentTv;

		private View.OnClickListener leftOnclicklistener;
		private View.OnClickListener rightOnclicklistener;

		private Context builderContext;
		private LayoutInflater builderInflater;
		private ViewGroup builderViewGroup;

		// small dialog
		private String tosatTv;

		public Builder(Context context, LayoutInflater inflater,
				ViewGroup container) {

			builderContext = context;

			builderInflater = inflater;

			builderViewGroup = container;

		}

		/**
		 * @param resid
		 * @return 设置图片
		 * 
		 */
		public Builder setResouceId(byte[] mDrawableByte) {

			mImageDrawableByte = mDrawableByte;
			return this;

		}

		/**
		 * @param mOnClickListener
		 * @return 设置对话框左按钮点击事件
		 */
		public Builder setLeftOnclicklistener(
				View.OnClickListener mOnClickListener) {

			if (isDebug) {

				Log.v("tag", "左按钮添加事件赋值");

			}

			leftOnclicklistener = mOnClickListener;
			return this;

		}

		/**
		 * @param mOnClickListener
		 * @return 设置对话框右按钮点击事件
		 */
		public Builder setRightOnclicklistener(
				View.OnClickListener mOnClickListener) {

			rightOnclicklistener = mOnClickListener;
			return this;

		}

		public Builder setTitle(String title) {
			titleTv = title;
			return this;
		}

		public Builder setcontent(String title) {
			contentTv = title;
			return this;
		}

		public Builder setToast(String toast) {

			tosatTv = toast;
			return this;
		}

		/**
		 * @return 构建对话框视图
		 * 
		 */
		@SuppressLint("NewApi")
		public View createDialogView() {

			View dialogView = builderInflater.inflate(
					R.layout.tf_common_dialog, builderViewGroup, false);

			dialogView.setFocusable(true);

			// 图片对话框
			ImageView bigImageView = (ImageView) dialogView
					.findViewById(R.id.big_dialog);

			// 中型对话框
			LinearLayout middleLinear = (LinearLayout) dialogView
					.findViewById(R.id.middle_dialog);

			TextView titleTvInView = (TextView) dialogView
					.findViewById(R.id.tv_commondialog_title);

			TextView contentTvInView = (TextView) dialogView
					.findViewById(R.id.tv_commondialog_content);
			View bnLeft = dialogView.findViewById(R.id.bn_left);
			View bnRight = dialogView.findViewById(R.id.bn_right);

			// 简易对话框
			LinearLayout smallLinear = (LinearLayout) dialogView
					.findViewById(R.id.small_dialog);
			TextView toastTvInView = (TextView) dialogView
					.findViewById(R.id.tv_toast);

			if (mImageDrawableByte != null) {

				if (isDebug) {
					Log.v("TAG", "大对话框，设置图片 ");
				}

				Bitmap imageBigdialog = BitmapFactory.decodeByteArray(
						mImageDrawableByte, 0, mImageDrawableByte.length);

				bigImageView.setBackground(new BitmapDrawable(imageBigdialog));

				bigImageView.setVisibility(View.VISIBLE);
				middleLinear.setVisibility(View.GONE);
				smallLinear.setVisibility(View.GONE);

			}

			if (contentTv != null) {
				titleTvInView.setText(titleTv);
				contentTvInView.setText(contentTv);

				if (isDebug) {
					Log.v("TAG", "BaseDialogFragment绑定事件");
				}

				bnLeft.setOnClickListener(leftOnclicklistener);
				bnRight.setOnClickListener(rightOnclicklistener);

				bnLeft.setClickable(true);
				bnRight.setClickable(true);

				bigImageView.setVisibility(View.GONE);
				middleLinear.setVisibility(View.VISIBLE);
				smallLinear.setVisibility(View.GONE);
			}

			if (tosatTv != null) {
				toastTvInView.setText(tosatTv);

				bigImageView.setVisibility(View.GONE);
				middleLinear.setVisibility(View.GONE);
				smallLinear.setVisibility(View.VISIBLE);
			}

			return dialogView;
		}
	}

}
