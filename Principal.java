import java.util.Scanner;

import javax.swing.JOptionPane;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();
	public static void main(String[] args) {
		
		char menu = scan.next().charAt(0);
		
		do {
			System.out.println("----MENU TESTE2----\n"
					+ "1- Ler arquivos \n"
					+ "2- Consultar todos os dados \n"
					+ "3- Calcular média das idades \n"
				    + "4- Calcular média dos IMC \n"
					+ "0- sair \n\n");
			menu = scan.next().charAt(0);

			switch (menu) {

			case 1:
				//lerArquivos();
				break;
			case 2:
				//ConsultarDados();
				break;
			case 3:
				//calcularMedia();
				break;
			case 4:
				//calcularMediaIMC();
				break;
			case 0:
				System.out.println("Programa Encerrado." +"\n" +"Algoritmo desenvolvido por Aryadne Bastos e Igor Ofrante.");
				break;
			default :
				JOptionPane.showMessageDialog(null, "Opção Inválida, tente novamente!",null, JOptionPane.ERROR_MESSAGE);
				break;
			}

		}while(menu!=0);

	

	}

}
