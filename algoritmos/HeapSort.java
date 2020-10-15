package algoritmos;

import java.util.ArrayList;
import dados.Banco;


public class HeapSort {
	

	public void metodo(ArrayList<Banco> vetor){
		int nElem = vetor.size();
		int dir = nElem-1;
		int esq = (dir-1)/2;
		Banco temp;

		while (esq >= 0){
			refazHeap (esq, nElem-1,vetor);
			esq--;
		}
		while (dir > 0){
			temp = vetor.get(0);
			vetor.set(0, vetor.get(dir));
			vetor.set(dir, temp);
			dir--;
			refazHeap(0, dir,vetor);
		}
	}

	private void refazHeap (int esq, int dir,ArrayList<Banco> vetor){
		int i = esq;
		int mF = 2*i+1; // maior filho
		Banco raiz = vetor.get(i);
		boolean heap = false;

		while ((mF <= dir) && (!heap)){
			if ( mF < dir)
				if (vetor.get(mF).compareTo(vetor.get(mF+1))==-1)
					mF ++;
			if (raiz.compareTo(vetor.get(mF))==-1) {
				vetor.set(i, vetor.get(mF));
				i = mF;
				mF = 2*i+1;
			}
			else
				heap = true;
		}
		vetor.set(i, raiz);
	}


}
