package com.zjkj.tools.utils.diglog;

import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MiddleDialogFragment extends BaseDialogFragment {

	private final static boolean isDebug = false;

	private final static String TAG = "com.pocketcampus.tools.utils.diglog.MiddleDialogFragment";

	private final static String titleTag = "middleDialogTitleTag";
	private final static String contentTag = "middleDialogContentTag";

	public static MiddleDialogFragmentBuilder createBuilder(Context context,
			FragmentManager fragmentManager) {

		return new MiddleDialogFragmentBuilder(context, fragmentManager,
				MiddleDialogFragment.class);

	}

	/**
	 * @return 获取左按钮点击事件
	 */
	private List<LeftBnClickLintener> getLeftListener() {

		if (isDebug) {
			Log.v("tag", "获取左按钮点击事件");
		}

		return getDialogLinstener(LeftBnClickLintener.class);
	}

	/**
	 * @return 获取右按钮点击事件
	 */
	private List<RightBnClickLintener> getRightListener() {
		return getDialogLinstener(RightBnClickLintener.class);
	}

	@Override
	protected Builder build(Builder builder) {
		// TODO Auto-generated method stub

		String titleBuilder = getTitle();
		if (!TextUtils.isEmpty(titleBuilder)) {
			builder.setTitle(titleBuilder);

		}

		String contentBuilder = getContent();
		if (!TextUtils.isEmpty(contentBuilder)) {

			builder.setcontent(contentBuilder);

		}

		builder.setLeftOnclicklistener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (isDebug) {
					Log.v(TAG, "进入点击事件left.onLeftCilcklinstener();");
				}

				for (LeftBnClickLintener left : getLeftListener()) {
					left.onLeftCilcklinstener();

					if (isDebug) {
						Log.v(TAG, "left.onLeftCilcklinstener();");
					}

				}

			}
		});

		builder.setRightOnclicklistener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				for (RightBnClickLintener right : getRightListener()) {
					right.onRightCilcklinstener();

					if (isDebug) {
						Log.v(TAG, "right.onRightCilcklinstener();");
					}

				}
			}
		});

		return builder;
	}

	/**
	 * @return 获取标题
	 */
	private String getTitle() {
		return getArguments().getString(titleTag);
	}

	/**
	 * @return 获取内容
	 */
	private String getContent() {
		return getArguments().getString(contentTag);
	}

	public static class MiddleDialogFragmentBuilder extends
			DialogCommonBuilder<MiddleDialogFragmentBuilder> {

		private String mTitle;
		private String mContent;

		// private View.OnClickListener mLeftOnclickListener;
		// private View.OnClickListener mRightOnclickListener;

		public MiddleDialogFragmentBuilder(Context contextParams,
				FragmentManager fmParams,
				Class<? extends BaseDialogFragment> clazzParams) {
			super(contextParams, fmParams, clazzParams);
		}

		/**
		 * @param titleStr
		 * @return 设置标题
		 */
		public MiddleDialogFragmentBuilder setTitle(String titleStr) {

			mTitle = titleStr;

			return this;
		}

		/**
		 * @param contentStr
		 * @return 设置对话框内容
		 */
		public MiddleDialogFragmentBuilder setContent(String contentStr) {

			mContent = contentStr;

			return this;
		}

		// /**
		// * @param leftOnclickkListener
		// * @return 设置左按钮点击事件
		// */
		// public MiddleDialogFragmentBuilder setLeftOnclicklistener(
		// View.OnClickListener leftOnclickkListener) {
		//
		// mLeftOnclickListener = leftOnclickkListener;
		//
		// return this;
		// }
		//
		// /**
		// * @param rightOnclickkListener
		// * @return 设置右按钮点击事件
		// */
		// public MiddleDialogFragmentBuilder setRightOnclicklistener(
		// View.OnClickListener rightOnclickkListener) {
		//
		// mRightOnclickListener = rightOnclickkListener;
		//
		// return this;
		// }

		@Override
		public Bundle prepareArguments() {
			// TODO Auto-generated method stub
			Bundle mBundle = new Bundle();

			mBundle.putString("dialogType",
					DialogCommonBuilder.typeDialogMiddle);
			mBundle.putString("typeTag", TAG);

			mBundle.putString(titleTag, mTitle);
			mBundle.putString(contentTag, mContent);

			return mBundle;
		}

		@Override
		public MiddleDialogFragmentBuilder self() {
			// TODO Auto-generated method stub
			return this;
		}

	}

}
