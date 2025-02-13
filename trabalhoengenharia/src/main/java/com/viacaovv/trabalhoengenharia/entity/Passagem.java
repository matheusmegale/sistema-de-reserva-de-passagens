package com.viacaovv.trabalhoengenharia.entity;

import jakarta.persistence.*;

@Entity
@Table(name="passagem")
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_passagem")
    private int idPassagem;

    @OneToOne
    @JoinColumn(name="id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name="id_cliente")
    private int idCliente;

    @ManyToOne
    @JoinColumn(name="id_viagem", insertable = false, updatable = false)
    private Viagem viagem;

    @Column(name="id_viagem")
    private int idViagem;

    @Column(name="n_poltrona")
    private int nPoltrona;

    @Column(name="preco")
    private String preco;

    public Passagem() {

    }

    public Passagem(int idCliente, int idViagem, int nPoltrona) {
        this.idCliente = idCliente;
        this.idViagem = idViagem;
        this.nPoltrona = nPoltrona;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public int getnPoltrona() {
        return nPoltrona;
    }

    public void setnPoltrona(int nPoltrona) {
        this.nPoltrona = nPoltrona;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return  "\nNome Passageiro = " + cliente.getNome() +
                "\nNúmero da Poltrona = " + nPoltrona +
                "\nPreço da Passagem = " + preco + "\n";
    }
}