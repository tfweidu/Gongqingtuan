package com.zjkj.tools.utils.diglog;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;

public class SmallDialogFragment extends BaseDialogFragment {

	private final static boolean isDebug = false;

	private final static String TAG = "com.pocketcampus.tools.utils.diglog.SmallDialogFragment";

	private final static String toastTag = "SmallDialogToastTag";

	public static SmallDialogFragmentBuilder createBuilder(Context context,
			FragmentManager fragmentManager) {

		return new SmallDialogFragmentBuilder(context, fragmentManager,
				SmallDialogFragment.class);

	}

	@Override
	protected Builder build(Builder builder) {
		// TODO Auto-generated method stub

		String toastBuilder = getToast();
		if (!TextUtils.isEmpty(toastBuilder)) {

			builder.setToast(toastBuilder);

		}

		return builder;
	}

	/**
	 * @return 获取Toast内容
	 */
	private String getToast() {
		return getArguments().getString(toastTag);
	}

	public static class SmallDialogFragmentBuilder extends
			DialogCommonBuilder<SmallDialogFragmentBuilder> {

		private String mToast;

		// private View.OnClickListener mLeftOnclickListener;
		// private View.OnClickListener mRightOnclickListener;

		public SmallDialogFragmentBuilder(Context contextParams,
				FragmentManager fmParams,
				Class<? extends BaseDialogFragment> clazzParams) {
			super(contextParams, fmParams, clazzParams);
		}

		/**
		 * @param titleStr
		 * @return 设置Toast
		 */
		public SmallDialogFragmentBuilder setToast(String toastStr) {

			mToast = toastStr;

			return this;
		}

		@Override
		public Bundle prepareArguments() {
			// TODO Auto-generated method stub
			Bundle mBundle = new Bundle();

			mBundle.putString("dialogType", DialogCommonBuilder.typeDialogSmall);
			mBundle.putString("typeTag", TAG);

			mBundle.putString(toastTag, mToast);

			return mBundle;
		}

		@Override
		public SmallDialogFragmentBuilder self() {
			// TODO Auto-generated method stub
			return this;
		}

	}

}
