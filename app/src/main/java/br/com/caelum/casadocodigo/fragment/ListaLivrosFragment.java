package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;
import com.mugen.attachers.BaseAttacher;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.adapter.LivroInvertidoAdapter;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    public static final String LIVROS = "livros";
    @BindView(R.id.lista_livros)
    RecyclerView lista;

    private boolean carregando;
    private ArrayList<Livro> livros;
    private FirebaseRemoteConfig remoteConfig;

    public static ListaLivrosFragment com(ArrayList<Livro> livros) {

        ListaLivrosFragment fragment = new ListaLivrosFragment();
        Bundle arguments = new Bundle();

        arguments.putSerializable(LIVROS, livros);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        criaEConfiguraRemoteConfig();
    }

    private void criaEConfiguraRemoteConfig() {
        remoteConfig = FirebaseRemoteConfig.getInstance();

        remoteConfig.setDefaults(R.xml.remote_config);

        remoteConfig.fetch(15).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    remoteConfig.activateFetched();
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();


        configuraActionBar();

        atualizaLista();
    }

    private void atualizaLista() {
        if (remoteConfig.getBoolean("idioma")) {
            lista.setAdapter(new LivroInvertidoAdapter(livros));
        } else {
            lista.setAdapter(new LivroAdapter(livros));
        }
    }

    private void configuraActionBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("Catalogo");
        activity.getSupportActionBar().setSubtitle("");
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        Bundle argumentos = getArguments();
        livros = (ArrayList<Livro>) argumentos.get(LIVROS);

        lista.setLayoutManager(new LinearLayoutManager(getContext()));



        BaseAttacher attacher = Mugen.with(lista, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                new WebClient().buscaLivros(5, livros.size());
                carregando = true;

                Snackbar.make(lista, "Carregando mais livros", Snackbar.LENGTH_SHORT)
                        .show();
            }

            @Override
            public boolean isLoading() {
                return carregando;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        });


        attacher.setLoadMoreEnabled(true);

        attacher.start();

        return view;

    }


    public void atualizaLista(ArrayList<Livro> livros) {
        carregando = false;

        this.livros.addAll(livros);

        this.lista.getAdapter().notifyDataSetChanged();
    }
}
