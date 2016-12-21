package com.liu.lxf.myapplication.utils.bitmap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 内存缓存
 */
public class MemoryCacheUtils {

	// 从 Android 2.3 (API Level 9)开始，垃圾回收器会更倾向于回收持有软引用或弱引用的对象，这让软引用和弱引用变得不再可靠。
	//private HashMap<String, SoftReference<Bitmap>> mBitmapMap = new HashMap<String, SoftReference<Bitmap>>();

	// LruCache least recentlly used 最近最少使用的算法
	// 可以将内存控制在一定范围, 不会超过最大值
	private LruCache<String, Bitmap> mLruCache;

	public MemoryCacheUtils() {
		long maxMemory = Runtime.getRuntime().maxMemory();// 当前手机给每个app分配的内存大小
															// 16M
		System.out.println("max memory:" + maxMemory);
		mLruCache = new LruCache<String, Bitmap>((int) maxMemory / 8) {// maxSize:内存最大值;用总内存的8分之一作为lrucache的最大值
			
			//计算每个对象的占用内存大小
			@Override
			protected int sizeOf(String key, Bitmap value) {
				//value.getByteCount();
				int totalCount =  value.getRowBytes() * value.getHeight();
				return totalCount;
			}
		};
	}

	/**
	 * 读内存缓存
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getBitmapFromMemory(String url) {

		Bitmap bitmap = mLruCache.get(url);
		return bitmap;
	}

	/**
	 * 写内存缓存
	 * 
	 * @param url
	 * @param bitmap
	 */
	public void setBitmap2Memory(String url, Bitmap bitmap) {
//		SoftReference<Bitmap> ref = new SoftReference<Bitmap>(bitmap);
//		mBitmapMap.put(url, ref);
		mLruCache.put(url, bitmap);
	}

}
