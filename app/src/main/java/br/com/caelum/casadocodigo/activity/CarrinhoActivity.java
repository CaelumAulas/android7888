package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {


    @BindView(R.id.lista_itens_carrinho)
    RecyclerView itens;

    @BindView(R.id.valor_carrinho)
    TextView valor;

    private Carrinho carrinho = new Carrinho();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);


        itens.setAdapter(new ItensAdapter(carrinho.getItens(), this));

        valor.setText("R$ "+ carrinho.getValorTotal());
    }
}
