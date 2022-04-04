package com.example.oatsv5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.100.141:8000/";
    Button btnStudent;
    Button btnGuest;
    Button btnAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitInterface = retrofit.create(RetrofitInterface.class);
//        addListenerOnButton();
//        addListenerOnButton2();
//        addListenerOnButton3();
        getSupportActionBar().hide();

        findViewById(R.id.btnlogGuest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginGuestDialog();
            }
        });
        findViewById(R.id.btnRegGuest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegDialog();
            }
        });

    }

    private void handleLoginGuestDialog() {
        View view = getLayoutInflater().inflate(R.layout.login_guest_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button loginBtn = view.findViewById(R.id.loginGuest);
        EditText GemailEdit = view.findViewById(R.id.editLogGEmail);
        EditText GpasswordEdit = view.findViewById(R.id.editLogGPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("guest_mail", GemailEdit.getText().toString());
                map.put("guest_password", GpasswordEdit.getText().toString());

                Call<LoginGuestResult> call =retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<LoginGuestResult>() {
                    @Override
                    public void onResponse(Call<LoginGuestResult> call, Response<LoginGuestResult> response) {
                        if (response !=null && response.isSuccessful()){
                            LoginGuestResult result = response.body();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AuthActivity.this);
                            builder1.setTitle(result.getEmail());
                            builder1.setMessage(result.getPassword());

                            builder1.show();
                            Toast.makeText(AuthActivity.this,"Logged in  Successfully !", Toast.LENGTH_LONG).show();

                            startActivity( new Intent(AuthActivity.this,ThesesViewActivity.class));
                            finish();
                        } else if (response.code() == 500) {
                            Toast.makeText(AuthActivity.this,"Wrong Credentials!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginGuestResult> call, Throwable t) {
                        Toast.makeText(AuthActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void handleRegDialog() {
        View view = getLayoutInflater().inflate(R.layout.register_guest_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button regGuest = view.findViewById(R.id.regG);
        EditText regGfname = view.findViewById(R.id.editRegGFname);
        EditText regGlname = view.findViewById(R.id.editRegGLname);
        EditText regGcontact = view.findViewById(R.id.editRegGContact);
        EditText regGprof = view.findViewById(R.id.editRegGProf);
        EditText regGcomp = view.findViewById(R.id.editRegGComp);
        EditText regGcompadd = view.findViewById(R.id.editRegGCompAdd);
        EditText regGmail = view.findViewById(R.id.editRegGEmail);
        EditText regGPass = view.findViewById(R.id.editRegGPass);

        regGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("guest_fname", regGfname.getText().toString());
                map.put("guest_lname", regGlname.getText().toString());
                map.put("guest_contact", regGcontact.getText().toString());
                map.put("guest_profession", regGprof.getText().toString());
                map.put("guest_company", regGcomp.getText().toString());
                map.put("guest_company_address", regGcompadd.getText().toString());
                map.put("guest_mail", regGmail.getText().toString());
                map.put("passwords", regGPass.getText().toString());

                Call<Void> call = retrofitInterface.executeRegister(map);
                call.enqueue(new Callback<Void>() {
                    private Call<Void> call;
                    private Response<Void> response;

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        this.call = call;
                        this.response = response;
                        if (response !=null && response.isSuccessful()){
                            Toast.makeText(AuthActivity.this,"Registered Successfully !", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 500) {
                        Toast.makeText(AuthActivity.this,"Account Already Registered!", Toast.LENGTH_LONG).show();
                        }else if (response.code() == 400){
                            Toast.makeText(AuthActivity.this,response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AuthActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}



//
//        Button btnGuest = findViewById(R.id.btnGuest);
//        btnGuest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View g) {
//                Toast.makeText(getApplicationContext(),"Login As Guest",Toast.LENGTH_SHORT).show();
//                Intent guestlogin = new Intent(getApplicationContext(), GuestLogin.class);
//                startActivity(guestlogin);
//            }
//        });
//
//        Button btnAdmin = findViewById(R.id.btnAdmin);
//        btnAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View a) {
//                Toast.makeText(getApplicationContext(),"Login As Admin",Toast.LENGTH_SHORT).show();
//                Intent adminlogin = new Intent(getApplicationContext(), AdminLogin.class);
//                startActivity(adminlogin);
//            }
//        });
//    }

//    private void addListenerOnButton() {
//        final Context context = this;
//
//        btnStudent = (Button) findViewById(R.id.btnStudent);
//
//        btnStudent.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                Intent intent = new Intent(context, StudentLogin.class);
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"Login As Student",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//        private void addListenerOnButton2() {
//            final Context context = this;
//
//            btnGuest = (Button) findViewById(R.id.btnGuest);
//
//            btnGuest.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg1) {
//
//                    Intent intent = new Intent(context, GuestLogin.class);
//                    startActivity(intent);
//                    Toast.makeText(getApplicationContext(),"Login As Guest",Toast.LENGTH_SHORT).show();
//                }
//
//            });
//        }
//
//    private void addListenerOnButton3() {
//
//        final Context context = this;
//
//        btnAdmin = (Button) findViewById(R.id.btnAdmin);
//
//        btnAdmin.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg2) {
//
//                Intent intent = new Intent(context, AdminLogin.class);
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"Login As Admin",Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }


//}

