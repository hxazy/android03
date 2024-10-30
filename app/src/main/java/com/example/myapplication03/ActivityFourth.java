package com.example.myapplication03;

import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityFourth extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> items;
    private CustomAdapter adapter;
    private ActionMode actionMode;
    private ArrayList<Boolean> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        listView = findViewById(R.id.listView);

        items = new ArrayList<>();
        items.add(new Item(R.drawable.robot, "one"));
        items.add(new Item(R.drawable.robot, "two"));
        items.add(new Item(R.drawable.robot, "three"));
        items.add(new Item(R.drawable.robot, "four"));
        items.add(new Item(R.drawable.robot, "five"));
        items.add(new Item(R.drawable.robot, "six"));

        selectedItems = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            selectedItems.add(false);
        }

        adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (actionMode == null) {
                actionMode = startActionMode(actionModeCallback);
            }
            toggleSelection(position);
        });
    }

    private void toggleSelection(int position) {
        selectedItems.set(position, !selectedItems.get(position));
        adapter.notifyDataSetChanged();
        updateSelectedCount();
    }

    private void updateSelectedCount() {
        int selectedCountValue = 0;
        for (Boolean selected : selectedItems) {
            if (selected) selectedCountValue++;
        }
        if (actionMode != null) {
            actionMode.setTitle(selectedCountValue + " selected");
        }
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_fourth, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            updateSelectedCount();
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.action_delete) {
                deleteSelectedItems();
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            selectedItems.clear();
            for (int i = 0; i < items.size(); i++) {
                selectedItems.add(false);
            }
            adapter.notifyDataSetChanged();
        }
    };

    private void deleteSelectedItems() {
        for (int i = selectedItems.size() - 1; i >= 0; i--) {
            if (selectedItems.get(i)) {
                items.remove(i);
                selectedItems.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private static class Item {
        int iconResId;
        String text;

        Item(int iconResId, String text) {
            this.iconResId = iconResId;
            this.text = text;
        }
    }

    private class CustomAdapter extends ArrayAdapter<Item> {
        public CustomAdapter(Context context, ArrayList<Item> items) {
            super(context, 0, items);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fourth, parent, false);
            }

            ImageView itemIcon = convertView.findViewById(R.id.item_icon);
            TextView itemText = convertView.findViewById(R.id.item_text);

            Item currentItem = getItem(position);
            if (currentItem != null) {
                itemText.setText(currentItem.text);
                itemIcon.setImageResource(currentItem.iconResId);
            }

            if (selectedItems.get(position)) {
                convertView.setBackgroundColor(0xFF2196F3);
            } else {
                convertView.setBackgroundColor(0xFFFFFFFF);
            }

            return convertView;
        }
    }
}
