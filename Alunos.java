
public class Alunos {
	private int matricula, idade;
	private double coeficiente;
	private  int periodo;
	private double altura;
	private int qFilhos;
	private double peso;
	private int tProgramacao, nDisciplinas;
	private double mediaSalario;
	
	public Alunos() {
		super();
		this.idade = 0;
		this.matricula = 0;
		this.periodo = 0;
		this.qFilhos = 0;
		this.tProgramacao = 0;
		this.nDisciplinas = 0;
		this.coeficiente = 0;
		this.altura = 0;
		this.peso = 0;
		this.mediaSalario = 0;
	}



	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public int getMatricula() {
		return matricula;
	}


	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}


	public int getPeriodo() {
		return periodo;
	}


	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}


	public int getqFilhos() {
		return qFilhos;
	}


	public void setqFilhos(int qFilhos) {
		this.qFilhos = qFilhos;
	}


	public int gettProgramacao() {
		return tProgramacao;
	}


	public void settProgramacao(int tProgramacao) {
		this.tProgramacao = tProgramacao;
	}


	public int getnDisciplinas() {
		return nDisciplinas;
	}


	public void setnDisciplinas(int nDisciplinas) {
		this.nDisciplinas = nDisciplinas;
	}


	public double getCoeficiente() {
		return coeficiente;
	}


	public void setCoeficiente(double coeficiente) {
		this.coeficiente = coeficiente;
	}


	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public double getMediaSalario() {
		return mediaSalario;
	}


	public void setMediaSalario(double mediaSalario) {
		this.mediaSalario = mediaSalario;
	}



	@Override
	public String toString() {
		return  matricula + "\t" + idade + "\t" + coeficiente + "\t"
				+ periodo + "\t" + altura + "\t" + qFilhos + "\t" + peso + "\t"
				+ tProgramacao + "\t" + nDisciplinas + "\t" + mediaSalario + "\n";
	}



	

	



}
