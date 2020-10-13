package dados;

public class No {
	private Item info;
	private No esq, dir;
	private byte fatorBalanceamento;

	public No (Item i){//construtor
		this.info = i;
		this.fatorBalanceamento = 0;
	}
	public No getDir() {
		return this.dir;
	}
	public void setDir(No dir) {
		this.dir = dir;
	}
	public No getEsq() {
		return this.esq;
	}
	public void setEsq(No esq) {
		this.esq = esq;
	}
	public byte getFatorBalanceamento() {
		return this.fatorBalanceamento;
	}
	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}
	public Item getInfo() {
		return this.info;
	}
}

