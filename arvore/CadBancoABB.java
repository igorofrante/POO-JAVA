package arvore;

import java.util.ArrayList;
import dados.Banco;
import dados.NoArv;

public class CadBancoABB extends CadBancoArv {

	public CadBancoABB(int tam) {
		super(tam);
	}

	public void ABB() {
		super.insereTudo();
		this.CamCentral();
		this.ArvoreBalanceada();
	}

	public ArrayList<Banco> pesquisaABB(String chave) {
		ArrayList<Banco> vet = new ArrayList<Banco>();
		this.pesquisaABB(chave, super.raiz, vet);
		return vet;
	}

	private void pesquisaABB(String chave, NoArv arv, ArrayList<Banco> vet) {
		if (arv != null) {
			this.pesquisaABB(chave, arv.getEsq(), vet);
			if (chave.equals(arv.getInfo().getCpf())) {
				vet.add(arv.getInfo());
			}
			this.pesquisaABB(chave, arv.getDir(), vet);
		}
	}

	public void CamCentral() {
		super.vetBanco = new ArrayList<Banco>(super.vetBanco.size());
		this.FazCamCentral(super.raiz, super.vetBanco);
	}

	private void FazCamCentral(NoArv arv, ArrayList<Banco> vet) {
		if (arv != null) {
			this.FazCamCentral(arv.getEsq(), vet);
			vet.add(arv.getInfo());
			this.FazCamCentral(arv.getDir(), vet);
		}

	}

	public void ArvoreBalanceada() {
		super.raiz = null;
		this.Balancear(super.vetBanco, 0, super.vetBanco.size() - 1);

	}

	private void Balancear(ArrayList<Banco> vet, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			super.insere(vet.get(meio));
			this.Balancear(vet, inic, meio - 1);
			this.Balancear(vet, meio + 1, fim);
		}
	}

}
