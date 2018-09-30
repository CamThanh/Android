package com.camthanh.vn.camthanhbook.signinup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.camthanh.vn.camthanhbook.MainActivity;
import com.camthanh.vn.camthanhbook.R;
import com.camthanh.vn.camthanhbook.rest.WebService;

public class SignInActivity extends Activity {
    private TabLayout tabLayout;
    private LinearLayout layoutRegister, layoutLogin;
    //Register form
    EditText editTextNameRe;
    EditText editTextPassRe;
    EditText editTextRePassRe;
    EditText editTextEmailRe;
    EditText editPhoneRe;
    EditText editFirstNameRe;
    EditText editLastNameRe;
    Button buttonRegister;
    //Login form
    EditText editTextNameSign;
    EditText editTextPasSign;
    Button buttonSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        layoutRegister = findViewById(R.id.layoutRegister);
        layoutLogin = findViewById(R.id.layoutLogin);
        tabLayout = findViewById(R.id.tabLayout);
        editTextNameRe = findViewById(R.id.editTextNameRe);
        editTextPassRe = findViewById(R.id.editTextPassRe);
        editTextRePassRe = findViewById(R.id.editTextRePassRe);
        editTextEmailRe = findViewById(R.id.editTextEmailRe);
        editPhoneRe = findViewById(R.id.editPhoneRe);
        editFirstNameRe = findViewById(R.id.editFirstNameRe);
        editLastNameRe = findViewById(R.id.editLastNameRe);
        editTextNameSign = findViewById(R.id.editTextNameSign);
        editTextPasSign = findViewById(R.id.editTextPasSign);
        setTestValue();
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccout();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        layoutLogin.setVisibility(View.VISIBLE);
                        layoutRegister.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        layoutLogin.setVisibility(View.INVISIBLE);
                        layoutRegister.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void registerAccount() {
        String name = editTextNameRe.getText().toString();
        String pass = editTextPassRe.getText().toString();
        String rePass = editTextRePassRe.getText().toString();
        String email = editTextEmailRe.getText().toString();
        String phone = editPhoneRe.getText().toString();
        String first = editFirstNameRe.getText().toString();
        String last = editLastNameRe.getText().toString();
        if (name.equals("") || pass.equals("") || rePass.equals("") || email.equals("") || phone.equals("") || first.equals("") || last.equals("") || !pass.equals(rePass)) {
            Toast.makeText(getApplicationContext(), "Thông tin chưa hợp lệ", Toast.LENGTH_LONG).show();
        }
        WebService.getInstance().registerUser(name, pass, email, phone, first, last, "M", 18, new WebService.ServiceCallBack() {
            @Override
            public void onSuccess(Object object) {
                Toast.makeText(getApplicationContext(), "Đăng Ký Thành Công", Toast.LENGTH_LONG).show();
                goToMainPage();
            }

            @Override
            public void onFailure(Object t) {
                Toast.makeText(getApplicationContext(), "Đăng Ký Thất Bại", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginAccout() {
        String user = editTextNameSign.getText().toString();
        String pass = editTextPasSign.getText().toString();
        WebService.getInstance().loginUser(user, pass, new WebService.ServiceCallBack() {
            @Override
            public void onSuccess(Object object) {
                Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                goToMainPage();
            }

            @Override
            public void onFailure(Object t) {
                Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToMainPage(){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void setTestValue() {
        editTextNameRe.setText("tamtam");
        editTextRePassRe.setText("12345");
        editTextPassRe.setText("12345");
        editTextEmailRe.setText("tam@gmail.com");
        editPhoneRe.setText("123456");
        editFirstNameRe.setText("huynh");
        editLastNameRe.setText("tam");
    }

}
