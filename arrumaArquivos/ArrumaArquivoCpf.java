package arrumaArquivos;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import io.*;
import item.Banco;

public class ArrumaArquivoCpf {
	static int ind;
	static int[] tamanhos = {50000};
	public static void main (String[] args){
		ArrayList<String> cpf = new ArrayList<String>(400);
		ArrayList<Banco> lista = null;

		lista = preencheIguais (cpf);
		preencheDiferentes(lista, cpf);
		embaralha (cpf);
		try{
			GravaArq arq = new GravaArq ("Conta.txt", false);
			for (String s: cpf){
				arq.gravaArquivo(s+"\n");
			}
			arq.fechaArquivo();

		}
		catch (IOException erro){}

	}

	private static ArrayList<Banco> preencheIguais(ArrayList<String> cpf){
		int j, k;
		j=0;
		LeArquivo entrada; 
		ArrayList<Banco> lista = new ArrayList<Banco>(1000);
		try {
			entrada = new LeArquivo ("conta1000ord.txt");
			entrada.leArquivoBanco(lista);
			for (j=0; j<120; j++){
				k = (int)(Math.random()*1000);
				cpf.add(lista.get(k).getCpf());
			}
			lista = new ArrayList<Banco>(10000);
			entrada = new LeArquivo ("conta10000ord.txt");
			entrada.leArquivoBanco(lista);
			for (j=120; j<250; j++){
				k = (int)(Math.random()*10000);
				cpf.add(lista.get(k).getCpf());
			}
			lista = new ArrayList<Banco>(50000);
			entrada = new LeArquivo ("conta50000ord.txt");
			entrada.leArquivoBanco(lista);
			for (j=250; j<350; j++){
				k = (int)(Math.random()*50000);
				cpf.add(lista.get(k).getCpf());
			}
		} catch (FileNotFoundException e) {}
		return lista;
	}
	
	private static void preencheDiferentes(ArrayList<Banco> lista, ArrayList<String> cpf) {
		int j, aux;
		String temp;
		Random r = new Random();

		for (j=350; j<400; j++){
			temp="";
			for (int k=0; k<11; k++){
				aux = r.nextInt(10);
				temp += aux;
			}
			if (ArrumaArquivo.pesquisa(temp, cpf)<0)
				cpf.add(temp);
			else
				j--;
		}
	}

	static String formaProfEstado (ArrayList<String> arqProf, 
			                      ArrayList<String> arqEst){
		int auxp, auxe;
		String temp;

		auxp = (int)(Math.random()*arqProf.size());
		auxe = (int)(Math.random()*arqEst.size());
		temp = arqProf.get(auxp)+";"+arqEst.get(auxe);
		return temp;
	}

	private static void embaralha(ArrayList<String> cpf) {
		String temp;
		int ind;
		Random r = new Random();
		
		for (int i=0; i<cpf.size();i++){
			ind = r.nextInt(cpf.size());
			temp = cpf.get(i);
			cpf.set(i, cpf.get(ind));
			cpf.set(ind, temp);
		}
		
	}
}
