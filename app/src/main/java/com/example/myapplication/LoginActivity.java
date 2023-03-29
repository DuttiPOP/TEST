package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailString = etEmail.getText().toString();
                String passwordString = etPassword.getText().toString();

                if (emailString.equals("123") && passwordString.equals("123")){
                    String jsonResponse = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";

                    try {
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        String name = jsonObject.getString("name");
                        int age = jsonObject.getInt("age");
                        String email = jsonObject.getString("email");

                        saveUserData(name, age, email);

                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Ошибка при обработке JSON", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveUserData(String name, int age, String email) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putInt("age", age);
        editor.putString("email", email);
        editor.putBoolean("is_logged_in", true);
        editor.apply();
    }

}
