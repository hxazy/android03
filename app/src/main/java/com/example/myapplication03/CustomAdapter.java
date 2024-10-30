package com.example.myapplication03;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class CustomAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater inflater;
    private int selectedPosition = -1;

    public CustomAdapter(Context context, List<Map<String, Object>> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.title);
        ImageView image = convertView.findViewById(R.id.image);

        title.setText((String) data.get(position).get("title"));
        image.setImageResource((int) data.get(position).get("image"));

        if (position == selectedPosition) {
            convertView.setBackgroundColor(0xFF8B0000);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }
}
