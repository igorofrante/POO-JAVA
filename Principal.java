import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();
	public static void main(String[] args) {

		char menu;

		do {
			System.out.println("----MENU TESTE2----\n"
					+ "1 - Ler arquivos \n"
					+ "2 - Consultar todos os dados \n"
					+ "3 - Calcular mÃ©dia das idades \n"
					+ "4 - Calcular mÃ©dia dos IMC \n"
					+ "0 - sair \n\n");
			menu = scan.next().charAt(0);

			switch (menu) {

			case '1':
				lerArquivos();
				break;
			case '2':
				//ConsultarDados();
				break;
			case '3':
				//calcularMedia();
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

	static void lerArquivos() {
		memoria.delete(0,memoria.length());
		System.out.println("[Atenção] A memória foi limpa!");
		System.out.println("Digite o número do arquivo a ser lido"
				+ "1 - 500 linhas\n"
				+ "2 - 1000 linhas\n");

		char narq=scan.next().charAt(0);

		try{

			BufferedReader arqEntrada;

			System.out.println("Digite o número do arquivo a ser lido\n");
			arqEntrada = new BufferedReader (new FileReader(narq+".txt"));
			String linha = "";

			while( (linha=arqEntrada.readLine()) != null){
				memoria.append(linha+"\n");
			}
			arqEntrada.close();
			System.out.println("O arquivo " +(narq)+".txt" +" foi lido.");




			System.out.println("Leitura encerrada!" +"\n");


		}
		catch(FileNotFoundException erro1){
			JOptionPane.showMessageDialog(null, "O arquivo " +(narq)+".txt" +" nao foi encontrado!" ,null, JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro de leitura!",null, JOptionPane.ERROR_MESSAGE);
		}

	}
}
