package com.iShamrock.iMuseum.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iShamrock.iMuseum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 03.08.
 */
public class DrawerAdapter extends BaseAdapter {
    private Context context;
    private List<String> drawerList;
    private List<Integer> iconList;

    public final class ViewHolder {
        LinearLayout layout;
        TextView text;
//        ImageView icon;
    }

    private LayoutInflater mInflater;

    public DrawerAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        //data
        drawerList = new ArrayList<String>();
        drawerList.add("iMuseum");
        drawerList.add("Homepage");
        drawerList.add("Map");
        drawerList.add("Favor");
        drawerList.add("Navigation");
//        drawerList.add("Vision");
    }

    @Override
    public int getCount() {
        return drawerList.size();
    }

    @Override
    public Object getItem(int i) {
        return drawerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            if (i == 0) {
                //title
                view = mInflater.inflate(R.layout.drawer_list_title, null);
                holder.layout = (LinearLayout) view.findViewById(R.id.drawer_title_layout);
                holder.text = (TextView) view.findViewById(R.id.drawer_title_text);
            } else {
                view = mInflater.inflate(R.layout.drawer_list_item, null);
//                holder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
                holder.layout = (LinearLayout) view.findViewById(R.id.drawer_layout);
                holder.text = (TextView) view.findViewById(R.id.drawer_text);
//                holder.icon.setImageDrawable(context.getResources().getDrawable(iconList.get(i)));
            }
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.text.setText(drawerList.get(i));
        return view;
    }
}
