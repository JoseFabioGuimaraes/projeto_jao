package br.ufpb.dcx.gerenciaswitchs;

import java.util.Objects;

public class Switch {
    private Marca marca;
    private String ip;
    private String localizacao;
    private boolean gerenciavel;
    private int quantPortas;
    private String estado;
    public static final String ATIVO = "Ativo";
    public static final String INATIVO = "Inativo";
    public static final String DANIFICADO = "Danificado";

    public Switch (Marca marca, String ip, String localizacao, boolean gerenciavel, int quantPortas, String estado) {
        this.marca = marca;
        this.ip = ip;
        this.localizacao = localizacao;
        this.gerenciavel = gerenciavel;
        this.quantPortas = quantPortas;
        this.estado = estado;

    }
    public Switch () {

        this(null,"","",false,0,"");
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    public void setGerenciavel(boolean gerenciavel) {
        this.gerenciavel = gerenciavel;
    }
    public void setQuantPortas(int quantPortas) {
        this.quantPortas = quantPortas;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Marca getMarca() {
        return this.marca;
    }
    public String getIp() {
        return this.ip;
    }
    public String getLocalizacao() {
        return this.localizacao;
    }
    public boolean ehGerenciavel() {
        return this.gerenciavel;
    }
    public int getQuantPortas() {
        return this.quantPortas;
    }
    public String getEstado() {
        return this.estado;
    }

    public String toString () {
        if (this.gerenciavel) {
            return "SWITCH\nMarca: " + this.marca.getNome() + "\nIP: " + this.ip + "\nLocalização: " + this.localizacao + "\nGerenciável\nQuantidade de Portas: "+this.quantPortas+"\nEstado do Switch: "+this.estado;
        }
        return "SWITCH\nMarca: " + this.marca.getNome() + "\nIP: " + this.ip + "\nLocalização: " + this.localizacao + "\nNão é Gerenciável\nQuantidade de Portas: "+this.quantPortas+"\nEstado do Switch: "+this.estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Switch aSwitch = (Switch) o;
        return Objects.equals(ip, aSwitch.ip) && Objects.equals(estado, aSwitch.estado);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(ip);
        result = 31 * result + Objects.hashCode(estado);
        return result;
    }
}
