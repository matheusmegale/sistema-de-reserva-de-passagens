package com.viacaovv.trabalhoengenharia.entity;

public final class Administrador extends Usuario{

    private static Administrador instancia;

    public Administrador() {
        super("adm@exemplo.com", "adm123");
    }

    public static Administrador getInstance() {
        if (instancia == null) {
            instancia = new Administrador();
        }
        return instancia;
    }

}
