
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import javax.swing.JOptionPane;

import algoritmos.HeapSort;
import algoritmos.PesquisaBinaria;
import dados.Banco;
import io.GravaArq;
import io.LeArquivo;
import io.LeArquivoCpf;
import vetor.CadBanco;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();
	public static void main(String[] args) throws IOException {

		char menu;
		//1) Comece a contar o tempo.
		do {
			System.out.println("----MENU---\n"
					+ "1 - Etapa HeapSort + Pesquisa Bin�ria \n"
					+ "2 - Etapa QuickSort + Pesquisa Bin�ria \n"
					+ "3 - Etapa �rvores\n"					
					+ "0 - Sair \n\n");
			menu = scan.next().charAt(0);

			switch (menu) {

			case '1':
				primeiraEtapa();
				break;
			case '2':
				segundaEtapa();
				break;
			case '3':
				terceiraEtapa();
				break;
			case '4':
				//calcularMediaIMC();
				break;
			case '0':
				System.out.println("Programa Encerrado." +"\n" +"Algoritmo desenvolvido por Aryadne Bastos e Igor Ofrante.");
				break;
			default :
				JOptionPane.showMessageDialog(null, "Opção Inválida, tente novamente!",null, JOptionPane.ERROR_MESSAGE);
				break;
			}

		}while(menu!=0);



	}

	static void primeiraEtapa () throws IOException {
		long startTime = System.currentTimeMillis();// m�todo de tempo
		//2) Carregue o vetor com o arquivo de 500 elementos aleat�rios.
		//lerArquivo("1");
		CadBanco contas = new CadBanco(500);
		LeArquivo arquivo = new LeArquivo("conta500alea.txt");
		arquivo.leArquivoBanco(contas.getBancoLista());



		//3) Use o m�todo HeapSort para ordenar os registros pelo CPF, se tiver mais de um CPF 	igual, ordenar pela ag�ncia e n�mero da conta. 
		HeapSort heap = new HeapSort();
		heap.metodo(contas.getBancoLista());

		//4) Gravar
		//gravarDados("HeapAlea500");
		//GravaArq grava = new GravaArq("HeapAlea500.txt",true);
		//grava.gravaArquivo(contas.toString());
		//grava.fechaArquivo();

		LeArquivoCpf cpfs = new LeArquivoCpf("Conta.txt");
		String[] buscar = cpfs.leArquivo(400);
		CadBanco encontrados = new CadBanco(50);

		for (int i = 0; i < buscar.length; i++) {
			System.out.println(i);
		}

		ArrayList<Banco> contas2 = new ArrayList<Banco>();
		for(int i = 0 ; i<buscar.length;i++) {
			contas2 = contas.pesqBin(buscar[i]);
			if(contas2!=null) {
				encontrados.insereLista(contas2);
			}
			System.out.println(i);
		}
		
		encontrados.removerRepetidos();

		GravaArq grava2 = new GravaArq("achados.txt",true);
		grava2.gravaArquivo(encontrados.toString());
		grava2.fechaArquivo();


	}

	//GravaArq grava2 = new GravaArq("achados.txt",true);
	// grava2.gravaArquivo(encontrados.toString());
	//grava2.fechaArquivo();



	//M�todo Imprimir tempo em segundo
	//System.out.println((System.currentTimeMillis()-startTime)/1000.0 + " segundos");


	static void segundaEtapa () {

	}
	static void terceiraEtapa () {

	}
	
	
}
