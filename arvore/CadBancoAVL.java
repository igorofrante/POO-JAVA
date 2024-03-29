package arvore;

import java.util.ArrayList;
import dados.Banco;
import dados.NoAVL;
import vetor.CadBanco;

public class CadBancoAVL extends CadBanco {
	private NoAVL raiz;
	private boolean h;

	public CadBancoAVL(int tam) {
		super(tam);
		this.raiz = null;
		this.h = true;
	}

	public boolean pesquisa(Banco conta) { // pesquisa se existe elemento igual previamente adicionado
		NoAVL temp;
		temp = this.pesquisa(conta, this.raiz);
		if (temp != null)
			return true;
		else
			return false;
	}

	private NoAVL pesquisa(Banco elem, NoAVL no) {
		NoAVL temp;
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

	protected void insereTudo() { // insere todos os elementos na arvore
		for (Banco banco : super.vetBanco) {
			this.insere(banco);
		}
	}

	public void insere(Banco elem) { // insere na arvore se nao houver elemento igual previamente adicionado
		boolean existe = this.pesquisa(elem);
		if (!existe) {
			this.raiz = this.insere(elem, this.raiz);
		}
	}

	private NoAVL insere(Banco elem, NoAVL no) {
		if (no == null) {
			NoAVL novo = new NoAVL(elem);
			this.h = true;
			return novo;

		} else {
			if (elem.compareTo(no.getInfo()) < 0) {
				no.setEsq(this.insere(elem, no.getEsq()));
				no = this.balancearDir(no);
				return no;
			} else {
				no.setDir(this.insere(elem, no.getDir()));
				no = this.balancearEsq(no);
				return no;
			}
		}
	}

	private NoAVL balancearDir(NoAVL no) {
		if (this.h)
			switch (no.getFatorBalanceamento()) {
			case 1:
				no.setFatorBalanceamento((byte) 0);
				this.h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) -1);
				break;
			case -1:
				no = this.rotacaoDireita(no);
			}
		return no;
	}

	private NoAVL balancearEsq(NoAVL no) {
		if (this.h)
			switch (no.getFatorBalanceamento()) {
			case -1:
				no.setFatorBalanceamento((byte) 0);
				this.h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) 1);
				break;
			case 1:
				no = this.rotacaoEsquerda(no);
			}
		return no;
	}

	private NoAVL rotacaoDireita(NoAVL no) {
		NoAVL temp1, temp2;
		temp1 = no.getEsq();
		if (temp1.getFatorBalanceamento() == -1) {
			no.setEsq(temp1.getDir());
			temp1.setDir(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = (NoAVL) temp1.getDir();
			temp1.setDir(temp2.getEsq());
			temp2.setEsq(temp1);
			no.setEsq(temp2.getDir());
			temp2.setDir(no);
			if (temp2.getFatorBalanceamento() == -1)
				no.setFatorBalanceamento((byte) 1);
			else
				no.setFatorBalanceamento((byte) 0);
			if (temp2.getFatorBalanceamento() == 1)
				temp1.setFatorBalanceamento((byte) -1);
			else
				temp1.setFatorBalanceamento((byte) 0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;
	}

	private NoAVL rotacaoEsquerda(NoAVL no) {
		NoAVL temp1, temp2;
		temp1 = (NoAVL) no.getDir();
		if (temp1.getFatorBalanceamento() == 1) {
			no.setDir(temp1.getEsq());
			temp1.setEsq(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = (NoAVL) temp1.getEsq();
			temp1.setEsq(temp2.getDir());
			temp2.setDir(temp1);
			no.setDir(temp2.getEsq());
			temp2.setEsq(no);
			if (temp2.getFatorBalanceamento() == 1)
				no.setFatorBalanceamento((byte) -1);
			else
				no.setFatorBalanceamento((byte) 0);
			if (temp2.getFatorBalanceamento() == -1)
				temp1.setFatorBalanceamento((byte) 1);

			else
				temp1.setFatorBalanceamento((byte) 0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;
	}

	public void avl() { //metodo chamado na classe principal
		this.insereTudo();
	}

	public ArrayList<Banco> pesquisaAVL(String chave) { // baseado no Caminhamento Central
		ArrayList<Banco> vet = new ArrayList<Banco>();
		this.pesquisaAVL(chave, this.raiz, vet);
		if (vet.size() != 0) {
			return vet;
		} else {
			return null;
		}

	}

	private void pesquisaAVL(String chave, NoAVL arv, ArrayList<Banco> vet) {
		if (arv != null) {
			this.pesquisaAVL(chave, arv.getEsq(), vet);
			if (chave.equals(arv.getInfo().getCpf())) {
				vet.add(arv.getInfo());
			}
			this.pesquisaAVL(chave, arv.getDir(), vet);
		}
	}
}
