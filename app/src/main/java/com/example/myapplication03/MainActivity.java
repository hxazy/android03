package com.example.myapplication03;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String selectedItemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ListView listView = findViewById(R.id.listView);

        List<Map<String, Object>> data = new ArrayList<>();
        int[] images = {
                R.drawable.lion,
                R.drawable.tiger,
                R.drawable.monkey,
                R.drawable.dog,
                R.drawable.cat,
                R.drawable.elephant,
        };

        String[] texts = {
                "lion",
                "tiger",
                "monkey",
                "dog",
                "cat",
                "elephant"
        };
        for (int i = 0; i < texts.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("title", texts[i]);
            item.put("image", images[i]);
            data.add(item);
        }

        CustomAdapter adapter = new CustomAdapter(this, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) data.get(position).get("title");
                Toast.makeText(MainActivity.this, selectedItemText, Toast.LENGTH_SHORT).show();

                adapter.setSelectedPosition(position);
            }
        });
    }
}
