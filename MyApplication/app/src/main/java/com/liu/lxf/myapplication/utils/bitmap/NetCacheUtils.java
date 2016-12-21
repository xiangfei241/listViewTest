package com.liu.lxf.myapplication.utils.bitmap;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 网络缓存
 * 
 */
public class NetCacheUtils {

	private LocalCacheUtils mLocalUtils;
	private MemoryCacheUtils mMemoryCacheUtils;

	public NetCacheUtils(LocalCacheUtils localUtils,
			MemoryCacheUtils memoryCacheUtils) {
		mLocalUtils = localUtils;
		mMemoryCacheUtils = memoryCacheUtils;
	}

	public void getBitmapFromNet(ImageView ivPic, String url) {
		// thread + handler
		new BitmapTask().execute(ivPic, url);

	}

	/**
	 * AsyncTask = 线程池 + Handler 第一个泛型,表示传参的类型(和doInBackground参数类型要一致)
	 * 第二个泛型,表示更新进度的类型(和onProgressUpdate参数类型一致)
	 * 第三个泛型,表示加载结束之后,返回的对象类型(和doInBackground返回参数类型一致,和onPostExecute参数类型一致)
	 * 
	 */
	class BitmapTask extends AsyncTask<Object, Integer, Bitmap> {

		private ImageView imageView;
		private String url;

		// 主线程运行
		@Override
		protected void onPreExecute() {
			// 预加载, 加载前的准备操作
			super.onPreExecute();
		}

		// 子线程运行
		@Override
		protected Bitmap doInBackground(Object... params) {
			// 开始异步加载
			imageView = (ImageView) params[0];
			url = (String) params[1];

			imageView.setTag(url);// 将imageView和url绑定在一起

			Bitmap bitmap = download(url);

			return bitmap;
		}

		// 主线程运行
		@Override
		protected void onProgressUpdate(Integer... values) {
			// 进度更新(下载文件时用来更新进度)
			super.onProgressUpdate(values);
		}

		// 主线程运行
		@Override
		protected void onPostExecute(Bitmap result) {
			// 加载完成
			if (result != null) {
				// 在设置图片之前,确保此图片就是当前imageview需要的图片(因为listview可以重用imageview,导致多个item可能共用一个imageview对象,造成图片加载错误)
				String url = (String) imageView.getTag();
				if (this.url.equals(url)) {
					imageView.setImageBitmap(result);

					// 写本地缓存
					mLocalUtils.setBitmap2Local(url, result);

					// 写内存缓存
					mMemoryCacheUtils.setBitmap2Memory(url, result);

					System.out.println("从网络加载图片啦...");
				}
			}
		}

	}

	/**
	 * 下载图片
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap download(String url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);// 连接超时,没有连接上
			conn.setReadTimeout(5000);// 读取超时,连接上了, 但是服务器迟迟不给响应

			conn.connect();

			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				InputStream in = conn.getInputStream();
				// 通过输入流生成bitmap对象
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				return bitmap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

}
