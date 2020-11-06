package arvore;

import java.util.ArrayList;
import java.util.TreeSet;

import dados.Banco;
import dados.NoArvore;
import vetor.CadBanco;

public class CadBancoArv extends CadBanco {
	private NoArvore raiz;
	private ArrayList<Banco> ordenado;
	private NoArvore raizABB;


	public CadBancoArv(int tam) {
		super(tam);
		this.raiz = null;
		this.raizABB = null;
		this.ordenado = new ArrayList<Banco>(super.getBancoLista().size());
	}

	public NoArvore getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
	}

	public ArrayList<Banco> getOrdenado() {
		return ordenado;
	}

	public void setOrdenado(ArrayList<Banco> ordenado) {
		this.ordenado = ordenado;
	}

	public NoArvore getRaizABB() {
		return raizABB;
	}

	public void setRaizABB(NoArvore raizABB) {
		this.raizABB = raizABB;
	}

	public boolean pesquisa(Banco conta) {
		NoArvore temp;
		temp = this.pesquisa(conta, this.raiz);
		if (temp != null)
			return true;
		else
			return false;
	}

	private NoArvore pesquisa(Banco elem, NoArvore no) {
		NoArvore temp;
		temp = no;
		if (temp != null) {
			if (elem.compareTo(no.getInfo()) < 0)
				temp = this.pesquisa(elem, temp.getEsq());
			else {
				if (elem.compareTo(no.getInfo()) > 0)
					temp = this.pesquisa(elem, temp.getDir());
			}
		}
		return temp;
	}
	
	public void insere(Banco elem) {
		boolean existe = this.pesquisa(elem);
		if (!existe) {
			this.raiz = this.insere(elem, this.raiz);
		}

	}

	private NoArvore insere(Banco elem, NoArvore no) {
		NoArvore novo;
		if (no == null) {
			novo = new NoArvore(elem);
			return novo;
		} else {
			if (elem.compareTo(no.getInfo()) < 0) { // mudar para o compareTO do banco
				no.setEsq(this.insere(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(this.insere(elem, no.getDir()));
				return no;
			}
		}
	}
	
	public void insereTudo() {
		for (Banco banco : super.vetBanco) {
			this.insere(banco);
		}
	}

		
	public boolean pesquisaABBToda(Banco conta) {
		NoArvore temp;
		temp = this.pesquisaABB(conta, this.raizABB);
		if (temp != null)
			return true;
		else
			return false;
	}

	private NoArvore pesquisaABB(Banco elem, NoArvore no) {
		NoArvore temp;
		temp = no;
		if (temp != null) {
			if (elem.compareTo(no.getInfo()) < 0)
				temp = this.pesquisaABB(elem, temp.getEsq());
			else {
				if (elem.compareTo(no.getInfo()) > 0)
					temp = this.pesquisaABB(elem, temp.getDir());
			}
		}
		return temp;
	}

	public NoArvore pesquisaABBToda(String chave) {
		return (this.pesquisaABB(chave, this.raizABB));
	}

	public NoArvore pesquisaABB(String chave, NoArvore no) {
		NoArvore temp;
		temp = no;
		if (temp != null) {
			if (chave.compareTo(no.getInfo().getCpf()) < 0)
				temp = this.pesquisaABB(chave, temp.getEsq());
			else {
				if (chave.compareTo(no.getInfo().getCpf()) > 0)
					temp = this.pesquisaABB(chave, temp.getDir());
			}
		}
		return temp;
	}
	
	public void insereABB(Banco elem) {
		boolean existe = this.pesquisaABBToda(elem);
		if (!existe) {
			this.raizABB = this.insereABB(elem, this.raizABB);
		}

	}
	
	private NoArvore insereABB(Banco elem, NoArvore no) {
		NoArvore novo;
		if (no == null) {
			novo = new NoArvore(elem);
			return novo;
		} else {
			if (elem.compareTo(no.getInfo()) < 0) { // mudar para o compareTO do banco
				no.setEsq(this.insereABB(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(this.insereABB(elem, no.getDir()));
				return no;
			}
		}
	}

	public void CamCentral() {
		this.FazCamCentral(this.raiz, this.ordenado);
	}

	private void FazCamCentral(NoArvore arv, ArrayList<Banco> vet) {
		if (arv != null) {
			this.FazCamCentral(arv.getEsq(), vet);
			vet.add(arv.getInfo());
			this.FazCamCentral(arv.getDir(), vet);
		}

	}

	public void ArvoreBalanceada() {
		this.Balancear(this.ordenado, 0, this.ordenado.size() - 1);

	}

	private void Balancear(ArrayList<Banco> vet, int inic, int fim) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			this.insereABB(vet.get(meio));
			this.Balancear(vet, inic, meio - 1);
			this.Balancear(vet, meio + 1, fim);
		}
	}

	public String toString() {
		String temp = "";
		int i;

		for (i = 0; i < super.vetBanco.size(); i++)
			temp += super.vetBanco.get(i).toString() + "\n";
		return temp;
	}
	
	public ArrayList<Banco> VetorBalanceado(){
		ArrayList<Banco> novo = new ArrayList<Banco>(ordenado.size());
		return (this.BalancearVetor(this.ordenado, 0, this.ordenado.size() - 1, novo));
	}
	
	private ArrayList<Banco> BalancearVetor(ArrayList<Banco> vet, int inic, int fim,ArrayList<Banco> novo) {
		int meio;
		if (fim >= inic) {
			meio = (inic + fim) / 2;
			novo.add(vet.get(meio));
			this.BalancearVetor(vet, inic, meio - 1,novo);
			this.BalancearVetor(vet, meio + 1, fim,novo);
		}
		return novo;
	}
	
	
	public String toStringBalanceado() {
		String temp = "";
		int i;
		ArrayList<Banco> novo = this.VetorBalanceado();
		for (i = 0; i < novo.size(); i++)
			temp += novo.get(i).toString() + "\n";
		return temp;
	}
}
