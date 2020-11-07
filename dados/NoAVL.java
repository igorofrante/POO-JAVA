package dados;

public class NoAVL extends NoArvore {

	private byte fatorBalanceamento;
	private NoAVL dir, esq;

	public NoAVL(Banco info, byte fatorBalanceamento) {
		super(info);
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

}