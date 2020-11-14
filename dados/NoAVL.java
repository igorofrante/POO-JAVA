package dados;

public class NoAVL {
	private Banco info;
	private byte fatorBalanceamento;
	private NoAVL dir, esq;

	public NoAVL(Banco info, byte fatorBalanceamento) {
		this.info=info;
		this.fatorBalanceamento = fatorBalanceamento;
	}

	public byte getFatorBalanceamento() {
		return this.fatorBalanceamento;
	}

	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
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

	public Banco getInfo() {
		return info;
	}

	public void setInfo(Banco info) {
		this.info = info;
	}

}