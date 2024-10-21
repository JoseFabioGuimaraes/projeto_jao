package br.ufpb.dcx.gerenciaswitchs;

import java.util.ArrayList;
import java.util.Objects;

public class Marca {
    private String nome;
    private CodigosBas codigosBas;

    public Marca (String nome, CodigosBas codigosBas) {
        this.codigosBas = codigosBas;
        this.nome = nome;
    }
    public Marca (String nome) {
        this(nome, null);
    }
    public Marca () {
        this("", null);
    }

    public void setCodigosBas(CodigosBas codigosBas) {
        this.codigosBas = codigosBas;
    }
    public CodigosBas getCodigosBas() {
        return this.codigosBas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    public String toString () {
        return this.nome + this.codigosBas.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marca marca = (Marca) o;
        return Objects.equals(nome, marca.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}
