package arvoreAVL;

import dados.Item;

public class ArvoreAVL {//o que achei no pdf
	private No raiz;
	private boolean h;


	public ArvoreAVL(){
		this.raiz = null;
		this.h = true;
	}

	public void insereRaiz (Item elem){
		this.raiz = this.insere (elem, this.raiz);
	}
	
	private No insere (Item elem, No no){
		if (no == null){
			No novo = new No(elem);
			this.h = true;
			return novo;

		}
		else{
			if (elem.getChave() < no.getInfo().getChave()){
				// Insere à esquerda e verifica se precisa balancear à direita
				no.setEsq(this.insere (elem, no.getEsq()));
				no = this.balancearDir (no);
				return no;
			}
			else{
				// Insere à direita e verifica se precisa balancear à esquerda
				no.setDir(this.insere (elem, no.getDir()));
				no = this.balancearEsq (no);
				return no;
			}
		}
	}

	private No balancearDir (No no){
		if (this.h)
			switch (no.getFatorBalanceamento()){
			case 1 : no.setFatorBalanceamento((byte)0);
			this.h = false;
			break;
			case 0 : no.setFatorBalanceamento((byte)-1);
			break;
			case -1: no = this.rotaçãoDireita (no);
			}
		return no;
	}

	private No balancearEsq (No no){
		if (this.h)
			switch (no.getFatorBalanceamento()){
			case -1: no.setFatorBalanceamento((byte)0);
			this.h = false;
			break;
			case 0 : no.setFatorBalanceamento((byte)1);
			break;
			case 1 : no = this.rotaçãoEsquerda (no); }
		return no;
	}

	private No rotaçãoDireita (No no){
		No temp1, temp2;
		temp1 = no.getEsq();
		if (temp1.getFatorBalanceamento() == -1){
			no.setEsq(temp1.getDir());
			temp1.setDir(no);
			no.setFatorBalanceamento((byte)0);
			no = temp1;
		}
		else {
			temp2 = temp1.getDir();
			temp1.setDir(temp2.getEsq());
			temp2.setEsq(temp1);
			no.setEsq(temp2.getDir());
			temp2.setDir(no);
			if (temp2.getFatorBalanceamento() == -1)
				no.setFatorBalanceamento((byte)1);
			else
				no.setFatorBalanceamento((byte)0);
			if (temp2.getFatorBalanceamento() == 1)
				temp1.setFatorBalanceamento((byte)-1);
			else
				temp1.setFatorBalanceamento((byte)0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte)0);
		this.h = false;
		return no;
	}

	private No rotaçãoEsquerda (No no){
		No temp1, temp2;
		temp1 = no.getDir();
		if (temp1.getFatorBalanceamento() == 1){
			no.setDir (temp1.getEsq());
			temp1.setEsq(no);
			no.setFatorBalanceamento((byte)0);
			no = temp1;
		}
		else {
			temp2 = temp1.getEsq();
			temp1.setEsq(temp2.getDir());
			temp2.setDir(temp1);
			no.setDir(temp2.getEsq());
			temp2.setEsq(no);
			if (temp2.getFatorBalanceamento() == 1)
				no.setFatorBalanceamento((byte)-1);
			else
				no. setFatorBalanceamento((byte)0);
			if (temp2. getFatorBalanceamento() == -1)
				temp1. setFatorBalanceamento((byte)1);

			else
				temp1. setFatorBalanceamento((byte)0);
			no = temp2;
		}
		no.setFatorBalanceamento((byte)0);
		this.h = false;
		return no;
	}




}

