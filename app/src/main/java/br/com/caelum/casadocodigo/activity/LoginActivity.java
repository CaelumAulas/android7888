package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText campoLogin;
    @BindView(R.id.login_senha)
    EditText campoSenha;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            logaUsuario();
        }
    }

    @OnClick(R.id.login_novo)
    public void criaNovoUsuario(Button button) {


        String texto = button.getText().toString();

        naoPodeClicarNoBotao(button);

        String email = campoLogin.getText().toString();
        String senha = campoSenha.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(trataRespostaFirebase(button, texto));

    }


    @OnClick(R.id.login_logar)
    public void fazLogin(Button button) {
        String texto = button.getText().toString();

        naoPodeClicarNoBotao(button);

        String email = campoLogin.getText().toString();
        String senha = campoSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(trataRespostaFirebase(button, texto));

    }

    @NonNull
    private void naoPodeClicarNoBotao(Button button) {
        button.setText("Carregando");
        button.setClickable(false);
        button.setBackgroundColor(Color.GRAY);
    }


    @NonNull
    private OnCompleteListener<AuthResult> trataRespostaFirebase(final Button button, final String texto) {
        return new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                podeClicarNo(button, texto);

                if (task.isSuccessful()) {
                    logaUsuario();
                } else {
                    trataErro(task.getException());
                }

            }
        };
    }

    private void podeClicarNo(Button button, String texto) {
        button.setClickable(true);
        button.setText(texto);
        button.setBackgroundColor(getColor(R.color.colorPrimaryDark));
    }

    private void trataErro(Exception erro) {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.casadocodigo)
                .setMessage(erro.getMessage())
                .setTitle("Tivemos problema")
                .show();
    }

    private void logaUsuario() {
        Intent vaiParaMain = new Intent(this, MainActivity.class);
        startActivity(vaiParaMain);
        finish();
    }
}
