package com.example.nguyenvantung.place.Adapter.ContextMenuForItemRecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenvantung.place.Model.ObjectModel.ContextMenuForItemRecyclerviewModel;
import com.example.nguyenvantung.place.R;

import java.util.List;

public class ContextMenuAdapter extends BaseAdapter {
    private Context context;
    private List<ContextMenuForItemRecyclerviewModel> listItem;
    private LayoutInflater layoutInflater;

    public ContextMenuAdapter(Context context, List<ContextMenuForItemRecyclerviewModel> listItem){
        this.context = context;
        this.listItem = listItem;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = layoutInflater.inflate(R.layout.item_contextmenu, viewGroup, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);

            viewHolder.item_contextmenu_img_icon = view.findViewById(R.id.item_contextmenu_img_icon);
            viewHolder.item_contextmenu_title = view.findViewById(R.id.item_contextmenu_title);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.item_contextmenu_img_icon.setImageResource(listItem.get(i).getIcon());
        viewHolder.item_contextmenu_title.setText(listItem.get(i).getTitle());


        return null;
    }

    public class ViewHolder{
        ImageView item_contextmenu_img_icon;
        TextView item_contextmenu_title;
        View view;
    }
}
