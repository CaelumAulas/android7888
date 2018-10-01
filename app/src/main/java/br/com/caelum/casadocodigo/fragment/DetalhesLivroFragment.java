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
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesLivroFragment extends Fragment {

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;
    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;
    @BindView(R.id.detalhes_livro_autores)
    TextView autores;
    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;
    @BindView(R.id.detalhes_livro_num_paginas)
    TextView numeroPaginas;
    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;


    public static DetalhesLivroFragment com(Livro livro) {


        DetalhesLivroFragment detalhes = new DetalhesLivroFragment();

        Bundle argumentos = new Bundle();
        argumentos.putSerializable("livro", livro);
        detalhes.setArguments(argumentos);

        return detalhes;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);

        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        Livro livro = (Livro) arguments.get("livro");

        nome.setText(livro.getNome());

        return view;

    }
}
