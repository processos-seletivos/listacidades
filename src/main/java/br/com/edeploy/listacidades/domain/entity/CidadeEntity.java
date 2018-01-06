package br.com.edeploy.listacidades.domain.entity;

import br.com.edeploy.listacidades.domain.Cidade;

/**
 * Created by bruno on 06/01/18.
 */
public class CidadeEntity implements Cidade {
    private String nome;
    private String estado;

    public CidadeEntity(){}

    public CidadeEntity(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
