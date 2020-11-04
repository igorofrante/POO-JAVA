package arvore;

import java.util.ArrayList;

import dados.Banco;
import dados.NoArvore;

public class CadBancoArv {
	private NoArvore raiz;
	private ArrayList<Banco> ListaBanco;

	public CadBancoArv() {
		this.raiz = null;
	}

	public boolean pesquisa(int chave) {
		NoArvore temp;
		temp = this.pesquisa(chave, this.raiz);
		if (temp != null)
			return true;
		else
			return false;
	}

	private NoArvore pesquisa(int chave, NoArvore no) {
		NoArvore temp;
		temp = no;
		if (temp != null) {
			if (chave < temp.getInfo().getChave())
				temp = this.pesquisa(chave, temp.getEsq());
			else {
				if (chave > temp.getInfo().getChave())
					temp = this.pesquisa(chave, temp.getDir());
			}
		}
		return temp;
	}

	public boolean insere(NoArvore elem) {
		boolean existe;
		existe = this.pesquisa(elem.getChave());
		if (existe)
			return false;
		else {
			this.raiz = this.insere(elem, this.raiz);
			return true;
		}
	}

	private NoArvore insere(Banco elem, NoArvore no) {
		NoArvore novo;
		if (no == null) {
			novo = new NoArvore(elem);
			return novo;
		} else {
			if (elem.compareTo(no.getInfo())<0) { // mudar para o compareTO do banco
				no.setEsq(this.insere(elem, no.getEsq()));
				return no;
			} else {
				no.setDir(this.insere(elem, no.getDir()));
				return no;
			}
		}
	}

	private NoArvore remove(int chave, NoArvore arv) {
		if (arv == null)
			return arv;
		else {
			if (chave < arv.getInfo().getChave())
				arv.setEsq(this.remove(chave, arv.getEsq()));
			else if (chave > arv.getInfo().getChave())
				arv.setDir(this.remove(chave, arv.getDir()));
			else if (arv.getDir() == null)
				return arv.getEsq();
			else if (arv.getEsq() == null)
				return arv.getDir();
			else
				arv.setEsq(this.arruma(arv, arv.getEsq()));
		}
		return arv;
	}

	private NoArvore arruma(NoArvore Q, NoArvore R) {
		if (R.getDir() != null)
			R.setDir(this.arruma(Q, R.getDir()));
		else {
			Q.setInfo(R.getInfo());
			R = R.getEsq();
		}
		return R;
	}
	// métodos (inclusive os de inserção, remoção e pesquisa)
}
