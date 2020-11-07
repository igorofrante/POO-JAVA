package arvore;

import dados.Banco;
import dados.NoAVL;
import vetor.CadBanco;

public class CadBancoAVL extends CadBanco {// o que achei no pdf
	private NoAVL raiz;
	private boolean h;

	public CadBancoAVL(int tam) {
		super(tam);
		this.raiz = null;
		this.h = true;
	}

	public void insereRaiz(Banco elem) {
		this.raiz = this.insere(elem, this.raiz);
	}

	private NoAVL insere(Banco elem, NoAVL no) {
		if (no == null) {
			NoAVL novo = new NoAVL(elem, (byte) 0);
			this.h = true;
			return novo;

		} else {
			if (elem.compareTo(no.getInfo()) < 0) {
				// Insere à esquerda e verifica se precisa balancear à direita
				no.setEsq(this.insere(elem, no.getEsq()));
				no = this.balancearDir(no);
				return no;
			} else {
				// Insere à direita e verifica se precisa balancear à esquerda
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
			temp2 = temp1.getDir();
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
		temp1 = no.getDir();
		if (temp1.getFatorBalanceamento() == 1) {
			no.setDir(temp1.getEsq());
			temp1.setEsq(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getEsq();
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

}
