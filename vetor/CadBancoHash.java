package vetor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import dados.Banco;

public class CadBancoHash extends CadBanco {
	private ArrayList<LinkedList<Banco>> vetor;
	private int tam;

	public CadBancoHash(int tam) {
		super(tam);
		this.vetor = new ArrayList<LinkedList<Banco>>(this.defPrimo(tam));
		this.tam = this.defPrimo(tam);
	}

	private int defPrimo(int tam) {
		boolean para = false;
		int p1 = tam--, p2 = tam++;

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
			return ((int) (1.1 * p2));
		} else if (this.ePrimo(p1)) {
			return ((int) (1.1 * p1));
		} else {
			return ((int) (1.1 * p2));
		}

	}

	private boolean ePrimo(int n) {
		if (n <= 1)
			return false;

		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;

		return true;
	}

	public void hashing() {
		this.fazHashing();
	}

	private int pos(String chave) {
		long pos = Long.parseLong(chave) % this.tam;
		return (int) pos;
	}

	private void fazHashing() {
		int pos = 0;
		LinkedList<Banco> v1 = new LinkedList<Banco>();
		for (Banco banco : vetBanco) {
			pos = this.pos(banco.getCpf());

			if (vetor.get(pos) == null) {
				v1 = new LinkedList<Banco>();
				v1.add(banco);
				vetor.add(pos, v1);
			} else {
				v1 = new LinkedList<Banco>();
				v1 = vetor.get(pos);
				v1.add(banco);
				vetor.add(pos, v1);
			}
		}
	}

	public LinkedList<Banco> pesquisaHash(String chave) {
		long pos = Long.parseLong(chave) % this.tam;

		if (vetor.get((int) pos) == null) {
			return null;
		} else {
			LinkedList<Banco> v1 = new LinkedList<Banco>();
			LinkedList<Banco> v2 = new LinkedList<Banco>();
			v1=vetor.get((int)pos);
			for (Banco banco : v1) {
				if(chave.equals(banco.getCpf())) {
					v2.add(banco);
				}
			}
			
			if(!v2.isEmpty()) {
				return v2;
			}else {
				return null;
			}
				
		}
	}

//	public String toString() {
//		String temp = "";
//		int j = 0;
//		for (int i = 0; i < this.vetor.size(); i++) {
//			if (this.vetor.get(i) != null) {
//				while (j < this.vetor.get(i).size()) {
//					temp += this.vetor.get(i).get(j).toString() + "\n";
//					j++;
//				}
//			}
//			j = 0;
//		}
//
//		return temp;
//	}
}

