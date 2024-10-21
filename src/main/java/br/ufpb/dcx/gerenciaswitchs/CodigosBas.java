package br.ufpb.dcx.gerenciaswitchs;

public class CodigosBas {
    private String comandoAcesso;
    private String comandoConfig;
    private String comandoVLAN;
    private String comandoGerencia;

    public CodigosBas (String comandoAcesso, String comandoConfig, String comandoVLAN, String comandoGerencia) {
        this.comandoAcesso = comandoAcesso;
        this.comandoConfig = comandoConfig;
        this.comandoVLAN = comandoVLAN;
        this.comandoGerencia = comandoGerencia;
    }
    public CodigosBas () {
        this("","","","");
    }

    public String getComandoAcesso() {
        return this.comandoAcesso;
    }
    public void setComandoAcesso(String comandoAcesso) {
        this.comandoAcesso = comandoAcesso;
    }
    public String getComandoConfig() {
        return this.comandoConfig;
    }
    public void setComandoConfig(String comandoConfig) {
        this.comandoConfig = comandoConfig;
    }
    public String getComandoVLAN() {
        return this.comandoVLAN;
    }
    public void setComandoVLAN(String comandoVLAN) {
        this.comandoVLAN = comandoVLAN;
    }
    public String getComandoGerencia() {
        return this.comandoGerencia;
    }
    public void setComandoGerencia(String comandoGerencia) {
        this.comandoGerencia = comandoGerencia;
    }

    public String toString () {
        return "\nCOMANDOS BASICOS:\nAcesso: "+this.comandoAcesso+"\nAbrir Configurações: "+this.comandoConfig+"\nConfigurar VLANs: "+this.comandoVLAN+"\nCodigo de Gerenciamento: "+this.comandoGerencia;
    }
}
