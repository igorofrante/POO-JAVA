package arvore;

import java.util.ArrayList;
import java.util.TreeSet;

import dados.Banco;
import dados.NoArvore;
import vetor.CadBanco;

public class CadBancoArv extends CadBanco {
	protected NoArvore raiz;

	public CadBancoArv(int tam) {
		super(tam);
		this.raiz = null;
	}

	public NoArvore getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArvore raiz) {
		this.raiz = raiz;
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

	protected void insereTudo() {
		for (Banco banco : super.vetBanco) {
			this.insere(banco);
		}
	}

}
