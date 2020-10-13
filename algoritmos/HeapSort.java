package algoritmos;

import dados.Item;


public class HeapSort {
	

	public void metodo(Item[] vetor){
		int nElem = vetor.length;
		int dir = nElem-1;
		int esq = (dir-1)/2;
		Item temp;

		while (esq >= 0){
			refazHeap (esq, nElem-1,vetor);
			esq--;
		}
		while (dir > 0){
			temp = vetor[0];
			vetor [0] = vetor [dir];
			vetor [dir] = temp;
			dir--;
			refazHeap(0, dir,vetor);
		}
	}

	private void refazHeap (int esq, int dir,Item[] vetor){
		int i = esq;
		int mF = 2*i+1; // maior filho
		Item raiz = vetor[i];
		boolean heap = false;

		while ((mF <= dir) && (!heap)){
			if ( mF < dir)
				if (vetor[mF].getChave() < vetor[mF+1].getChave())
					mF ++;
			if (raiz.getChave() < vetor[mF].getChave()) {
				vetor[i] = vetor[mF];
				i = mF;
				mF = 2*i+1;
			}
			else
				heap = true;
		}
		vetor[i] = raiz;
	}


}
