package pesqBina;

import dados.Item;

public class pesquisaBinaria {

	public int metodo(Item[] vetor,int chave){
		int nElem = vetor.length;
		int meio, esq, dir;
		esq = 0;
		dir = nElem-1;
		while (esq <= dir){
			meio = (esq + dir)/2;
			if (chave == vetor[meio].getChave())
				return meio;
			else{
				if (chave < vetor[meio].getChave())
					dir = meio - 1;
				else
					esq = meio + 1;
			}
		}
		return -1;
	}
}
