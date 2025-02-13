package com.viacaovv.trabalhoengenharia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="onibus")
public class Onibus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_onibus")
    private int id;

    @Column(name="placa")
    private String placa;

    @Column(name="quant_poltronas")
    private int quantPoltronas;

    // mappedBy = "onibus" refere-se ao atributo onibus na classe Viagem
    @OneToMany(mappedBy = "onibus",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                       CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Viagem> viagens;

    public Onibus() {

    }

    // id eh definido pelo MySQL
    public Onibus(String placa, int quantPoltronas) {
        this.placa = placa;
        this.quantPoltronas = quantPoltronas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getQuantPoltronas() {
        return quantPoltronas;
    }

    public void setQuantPoltronas(int quantPoltronas) {
        this.quantPoltronas = quantPoltronas;
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

        viagem.setOnibus(this);

    }
}
