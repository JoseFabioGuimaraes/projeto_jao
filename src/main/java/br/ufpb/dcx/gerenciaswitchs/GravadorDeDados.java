package br.ufpb.dcx.gerenciaswitchs;

import java.io.*;
import java.util.ArrayList;

public class GravadorDeDados {
    public static final String NOME_ARQUIVO_SWITCH = "Switch.txt";

    public void salvarTexto (ArrayList<String> texto) throws IOException {
        BufferedWriter gravador = null;
        try {
            gravador = new BufferedWriter(new FileWriter(NOME_ARQUIVO_SWITCH));
            for (String s: texto) {
                gravador.write(s+"\n");
            }
        }
        finally {
            if (gravador != null){
                gravador.close();
            }
        }
    }
    public void salvarSwitches (ArrayList<Switch> switches) throws IOException {
        ArrayList<String> textoSwitches = new ArrayList<>();
        for (Switch s : switches) {
            if (s.ehGerenciavel()) {
                String textoSwitch = s.getMarca().getNome() + "---" + s.getMarca().getCodigosBas().getComandoAcesso() + "---" + s.getMarca().getCodigosBas().getComandoConfig() + "---" + s.getMarca().getCodigosBas().getComandoVLAN() + "---" + s.getMarca().getCodigosBas().getComandoGerencia() + "---" + s.getIp() + "---" + s.getLocalizacao() + "---" + "true" + "---" + s.getQuantPortas() + "---" + s.getEstado();
                textoSwitches.add(textoSwitch);
            }
            else {
                String textoSwitch = s.getMarca().getNome() + "---" + s.getMarca().getCodigosBas().getComandoAcesso() + "---" + s.getMarca().getCodigosBas().getComandoConfig() + "---" + s.getMarca().getCodigosBas().getComandoVLAN() + "---" + s.getMarca().getCodigosBas().getComandoGerencia() + "---" + s.getIp() + "---" + s.getLocalizacao() + "---" + "false" + "---" + s.getQuantPortas() + "---" + s.getEstado();
                textoSwitches.add(textoSwitch);
            }
        }
        salvarTexto(textoSwitches);
    }

    public ArrayList<String> recuperarTexto () throws IOException {
        BufferedReader leitor = null;
        ArrayList<String> textoLido = new ArrayList<>();
        try {
            leitor = new BufferedReader(new FileReader(NOME_ARQUIVO_SWITCH));
            String texto = null;
            do {
                texto = leitor.readLine();
                if (texto != null) {
                    textoLido.add(texto);
                }
            }
            while (texto != null);
        }
        finally {
            if (leitor != null) {
                leitor.close();
            }
        }
        return textoLido;
    }
    public ArrayList<Switch> recuperarSwitches () throws IOException {
        ArrayList<String> dadosSwitches = recuperarTexto();
        ArrayList<Switch> switchesRecuperados = new ArrayList<>();
        for (String linhaLida: dadosSwitches) {
            String [] dados = linhaLida.split("---");
            CodigosBas c = new CodigosBas(dados[1], dados[2], dados[3], dados[4]);
            Marca m = new Marca(dados[0], c);
            Switch s = new Switch(m, dados[5], dados[6], false, Integer.parseInt(dados[8]), dados[9]);
            if (dados[7].equals("true")) {
                s = new Switch(m, dados[5], dados[6], true, Integer.parseInt(dados[8]), dados[9]);
            }
            switchesRecuperados.add(s);
        }
        return switchesRecuperados;
    }
}
