package algoritmos;

import dados.Item;

public class QuickSort{


	public void metodo (Item[] vetor){
		int nElem = vetor.length;
		ordena (0, nElem-1,vetor);
	}
	
	private void ordena (int esq, int dir, Item[] vetor){
		int pivo, i = esq, j = dir;
		Item temp;

		pivo = vetor[(i+j)/2].getChave();
		do {
			while (vetor[i].getChave() < pivo)
				i++;
			while (vetor[j].getChave() > pivo)
				j--;
			if (i <= j) {
				temp = vetor[i];
				vetor[i] = vetor[j];
				vetor[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);
		if (esq < j)
			ordena (esq, j,vetor);
		if (dir > i)
			ordena (i, dir,vetor);
	}

}
