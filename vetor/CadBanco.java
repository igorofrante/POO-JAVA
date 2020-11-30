package vetor;

import java.util.ArrayList;
import dados.Banco;

public class CadBanco {
	protected ArrayList<Banco> vetBanco;

	public CadBanco(int tam) {
		this.vetBanco = new ArrayList<Banco>(tam);
	}

	public CadBanco(ArrayList<Banco> lista) {
		this.vetBanco = lista;
	}

	public String toString() {
		String temp = "";
		int i;

		for (i = 0; i < this.vetBanco.size(); i++)
			temp += this.vetBanco.get(i).toString() + "\n";
		return temp;
	}

	public String toString(int pos) {
		return this.vetBanco.get(pos).toString();
	}

	public Banco getBanco(int pos) {
		return this.vetBanco.get(pos);
	}

	public ArrayList<Banco> getBancoLista() {
		return this.vetBanco;
	}

	public void setBancoLista(ArrayList<Banco> novoVet) {
		this.vetBanco = novoVet;
	}

	public ArrayList<Banco> pesqBin(String chave) {
		int nElem = vetBanco.size();
		int meio, esq, dir;
		esq = 0;
		dir = nElem - 1;
		ArrayList<Banco> lista = new ArrayList<Banco>();
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (chave.equals(this.vetBanco.get(meio).getCpf())) {

				while ((meio - 1) >= 0 && chave.equals(this.vetBanco.get(meio - 1).getCpf())) {
					meio--;
				}

				while (chave.equals(this.vetBanco.get(meio).getCpf())) {
					lista.add(this.vetBanco.get(meio));
					meio++;
				}
				return lista;
			} else {
				if (chave.compareTo(this.vetBanco.get(meio).getCpf()) < 0)
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return null;

	}

	public void insereLista(ArrayList<Banco> lista) {
		this.vetBanco.addAll(lista);
	}

	public void quicksort() {
		ordena(0, this.vetBanco.size() - 1);
	}

	private void ordena(int esq, int dir) {
		Banco pivo;
		int i = esq, j = dir;
		Banco temp;

		pivo = this.vetBanco.get((i + j) / 2);
		do {
			while (this.vetBanco.get(i).compareTo(pivo) < 0)
				i++;
			while (this.vetBanco.get(j).compareTo(pivo) > 0)
				j--;
			if (i <= j) {
				temp = this.vetBanco.get(i);
				this.vetBanco.set(i, this.vetBanco.get(j));
				this.vetBanco.set(j, temp);
				i++;
				j--;
			}
		} while (i <= j);
		if (esq < j)
			ordena(esq, j);
		if (dir > i)
			ordena(i, dir);
	}

	public void HeapSort() {
		int nElem = this.vetBanco.size();
		int dir = nElem - 1;
		int esq = (dir - 1) / 2;
		Banco temp;

		while (esq >= 0) {
			refazHeap(esq, nElem - 1);
			esq--;
		}
		while (dir > 0) {
			temp = this.vetBanco.get(0);
			this.vetBanco.set(0, vetBanco.get(dir));
			this.vetBanco.set(dir, temp);
			dir--;
			refazHeap(0, dir);
		}
	}

	private void refazHeap(int esq, int dir) {
		int i = esq;
		int mF = 2 * i + 1; // maior filho
		Banco raiz = this.vetBanco.get(i);
		boolean heap = false;

		while ((mF <= dir) && (!heap)) {
			if (mF < dir)
				if (this.vetBanco.get(mF).compareTo(this.vetBanco.get(mF + 1)) < 0)
					mF++;
			if (raiz.compareTo(this.vetBanco.get(mF)) < 0) {
				this.vetBanco.set(i, this.vetBanco.get(mF));
				i = mF;
				mF = 2 * i + 1;
			} else
				heap = true;
		}
		this.vetBanco.set(i, raiz);
	}

}
