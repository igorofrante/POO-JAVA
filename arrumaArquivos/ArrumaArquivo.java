package arrumaArquivos;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import dados.Banco;
import io.*;
import vetor.CadBanco;
public class ArrumaArquivo {
	static int ind;
	static int[] tamanhos = {500, 1000, 5000, 10000, 50000};
	public static void main (String[] args){
		for (ind=0; ind<tamanhos.length; ind++) {
			System.out.println("ind =" + ind);
			principal();
		}
	}

	static void principal (){
		ArrayList<Integer> agencia = new ArrayList<Integer>(tamanhos[ind]);
		ArrayList<String> numero = new ArrayList<String>(tamanhos[ind]);
		ArrayList<Double> saldo = new ArrayList<Double>(tamanhos[ind]);
		ArrayList<String> cpf = new ArrayList<String>();
		ArrayList<String> nome = new ArrayList<String>();
		ArrayList<Banco> banco=null;
		CadBanco cad;
		LeArquivo entrada; 
		if (ind > 0){
			try{
				entrada = new LeArquivo ("conta"+tamanhos[ind-1]+"alea.txt");
				banco = new ArrayList<Banco>(tamanhos[ind]);			
				entrada.leArquivoBanco(banco);
				for (Banco b: banco){
					agencia.add(b.getAgencia());
					numero.add(b.getConta());
					saldo.add(b.getSaldo());
					cpf.add(b.getCpf());
					nome.add(b.getNome());
				}
			}catch (FileNotFoundException erro){}
		}
		/*
		 * forma cpf, agencia, numero, saldo
		 * cpf tem que ter iguais
		 * agencia - entre 100 e 999
		 * numero : sortear entre cc 001 e poupança 002 depois acrescentar os numeros 
		 * entre 10000 e 99999 (armazenar como String)
		 * nao pode ter agencia e numero iguais
		 */
		formaAgencia(agencia);
		formaNumero(numero, agencia);
		formaNome(nome);
		formaCpf(cpf, nome);
		formaSaldo (saldo);

		
		cad = new CadBanco(tamanhos[ind]);
		preenche(cad, agencia, numero, cpf, saldo, nome);
			
		try{
			GravaArq arq = new GravaArq ("conta"+tamanhos[ind]+"alea.txt", false);
			arq.gravaArquivo(cad.toString());
			arq.fechaArquivo();
			cad.quicksort();
			arq = new GravaArq ("conta"+tamanhos[ind]+"ord.txt",false);
			arq.gravaArquivo(cad.toString());
			arq.fechaArquivo();
			cad.quicksortInv();
			arq = new GravaArq ("conta"+tamanhos[ind]+"inv.txt",false);
			arq.gravaArquivo(cad.toString());
			arq.fechaArquivo();

		}
		catch (IOException erro){}
	}


	static int pesquisa (String elem, ArrayList<String> cpf){
		for (int i=0; i<cpf.size(); i++){
			if (elem.equals(cpf.get(i)))
				return i;
		}
		return -1;
	}
	
	static ArrayList<Integer> pesquisaConta (String elem, ArrayList<String> conta){
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for (int i=0; i<conta.size(); i++){
			if (elem.equals(conta.get(i)))
				pos.add(i);
		}
		return pos;
	}


	static void formaAgencia (ArrayList<Integer> agencia){
		int i, j, aux, numero;
		Random r = new Random();
		
		String temp;
		if (ind==0)
			i = 0;
		else
			i = tamanhos[ind-1];
		System.out.println ("ind = "+ind);
		j=0;
		for (; (j+i)<tamanhos[ind]; j++){
			temp = "";
			for (int k=0; k<5; k++){
				aux = r.nextInt(10);
				temp += aux;
			}
			numero = Integer.parseInt(temp);
			agencia.add(numero);
		}		
	}

	static void formaCpf(ArrayList<String> cpf, ArrayList<String> nome){
		int i, aux;
		String temp;
		Random r = new Random();
		if (ind==0)
			i = 0;
		else
			i = tamanhos[ind-1];
		for (; i<tamanhos[ind]*0.9; i++){
			temp="";
			for (int j=0; j<11; j++){
				aux = r.nextInt(10);
				temp += aux;
			}
			if (pesquisa(temp, cpf)<0)
				cpf.add(temp);
			else
				i--;
		}
		for (; i<tamanhos[ind]; i++){
			aux = r.nextInt(cpf.size());
			cpf.add(cpf.get(aux));
			nome.set(i, nome.get(aux));
			
		}
	}
	static void formaNumero(ArrayList<String> numero,ArrayList<Integer> agencia){
		Random r = new Random();
		ArrayList<Integer> pos;
		int i, aux;
		boolean igual=false;
		String temp;
		if (ind==0)
			i = 0;
		else
			i = tamanhos[ind-1];
		for (; i<tamanhos[ind]; i++){
			igual = false;
			temp="0";
			// poupança
			if (i%3==0 || i%5==0)
				temp += "10";
			else {  
				// conta especial
				if (i%7==0) {
					temp += "02";
				}
				else {
					// conta comum
					temp +="01";
				}
			}
			for (int j=0; j<5; j++){
				aux = r.nextInt(10);
				temp += aux;
			}
			pos = pesquisaConta(temp, numero);
			if (pos.isEmpty())
				numero.add(temp);
			else {
				for (int ind : pos) {
					if (agencia.get(ind)==agencia.get(i)) {
						igual = false;
						break;
					}
				}
				if (igual)
					i--;
				else
					numero.add(temp);
			}
		}
	}

