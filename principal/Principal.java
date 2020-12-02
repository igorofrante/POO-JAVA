package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import arvore.CadBancoABB;
import arvore.CadBancoAVL;
import dados.Banco;
import io.GravaArq;
import io.LeArquivo;
import io.LeArquivoCpf;
import vetor.CadBanco;
import vetor.CadBancoHash;
import frame.NewJFrame;

public class Principal {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		NewJFrame.main(null); //execucao da interface grafica 

	}

	public static void primeiraEtapa() throws IOException {
		String tipo = "Heap";
		String localDir = System.getProperty("user.dir"); //obtem o caminho completo do projeto
		String localDirC = localDir.replace('\\', '/') + "/contas/"; 
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// obtem o tempo do sistema em milisegundos
		double startArq = 0;
		String[] narq = { "500", "1000", "5000", "10000", "50000" }; //narq representa a quantidade de registro no arquivo
		String[] ord = { "Alea", "Ord", "Inv" }; //representa o tipo da ordena��o do arquivo
		double tempo = 0;

		System.out.println(tipo + "\n");

		for (int o = 0; o < ord.length; o++) {
			System.out.println(ord[o] + "\n");

			for (int k = 0; k < narq.length; k++) {
				tempo = 0;
				System.out.println(narq[k]);

				for (int w = 0; w < 5; w++) { //executa o mesmo arquivo cinco vezes
					startArq = System.currentTimeMillis(); //obtem o tempo do sistema durante cada execu��o em milisegundos
					CadBanco contas = new CadBanco(Integer.parseInt(narq[k])); 
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					arquivo.leArquivoBanco(contas.getBancoLista());
					arquivo.fechaArquivo();
					contas.heapSort(); //executa o metodo heapsort

					GravaArq grava = new GravaArq(localDirT + tipo + ord[o] + narq[k] + ".txt", false);//grava a ordenacao dos metodos heapsort/quicksort 
					grava.gravaArquivo(contas.toString());
					grava.fechaArquivo();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "Conta.txt"); //leitura dos cpfs selecionados
					ArrayList<String> buscar = cpfs.leArquivo(400);
					cpfs.fechaArquivo();

					ArrayList<Banco> lista = new ArrayList<Banco>();
					String stringao = "";
					double saldoTotal = 0.0;
					int j = 0;
					for (int i = 0; i < buscar.size(); i++) {//percorre todos os cpfs
						lista = contas.pesqBin(buscar.get(i)); //retorna a busca das contas de um cpf
						if (lista == null) {// caso retorne nulo
							stringao += "CPF " + buscar.get(i) + ": \n" + "NAO HA NENHUM REGISTRO COM O CPF "
									+ buscar.get(i) + "\n\n";
						} else {// caso n�o retorne nulo
							stringao += "CPF " + lista.get(j).getCpf() + " NOME " + lista.get(j).getNome() + "\n";
							while (j != lista.size()) { //percorre a lista de todas contas
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
							if (lista.size() != 1) {//caso a lista seja maior que um adiciona o saldo totalizar no stringao
								stringao += "Saldo Total: " + saldoTotal + "\n\n";
							} else {
								stringao += "\n";
							}

							j = 0;
							saldoTotal = 0;	//reseta as variaveis
						}

					}

					GravaArq grava2 = new GravaArq(localDirT + "extrato" + tipo + ord[o] + narq[k] + ".txt", false); // grava o resultado (extrato) de todas as contas presentes no stringao
					grava2.gravaArquivo(stringao.toString());
					grava2.fechaArquivo();

					tempo += (System.currentTimeMillis() - startArq) / 1000.0; //soma os tempos parciais de cada execucao
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos"); //imprime o tempo parcial em segundos

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos" + "\n"); // imprime a media em segundos
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n"); // imprime o tempo total de execucao do metodo

	}

	public static void segundaEtapa() throws IOException {
		String tipo = "Quick";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// m�todo de tempo
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
					CadBanco contas = new CadBanco(Integer.parseInt(narq[k]));
					LeArquivo arquivo = new LeArquivo(localDirC + "conta" + narq[k] + ord[o].toLowerCase() + ".txt");
					arquivo.leArquivoBanco(contas.getBancoLista());
					arquivo.fechaArquivo();
					contas.quickSort(); //executa o metodo quicksort

					GravaArq grava = new GravaArq(localDirT + tipo + ord[o] + narq[k] + ".txt", false);
					grava.gravaArquivo(contas.toString());
					grava.fechaArquivo();

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "Conta.txt");
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

	public static void terceiraEtapa() throws IOException {
		String tipo = "ABB";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// m�todo de tempo
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
					arquivo.fechaArquivo();
					try {
						contas.abb(); //executa o metodo abb
					} catch (StackOverflowError e) {
						System.out.println("Erro de estouro de pilha, pulado!");
						break;
					}

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "Conta.txt");
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

	public static void quartaEtapa() throws IOException {
		String tipo = "AVL";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// m�todo de tempo
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
					arquivo.fechaArquivo();
					contas.avl(); //executa o metodo avl

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "Conta.txt");
					ArrayList<String> buscar = cpfs.leArquivo(400);
					cpfs.fechaArquivo();

					ArrayList<Banco> lista = new ArrayList<Banco>();
					String stringao = "";
					double saldoTotal = 0.0;
					int j = 0;
					for (int i = 0; i < buscar.size(); i++) {

						lista = contas.pesquisaAVL(buscar.get(i));
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

					// M�todo Imprimir tempo em segundo

					tempo += (System.currentTimeMillis() - startArq) / 1000.0;
					System.out.println("Parcial: " + (System.currentTimeMillis() - startArq) / 1000.0 + " segundos");

				}
				System.out.println("Media art: " + tempo / 5.0 + " segundos");
				System.out.println("\n");
			}
		}
		System.out.println("Total: " + (System.currentTimeMillis() - startTime) / 1000.0 + " segundos" + "\n");
	}

	public static void quintaEtapa() throws IOException {
		String tipo = "Hash";
		String localDir = System.getProperty("user.dir");
		String localDirC = localDir.replace('\\', '/') + "/contas/";
		String localDirT = localDir.replace('\\', '/') + "/" + tipo + "/";
		double startTime = System.currentTimeMillis();// m�todo de tempo
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
					CadBancoHash contas = new CadBancoHash(Integer.parseInt(narq[k]));
					arquivo.leArquivoBanco(contas.getBancoLista());
					arquivo.fechaArquivo();
					contas.hashing(); //executa o metodo hashing

					LeArquivoCpf cpfs = new LeArquivoCpf(localDirC + "Conta.txt");
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
