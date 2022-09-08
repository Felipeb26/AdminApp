package com.bats.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bats.admin.utils.ToastNote;

public class SingUp extends AppCompatActivity {

    private EditText inputUser, inputPass, inputMail;
    private String user, mail, pass;
    private Button btnSaveUser;
    private final int eye_slash = R.drawable.ic_eye_slash;
    private final int eye = R.drawable.ic_eye;
    ToastNote note = new ToastNote();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        initComponents();
        saveUser();
        exposePass();
    }

    private void initComponents() {
        inputUser = findViewById(R.id.input_username_singup);
        inputPass = findViewById(R.id.input_password_singup);
        inputMail = findViewById(R.id.input_email_singup);
        btnSaveUser = findViewById(R.id.btn_saveUser);
        inputPass.setTransformationMethod(new PasswordTransformationMethod());
    }

    private void saveUser() {
        btnSaveUser.setOnClickListener((View) -> {
            verifiyUser();
        });
    }

    private void verifiyUser() {
        user = inputUser.getText().toString();
        mail = inputMail.getText().toString();
        pass = inputPass.getText().toString();
        if (user.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
            note.toastNotification(SingUp.this, "todos os campos devem ser preenchhidos");
        } else {
            note.toastNotification(SingUp.this, "usuario salvo com sucesso");
            Intent intent = new Intent(SingUp.this,Login.class);
            startActivity(intent);
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    private void exposePass(){
        inputPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (inputPass.getRight() - inputPass.getCompoundDrawables()[2].getBounds().width())) {
                        if (inputPass.getTransformationMethod() == null) {
                            inputPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, eye, 0);
                            inputPass.setTransformationMethod(new PasswordTransformationMethod());
                        } else {
                            inputPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, eye_slash, 0);
                            inputPass.setTransformationMethod(null);
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
}