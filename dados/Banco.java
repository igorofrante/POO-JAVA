package dados;

public class Banco {
	private int agencia;
	private String conta, cpf;
	private double saldo;
	private String nome;
	/**
	 * @param agencia
	 * @param numero
	 * @param cpf
	 * @param saldo
	 */
	public Banco(int agencia, String numero, double saldo, String cpf, String nome) {
		super();
		this.agencia = agencia;
		this.conta = numero;
		this.cpf = cpf;
		this.saldo = saldo;
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setCOnta(String numero) {
		this.conta = numero;
	}
	public String getCpf() {
		return cpf;
	}
	public String getCpfConta() {
		return cpf+conta;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return cpf+";"+nome+";"+agencia + ";" + conta + ";" + saldo;
	}
	
	public int compareTo (Banco b) {
		long cpf1 = Long.parseLong(this.cpf);
		long cpf2 = Long.parseLong(b.getCpf());
		if (cpf1<cpf2)
			return -1;
		else if (cpf1>cpf2)
			return 1;
		else {
			int c1 = this.agencia;
			int c2 = b.getAgencia();
			return (c1-c2);
		}
	}
	
	
	

}
