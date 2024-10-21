package br.ufpb.dcx.gerenciaswitchs;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwitchList switchList = new SwitchList();
        int option = 0;

        try {
            switchList.recuperarDados();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar dados: " + e.getMessage());
        }

        while (option != 12) {
            String menu = "Gerenciador de Switches\n"
                    + "1. Cadastrar Switch\n"
                    + "2. Deletar Switch por IP\n"
                    + "3. Pesquisar Codigo Bas\n"
                    + "4. Pesquisar Switch por IP\n"
                    + "5. Exibir lista de Switchs\n"
                    + "6. Exibir Lista por Marcar\n"
                    //+ "7. Pesquisar Switch por IP\n"
                    + "7. Alterar IP por IP\n"
                    + "8. Alterar localização por IP\n"
                    + "9. Alterar Estado por IP\n"
                    + "10. Alterar Codigo Bas por Marca\n"
                    + "11. Remover Switch\n"
                    + "12. Sair\n"
                    + "Escolha uma opção: ";
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break; // User pressed cancel
            }
            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    try {
                        String ip = JOptionPane.showInputDialog("Digite o IP do Switch:");
                        String marcaNome = JOptionPane.showInputDialog("Digite a Marca do Switch:");
                        String comandoAcesso = JOptionPane.showInputDialog("Digite o Comando de Acesso:");
                        String comandoConfig = JOptionPane.showInputDialog("Digite o Comando de Configuração:");
                        String comandoVLAN = JOptionPane.showInputDialog("Digite o Comando de VLAN:");
                        String comandoGerencia = JOptionPane.showInputDialog("Digite o Comando de Gerenciamento:");
                        String localizacao = JOptionPane.showInputDialog("Digite a Localização do Switch:");
                        boolean gerenciavel = JOptionPane.showInputDialog("O Switch é gerenciável? (true/false)").equalsIgnoreCase("true");
                        int quantPortas = Integer.parseInt(JOptionPane.showInputDialog("Digite a Quantidade de Portas:"));
                        String estado = JOptionPane.showInputDialog("Digite o Estado do Switch:");

                        CodigosBas codigosBas = new CodigosBas(comandoAcesso, comandoConfig, comandoVLAN, comandoGerencia);
                        Marca marca = new Marca(marcaNome, codigosBas);
                        Switch s = new Switch(marca, ip, localizacao, gerenciavel, quantPortas, estado);
                        switchList.cadastrarSwitch(s);

                        switchList.salvarDados();
                        JOptionPane.showMessageDialog(null, "Switch cadastrado e dados salvos com sucesso.");
                    } catch (IpExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        String ipParaDeletar = JOptionPane.showInputDialog("Digite o IP do Switch a ser deletado:");
                        switchList.deletarSwitchPorIp(ipParaDeletar);
                        JOptionPane.showMessageDialog(null, "Switch deletado com sucesso.");
                    } catch (IpNaoExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        String marcaNomePesquisa = JOptionPane.showInputDialog("Digite a Marca do Switch:");
                        Marca marcaPesquisa = new Marca(marcaNomePesquisa, null);
                        CodigosBas codigosBasPesquisa = switchList.pesquisarCodigosBasPorMarca(marcaPesquisa);
                        JOptionPane.showMessageDialog(null, "Códigos Básicos encontrados: " + codigosBasPesquisa.toString());
                    } catch (MarcaNaoExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    String ipPesquisa = JOptionPane.showInputDialog("Digite o IP do Switch a ser pesquisado:");
                    Switch switchEncontrado = switchList.pesquisarSwitchPorIp(ipPesquisa);

                    if (switchEncontrado != null) {
                        JOptionPane.showMessageDialog(null, "Switch encontrado: " + switchEncontrado.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Switch encontrado para o IP: " + ipPesquisa);
                    }
                    break;

                case 5:
                    ArrayList<Switch> listaDeSwitchs = switchList.getListaDeSwitchs();
                    String switchesInfo = "";

                    for (Switch switchLista : listaDeSwitchs) {
                        switchesInfo += switchLista.toString() + "\n";
                    }
                    if (!switchesInfo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, switchesInfo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum switch encontrado.");
                    }
                    break;

                case 6:
                    String marcaNomeaPesquisa = JOptionPane.showInputDialog("Digite a Marca dos Switches a serem pesquisados:");
                    Marca marcaaPesquisa = new Marca(marcaNomeaPesquisa, null); // Supondo que o construtor de Marca aceite null para CodigosBas
                    ArrayList<Switch> switchesPorMarca = switchList.exibirListaSwitchPorMarca(marcaaPesquisa);
                    String switchesInfoPorMarca = "";

                    for (Switch switchListaMarca : switchesPorMarca) {
                        switchesInfoPorMarca += switchListaMarca.toString() + "\n";
                    }

                    if (!switchesInfoPorMarca.isEmpty()) {
                        JOptionPane.showMessageDialog(null, switchesInfoPorMarca);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum switch encontrado para a marca: " + marcaNomeaPesquisa);
                    }
                    break;

                case 7:
                    try {
                        String ipAtual = JOptionPane.showInputDialog("Digite o IP atual do Switch:");
                        String novoIp = JOptionPane.showInputDialog("Digite o novo IP do Switch:");
                        switchList.alterarIpPorIp(ipAtual, novoIp);
                        JOptionPane.showMessageDialog(null, "IP do Switch alterado com sucesso.");
                    } catch (IpExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        String ipParaAlterarLocalizacao = JOptionPane.showInputDialog("Digite o IP do Switch:");
                        String novaLocalizacao = JOptionPane.showInputDialog("Digite a nova localização do Switch:");
                        switchList.alterarLocalizacaoPorIp(ipParaAlterarLocalizacao, novaLocalizacao);
                        JOptionPane.showMessageDialog(null, "Localização do Switch alterada com sucesso.");
                    }catch (IpNaoExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;

                case 9:
                    try {
                        String ipParaAlterarEstado = JOptionPane.showInputDialog("Digite o IP do Switch:");
                        String novoEstado = JOptionPane.showInputDialog("Digite o novo estado do Switch:");
                        switchList.alterarEstadoPorIp(ipParaAlterarEstado, novoEstado);
                        JOptionPane.showMessageDialog(null, "Estado do Switch alterado com sucesso.");
                    }catch (IpNaoExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        String nomeMarca = JOptionPane.showInputDialog("Digite o nome da Marca:");
                        String comandoAcessoNovo = JOptionPane.showInputDialog("Digite o novo comando de acesso:");
                        String comandoConfigNovo = JOptionPane.showInputDialog("Digite o novo comando de configuração:");
                        String comandoVLANNovo = JOptionPane.showInputDialog("Digite o novo comando de VLAN:");
                        String comandoGerenciaNovo = JOptionPane.showInputDialog("Digite o novo comando de gerência:");

                        CodigosBas novosCodigosBas = new CodigosBas(comandoAcessoNovo, comandoConfigNovo, comandoVLANNovo, comandoGerenciaNovo);
                        switchList.alterarCodigosBasPorMarca(nomeMarca, novosCodigosBas);

                        JOptionPane.showMessageDialog(null, "Códigos Básicos da Marca alterados com sucesso.");
                    } catch (MarcaNaoExistente e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                    break;

                case 11:
                    String ipParaRemover = JOptionPane.showInputDialog("Digite o IP do Switch a ser removido:");
                    Switch switchParaRemover = switchList.pesquisarSwitchPorIp(ipParaRemover);

                    if (switchParaRemover != null) {
                        switchList.removerSwitch(switchParaRemover);
                        JOptionPane.showMessageDialog(null, "Switch removido com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum Switch encontrado para o IP: " + ipParaRemover);
                    }
                    break;

                case 12:
                    JOptionPane.showMessageDialog(null, "Saindo do programa. Até logo!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}