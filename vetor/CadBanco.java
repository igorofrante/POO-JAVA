package vetor;

import java.util.ArrayList;

import dados.Banco;

public class CadBanco {
	private ArrayList<Banco> vetBanco;

	public CadBanco (int tam){
		this.vetBanco = new ArrayList<Banco>(tam); 
	}
	
	public CadBanco (ArrayList<Banco> lista){
		this.vetBanco = lista; 
	}
	public String toString (){
 		String temp = "";
		int i;
		
		for (i=0; i<this.vetBanco.size(); i++)
			temp += this.vetBanco.get(i).toString()+"\n";
		return temp;
	}
	
	public String toString (int pos){
		return this.vetBanco.get(pos).toString();
	}
	public Banco getBanco (int pos){
		return this.vetBanco.get(pos);
	}
	public int getTam(){
		return this.vetBanco.size();
	}
	public int pesquisaSeqCpf (String cpf){
		int i=0;
		
		while (i<this.vetBanco.size())
			if (cpf.equalsIgnoreCase(this.vetBanco.get(i).getCpf()))
				return i;
			else
				i++;
		return -1;
	}	
/*	public int pesqSeqNome (String nome){
		int i=0;
		
		while (i<this.vetBanco.size())
			if (nome.equalsIgnoreCase(this.vetBanco.get(i).getEmpresa()))
				return i;
			else
				i++;
		return -1;
	}
	*/
	public int pesquisaSeq (String ag, String conta){
		int i=0;
		String chave = ag+" "+conta;
		while (i<this.vetBanco.size())
			if (chave.equalsIgnoreCase(this.vetBanco.get(i).getAgencia()+ this.vetBanco.get(i).getConta()))
				return i;
			else
				i++;
		return -1;
	}	
	
	public ArrayList<Banco> pesqBin (String chave) {
		int meio, esq, dir, i;
		esq = 0;
		dir = this.vetBanco.size()-1;
		ArrayList<Banco> lista = new ArrayList<Banco>();
		while (esq <= dir){
			meio = (esq + dir)/2;
			if (chave.equals(this.vetBanco.get(meio).getCpf())) {
				i=meio-1;
				while (chave.equals(this.vetBanco.get(i).getCpf()))
					i--;
				i++;
				while (chave.equals(this.vetBanco.get(i).getCpf())) {
					lista.add(this.vetBanco.get(i));
					i++;
				}
				return lista;
			}
			else{
				if (chave.compareToIgnoreCase(this.vetBanco.get(meio).getCpf())<0)
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return null;
		
	}
	public void insere (Banco dupl){
			this.vetBanco.add(dupl);
	}	
	public void quicksort (){
	    ordena (0, this.vetBanco.size()-1);
	 }

	private void ordena (int esq, int dir){
		Banco pivo;
	    int i = esq, j = dir;
	    Banco temp;

	    pivo = this.vetBanco.get((i+j)/2);
	    do {
	    	while (this.vetBanco.get(i).compareTo(pivo)<0)
	    		i++;
	    	while (this.vetBanco.get(j).compareTo(pivo)>0) 
	    		j--;
	    	if (i <= j) {
	    		temp = this.vetBanco.get(i);
	    		this.vetBanco.set(i, this.vetBanco.get(j));
	    		this.vetBanco.set(j, temp);
	    		i++;		
	    		j--;
	    	}
	    } while (i <= j);
	    if (esq < j) ordena (esq, j);
	    if (dir > i) ordena (i, dir);
	}

	public void quicksortInv (){
	    ordenaInv (0, this.vetBanco.size()-1);
	 }

	private void ordenaInv (int esq, int dir){
	    Banco pivo;
	    int i = esq, j = dir;
	    Banco temp;

	    pivo = this.vetBanco.get((i+j)/2);
	    do {
	    	while (this.vetBanco.get(i).compareTo(pivo)>0) 
	    		i++;
	    	while (this.vetBanco.get(j).compareTo(pivo)<0) 
	    		j--;
	    	if (i <= j) {
	    		temp = this.vetBanco.get(i);
	    		this.vetBanco.set(i, this.vetBanco.get(j));
	    		this.vetBanco.set(j, temp);
	    		i++;		
	    		j--;
	    	}
	    } while (i <= j);
	    if (esq < j) ordenaInv (esq, j);
	    if (dir > i) ordenaInv (i, dir);
	}

}
