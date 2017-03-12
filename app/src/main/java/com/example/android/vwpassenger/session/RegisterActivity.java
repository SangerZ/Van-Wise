package com.example.android.vwpassenger.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.vwpassenger.MainActivity;
import com.example.android.vwpassenger.R;
import com.example.android.vwpassenger.api.VanWiseAPIClient;
import com.example.android.vwpassenger.api.VanWiseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText regisUsername;
    private EditText regisPassword;
    private EditText regisDisplayName;
    private EditText regisPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regisUsername = (EditText) findViewById(R.id.regis_username);
        regisPassword = (EditText) findViewById(R.id.regis_password);
        regisDisplayName = (EditText) findViewById(R.id.regis_display_name);
        regisPhone = (EditText) findViewById(R.id.regis_phone);

        Button btRegister = (Button) findViewById(R.id.bt_register);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VanWiseInterface api = VanWiseAPIClient.getClient().create(VanWiseInterface.class);

                String username = regisUsername.getText().toString();
                String passwordHash = PasswordHasher.hashPassword(regisPassword.getText().toString());
                String displayName = regisDisplayName.getText().toString();
                String phone = regisPhone.getText().toString();

                Call<SignUpResponse> call = api.SignUp(username,passwordHash,displayName,phone);
                call.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        SignUpResponse res = response.body();
                        Log.d(TAG, response.toString());
                        if (res.getResult()) {
                            SessionManager.getInstance().createLoginSession(1L, res.getUserID(), "Test");

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),res.getErrorMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });
            }
        });
    }
}
