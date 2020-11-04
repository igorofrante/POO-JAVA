package dados;

public class NoArvore {
	private Banco info;
	private NoArvore dir, esq;

	public NoArvore (Banco info){
		this.info = info;
	}
	public NoArvore getDir() {
		return dir;
	}
	public void setDir(NoArvore dir) {
		this.dir = dir;
	}
	public NoArvore getEsq() {
		return esq;
	}
	public void setEsq(NoArvore esq) {
		this.esq = esq;
	}
	public Banco getInfo() {
		return info;
	}
	public void setInfo(Banco novo) {
		this.info = novo;
	}
}
