package com.unicodi.android.unicodi.utilgoogry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class FileUtil {
	public static final String APP_NAME = "Unicodi";
	public static final String UNDER_BAR = "_";
	public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";
	public static final String SAVED_DIR = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ APP_NAME + File.separator;
	public static final String EXTENSION = ".png";

	public static String saveBitmapToPNGFile(Bitmap bitmap, String path) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(path));
			bitmap.compress(CompressFormat.PNG, 100, fos);
		} catch (FileNotFoundException e) {
			return null;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
		}
		return path;
	}

	public static void initialDirectory() {
		File file = new File(SAVED_DIR);

		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static String createForderNameByTimestamp(String path) {
		return path
				+ new SimpleDateFormat(DATE_FORMAT, Locale.KOREAN)
						.format(new Date()) + File.separator;
	}

	public static String createFileNameByTimestamp() {
		return APP_NAME
				+ UNDER_BAR
				+ new SimpleDateFormat(DATE_FORMAT, Locale.KOREAN)
						.format(new Date()) + EXTENSION;
	}

	// deprecated in API level 11
	@SuppressWarnings("deprecation")
	public static String getAbsolutePath(Activity activity, Uri relativePath) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = activity.managedQuery(relativePath, projection, null,
				null, null);
		activity.startManagingCursor(cursor);
		int columnIndex = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(columnIndex);
	}

	public synchronized static int GetExifOrientation(String filepath) {

		int degree = 0;
		ExifInterface exif = null;

		try {
			exif = new ExifInterface(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (exif != null) {
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION, -1);

			if (orientation != -1) {
				// We only recognize a subset of orientation tag values.
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;

				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;

				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
				}

			}
		}
		return degree;
	}

	public synchronized static Bitmap GetRotatedBitmap(Bitmap bitmap,
			int degrees) {
		if (degrees != 0 && bitmap != null) {
			Matrix m = new Matrix();
			Log.i("googrybug", "width = " + bitmap.getWidth() + " , height = "
					+ bitmap.getHeight());
			m.setRotate(degrees, (float) bitmap.getWidth() / 2,
					(float) bitmap.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != b2) {
					bitmap.recycle();
					bitmap = b2;
				}
			} catch (OutOfMemoryError ex) {
				// We have no memory to rotate. Return the original bitmap.
			}
		}
		return bitmap;
	}

	public static Vector<SavedFile> getFileList(String path) {
		Vector<SavedFile> dirList = new Vector<SavedFile>();
		File rootDir = new File(path);
		String[] rootList = rootDir.list();
		int n;
		for (int i = 0; i < rootList.length; i++) {
			File subDir = new File(path + rootList[i]);
			if (subDir.isDirectory()) {
				String[] subList = subDir.list();
				n = 0;
				for (int j = 0; j < subList.length; j++) {
					if (ImageUtil.isImage(subList[j])) {
						n++;
					}
				}
				if (n > 0) {
					SavedFile tmp = new SavedFile(rootList[i]);
					for (int j = 0; j < subList.length; j++) {
						if (ImageUtil.isImage(subList[j])) {
							tmp.addFile(subList[j]);
						}
					}
					dirList.add(tmp);
				}
			}
		}
		return dirList;
	}

	public static Vector<String> getSubFileList(String root)
			throws NullPointerException {
		Vector<String> list = new Vector<String>();
		File rootDir = new File(FileUtil.SAVED_DIR + root);
		String[] rootList = rootDir.list();
		for (int i = 0; i < rootList.length; i++) {
			if (ImageUtil.isImage(rootList[i])) {
				list.add(rootList[i]);
			}
		}
		return list;
	}

	public static Vector<String> getSelectableFileList(String root) {
		Vector<String> list = new Vector<String>();
		File rootDir = new File(FileUtil.SAVED_DIR + root);
		String[] rootList = rootDir.list();
		for (int i = 0; i < rootList.length; i++) {
			if (ImageUtil.isImage(rootList[i])) {
				list.add(rootList[i]);
			}
		}
		return list;
	}

}
