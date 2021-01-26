package com.asazing.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText inputNameR, inputLastNameR, inputEmailR, inputPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        inputNameR = findViewById(R.id.inputNameRegister);
        inputLastNameR = findViewById(R.id.inputLastNameRegister);
        inputEmailR = findViewById(R.id.inputEmailRegister);
        inputPassword = findViewById(R.id.inputPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register("http://192.168.1.11/api/login/register.php");
            }
        });
    }

    private void register(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Se registro Correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Error al Registrar", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error){
                System.out.println("Error : " + error.toString() );
                Toast.makeText(RegisterActivity.this, "Error :" + error, Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", inputNameR.getText().toString());
                params.put("lastname", inputLastNameR.getText().toString());
                params.put("email", inputEmailR.getText().toString());
                params.put("pass", inputPassword.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}