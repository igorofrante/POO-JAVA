package dados;

public class NoArv {
	protected Banco info;
	protected NoArv dir, esq;

	public NoArv (Banco info){
		this.info = info;
	}

	public Banco getInfo() {
		return info;
	}

	public void setInfo(Banco info) {
		this.info = info;
	}

	public NoArv getDir() {
		return dir;
	}

	public void setDir(NoArv dir) {
		this.dir = dir;
	}

	public NoArv getEsq() {
		return esq;
	}

	public void setEsq(NoArv esq) {
		this.esq = esq;
	}
	
	
}
