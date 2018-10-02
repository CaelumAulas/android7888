package br.com.caelum.casadocodigo.interfaces;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.modelo.Livro;

public interface LivroDelegate {
    void lidaComClickNo(Livro livro);

    void lidaCom(ArrayList<Livro> livros);

    void lidaCom(Throwable erro);
}
