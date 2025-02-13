package com.viacaovv.trabalhoengenharia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="motorista")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_motorista")
    private int id;

    @Column(name="nome")
    private String nome;

    @Min(value=21, message="O motorista deve ter no mínimo 21 anos de idade")
    @Max(value=75, message="O motorista não pode ter mais do que 75 anos de idade")
    @Column(name="idade")
    private int idade;

    @Column(name="n_habilitacao")
    private String nHabilitacao;

    // mappedBy = "motorista" refere-se ao atributo motorista na classe Viagem
    @OneToMany(mappedBy = "motorista",
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Viagem> viagens;

    public Motorista() {

    }

    // id eh definido pelo MySQL
    public Motorista(String nome, int idade, String nHabilitacao) {
        this.nome = nome;
        this.idade = idade;
        this.nHabilitacao = nHabilitacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getnHabilitacao() {
        return nHabilitacao;
    }

    public void setnHabilitacao(String nHabilitacao) {
        this.nHabilitacao = nHabilitacao;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public void addViagem(Viagem viagem) {

        if (viagens == null) {
            viagens = new ArrayList<>();
        }

        viagens.add(viagem);

        viagem.setMotorista(this);

    }

}
