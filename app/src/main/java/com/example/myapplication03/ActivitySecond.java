package com.example.myapplication03;

import static com.example.myapplication03.R.id.cancel_button;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showCustomDialog();
    }
    private void showCustomDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        EditText usernameEditText = dialogView.findViewById(R.id.username);
        EditText passwordEditText = dialogView.findViewById(R.id.password);
        Button cancelButton = dialogView.findViewById(cancel_button);
        Button signInButton = dialogView.findViewById(R.id.sign_in_button);


        AlertDialog dialog = builder.create();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
