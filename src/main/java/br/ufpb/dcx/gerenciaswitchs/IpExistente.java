package br.ufpb.dcx.gerenciaswitchs;

public class IpExistente extends Exception {
    public IpExistente(String ip) {
        super("O IP "+ip+" já está cadastrado.");
    }
}

class IpNaoExistente extends Exception {
    public IpNaoExistente(String ip) {
        super("O IP " + ip + " não existe.");
    }
}