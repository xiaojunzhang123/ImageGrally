package com.zxj.imagegrally.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxj.imagegrally.R;
import com.zxj.imagegrally.activity.ImagePagerActivity;
import com.zxj.imagegrally.bean.MyBean;
import com.zxj.imagegrally.view.NoScrollGridView;

import java.util.ArrayList;

/**
 * Created by apple on 16/9/13.
 */
public class MyListAdapter extends BaseAdapter {
    private ArrayList<MyBean> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MyListAdapter(Context context,ArrayList<MyBean> list) {
        mInflater = LayoutInflater.from(context);
        mContext=context;
        this.mList=list;
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public MyBean getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).id;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);

            holder.avator=(ImageView)convertView.findViewById(R.id.avator);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.gridView=(NoScrollGridView)convertView.findViewById(R.id.gridView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final MyBean bean = getItem(position);

        ImageLoader.getInstance().displayImage(bean.avator,holder.avator);

        holder.name.setText(bean.name);
        holder.content.setText(bean.content);

        if(bean.urls!=null && bean.urls.length>0){
            holder.gridView.setVisibility(View.VISIBLE);

            holder.gridView.setAdapter(new MyGridAdapter(bean.urls,mContext));

            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    imageBrower(position,bean.urls);
                }
            });

        }else{
            holder.gridView.setVisibility(View.GONE);
        }


        return convertView;
    }

    private void imageBrower(int position,String[] urls){
        Intent intent=new Intent(mContext, ImagePagerActivity.class);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,urls);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,position);
        mContext.startActivity(intent);


    }

    static class ViewHolder{
        TextView name;
        ImageView avator;
        TextView content;
        NoScrollGridView gridView;
    }
}
