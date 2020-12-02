package arvore;

import java.util.ArrayList;
import dados.Banco;
import dados.NoArv;
import vetor.CadBanco;

public class CadBancoArv extends CadBanco {
	protected NoArv raiz;

	public CadBancoArv(int tam) {
		super(tam);
		this.raiz = null;
	}

	public NoArv getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArv raiz) {
		this.raiz = raiz;
	}

	public boolean pesquisa(Banco conta) { //pesquisa na arvore
		NoArv temp;
		temp = this.pesquisa(conta, this.raiz);
		if (temp != null)
			return true;
		else
			return false;
	}

	private NoArv pesquisa(Banco elem, NoArv no) {
		NoArv temp;
		temp = no;
		if (temp != null) {
			for (Banco banco : no.getInfo()) {
				if (temp != null) {
					if (elem.compareTo(banco) < 0)
						temp = this.pesquisa(elem, temp.getEsq());
					else {
						if (elem.compareTo(banco) > 0)
							temp = this.pesquisa(elem, temp.getDir());
					}
				}
			}
		}
		return temp;
	}

	protected void insereTudo() { //insere todos os elementos na arvore
		for (Banco banco : super.vetBanco) {
			this.insere(banco);
		}
	}

	public void insereA(ArrayList<Banco> elem) { // inserir sem pesquisar na arvore balanceada
		this.raiz = this.insereA(elem, this.raiz);
	}

	private NoArv insereA(ArrayList<Banco> elem, NoArv no) {
		NoArv novo;
		if (no == null) {
			novo = new NoArv(elem);
			return novo;
		} else {
			if (elem.get(0).compareTo2(no.getInfo().get(0)) < 0) {
				no.setEsq(this.insereA(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(this.insereA(elem, no.getDir()));
				return no;
			}
		}

	}

	public void insere(Banco elem) { //insere na arvore se nao houver elemento igual previamente adicionado
		boolean existe = this.pesquisa(elem);
		if (!existe) {
			this.raiz = this.insere(elem, this.raiz);
		}

	}

	protected NoArv insere(Banco elem, NoArv no) {
		if (no == null) {
			NoArv novo;
			ArrayList<Banco> lista = new ArrayList<Banco>();
			lista.add(elem);
			novo = new NoArv(lista);
			return novo;
		} else {
			if (elem.compareTo2(no.getInfo().get(0)) < 0) {
				no.setEsq(this.insere(elem, no.getEsq()));
				return no;
			} else if (elem.compareTo2(no.getInfo().get(0)) > 0) {
				no.setDir(this.insere(elem, no.getDir()));
				return no;
			} else {
				no.getInfo().add(elem);
			}

			return no;
		}
	}

}
