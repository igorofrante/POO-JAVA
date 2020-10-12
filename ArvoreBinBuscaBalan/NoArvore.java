package ArvoreBinBuscaBalan;

import dados.Item;

public class NoArvore {
	private Item info;
	private NoArvore dir, esq;

	public NoArvore (Item info){
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
	public Item getInfo() {
		return info;
	}
	public void setInfo(Item novo) {
		this.info = novo;
	}
}
