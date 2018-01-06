package br.com.edeploy.listacidades.domain.dto;

import br.com.edeploy.listacidades.domain.Cidade;

/**
 * Created by bruno on 06/01/18.
 */
public class CidadeDTO implements Cidade{

    String nome;
    String estado;

    public CidadeDTO() {}
    public CidadeDTO(Cidade cidade){
        this(cidade.getNome(), cidade.getEstado());
    }

    public CidadeDTO(String nome, String estado){
        this.nome = nome;
        this.estado = estado;
    }
    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getEstado() {
        return estado;
    }
}
