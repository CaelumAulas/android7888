package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    public static final String LIVROS = "livros";
    @BindView(R.id.lista_livros)
    RecyclerView lista;

    public static ListaLivrosFragment com(ArrayList<Livro> livros) {

        ListaLivrosFragment fragment = new ListaLivrosFragment();
        Bundle arguments = new Bundle();

        arguments.putSerializable(LIVROS, livros);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        Bundle argumentos = getArguments();
        ArrayList<Livro> livros = (ArrayList<Livro>) argumentos.get(LIVROS);

        lista.setLayoutManager(new LinearLayoutManager(getContext()));

        lista.setAdapter(new LivroAdapter(livros));


        return view;


    }
}
