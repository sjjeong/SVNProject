package com.unicodi.android.unicodi.utilgoogry;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class ImageUtil {
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;

	public static void setScreenWidth(Activity activity) {
		if (SCREEN_WIDTH == 0) {
			DisplayMetrics metrics = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			SCREEN_WIDTH = metrics.widthPixels;
		}
	}

	public static void setScreenHeight(Activity activity) {
		if (SCREEN_HEIGHT == 0) {
			DisplayMetrics metrics = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			SCREEN_HEIGHT = metrics.heightPixels;
		}
	}

	public static Bitmap resizeImageByDevice(Activity activity, Bitmap original) {
		double resizedWidth;
		double resizedHeight;
		if ((double) SCREEN_HEIGHT / (double) SCREEN_WIDTH >= (double) original
				.getHeight() / (double) original.getWidth()) {
			resizedWidth = SCREEN_WIDTH;
			resizedHeight = resizedWidth
					* ((double) original.getHeight() / (double) original
							.getWidth());
		} else {
			resizedHeight = SCREEN_HEIGHT;
			resizedWidth = resizedHeight
					/ ((double) original.getHeight() / (double) original
							.getWidth());
		}

		return Bitmap.createScaledBitmap(original, (int) resizedWidth,
				(int) resizedHeight, true);
	}

	public static boolean checkPointInRect(Rect rect, int x, int y) {
		return (x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom);
	}

	public static Bitmap addSampleImage(Bitmap baseBmp, Bitmap overlayBmp) {
		Bitmap resultBmp = Bitmap.createBitmap(baseBmp.getWidth(),
				baseBmp.getHeight(), baseBmp.getConfig());
		Canvas canvas = new Canvas(resultBmp);
		canvas.drawBitmap(baseBmp, null, new Rect(0, 0, baseBmp.getWidth(),
				baseBmp.getHeight()), null);
		Paint paint = new Paint();
		paint.setAlpha(200);
		canvas.drawBitmap(overlayBmp, null, new Rect(0, 0, baseBmp.getWidth(),
				baseBmp.getHeight()), paint);
		return resultBmp;
	}

	public static boolean isImage(String name) {
		return name.endsWith(".png") || name.endsWith(".jpg")
				|| name.endsWith(".gif");
	}
}
