package com.liu.lxf.myapplication.utils.bitmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * 本地缓存
 * 
 */
public class LocalCacheUtils {

	private static final String LOCAL_CACHE_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/liu_cache";

	/**
	 * 读本地缓存
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromLocal(String url) {
		try {
			File file = new File(LOCAL_CACHE_PATH, MD5Encoder.encode(url));
			if (file.exists()) {
				// 有本地缓存
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						file));

				return bitmap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 写本地缓存
	 * 
	 * @param url
	 * @param bitmap
	 */
	public void setBitmap2Local(String url, Bitmap bitmap) {
		try {
			File file = new File(LOCAL_CACHE_PATH, MD5Encoder.encode(url));

			File parentFile = file.getParentFile();// 获取所在的文件夹
			if (!parentFile.exists()) {
				parentFile.mkdirs();// 创建文件夹
			}

			// 将图片压缩保存在本地, 压缩格式jpeg, 压缩比例100%
			bitmap.compress(CompressFormat.JPEG, 100,
					new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
