package com.asazing.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText inputEmail, inputPass;
    Button btnLogin;
    TextView goToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        goToRegister = findViewById(R.id.gotoRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth("http://192.168.1.11/api/login/auth.php");
            }
        });
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void auth(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña INCORRECA", Toast.LENGTH_SHORT).show();
                }

            }
            }, new Response.ErrorListener(){
             public void onErrorResponse(VolleyError error){
                 System.out.println("Error : " + error.toString() );
                 Toast.makeText(MainActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
             }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", inputEmail.getText().toString());
                params.put("pass", inputPass.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}