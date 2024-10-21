package br.ufpb.dcx.gerenciaswitchs;

import java.util.ArrayList;

public interface SwitchsListInterface {
    void cadastrarSwitch (Switch s);
    void deletarSwitchPorIp (Switch s);
    CodigosBas pesquisarCodigosBasPorMarca (Marca marca);
    Switch pesquisarSwitchPorIp (String ip);
    ArrayList<Switch> getListaDeSwitchs ();
    ArrayList<Switch> exibirListaSwitchPorMarca (Marca marca);
    Switch PesquisarSwitchPorIp (String ip);
    void alterarIpPorIp (String ip);
    void alterarLocalizacaoPorIp (String ip);
    void alterarEstadoPorIp (String ip);
    void alterarCodigosBasPorMarca(String nome, CodigosBas codigosBas);
}
