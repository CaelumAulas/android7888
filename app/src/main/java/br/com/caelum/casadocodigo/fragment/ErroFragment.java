package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ErroFragment extends Fragment {

    public static final String ERRO = "erro";

    public static ErroFragment com(Throwable erro) {

        ErroFragment fragment = new ErroFragment();

        Bundle arguments = new Bundle();

        arguments.putSerializable(ERRO, erro);
        fragment.setArguments(arguments);

        return fragment;
    }

    @BindView(R.id.fragment_erro_mensagem)
    TextView msgErro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_erro, container, false);

        ButterKnife.bind(this, view);

        Throwable erro = (Throwable) getArguments().get(ERRO);
        msgErro.setText(erro.getMessage());


        return view;

    }
}
