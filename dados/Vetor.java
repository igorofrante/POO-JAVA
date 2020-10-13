package dados;

public class Vetor {

	private Item[] vet;
	private int nElem;

	public Vetor(int tamanho) {
		this.vet = new Item[tamanho];
		nElem = 0;
	}

	public int getQuantVet() {
		return this.nElem;
	}

	public void setVetor(int indice, Item it) {
		this.vet[indice] = it;
	}

	public Item getVet(int indice) {
		return this.vet[indice];
	}

	public void setQuantVet(int novoValor) {
		this.nElem = novoValor;
	}

	public boolean eVazia() {
		return this.nElem == 0;
	}

	public boolean eCheia() {
		return this.nElem == this.vet.length;
	}

	// inserir dados no vetor
	public boolean inserirDados(Item it) {
		if (this.eCheia()) {
			return false;
		} else {
			this.vet[this.nElem] = it;
			this.nElem++;
			return true;
		}


	}

}
