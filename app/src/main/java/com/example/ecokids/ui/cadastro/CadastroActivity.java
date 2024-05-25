package com.example.ecokids.ui.cadastro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecokids.MainActivity;
import com.example.ecokids.R;
import com.example.ecokids.ui.login.LoginActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private CheckBox showPasswordCheckBox;

    private static final String PREF_NAME_KEY = "user_name";
    private static final String PREF_EMAIL_KEY = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastro);

        nomeEditText = findViewById(R.id.edt_nome_cadastro);
        emailEditText = findViewById(R.id.edt_email_cadastro);
        senhaEditText = findViewById(R.id.edt_senha_cadastro);
        showPasswordCheckBox = findViewById(R.id.ckb_mostrar_senha);

        Button registerButton = findViewById(R.id.btn_registrar);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        Button backButton = findViewById(R.id.btn_voltar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para a LoginActivity
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
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
                    senhaEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // Esconder senha
                    senhaEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void registrar() {
        String nome = nomeEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String senha = senhaEditText.getText().toString().trim();

        // Validar o nome
        if (TextUtils.isEmpty(nome)) {
            Toast.makeText(this, "Por favor, insira um nome válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar o email
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar a senha
        if (TextUtils.isEmpty(senha) || senha.length() < 8) {
            Toast.makeText(this, "A senha deve ter pelo menos 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Se todas as validações passarem, você pode prosseguir com o registro
//        if (email.equals("teste@example.com") && senha.equals("12345678")) {
            Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();

            saveUserData(email, "Nome do Usuário");

            Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finalizar a LoginActivity para que o usuário não possa voltar para ela usando o botão de voltar
//        } else {
//            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
//        }
    }

    // Método de validação de email
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void saveUserData(String email, String nome) {
        SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_NAME_KEY, nome);
        editor.putString(PREF_EMAIL_KEY, email);
        editor.apply();
    }
}
