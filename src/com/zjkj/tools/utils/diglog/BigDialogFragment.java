package com.zjkj.tools.utils.diglog;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class BigDialogFragment extends BaseDialogFragment {

	private final static String TAG = "com.pocketcampus.tools.utils.diglog.BigDialogFragment";
	private final static String ImageDrawableFlagByte = "BigDialogImageDrawableFlag";

	public static BigDialogFragmentBuilder createBuilder(Context context,
			FragmentManager fragmentManager) {

		return new BigDialogFragmentBuilder(context, fragmentManager,
				BigDialogFragment.class);

	}

	protected byte[] getDrawableImage() {

		return getArguments().getByteArray(ImageDrawableFlagByte);

	}

	@Override
	protected Builder build(Builder builder) {

		byte[] imagByte = getDrawableImage();

		if (imagByte != null) {

			builder.setResouceId(imagByte);

		}

		return builder;
	}

	public static class BigDialogFragmentBuilder extends
			DialogCommonBuilder<BigDialogFragmentBuilder> {

		private byte[] bigDrawableImageByte;

		public BigDialogFragmentBuilder(Context contextParams,
				FragmentManager fmParams,
				Class<? extends BaseDialogFragment> clazzParams) {
			super(contextParams, fmParams, clazzParams);
		}

		public BigDialogFragmentBuilder setDrawable(byte[] mDrawableByte) {

			bigDrawableImageByte = mDrawableByte;

			return this;
		}

		@Override
		public Bundle prepareArguments() {
			// TODO Auto-generated method stub
			Bundle mBundle = new Bundle();

			mBundle.putString("dialogType", DialogCommonBuilder.typeDialogBig);

			mBundle.putByteArray(BigDialogFragment.ImageDrawableFlagByte,
					bigDrawableImageByte);
			mBundle.putString("typeTag", TAG);

			return mBundle;
		}

		@Override
		public BigDialogFragmentBuilder self() {
			// TODO Auto-generated method stub
			return this;
		}

	}

}
