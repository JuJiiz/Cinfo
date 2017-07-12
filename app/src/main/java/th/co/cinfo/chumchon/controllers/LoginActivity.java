package th.co.cinfo.chumchon.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.*;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText etUsername, etPassword;
    String pUsername, pPassword;
    ModelLogin modelLogin;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        modelLogin = new ModelLogin();

        btnLogin.setOnClickListener(this);
    }

    private void init() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            try {
                pUsername = etUsername.getText().toString();
                pPassword = Sha1Hash.SHA1(etPassword.getText().toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String loginStatus = ModelLogin.getByName(pUsername, pPassword,"status");
            String loginToken = ModelLogin.getByName(pUsername, pPassword,"token");

            SharedPreferences sp = getSharedPreferences("myToken", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("strToken", loginToken);
            editor.commit();

            //Toast.makeText(this,"Login Token: " + sp.getString("strToken", "NoData"), Toast.LENGTH_SHORT).show();

            if(loginStatus.equals("OK")){
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Status: " + loginStatus, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
