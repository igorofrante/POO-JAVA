package arvore;

import java.util.ArrayList;
import dados.Banco;
import dados.NoArv;

public class CadBancoABB extends CadBancoArv {
	private ArrayList<ArrayList<Banco>> ordenado;

	public CadBancoABB(int tam) {
		super(tam);
	}

	public void abb() {
		super.insereTudo();
		this.camCentral();
		this.arvoreBalanceada();
	}

	public ArrayList<Banco> pesquisaABB(String chave) {
		NoArv temp;
		temp = this.pesquisaABB(chave, this.raiz);
		if (temp != null)
			return temp.getInfo();
		else
			return null;
	}

	private NoArv pesquisaABB(String chave, NoArv no) {
		NoArv temp;
		temp = no;
		if (temp != null) {
			if (chave.compareTo(no.getInfo().get(0).getCpf()) < 0)
				temp = this.pesquisaABB(chave, temp.getEsq());
			else {
				if (chave.compareTo(no.getInfo().get(0).getCpf()) > 0)
					temp = this.pesquisaABB(chave, temp.getDir());
			}
		}
		return temp;
	}

	public void camCentral() {
		ordenado = new ArrayList<ArrayList<Banco>>(super.vetBanco.size());
		this.fazCamCentral(super.raiz, ordenado);
	}

	private void fazCamCentral(NoArv arv, ArrayList<ArrayList<Banco>> vet) {
		if (arv != null) {
			this.fazCamCentral(arv.getEsq(), vet);
			vet.add(arv.getInfo());
			this.fazCamCentral(arv.getDir(), vet);
		}

	}

	public void arvoreBalanceada() {
		super.raiz = null;
		this.balancear(ordenado, 0, ordenado.size() - 1);

	}

	private void balancear(ArrayList<ArrayList<Banco>> vet, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			super.insereA(vet.get(meio));
			this.balancear(vet, inic, meio - 1);
			this.balancear(vet, meio + 1, fim);
		}
	}

}