	public static void formaSaldo (ArrayList<Double> saldo) {
		Random r = new Random();
		double valor;
		int i;
		if (ind==0)
			i = 0;
		else
			i = tamanhos[ind-1];
		for (; i<tamanhos[ind]; i++) {
			valor = r.nextInt(20000);
			valor -= 3000;
			saldo.add(valor);
		}

	}
	static void preenche (CadBanco cad, ArrayList<Integer> agencia, 
			ArrayList<String> numero, ArrayList<String> cpf, ArrayList<Double> saldo,
			ArrayList<String> nome){
		for (int i=0; i<tamanhos[ind]; i++){
			Banco conta = new Banco (agencia.get(i), numero.get(i), saldo.get(i), cpf.get(i), nome.get(i));
			cad.insere(conta);
			System.out.println ("insere"+i);
		}
	}
	static void formaNome (ArrayList<String> nomeCompleto){
		String[] nome = {"ADRIANA", "ADRIANO", "ALTAIR", "ADILSON", "BEATRIZ", "ALESSANDRO",
				"ALESSANDRA", "ALEXANDRE", "ALINE", "ANTONIO", "AMANDA", "ANA", "ANA CAROLINA",
				"ANA BEATRIZ", "ANA CARLA", "ANA CRISTINA", "CRISTINA", "CARLA", "BERNARDO",
				"BIANCA", "BRENO", "BRUNO", "BRUNA", "CARLOS", "CRISTIANO", "CRISTOVAO", 
				"CRISTIANE", "DIANA", "DAYSE", "DOUGLAUS", "DAVI", "DIOGO", "ESTEVAO", "EDUARDO",
				"ELIAS", "ELIANA", "ELIANE", "ELIZABETH", "ISABEL", "FABIO", "FABIANO", "FABIANE",
				"FLAVIA", "FLAVIO", "GIOAVANA", "GUSTAVO", "GUILHERME", "GILBERTO", "IOLANDA", "IGOR",
				"IEDA", "JAQUELINE", "JOAO", "JOSE", "JOSE CARLOS", "JOSE AUGUSTO", "JULIA", "JULIO",
				"JULIO CESAR", "JOANA", "LUIZ", "LUIS", "LUIZ CARLOS", "LUIZ ALBERTO", "LILIAN",
				"LILIANE", "LUCIA", "LUCIO", "LUCIANO", "LUCIANA", "LUCIANO", "LIDIA", "LORENA",
				"LARISSA", "LETICIA", "MAURO", "MARIA", "MARIA DA PENHA", "MARIA DA GRACA", 
				"MARIA JOSE", "ANA MARIA","MARIO", "MELISSA", "MIRIAN", "MOACIR", "MILTON",
				"MILENA", "NATALIA", "NILCEA", "NILTON", "NEY", "NIVALDO", "OTAVIO", "OLGA",
				"OTAVIANO", "ATILA", "OSVALDO", "PAULO", "PAULO ROBERTO", "PAULO HENRIQUE", 
				"HENRIQUE", "PAULO AUGUSTO", "PAULO SERGIO", "PEDRO","PAULA", "PATRICIA","PAOLA",
				"RENATA", "RENATO", "KELLY", "RICARDO", "RAFAEL", "RAFAELA", "ROBERTO", "ROBERTA",
				"RUI","SANDRO", "SANDRA", "SUELI", "SERGIO","SAMANTA", "SIMONE", "TADEU", "TIAGO",
				"TARCISIO", "TATIANA", "TEREZA", "RODOLFO", "ROBSON", "VERA", "VALERIA", "WILLIAN",
				"WALACE", "VICENTE", "AGENOR", "ALBERTO", "REGINA", "ALAN", "ALOISIO", "ALVARO", 
				"RAFAEL", "RAFAELA", "MIGUEL", "DANIELA","DANIEL", "DANIELE", "MARIANA", "MELINA",
				"EMERSON", "RUBENS", "MARCOS"};
		String[] sobrenome = {"VARGAS", "OLIVEIRA", "SOUZA", "SILVA", "CAMILO", "ALVES", "RODRIGUES",
				"DA SILVA", "DE SOUZA", "REZENDE", "FERREIRA", "CONCEICAO", "FERNANDES", "MORAES",
				"CARVALHO", "VIEIRA", "COUTINHO", "ANDRADE", "SANTOS", "DOS SANTOS", "GALVAO", 
				"MARTINS","MARTINHO", "GODOI", "COSME", "GONCALVES", "ALVES", "SILVEIRA", 
				"MELLO", "GALVAO", "LOPES", "NASCIMENTO", "DE PAULA", "ABREU", "CASTRO", "LIMA",
				"RANGEL", "ROMAO", "DUARTE", "BARRETO", "CARVALHO", "PRATES", "SA", "MENEZES", "LOPES",
				"MACHADO","GUSMAO", "ROCHA", "MAIA", "VILELA", "TELLES", "MARQUES", "BATISTA", "GOMES",
				"MOREIRA", "CUNHA", "DIAS", "SARMENTO", "COELHO", "GARCIA", "VIANNA", "MOURA", 
				"CAVALCANTE", "COSTA", "NOGUEIRA", "RIBEIRO", "GUERRA", "CRUZ"};
		int i, j, auxi, quant;
		String temp;
		if (ind==0)
			i = 0;
		else
			i = tamanhos[ind-1];
		for (; i<tamanhos[ind]; i++){
			auxi = (int)(Math.random()*nome.length);
			temp = nome[auxi];
			quant = (int)(Math.random()*3+2);
			for (j=1; j<quant; j++){
				auxi = (int)(Math.random()*sobrenome.length);
				temp+=" "+sobrenome[auxi];
			}
			nomeCompleto.add(temp);
		}
	}
}
