package br.edu.fatecpg.sistemaBancario.model;

public class Conta {
	
    private int numeroCt;
    private double saldo;
    private String nome;

    public Conta(int num, double sal, String nm) {
        this.numeroCt = num;
        this.saldo = sal;
        this.nome = nm;
    }
    
    
    public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double sal) {
		this.saldo = sal;
	}
	public int getNumeroDeConta() {
    	return numeroCt;
    }
    public void setNumeroDeConta(int num){
        this.numeroCt = num;
    }
    public String getNome() {
    	return nome;
    }
    public void setNome(String nm){
        this.nome = nm;
    }
    

    public void deposito(double valor) {
        saldo += valor;
    }

    public boolean transferencia(Conta destino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        System.out.println("Saldo Insuficiente ");
        return false;
    }
    
    public void mostrarDetalhesCt() {
    	System.out.println("Conta: " + numeroCt + "\n" + "Titular: " + nome + "\n" + "Saldo: " + saldo);
    }
}

