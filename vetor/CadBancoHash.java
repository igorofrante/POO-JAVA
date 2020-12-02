package vetor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import dados.Banco;

public class CadBancoHash extends CadBanco {
	private ArrayList<LinkedList<Banco>> vetor;

	public CadBancoHash(int tam) {
		super(tam); // instancia o vetBanco na classe Cadbanco passando o tamanho
		tam = ((int) (1.1 * tam)); // adiciona a margem de erro de 10%
		this.vetor = new ArrayList<LinkedList<Banco>>(Collections.nCopies(this.defPrimo(tam), null)); // instancia o
																										// vetor com
																										// cópias de
																										// objetos
																										// nulos.
	}

	private int defPrimo(int tam) { // define o primo mais proximo
		boolean para = false;
		int p1 = tam, p2 = tam;
		p1--;
		p2++;
		if (this.ePrimo(tam)) {
			return tam;
		} else {
			while (!para) {
				if (this.ePrimo(p1) || this.ePrimo(p2)) {
					para = true;
				} else {
					p1--;
					p2++;
				}
			}
		}

		if (this.ePrimo(p1) && this.ePrimo(p2)) {
			return p2;
		} else if (this.ePrimo(p1)) {
			return p1;
		} else {
			return p2;
		}
	}

	private boolean ePrimo(int n) { // testa se o numero e primo
		if (n <= 1) {
			return false;
		}

		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void hashing() { // metodo utilizado na classe principal
		this.fazHashing();
	}

	private int pos(String chave) { // define a posicao de insercao ou busca
		long pos = Long.parseLong(chave) % this.vetor.size();
		return (int) pos;
	}

	private void fazHashing() { // executa o metodo hashing
		int pos = 0;
		LinkedList<Banco> v1 = new LinkedList<Banco>();
		for (Banco banco : vetBanco) {
			pos = this.pos(banco.getCpf());

			if (this.vetor.get(pos) == null) {
				v1 = new LinkedList<Banco>();
				v1.addLast(banco);
				this.vetor.set(pos, v1);
			} else {
				this.vetor.get(pos).addLast(banco);
			}

		}
	}

	public LinkedList<Banco> pesquisaHash(String chave) { // pesquisa no vetor hashing
		int pos = this.pos(chave);

		if (this.vetor.get(pos) == null) {
			return null;
		} else {
			LinkedList<Banco> v1 = new LinkedList<Banco>();
			LinkedList<Banco> v2 = new LinkedList<Banco>();
			v1 = vetor.get(pos);
			for (Banco banco : v1) {
				if (chave.equals(banco.getCpf())) {
					v2.add(banco);
				}
			}

			if (!v2.isEmpty()) {
				return v2;
			} else {
				return null;
			}

		}
	}

}
