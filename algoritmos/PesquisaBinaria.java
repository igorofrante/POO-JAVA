package algoritmos;

import java.util.ArrayList;

import dados.Banco;


public class PesquisaBinaria {

	public boolean metodo(ArrayList<Banco> vetor,Long chave){
		int nElem = vetor.size();
		int meio, esq, dir;
		esq = 0;
		dir = nElem-1;
		while (esq <= dir){
			meio = (esq + dir)/2;
			if (chave == Long.parseLong(vetor.get(meio).getCpf()))
				return true;
			else{
				if (chave < Long.parseLong(vetor.get(meio).getCpf()))
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return false;
	}
}
