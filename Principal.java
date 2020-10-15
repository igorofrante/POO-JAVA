
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import io.LeArquivo;
import vetor.CadBanco;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();
	public static void main(String[] args) throws FileNotFoundException {

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

	static void primeiraEtapa () throws FileNotFoundException {
		long startTime = System.currentTimeMillis();// método de tempo
		//2) Carregue o vetor com o arquivo de 500 elementos aleatórios.
		//lerArquivo("1");
		CadBanco contas = new CadBanco(500);
		LeArquivo arquivo = new LeArquivo("conta500alea.txt");
		arquivo.leArquivoBanco(contas.getBancoLista());
		
		System.out.println(contas.toString());
		

		//3) Use o método HeapSort para ordenar os registros pelo CPF, se tiver mais de um CPF 	igual, ordenar pela agência e número da conta. 

		//4) Gravar
		//gravarDados("HeapAlea500");


		//Método Imprimir tempo em segundo
		System.out.println((System.currentTimeMillis()-startTime)/1000.0 + " segundos");
	}

	static void segundaEtapa () {

	}
	static void terceiraEtapa () {

	}
}
