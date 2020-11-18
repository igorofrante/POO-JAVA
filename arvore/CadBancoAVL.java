package arvore;

import java.util.ArrayList;
import dados.Banco;
import dados.NoAVL;

public class CadBancoAVL extends CadBancoArv {// o que achei no pdf
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
			NoAVL novo = new NoAVL(elem);
			this.h = true;
			return novo;

		} else {
			if (elem.compareTo(no.getInfo()) < 0) {
				// Insere à esquerda e verifica se precisa balancear à direita
				no.setEsq(this.insere(elem, (NoAVL) no.getEsq()));
				no = this.balancearDir(no);
				return no;
			} else {
				// Insere à direita e verifica se precisa balancear à esquerda
				no.setDir(this.insere(elem, (NoAVL) no.getDir()));
				no = this.balancearEsq(no);
				return no;
			}
		}
	}
	
	protected void insereTudo() {
		for (Banco banco : super.vetBanco) {
			this.insereRaiz(banco);
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
		temp1 = (NoAVL)no.getEsq();
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
	
	public void AVL() {
		this.insereTudo();		
	}
	
	
	
	public ArrayList<Banco> pesquisaAVL(String chave) {
		ArrayList<Banco> vet = new ArrayList<Banco>();
		this.pesquisaAVL(chave, this.raiz, vet);
		return vet;
	}

	private void pesquisaAVL(String chave, NoAVL arv, ArrayList<Banco> vet) {
		if (arv != null) {
			this.pesquisaAVL(chave,(NoAVL)arv.getEsq(), vet);
			if(chave.equals(arv.getInfo().getCpf())) {
				vet.add(arv.getInfo());
			}			
			this.pesquisaAVL(chave,(NoAVL)arv.getDir(), vet);
		}
	}
}
