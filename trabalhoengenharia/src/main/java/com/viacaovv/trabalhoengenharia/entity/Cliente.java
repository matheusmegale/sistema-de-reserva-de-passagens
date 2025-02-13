package com.viacaovv.trabalhoengenharia.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cliente")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
})
public class Cliente extends Usuario{

    @Column(name="nome")
    private String nome;

    @Column(name="cpf")
    private String cpf;

    public Cliente() {

    }

    public Cliente(String email, String senha, String nome, String cpf) {
        super(email, senha);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
