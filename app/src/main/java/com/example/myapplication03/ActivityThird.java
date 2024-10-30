package com.example.myapplication03;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityThird extends AppCompatActivity {

    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        testTextView = findViewById(R.id.test_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_third, menu);
        return true;
    }

    public class MyConstants {
        public static final int font_size_small = 1000002;
        public static final int font_size_medium = 1000003;
        public static final int font_size_large = 1000004;
        public static final int color_red = 1000005;
        public static final int color_black = 1000006;
        public static final int menu_item_toast = 1000007;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MyConstants.font_size_small:
                testTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                return true;
            case MyConstants.font_size_medium:
                testTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                return true;
            case MyConstants.font_size_large:
                testTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                return true;
            case MyConstants.color_red:
                testTextView.setTextColor(Color.RED);
                return true;
            case MyConstants.color_black:
                testTextView.setTextColor(Color.BLACK);
                return true;
            case MyConstants.menu_item_toast:
                Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
