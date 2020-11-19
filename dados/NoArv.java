package dados;

import java.util.ArrayList;

public class NoArv {
	protected ArrayList<Banco> info;
	protected NoArv dir, esq;

	public NoArv (ArrayList<Banco> info){
		this.info = info;
	}

	public ArrayList<Banco> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<Banco> info) {
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
