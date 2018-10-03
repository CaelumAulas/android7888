package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Inject
    Carrinho carrinho;

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

        CasaDoCodigoApplication.getInstance().getComponent().inject(this);


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

    @OnClick({R.id.detalhes_livro_comprar_ambos,
            R.id.detalhes_livro_comprar_ebook,
            R.id.detalhes_livro_comprar_fisico})
    public void addLivro(View view) {

        Snackbar.make(dataPublicacao, "Adicionado", Snackbar.LENGTH_LONG).show();
        carrinho.adiciona(new Item(livro, tipoDeCompra(view)));


    }

    private TipoDeCompra tipoDeCompra(View view) {
        switch (view.getId()) {
            case R.id.detalhes_livro_comprar_ambos:
                return TipoDeCompra.JUNTOS;
            case R.id.detalhes_livro_comprar_ebook:
                return TipoDeCompra.VIRTUAL;
            default:
                return TipoDeCompra.FISICO;
        }
    }

    private Livro getLivro() {
        Bundle arguments = getArguments();
        return (Livro) arguments.get(LIVRO);
    }
}
