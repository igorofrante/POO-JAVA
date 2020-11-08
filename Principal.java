
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import arvore.CadBancoABB;
import arvore.CadBancoArv;
import dados.Banco;
import dados.NoArvore;
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
			System.out.println("LEMBRE-SE DE APAGAR O ARQ RESULTADO.TXT!");
			System.out.println("----MENU---\n" + "1 - Etapa HeapSort + Pesquisa Binaria \n"
					+ "2 - Etapa QuickSort + Pesquisa Binaria \n" + "3 - Etapa Arvores\n" + "4 - Etapa Hashing\n"
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
				quartaEtapa();
				break;
			case '0':
				System.out.println("Programa encerrado!\n\n" + "[AUTORES]\n" + "Igor Ofrante\n" + "Karen Alcantara\n"
						+ "Lucas Sarmento\n" + "Mackweyd Gomes\n" + "Pedro Henrique Fernandes.");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcao Invalida, tente novamente!", null,
						JOptionPane.ERROR_MESSAGE);
				break;
			}

		} while (menu != '0');

	}

	static void primeiraEtapa() throws IOException {
		GravaArq gravaRes = new GravaArq("Resumo.txt", true);
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "alea", "ord", "inv" };
		double tempo = 0;
		String resumo = "";
		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o].toUpperCase() + "\n");
			resumo += ord[o].toUpperCase() + "\n";
			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);
				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
					LeArquivo arquivo = new LeArquivo("conta" + narq[k] + ord[o] + ".txt");
					arquivo.leArquivoBanco(contas.getBancoLista());

					contas.HeapSort();

					// 4) Gravar
					GravaArq grava = new GravaArq("Heap" + ord[o].toUpperCase() + narq[k] + ".txt", false);
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
							stringao += "CPF " + buscar.get(i) + ": \n" + "NAO HA NENHUM REGISTRO COM O CPF "
									+ buscar.get(i) + "\n\n";
						} else {
							stringao += "CPF " + lista.get(j).getCpf() + " NOME " + lista.get(j).getNome() + "\n";
							while (j != lista.size()) {
								stringao += "Ag " + lista.get(j).getAgencia();
								if (lista.get(j).getConta().substring(0, 3).equals("001")) {
									stringao += " Conta Comum " + lista.get(j).getConta();
								} else if (lista.get(j).getConta().substring(0, 3).equals("002")) {
									stringao += " Conta Especial " + lista.get(j).getConta();
								} else {
									stringao += " Conta Poupança " + lista.get(j).getConta();
								}
								stringao += " Saldo " + lista.get(j).getSaldo() + "\n";
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

					GravaArq grava2 = new GravaArq("extratoHeap" + ord[o].toUpperCase() + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					// Método Imprimir tempo em segundo

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");
					resumo += "Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos" + "\n";
				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos" + "\n");
				resumo += "Media art: " + tempo / 5.0 + " segundos" + "\n\n";
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");
		resumo += "Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n\n";
		gravaRes.gravaArquivo(resumo + "\n");
		gravaRes.fechaArquivo();
	}

	static void segundaEtapa() throws IOException {
		GravaArq gravaRes = new GravaArq("Resumo.txt", true);
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "alea", "ord", "inv" };
		double tempo = 0;
		String resumo = "";
		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o].toUpperCase() + "\n");
			resumo += ord[o].toUpperCase() + "\n";
			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);
				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
					LeArquivo arquivo = new LeArquivo("conta" + narq[k] + ord[o] + ".txt");
					arquivo.leArquivoBanco(contas.getBancoLista());

					contas.quicksort();

					// 4) Gravar
					GravaArq grava = new GravaArq("Quick" + ord[o].toUpperCase() + narq[k] + ".txt", false);
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
							stringao += "CPF " + buscar.get(i) + ": \n" + "NAO HA NENHUM REGISTRO COM O CPF "
									+ buscar.get(i) + "\n\n";
						} else {
							stringao += "CPF " + lista.get(j).getCpf() + " NOME " + lista.get(j).getNome() + "\n";
							while (j != lista.size()) {
								stringao += "Ag " + lista.get(j).getAgencia();
								if (lista.get(j).getConta().substring(0, 3).equals("001")) {
									stringao += " Conta Comum " + lista.get(j).getConta();
								} else if (lista.get(j).getConta().substring(0, 3).equals("002")) {
									stringao += " Conta Especial " + lista.get(j).getConta();
								} else {
									stringao += " Conta Poupanca " + lista.get(j).getConta();
								}
								stringao += " Saldo " + lista.get(j).getSaldo() + "\n";
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

					GravaArq grava2 = new GravaArq("extratoQuick" + ord[o].toUpperCase() + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					// Método Imprimir tempo em segundo

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");
					resumo += "Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos" + "\n";
				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos" + "\n");
				resumo += "Media art: " + tempo / 5.0 + " segundos" + "\n\n";
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");
		resumo += "Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n\n";
		gravaRes.gravaArquivo(resumo + "\n");
		gravaRes.fechaArquivo();
	}

	static void terceiraEtapa() throws IOException {
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "alea", "ord", "inv" };
		String[] tipo = { "ABB", "AVL" };
		double tempo = 0;
		for (int m = 0; m < 2; m++) {

			System.out.println(tipo[m].toUpperCase() + "\n");
			for (int o = 0; o < ord.length; o++) {
				System.out.println(ord[o].toUpperCase() + "\n");
				for (int k = 0; k < narq.length; k++) {
					tempo = 0;
					System.out.println(narq[k]);
					for (int w = 0; w < 5; w++) {
						startArq = System.currentTimeMillis();
						LeArquivo arquivo = new LeArquivo("conta" + narq[k] + ord[o] + ".txt");
						CadBancoABB contas = new CadBancoABB(Integer.parseInt(narq[k]));
						arquivo.leArquivoBanco(contas.getBancoLista());

						contas.ABB();

						// 4) Gravar
						GravaArq grava = new GravaArq(tipo[m] + ord[o].toUpperCase() + narq[k] + ".txt", false);
						grava.gravaArquivo(contas.toStringBalanceado());
						grava.fechaArquivo();

						LeArquivoCpf cpfs = new LeArquivoCpf("Conta.txt");
						ArrayList<String> buscar = cpfs.leArquivo(400);
						cpfs.fechaArquivo();

						NoArvore folha = null;
						NoArvore folha2 = null;
						NoArvore folha3 = null;
						String stringao = "";
						double saldoTotal = 0.0;
						double saldo1 = 0.0;

						for (int i = 0; i < buscar.size(); i++) {
							folha = contas.pesquisaABBToda(buscar.get(i));
							if (folha == null) {
								stringao += "CPF " + buscar.get(i) + ": \n" + "NAO HA NENHUM REGISTRO COM O CPF "
										+ buscar.get(i) + "\n\n";
							} else {
								stringao += "CPF " + folha.getInfo().getCpf() + " NOME " + folha.getInfo().getNome()
										+ "\n";
								stringao += "Ag " + folha.getInfo().getAgencia();
								if (folha.getInfo().getConta().substring(0, 3).equals("001")) {
									stringao += " Conta Comum " + folha.getInfo().getConta();
								} else if (folha.getInfo().getConta().substring(0, 3).equals("002")) {
									stringao += " Especial " + folha.getInfo().getConta();
								} else {
									stringao += " Poupanca " + folha.getInfo().getConta();
								}
								stringao += folha.getInfo().getSaldo() + "\n";
								saldoTotal += folha.getInfo().getSaldo();
								saldo1 = folha.getInfo().getSaldo();
								folha3 = folha;
								
								// descobrir uma forma de navegar na árvore sem pegar repetidos.
								
								while ((folha2 = contas.pesquisaABBSR(buscar.get(i), folha, folha3)) != null) {
									stringao += "Ag " + folha2.getInfo().getAgencia();
									if (folha.getInfo().getConta().substring(0, 3).equals("001")) {
										stringao += " Conta Comum " + folha2.getInfo().getConta();
									} else if (folha.getInfo().getConta().substring(0, 3).equals("002")) {
										stringao += " Especial " + folha2.getInfo().getConta();
									} else {
										stringao += " Poupanca " + folha2.getInfo().getConta();
									}
									saldoTotal += folha2.getInfo().getSaldo();

								}

								if (saldoTotal != saldo1) {
									stringao += "Saldo Total: " + saldoTotal + "\n\n";
								} else {
									stringao += "\n";
								}
								saldoTotal = 0;
							}

						}

						GravaArq grava2 = new GravaArq("extrato" + tipo[m] + ord[o].toUpperCase() + narq[k] + ".txt",
								false);
						grava2.gravaArquivo(stringao.toString());
						grava2.fechaArquivo();

						// Método Imprimir tempo em segundo

						tempo += (System.currentTimeMillis() - startArq) / 1000.0;
						System.out
								.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

					}
					System.out.println("Media art: " + tempo / 5.0 + " segundos");
					System.out.println("\n");
				}
			}
			System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");

		}

	}

	static void quartaEtapa() throws IOException {

	}

}
