package com.example.ecokids.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecokids.MainActivity;
import com.example.ecokids.R;
import com.example.ecokids.ui.cadastro.CadastroActivity;
import com.example.ecokids.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private CheckBox showPasswordCheckBox;

    private static final String PREF_NAME_KEY = "user_name";
    private static final String PREF_EMAIL_KEY = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        emailEditText = findViewById(R.id.edt_email);
        passwordEditText = findViewById(R.id.edt_senha);
        showPasswordCheckBox = findViewById(R.id.ckb_mostrar_senha);

        Button loginButton = findViewById(R.id.btn_entrar);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Button registerButton = findViewById(R.id.btn_registrar);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar a CadastroActivity
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.btn_voltar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Adicionando funcionalidade ao CheckBox para mostrar/esconder a senha
        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Mostrar senha
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // Esconder senha
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void login() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Verificar se o email e a senha estão vazios
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se o email é válido (pode adicionar validações mais complexas se necessário)
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se a senha tem pelo menos 6 caracteres (pode ajustar o comprimento conforme necessário)
//        if (password.length() < 6) {
//            Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // Se todas as validações passarem, você pode prosseguir com o login
//        if (email.equals("teste@example.com") && password.equals("12345678")) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

            saveUserData(email, "Nome do Usuário");

//            setupUserDetails();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
//        } else {
//            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
//        }
    }

    // Método de validação de email simples
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void saveUserData(String email, String name) {
        SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_NAME_KEY, name);
        editor.putString(PREF_EMAIL_KEY, email);
        editor.apply();
    }

//    private void setupUserDetails() {
//        // Obter referência para o NavigationView
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Obter referência para o header do NavigationView
//        View headerView = navigationView.getHeaderView(0);
//
//        // Obter os TextViews para o nome e email do usuário
//        TextView nameTextView = headerView.findViewById(R.id.nav_header_title);
//        TextView emailTextView = headerView.findViewById(R.id.textView);
//
//        // Obter os dados do usuário dos SharedPreferences
//        SharedPreferences sharedPref = getSharedPreferences("user_data", MODE_PRIVATE);
//        String userName = sharedPref.getString(PREF_NAME_KEY, "");
//        String userEmail = sharedPref.getString(PREF_EMAIL_KEY, "");
//
//        // Definir o texto dos TextViews com os dados do usuário
//        nameTextView.setText(userName);
//        emailTextView.setText(userEmail);
//    }
}
