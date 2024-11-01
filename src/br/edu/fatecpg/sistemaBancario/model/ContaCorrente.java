package br.edu.fatecpg.sistemaBancario.model;

public class ContaCorrente extends Conta{
	
	private double limitCheck;

	public ContaCorrente(int num, double sal, String nm, double litCk) {
		super(num, sal, nm);
		this.limitCheck = litCk;
	}

	public double getLimitCheck() {
		return limitCheck;
	}

	public void setLimitCheck(double litCheck) {
		this.limitCheck = litCheck;
	}
	
	
	public boolean sacar(double valor) {
        if (valor <= getSaldo() + limitCheck) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }
	
	
	@Override
	public boolean transferencia(Conta destino, double valor) {
        if (sacar(valor)) {
            destino.deposito(valor);
            return true;
        }
        System.out.println("Saldo Insuficiente");
        return false;
    }
	
	public void mostrarDetalhesCt() {
    	System.out.println("Conta: " + getNumeroDeConta() + "\n" + "Titular: " + getNome() + "\n" + "Saldo: " + getSaldo() + "\n" + "Limite: " + limitCheck);
    }
}
