
public class Idade {
		
	private int matricula, idade;

	public Idade() {
		super();
		this.matricula = 0;
		this.idade = 0;
	}
	
	
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}


	@Override
	public String toString() {
		return this.matricula + "\t" + this.idade + "\n";
	}

}
