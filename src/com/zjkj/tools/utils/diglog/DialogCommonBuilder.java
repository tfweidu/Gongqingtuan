package com.zjkj.tools.utils.diglog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author qin 对话框公共构造类
 */
public abstract class DialogCommonBuilder<T extends DialogCommonBuilder<T>> {

	private static boolean isDebug = false;

	private boolean isCancelable = true;
	private boolean isCancelableTouchOutside = true;

	private String defaultTag = "com.pocketcampus.tools.utils.diglog.DialogCommonBuilder";

	private final Context context;
	private final FragmentManager mFragmentManager;
	private final Class clazz;

	public static String typeDialogBig = "BIG_DIALOG";
	public static String typeDialogMiddle = "MIDDLE_DIALOG";
	public static String typeDialogSmall = "SMALL_DIALOG";

	public abstract Bundle prepareArguments();

	public abstract T self();

	public DialogCommonBuilder(Context contextParams, FragmentManager fmParams,
			Class<? extends BaseDialogFragment> clazzParams) {

		context = contextParams;
		mFragmentManager = fmParams;
		clazz = clazzParams;
	}

	/**
	 * @param isCancelableParams
	 * @return 设置点击返回键对话框是否取消
	 * 
	 */
	public T setCancelable(boolean isCancelableParams) {

		isCancelable = isCancelableParams;

		return self();
	}

	public T setCancelableTouchOutside(boolean isTouchOutsideCancelableParams) {

		isCancelableTouchOutside = isTouchOutsideCancelableParams;

		return self();
	}

	@SuppressLint("NewApi")
	private BaseDialogFragment createDiagFragment() {

		Bundle mBundle = prepareArguments();

		// 获取要创建的对话框的类型 默认为中等对话框
		// String typeDialog = mBundle.getString("dialogType",
		// typeDialogMiddle);
		String typeTag = mBundle.getString("typeTag", typeDialogMiddle);

		if (!TextUtils.isEmpty(typeTag)) {

			defaultTag = typeTag;

		}
		BaseDialogFragment mBaseDialogFragment = (BaseDialogFragment) Fragment
				.instantiate(context, clazz.getName(), mBundle);

		mBaseDialogFragment.setCancelable(isCancelable);

		return mBaseDialogFragment;
	}

	/**
	 * @return 显示对话框
	 * 
	 */
	public DialogFragment show() {
		BaseDialogFragment showBaseDialogFragment = createDiagFragment();

		showBaseDialogFragment.show(mFragmentManager, defaultTag);

		if (isDebug) {

			Log.v("defaultTag+tag", defaultTag);

		}

		return showBaseDialogFragment;

	}

}
