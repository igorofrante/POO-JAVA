package dados;

public class Banco {
	private int agencia;
	private String conta, cpf;
	private double saldo;
	private String nome;

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

	public String getConta() {
		return conta;
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

	public String getCpf() {
		return cpf;
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
		return cpf + ";" + nome + ";" + agencia + ";" + conta + ";" + saldo;
	}

	public int compareTo(Banco b) { // Compara CPF, Agencia e conta.
		long cpf1 = Long.parseLong(this.cpf);
		long cpf2 = Long.parseLong(b.getCpf());
		if (cpf1 < cpf2)
			return -1;
		else if (cpf1 > cpf2)
			return 1;
		else {
			if (this.agencia < b.getAgencia())
				return -1;
			else if (this.agencia > b.getAgencia())
				return 1;
			else {
				int c1 = Integer.parseInt(this.conta);
				int c2 = Integer.parseInt(b.getConta());
				return (c1 - c2);
			}
		}
	}

	public int compareTo2(Banco b) { // Compara somente o CPF.
		long cpf1 = Long.parseLong(this.cpf);
		long cpf2 = Long.parseLong(b.getCpf());
		if (cpf1 < cpf2) {
			return -1;
		} else if (cpf1 > cpf2) {
			return 1;
		} else {
			return 0;
		}
	}

}
