package com.liu.lxf.myapplication.view;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.lxf.myapplication.R;
import com.liu.lxf.myapplication.bean.ItemBean;
import com.liu.lxf.myapplication.model.MyBitmapHelper;

/**
 *  * @author liuxiangfei
 */
public class MyAdapter extends BaseAdapter {
	private List<ItemBean> list;
	private Context context;
	private LayoutInflater layoutInflater;
	private MyBitmapHelper myBitmapHelper;

	public MyAdapter(Context context,  List<ItemBean> listBean) {
		 myBitmapHelper = new MyBitmapHelper();
		this.context = context;
		this.list = listBean;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ViewHolder holder = null;
		if (convertView == null) {
			view = layoutInflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.tv_des = (TextView) view.findViewById(R.id.tv_des);
			holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
			holder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.tv_title.setText(list.get(position).getTitle());
		holder.tv_des.setText(list.get(position).getDescription());
		if(!TextUtils.isEmpty(list.get(position).getImageHref())){
			myBitmapHelper.display(holder.iv_pic,list.get(position).getImageHref());
		}
		return view;
	}

	static class ViewHolder {
		TextView tv_des;
		TextView tv_title;
		ImageView iv_pic;
	}

}
