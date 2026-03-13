package br.com.senac.pessoa_api.entidades;

import jakarta.persistence.*;

@Entity
public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private  String nome;

    @Column
    private  String sobenome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobenome() {
        return sobenome;
    }

    public void setSobenome(String sobenome) {
        this.sobenome = sobenome;
    }
}
