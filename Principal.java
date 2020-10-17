
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

	static void primeiraEtapa () throws IOException{
		long startTime = System.currentTimeMillis();// método de tempo
		//2) Carregue o vetor com o arquivo de 500 elementos aleatórios.
		//lerArquivo("1");
		CadBanco contas = new CadBanco(500);
		LeArquivo arquivo = new LeArquivo("conta500alea.txt");
		arquivo.leArquivoBanco(contas.getBancoLista());



		//3) Use o método HeapSort para ordenar os registros pelo CPF, se tiver mais de um CPF 	igual, ordenar pela agência e número da conta. 
		
		contas.HeapSort();

		//4) Gravar
		GravaArq grava = new GravaArq("HeapAlea500.txt",true);
		grava.gravaArquivo(contas.toString());
		grava.fechaArquivo();

		LeArquivoCpf cpfs = new LeArquivoCpf("Conta.txt");
		ArrayList<String> buscar = cpfs.leArquivo(400);
		cpfs.fechaArquivo();
		CadBanco encontrados = new CadBanco(50);

		ArrayList<Banco> contas2 = new ArrayList<Banco>();
		for(int i = 0 ; i<buscar.size();i++) {
			contas2 = contas.pesqBin(buscar.get(i));
			if(contas2!=null) {
				encontrados.insereLista(contas2);
			}
		}
		
		encontrados.removerRepetidos();
		
		String stringao = "";
		int a = 0;
		double total = 0;
		double saldo = 0;
		boolean cont = true;
		
		for (int i = 0; i < buscar.size(); i++) {
			a = encontrados.pesquisaSeqCpf(buscar.get(i));
			if(a!=-1) {
				total=0;
				stringao += "CPF " +buscar.get(i) +" NOME: "+encontrados.getBanco(a).getNome() +"\n";
				while(cont && buscar.get(i).equals(encontrados.getBanco(a).getCpf())) {
					stringao += "Ag: " + encontrados.getBanco(a).getAgencia();
					if(encontrados.getBanco(a).getConta().substring(0, 3).equals("001")) {
						stringao += " Conta Comum: " +encontrados.getBanco(a).getConta();
					}else if (encontrados.getBanco(a).getConta().substring(0, 3).equals("002")){
						stringao += " Conta Especial: " +encontrados.getBanco(a).getConta();
					}else{
						stringao += " Conta Poupança: " +encontrados.getBanco(a).getConta();
					}
					saldo = encontrados.getBanco(a).getSaldo();
					stringao +=  " Saldo: " +saldo +"\n";
					total += encontrados.getBanco(a).getSaldo();
					a++;
					
					if(a>=encontrados.getTam()) {
						cont = false;
					}
				}
				if(total!=saldo) {
					stringao += "Saldo Total: " +total +"\n\n";	
				}else {
					stringao += "\n";
				}
				
				
			}else {
				stringao += "CPF " +buscar.get(i) +":\n"
					        +"NÃO HÁ NENHUM REGISTRO COM O CPF " +buscar.get(i)+"\n\n";
			}
		}
		
		System.out.println(stringao.toString());

//		GravaArq grava2 = new GravaArq("achados.txt",true);
//		grava2.gravaArquivo(encontrados.toString());
//		grava2.fechaArquivo();
		
		//GravaArq grava2 = new GravaArq("achados.txt",true);
		// grava2.gravaArquivo(encontrados.toString());
		//grava2.fechaArquivo();



		//Método Imprimir tempo em segundo
		//System.out.println((System.currentTimeMillis()-startTime)/1000.0 + " segundos");
		
			}

	


	static void segundaEtapa () {

	}
	static void terceiraEtapa () {

	}
	
	
}
