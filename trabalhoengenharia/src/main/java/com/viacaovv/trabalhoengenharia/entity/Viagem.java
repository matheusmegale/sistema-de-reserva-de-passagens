package com.viacaovv.trabalhoengenharia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="viagem")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_viagem")
    private int id;

    @Column(name="data_viagem")
    private String dataViagem;

    @Column(name="horario_saida")
    private String horarioSaida;

    @Column(name="origem")
    private String origem;

    @Column(name="destino")
    private String destino;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_motorista") // id_motorista da tabela viagem
    private Motorista motorista;

    @Column(name="id_motorista", insertable = false, updatable = false)
    private int idMotorista;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_onibus") // id_onibus da tabela viagem
    private Onibus onibus;

    @Column(name="id_onibus", insertable = false, updatable = false)
    private int idOnibus;

    @OneToMany(mappedBy = "viagem")
    private List<Passagem> passagens;

    public Viagem() {

    }

    public Viagem(String dataViagem, String horarioSaida, String origem, String destino) {
        this.dataViagem = dataViagem;
        this.horarioSaida = horarioSaida;
        this.origem = origem;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public int getIdOnibus() {
        return idOnibus;
    }

    public void setIdOnibus(int idOnibus) {
        this.idOnibus = idOnibus;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }

    public String relatorioGeral(){
        return "Viagem: " + "\n" +
                "\nId = " + id +
                "\nData Viagem = '" + dataViagem + "'" +
                "\nHorário Saída = '" + horarioSaida + "'" +
                "\nOrigem = '" + origem + "'" +
                "\nDestino = '" + destino + "'" +
                "\nId Motorista = " + idMotorista +
                "\nMome Motorista  = '" + motorista.getNome() + "'" +
                "\nIdade Motorista = " + motorista.getIdade() +
                "\nNúmero Habilitação = " + motorista.getnHabilitacao() +
                "\nId Ônibus = " + idOnibus +
                "\nPlaca = " + onibus.getPlaca() + "\n" +
                "\nPassageiros: " +
                "\n" +  passageiros();
    }

    public String passageiros(){
        String passageiros = "";
        for(Passagem passagem : passagens){
            passageiros+= passagem.toString();
        }
        return passageiros;
    }

    @Override
    public String toString() {
        return "Viagem: " +
                "\nId = " + id +
                "\nData Viagem = '" + dataViagem + "'" +
                "\nHorário Saída = '" + horarioSaida + "'" +
                "\nOrigem = '" + origem + "'" +
                "\nDestino = '" + destino + "'" +
                "\nId Motorista = " + idMotorista +
                "\nMome Motorista  = '" + motorista.getNome() + "'" +
                "\nIdade Motorista = " + motorista.getIdade() +
                "\nNúmero Habilitação = " + motorista.getnHabilitacao() +
                "\nId Ônibus = " + idOnibus +
                "\nPlaca = " + onibus.getPlaca();
    }
}
