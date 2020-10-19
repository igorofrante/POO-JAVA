
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dados.Banco;

import io.GravaArq;
import io.LeArquivo;
import io.LeArquivoCpf;
import vetor.CadBanco;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		char menu;
		// 1) Comece a contar o tempo.
		do {
			System.out.println("----MENU---\n" + "1 - Etapa HeapSort + Pesquisa Bin·ria \n"
					+ "2 - Etapa QuickSort + Pesquisa Bin·ria \n" + "3 - Etapa ¡rvores\n" + "0 - Sair \n\n");
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
				// calcularMediaIMC();
				break;
			case '0':
				System.out.println("Programa encerrado!\n\n" + "[AUTORES]\n" + "Igor Ofrante\n" + "Karen Alcantara\n"
						+ "Lucas Sarmento\n" + "Mackweyd Gomes\n" + "Pedro Henrique Fernandes.");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Op√ß√£o Inv√°lida, tente novamente!", null,
						JOptionPane.ERROR_MESSAGE);
				break;
			}

		} while (menu != '0');

	}

	static void primeiraEtapa() throws IOException {
		long startTime = System.currentTimeMillis();// mÈtodo de tempo
		long startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "alea", "ord", "inv" };

		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o].toUpperCase() + "\n");
			for (int k = 0; k < narq.length; k++) {
				startArq = System.currentTimeMillis();

				CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
				LeArquivo arquivo = new LeArquivo("conta" + narq[k] + ord[o] + ".txt");
				arquivo.leArquivoBanco(contas.getBancoLista());

				contas.HeapSort();

				
				GravaArq grava = new GravaArq("Heap" + ord[o].toUpperCase() + narq[k] + ".txt", true);
				grava.gravaArquivo(contas.toString());
				grava.fechaArquivo();

				LeArquivoCpf cpfs = new LeArquivoCpf("Conta.txt");
				ArrayList<String> buscar = cpfs.leArquivo(400);
				cpfs.fechaArquivo();

				ArrayList<Banco> lista = new ArrayList<Banco>();
				String stringao = "";
				double saldoTotal = 0.0;
				int j = 0;
				for (int i = 0; i < buscar.size(); i++) {
					lista = contas.pesqBin(buscar.get(i));
					if (lista == null) {
						stringao += "CPF " + buscar.get(i) + ": \n" + "N√O H¡ NENHUM REGISTRO COM O CPF "
								+ buscar.get(i) + "\n\n";
					} else {
						stringao += "CPF " + lista.get(j).getCpf() + " NOME " + lista.get(j).getNome() + "\n";
						while (j != lista.size()) {
							if (lista.get(j).getConta().substring(0, 3).equals("001")) {
								stringao += "Ag " + lista.get(j).getAgencia() + " Conta Comum "
										+ lista.get(j).getConta() + " Saldo " + lista.get(j).getSaldo() + "\n";
							} else if (lista.get(j).getConta().substring(0, 3).equals("002")) {
								stringao += "Ag " + lista.get(j).getAgencia() + " Conta Especial "
										+ lista.get(j).getConta() + " Saldo " + lista.get(j).getSaldo() + "\n";
							} else {
								stringao += "Ag " + lista.get(j).getAgencia() + " Conta PoupanÁa "
										+ lista.get(j).getConta() + " Saldo " + lista.get(j).getSaldo() + "\n";
							}
							saldoTotal += lista.get(j).getSaldo();
							j++;
						}
						if (lista.size() != 1) {
							stringao += "Saldo Total: " + saldoTotal + "\n\n";
						} else {
							stringao += "\n";
						}

						j = 0;
						saldoTotal = 0;
					}

				}

				GravaArq grava2 = new GravaArq("extratoHeap" + ord[o].toUpperCase() + narq[k] + ".txt", true);
				grava2.gravaArquivo(stringao.toString());
				grava2.fechaArquivo();

				// MÈtodo Imprimir tempo em segundo
				System.out.println(narq[k]);
				System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");
				System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");

			}
			System.out.println("\n");
		}

	}

	static void segundaEtapa() throws IOException {
		long startTime = System.currentTimeMillis();// mÈtodo de tempo
		long startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "alea", "ord", "inv" };
		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o].toUpperCase() + "\n");
			for (int k = 0; k < narq.length; k++) {
				startArq = System.currentTimeMillis();
				CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
				LeArquivo arquivo = new LeArquivo("conta" + narq[k] + ord[o] + ".txt");
				arquivo.leArquivoBanco(contas.getBancoLista());

				contas.quicksort();

				// 4) Gravar
				GravaArq grava = new GravaArq("Quick" + ord[o].toUpperCase() + narq[k] + ".txt", true);
				grava.gravaArquivo(contas.toString());
				grava.fechaArquivo();

				LeArquivoCpf cpfs = new LeArquivoCpf("Conta.txt");
				ArrayList<String> buscar = cpfs.leArquivo(400);
				cpfs.fechaArquivo();

				ArrayList<Banco> lista = new ArrayList<Banco>();
				String stringao = "";
				double saldoTotal = 0.0;
				int j = 0;
				for (int i = 0; i < buscar.size(); i++) {
					lista = contas.pesqBin(buscar.get(i));
					if (lista == null) {
						stringao += "CPF " + buscar.get(i) + ": \n" + "N√O H¡ NENHUM REGISTRO COM O CPF "
								+ buscar.get(i) + "\n\n";
					} else {
						stringao += "CPF " + lista.get(j).getCpf() + " NOME " + lista.get(j).getNome() + "\n";
						while (j != lista.size()) {
							if (lista.get(j).getConta().substring(0, 3).equals("001")) {
								stringao += "Ag " + lista.get(j).getAgencia() + " Conta Comum "
										+ lista.get(j).getConta() + " Saldo " + lista.get(j).getSaldo() + "\n";
							} else if (lista.get(j).getConta().substring(0, 3).equals("002")) {
								stringao += "Ag " + lista.get(j).getAgencia() + " Conta Especial "
										+ lista.get(j).getConta() + " Saldo " + lista.get(j).getSaldo() + "\n";
							} else {
								stringao += "Ag " + lista.get(j).getAgencia() + " Conta PoupanÁa "
										+ lista.get(j).getConta() + " Saldo " + lista.get(j).getSaldo() + "\n";
							}
							saldoTotal += lista.get(j).getSaldo();
							j++;
						}
						if (lista.size() != 1) {
							stringao += "Saldo Total: " + saldoTotal + "\n\n";
						} else {
							stringao += "\n";
						}

						j = 0;
						saldoTotal = 0;
					}

				}

				GravaArq grava2 = new GravaArq("extratoQuick" + ord[o].toUpperCase() + narq[k] + ".txt", true);
				grava2.gravaArquivo(stringao.toString());
				grava2.fechaArquivo();

				// MÈtodo Imprimir tempo em segundo
				System.out.println(narq[k]);

				System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");
				System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");

			}
			System.out.println("\n");
		}

	}

	static void terceiraEtapa() {

	}

}
