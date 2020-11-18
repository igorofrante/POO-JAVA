
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import arvore.CadBancoABB;
import arvore.CadBancoAVL;
import dados.Banco;
import io.GravaArq;
import io.LeArquivo;
import io.LeArquivoCpf;
import vetor.CadBanco;
import vetor.CadBancoHash;

public class Principal {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// char[] menuvet = {'1','2','3','4','5','0'};
		// int i=0;
		char menu;

		do {

			System.out.println("----MENU---\n" + "==Vetor==\n" + "1 - Etapa HeapSort + Pesquisa Binaria \n"
					+ "2 - Etapa QuickSort + Pesquisa Binaria \n\n" + "==Arvores==\n" + "3 - ABB \n" + "4 - AVL\n\n"
					+ "==Hashing==\n" + "5 - HashingVetEnc\n\n" + "0 - Sair \n\n");

			menu = scan.next().charAt(0);
			// menu=menuvet[i];
			switch (menu) {

			case '1':
				primeiraEtapa(); // HeapSort
				break;
			case '2':
				segundaEtapa(); // QuickSort
				break;
			case '3':
				terceiraEtapa(); // ABB
				break;
			case '4':
				quartaEtapa(); // AVL
				break;
			case '5':
				quintaEtapa(); // Hashing
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
		String tipo = "Heap";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "Alea", "Ord", "Inv" };
		double tempo = 0;

		System.out.println(tipo);

		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o] + "\n");

			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);

				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					arquivo.leArquivoBanco(contas.getBancoLista());
					contas.HeapSort();

					GravaArq grava = new GravaArq(localDirT + tipo + ord[o] + narq[k] + ".txt", false);
					grava.gravaArquivo(contas.toString());
					grava.fechaArquivo();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "conta.txt");
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

					GravaArq grava2 = new GravaArq(localDirT + "extrato" + tipo + ord[o] + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos" + "\n");
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");

	}

	static void segundaEtapa() throws IOException {
		String tipo = "Quick";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "Alea", "Ord", "Inv" };
		double tempo = 0;

		System.out.println(tipo);

		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o] + "\n");

			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);

				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					arquivo.leArquivoBanco(contas.getBancoLista());
					contas.quicksort();

					GravaArq grava = new GravaArq(localDirT + tipo + ord[o] + narq[k] + ".txt", false);
					grava.gravaArquivo(contas.toString());
					grava.fechaArquivo();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "conta.txt");
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

					GravaArq grava2 = new GravaArq(localDirT + "extrato" + tipo + ord[o] + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos" + "\n");

			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");
	}

	static void terceiraEtapa() throws IOException {
		String tipo = "ABB";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "Alea", "Ord", "Inv" };
		double tempo = 0;

		System.out.println(tipo + "\n");
		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o] + "\n");
			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);
				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					CadBancoABB contas = new CadBancoABB(Integer.parseInt(narq[k]));
					arquivo.leArquivoBanco(contas.getBancoLista());
					contas.ABB();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "conta.txt");
					ArrayList<String> buscar = cpfs.leArquivo(400);
					cpfs.fechaArquivo();

					ArrayList<Banco> lista = new ArrayList<Banco>();
					String stringao = "";
					double saldoTotal = 0.0;
					int j = 0;
					for (int i = 0; i < buscar.size(); i++) {

						lista = contas.pesquisaABB(buscar.get(i));
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

					GravaArq grava2 = new GravaArq(localDirT + "extrato" + tipo + ord[o] + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos");
				System.out.println("\n");
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");

	}

	static void quartaEtapa() throws IOException {
		String tipo = "AVL";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "Alea", "Ord", "Inv" };
		double tempo = 0;

		System.out.println(tipo + "\n");
		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o] + "\n");
			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);
				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					CadBancoAVL contas = new CadBancoAVL(Integer.parseInt(narq[k]));
					arquivo.leArquivoBanco(contas.getBancoLista());
					contas.AVL();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "conta.txt");
					ArrayList<String> buscar = cpfs.leArquivo(400);
					cpfs.fechaArquivo();

					ArrayList<Banco> lista = new ArrayList<Banco>();
					String stringao = "";
					double saldoTotal = 0.0;
					int j = 0;
					for (int i = 0; i < buscar.size(); i++) {

						lista = contas.pesquisaAVL(buscar.get(i));
						if (lista.size() == 0) {
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

					GravaArq grava2 = new GravaArq(localDirT + "extrato" + tipo + ord[o] + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					// Método Imprimir tempo em segundo

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos");
				System.out.println("\n");
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");
	}

	static void quintaEtapa() throws IOException {
		String tipo = "AVL";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// método de tempo
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" };
		String[] ord = { "Alea", "Ord", "Inv" };
		double tempo = 0;

		// Hash
		System.out.println(tipo + "\n");
		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o] + "\n");
			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);
				for (int w = 0; w < 5; w++) {
					startArq = System.currentTimeMillis();
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					CadBancoHash contas = new CadBancoHash(Integer.parseInt(narq[k]));
					arquivo.leArquivoBanco(contas.getBancoLista());
					contas.hashing();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "conta.txt");
					ArrayList<String> buscar = cpfs.leArquivo(400);
					cpfs.fechaArquivo();

					LinkedList<Banco> lista = new LinkedList<Banco>();
					String stringao = "";
					double saldoTotal = 0.0;
					int j = 0;
					for (int i = 0; i < buscar.size(); i++) {

						lista = contas.pesquisaHash(buscar.get(i));
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

					GravaArq grava2 = new GravaArq(localDirT + "/extrato" + tipo + ord[o] + narq[k] + ".txt", false);
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos");
				System.out.println("\n");
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");
	}

}
