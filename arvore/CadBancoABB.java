package arvore;

import java.util.ArrayList;
import dados.Banco;
import dados.NoArv;

public class CadBancoABB extends CadBancoArv {
	private ArrayList<ArrayList<Banco>> ordenado;
	public CadBancoABB(int tam) {
		super(tam);
	}

	public void ABB() {
		super.insereTudo();
		this.CamCentral();
		this.ArvoreBalanceada();
	}

	public ArrayList<Banco> pesquisaABB(String chave) {
		NoArv temp;
		temp = this.pesquisaABB(chave, this.raiz);
		if (temp != null)
			return temp.getInfo();
		else
			return null;
	}
	private NoArv pesquisaABB(String chave,NoArv no){
		NoArv temp;
		temp = no;
		if (temp != null) {
			if (no.getInfo().get(0).getCpf().compareTo(chave) < 0)
				temp = this.pesquisaABB(chave, temp.getEsq());
			else {
				if (no.getInfo().get(0).getCpf().compareTo(chave) > 0)
					temp = this.pesquisaABB(chave, temp.getDir());
			}
		}
		return temp;
	}

	public void CamCentral() {
		ordenado = new ArrayList<ArrayList<Banco>>(super.vetBanco.size());
		this.FazCamCentral(super.raiz, ordenado);
	}

	private void FazCamCentral(NoArv arv, ArrayList<ArrayList<Banco>> vet) {
		if (arv != null) {
			this.FazCamCentral(arv.getEsq(), vet);
			vet.add(arv.getInfo());
			this.FazCamCentral(arv.getDir(), vet);
		}

	}

	public void ArvoreBalanceada() {
		super.raiz = null;
		this.Balancear(ordenado, 0, ordenado.size() - 1);

	}

	private void Balancear(ArrayList<ArrayList<Banco>> vet, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			super.insereA(vet.get(meio));
			this.Balancear(vet, inic, meio - 1);
			this.Balancear(vet, meio + 1, fim);
		}
	}

}