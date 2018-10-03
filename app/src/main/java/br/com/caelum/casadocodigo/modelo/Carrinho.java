package br.com.caelum.casadocodigo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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

    private BigDecimal getValorTotal() {
        BigDecimal valor = new BigDecimal("0.00");

        for (Item item : itens) {
            valor = valor.add(new BigDecimal(item.getValor()));
        }

        valor.setScale(2, RoundingMode.HALF_UP);
        return valor;

    }


    public String getValor() {
        NumberFormat format = DecimalFormat.
                getCurrencyInstance(
                        new Locale("pt", "BR"));

        return format.format(getValorTotal());
    }
}
