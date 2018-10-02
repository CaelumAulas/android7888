package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesLivroFragment extends Fragment {

    public static final String LIVRO = "livro";
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
    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;
    private Livro livro;


    public static DetalhesLivroFragment com(Livro livro) {


        DetalhesLivroFragment detalhes = new DetalhesLivroFragment();

        Bundle argumentos = new Bundle();
        argumentos.putSerializable(LIVRO, livro);
        detalhes.setArguments(argumentos);

        return detalhes;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.livro = getLivro();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(livro.getNome());

        activity.getSupportActionBar().setSubtitle(livro.getISBN());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);

        ButterKnife.bind(this, view);


        nome.setText(livro.getNome());
        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled(true);

        picasso.load(livro.getUrlFoto()).placeholder(R.drawable.livro).fit().into(foto);

        return view;

    }

    private Livro getLivro() {
        Bundle arguments = getArguments();
        return (Livro) arguments.get(LIVRO);
    }
}
