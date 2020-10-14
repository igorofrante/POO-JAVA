package arrumaArquivos;
import java.io.*;
import java.util.ArrayList;

import dados.Banco;
import io.*;
import vetor.CadBanco;
public class Pesquisa {
	@SuppressWarnings("null")
	public static void main (String[] args){
		int[] tam = {500};//, 1000, 5000, 10000, 30000};
		ArrayList<Banco> empr = new ArrayList<Banco>();
		ArrayList<String> profEst = new ArrayList<String>();
		ArrayList<Banco> result = new ArrayList<Banco>();
		String[] vet = new String[400];
		String[] aux;
		GravaArq arq;
		CadBanco cad = null;
		String msg="";		
		try {
			Arquivo.leArquivo(profEst, "conta500ord.txt");
			arq = new GravaArq ("teste.txt", true);
			for (int i=0; i<profEst.size(); i++) {
				arq.gravaArquivo(profEst.get(i).toString()+"\n");
			}
			arq.fechaArquivo();
		}

		catch (FileNotFoundException e) {
			System.out.println("Arquivo nao existe");
		}
		catch (IOException erro){}

	}
}
