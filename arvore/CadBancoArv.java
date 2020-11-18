package arvore;

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

	public boolean pesquisa(Banco conta) {
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

	private NoArv insere(Banco elem, NoArv no) {
		NoArv novo;
		if (no == null) {
			novo = new NoArv(elem);
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

	protected void insereTudo() {
		for (Banco banco : super.vetBanco) {
			this.insere(banco);
		}
	}

}