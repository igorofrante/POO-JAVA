package dados;

public class NoAVL {
	private byte fatorBalanceamento;
	private Banco info;
	private NoAVL dir, esq;

	public NoAVL(Banco info) {
		this.info = info;
		this.fatorBalanceamento = 0;
	}

	public byte getFatorBalanceamento() {
		return this.fatorBalanceamento;
	}

	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}

	public Banco getInfo() {
		return info;
	}

	public void setInfo(Banco info) {
		this.info = info;
	}

	public NoAVL getDir() {
		return dir;
	}

	public void setDir(NoAVL dir) {
		this.dir = dir;
	}

	public NoAVL getEsq() {
		return esq;
	}

	public void setEsq(NoAVL esq) {
		this.esq = esq;
	}

}