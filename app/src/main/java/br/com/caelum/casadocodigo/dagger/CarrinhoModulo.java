package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class CarrinhoModulo {

    @Singleton
    @Provides
    public Carrinho getCarrinho() {
        return new Carrinho();
    }
}
