package br.ufpb.dcx.gerenciaswitchs;
import java.io.IOException;
import java.util.ArrayList;

public class SwitchList {
    private ArrayList<Switch> switchs;
    private GravadorDeDados gravadorDeSwitches;

    public SwitchList () {
        this.switchs = new ArrayList<>();
        this.gravadorDeSwitches = new GravadorDeDados();
    }

    public void cadastrarSwitch(Switch s) throws IpExistente {
        for (Switch switchExistente : this.switchs) {
            if (switchExistente.getIp().equals(s.getIp())) {
                throw new IpExistente(s.getIp());
            }
        }
        this.switchs.add(s);
    }
    public void deletarSwitchPorIp(String ip) throws IpNaoExistente {
        boolean ipEncontrado = false;
        for (Switch s : this.switchs) {
            if (s.getIp().equals(ip)) {
                this.switchs.remove(s);
                ipEncontrado = true;

            }
        }
        if (!ipEncontrado) {
            throw new IpNaoExistente(ip);
        }
    }


    public CodigosBas pesquisarCodigosBasPorMarca (Marca marca) throws MarcaNaoExistente {// vai precisar de Exeption a marca
        for (Switch s : this.switchs) {
            if (s.getMarca().equals(marca)) {
                return s.getMarca().getCodigosBas();
            }
        }
        throw new MarcaNaoExistente(marca.getNome());
    }
    public Switch pesquisarSwitchPorIp (String ip) {
        for (Switch s: this.switchs) {
            if (s.getIp().equals(ip)) {
                return s;
            }
        }
        return null;
    }
    public ArrayList<Switch> getListaDeSwitchs () {
        return this.switchs;
    }
    public ArrayList<Switch> exibirListaSwitchPorMarca (Marca marca) {
        ArrayList<Switch> switchesMarca = new ArrayList<>();
        for (Switch s: this.switchs) {
            if (s.getMarca().equals(marca)) {
                switchesMarca.add(s);
            }
        }
        return switchesMarca;
    }
    //FIXME: Remover esse método duplicado
    /*public Switch PesquisarSwitchPorIp (String ip) {
        for (Switch s: this.switchs) {
            if (s.getIp().equals(ip)) {
                return s;
            }
        }
        return null;
    }*/

    //Alterei o metodo para aceitar o IP atual e o novo IP
    public void alterarIpPorIp(String ipAtual, String novoIp) throws IpExistente {
        for (Switch s : this.switchs) {
            if (s.getIp().equals(novoIp)) {
                throw new IpExistente(novoIp);
            }
        }
        for (Switch s : this.switchs) {
            if (s.getIp().equals(ipAtual)) {
                s.setIp(novoIp);
                break;
            }
        }
    }
    //8
    public void alterarLocalizacaoPorIp (String ip, String localizacao) throws IpNaoExistente {// vai precisar de Exeption
        boolean ipEncontrado = false;
        for (Switch s: this.switchs) {
            if (s.getIp().equals(ip)) {
                s.setLocalizacao(localizacao);
                ipEncontrado = true;
                break;
            }
        }
        if (!ipEncontrado) {
            throw new IpNaoExistente(ip);
        }
    }
    //9
    public void alterarEstadoPorIp (String ip, String estado) throws IpNaoExistente {// vai precisar de Exeption
        boolean ipEncontrado = false;
        for (Switch s: this.switchs) {
            if (s.getIp().equals(ip)) {
                s.setEstado(estado);
                ipEncontrado = true;
                break;
            }
        }
        if (!ipEncontrado) {
            throw new IpNaoExistente(ip);
        }
    }
    public void alterarCodigosBasPorMarca(String nome, CodigosBas codigosBas) throws MarcaNaoExistente {
        boolean marcaEncontrada = false;
        for (Switch s : this.switchs) {
            if (s.getMarca().getNome().equals(nome)) {
                s.setMarca(new Marca(nome, codigosBas));
                marcaEncontrada = true;

            }
        }
        if (!marcaEncontrada) {
            throw new MarcaNaoExistente(nome);
        }
    }
    public void removerSwitch(Switch s) {

        this.switchs.remove(s);
    }

    public void salvarDados () throws IOException {
        this.gravadorDeSwitches.salvarSwitches(this.switchs);
    }
    public void recuperarDados () throws IOException {
        this.switchs = this.gravadorDeSwitches.recuperarSwitches();
    }
}
