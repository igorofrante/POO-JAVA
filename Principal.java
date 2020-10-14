import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import algoritmos.HeapSort;
import dados.Item;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();
	public static void main(String[] args) {

		char menu;
		//1) Comece a contar o tempo.
		do {
			System.out.println("----MENU TESTE 33---\n"
					+ "1 - Etapa HeapSort + Pesquisa Binária \n"
					+ "2 - Etapa QuickSort + Pesquisa Binária \n"
					+ "3 - Etapa Árvores\n"					
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
				JOptionPane.showMessageDialog(null, "OpÃ§Ã£o InvÃ¡lida, tente novamente!",null, JOptionPane.ERROR_MESSAGE);
				break;
			}

		}while(menu!=0);



	}

	static void lerArquivo(String narq) {
		memoria.delete(0,memoria.length());

		try{
			BufferedReader arqEntrada;
			arqEntrada = new BufferedReader (new FileReader(narq+".txt"));
			String linha = "";
			while( (linha=arqEntrada.readLine()) != null){
				memoria.append(linha+"\n");
			}
			arqEntrada.close();
			System.out.println("O arquivo " +(narq)+".txt" +" foi lido.");
		}
		catch(FileNotFoundException erro1){
			JOptionPane.showMessageDialog(null, "O arquivo " +(narq)+".txt" +" nao foi encontrado!" ,null, JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro de leitura!",null, JOptionPane.ERROR_MESSAGE);
		}

	}

	static void gravarDados(String nome){
		try{
			BufferedWriter arqSaida;
			arqSaida = new BufferedWriter(new FileWriter (nome+".txt"));
			arqSaida.write(memoria.toString());
			arqSaida.flush();  //salva no dispositivo
			arqSaida.close();
		}catch(Exception erro3){
			JOptionPane.showMessageDialog(null, "Erro de gravação!",null, JOptionPane.ERROR_MESSAGE);
		}
	}

	static void primeiraEtapa () {
		long startTime = System.currentTimeMillis();// método de tempo
		//2) Carregue o vetor com o arquivo de 500 elementos aleatórios.
		//lerArquivo("1");
		Item[] vetor = new Item[5];
		vetor[0]=new Item(50);
		vetor[1]=new Item(10);
		vetor[2]=new Item(70);
		vetor[3]=new Item(100);
		vetor[4]=new Item(75);
		HeapSort heap = new HeapSort();
		heap.metodo(vetor);

		for (int i = 0; i < vetor.length; i++) {
			System.out.println(vetor[i].getChave());
		}

		//3) Use o método HeapSort para ordenar os registros pelo CPF, se tiver mais de um CPF 	igual, ordenar pela agência e número da conta. 

		//4) Gravar
		gravarDados("HeapAlea500");


		//Método Imprimir tempo em segundo
		System.out.println((System.currentTimeMillis()-startTime)/1000.0 + " segundos");
	}

	static void segundaEtapa () {

	}
	static void terceiraEtapa () {

	}
}
