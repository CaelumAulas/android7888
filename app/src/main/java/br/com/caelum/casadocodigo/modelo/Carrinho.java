package br.com.caelum.casadocodigo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho implements Serializable {

    private List<Item> itens = new ArrayList<>();

    public void adiciona(Item item) {
        itens.add(item);
    }

    public void remove(Item item) {
        itens.remove(item);
    }

    public void limpa() {
        itens.clear();
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public BigDecimal getValorTotal() {
        BigDecimal valor = new BigDecimal("0.00");

        for (Item item : itens) {
            valor = valor.add(new BigDecimal(item.getValor()));
        }

        valor.setScale(2, RoundingMode.HALF_UP);
        return valor;

    }
}
