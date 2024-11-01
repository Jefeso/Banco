package br.edu.fatecpg.sistemaBancario.model;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(int num, double sal, String nm) {
		super(num, sal, nm);
	}
	
	@Override
	public void mostrarDetalhesCt() {
    	System.out.println("Conta: " + getNumeroDeConta() + "\n" + "Titular: " + getNome() + "\n" + "Saldo: " + getSaldo());
    }
}

