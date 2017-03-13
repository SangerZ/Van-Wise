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
import com.example.android.vwpassenger.web_service.SessionResponse;
import com.example.android.vwpassenger.web_service.VanWiseAPIClient;
import com.example.android.vwpassenger.web_service.VanWiseInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int SUCCESS = 1;

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        Button btLogin = (Button) findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VanWiseInterface api = VanWiseAPIClient.getClient().create(VanWiseInterface.class);

                String username = etUsername.getText().toString();
                String password_hash = PasswordHasher.hashPassword(etPassword.getText().toString());

                Call<SessionResponse> call = api.Login(username, password_hash);
                call.enqueue(new Callback<SessionResponse>() {
                    @Override
                    public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                        SessionResponse res = response.body();
                        if (res.getResultCode() == SUCCESS) {
                            UserData ud = res.getUserData();
                            SessionManager.getInstance().createLoginSession(1L, ud.getUserID(), ud.getDisplayName());
                            Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);

                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SessionResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, t.toString());
                    }
                });
            }
        });

        Button toRegister = (Button) findViewById(R.id.bt_to_register);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

//    private boolean isValidUsername(String username) {
//        if (username != null && !username.equals("") && username.length() >= MIN_USERNAME_LENGTH) {
//            return username.matches("[a-zA-Z0-9]");
//        }
//        return false;
//    }
}
