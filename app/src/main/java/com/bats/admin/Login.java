package com.bats.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.bats.admin.utils.ToastNote;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    private EditText inputUser, inputPass;
    private String username, password;
    private Button btnLogin, btnSingUp;
    private final int eye_slash = R.drawable.ic_eye_slash;
    private final int eye = R.drawable.ic_eye;
    private String useLogin = "felipe";
    private String pass = "2626";
    ToastNote note = new ToastNote();

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        isLogged();
        btnsClick();
        login();
    }

    public void initComponents() {
        inputUser = findViewById(R.id.input_username);
        inputPass = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSingUp = findViewById(R.id.btn_singUp);
        inputPass.setTransformationMethod(new PasswordTransformationMethod());
    }

    private void isLogged() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.key_login), Context.MODE_PRIVATE);
        String nome = preferences.getString("username", "");
        String senha = preferences.getString("pass", "");
        if (!nome.isEmpty() && !senha.isEmpty()) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setTitle("Autenticar");
            builder.setMessage("Authenticar com biometria ou senha?");
            builder.setCancelable(false);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Biometria", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    biometricLogin();
                }
            });
            builder.setNegativeButton("senha", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create();
            builder.show();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void login() {
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

    public void btnsClick() {
        btnLogin.setOnClickListener((View) -> {
            username = inputUser.getText().toString();
            password = inputPass.getText().toString();
            checkInputs(username, password);
        });
        btnSingUp.setOnClickListener((View) -> {
            Intent intent = new Intent(Login.this, SingUp.class);
            startActivity(intent);
        });
    }

    private void checkInputs(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            note.toastNotification(Login.this, "Ambos campos precisam ser informados");
            inputPass.setText(null);
            inputUser.setText(null);
            return;
        }
        if (username.equals(useLogin) && password.equals(pass)) {
            SharedPreferences preferences = getSharedPreferences(getString(R.string.key_login), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("pass", password);
            editor.commit();
            note.toastNotification(Login.this, "USUARIO CORRETO");
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            return;
        } else {
            note.toastNotification(Login.this,
                    "Login incorreto! entre em contato abaixo ou crie uma conta abaixo.");
            inputPass.setText(null);
            inputUser.setText(null);
            return;
        }
    }

    private void biometricLogin() {
        BiometricManager manager = BiometricManager.from(Login.this);
        switch (manager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                note.toastNotification(Login.this, "sem biometria no aparelho");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                note.toastNotification(Login.this, "biometria nao funciona");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                note.toastNotification(Login.this, "sem biometria no pre autenticada");
                break;
        }
        Executor executor = ContextCompat.getMainExecutor(Login.this);
        biometricPrompt = new BiometricPrompt(Login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                note.toastNotification(Login.this, "logado certo");
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("teste de fingerprint")
                .setDescription("agr ainda mais securo")
                .setConfirmationRequired(true)
                .setNegativeButtonText("digitar a senha")
                .build();
        biometricPrompt.authenticate(promptInfo);
    }

}