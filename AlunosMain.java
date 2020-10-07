import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.*;

public class AlunosMain {
	static Scanner entrada = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();

	public static void main(String[] args) {
		int menu;

		do {
			System.out.println("----MENU----\n"
					+ "1- Ler arquivos \n"
					+ "2- Consultar todos os dados \n"
					+ "3- Calcular média das idades \n"
				    + "4- Calcular média dos IMC \n"
					+ "0- sair \n\n");
			menu = entrada.nextInt();

			switch (menu) {

			case 1:
				lerArquivos();
				break;
			case 2:
				ConsultarDados();
				break;
			case 3:
				calcularMedia();
				break;
			case 4:
				calcularMediaIMC();
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


	static void lerArquivos(){
		//EDITION IGOR OFRANTE DUTRA
		//edit23
		//edit 34
		String linha; //linha de 3comentário
		String narq [] = {"1","2","3","4","6","7","9","10","11"};
		int i=0;

		try{
			memoria.delete(0,memoria.length());
			BufferedReader arqEntrada;
			int inicio=0, fim, primeiro,pos=0;
			for(i = 0; i < narq.length ; i++) {
				arqEntrada = new BufferedReader (new FileReader(narq[i]+".txt"));
				linha = "";

				if(narq[i]=="1"){

					while( (linha=arqEntrada.readLine()) != null){
						memoria.append(linha+"\n");
					}
					arqEntrada.close();
					System.out.println("O arquivo 1.txt foi lido.");
				}

				else{
					inicio=0;
					pos= memoria.indexOf("\n");	


					while( (linha=arqEntrada.readLine()) != null){						
						linha=linha+"\n";
						primeiro=linha.indexOf("\t",0)+1;
						fim=linha.indexOf("\n");

						memoria.insert(pos,("\t")+linha.substring(primeiro,fim));
						fim = memoria.indexOf("\n", inicio);
						inicio = fim + 1;
						if(memoria.indexOf("\n",inicio)>0){
							pos= memoria.indexOf("\n",inicio);
						}

					}
					arqEntrada.close();
					System.out.println("O arquivo " +(narq[i])+".txt" +" foi lido.");
				}


			}//fim for
			System.out.println("Leitura encerrada!" +"\n");
			

		}

		catch(FileNotFoundException erro1){
			JOptionPane.showMessageDialog(null, "O arquivo " +(narq[i])+".txt" +" não foi encontrado!" ,null, JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro de leitura!",null, JOptionPane.ERROR_MESSAGE);
		}

	}

	static void ConsultarDados() {
		String var[] = new String[10];
		String dados="\nAlunos:\n\n";
		int inicio, fim = 0, ultimo, primeiro=0;
		inicio = 0;
		try{
			if(memoria.length() != 0){
				while(inicio != memoria.length()){
					for(int i = 0; i<var.length;i++){
						if(i==0){//primeira variavel
							ultimo = memoria.indexOf("\t",inicio);
							var[i] = memoria.substring(inicio, ultimo);
							primeiro = ultimo + 1;
						}

						if(i!=0 && i!=9){//restante até a penultima variavel
							ultimo = memoria.indexOf("\t",primeiro);
							var[i] = memoria.substring(primeiro, ultimo);
							primeiro = ultimo + 1;
						}

						if(i==9){//ultima variavel
							fim = memoria.indexOf("\n", primeiro);
							var[i] = memoria.substring(primeiro, fim);
							Alunos reg = new Alunos ();
							reg.setMatricula(Integer.parseInt(var[0]));
							reg.setIdade(Integer.parseInt(var[1]));
							reg.setCoeficiente(Double.parseDouble(var[2]));
							reg.setPeriodo(Integer.parseInt(var[3]));
							reg.setAltura(Double.parseDouble(var[4]));
							reg.setqFilhos(Integer.parseInt(var[5]));
							reg.setPeso(Double.parseDouble(var[6]));
							reg.settProgramacao(Integer.parseInt(var[7]));
							reg.setnDisciplinas(Integer.parseInt(var[8]));
							reg.setMediaSalario(Double.parseDouble(var[9]));
							dados+="Matricula: "+reg.getMatricula()+"\n"+"Idade: "+reg.getIdade()+"\n"+"Coeficiente: "+reg.getCoeficiente()+"\n";
							dados+="Periodo: "+reg.getPeriodo()+"\n"+"Altura: "+reg.getAltura()+"\n"+"Quantidade de Filhos: "+reg.getqFilhos()+"\n";
							dados+="Peso: "+reg.getPeso()+"\n"+"Tempo de programação: "+reg.gettProgramacao()+"\n"+"N de disciplinas: "+reg.getnDisciplinas()+"\n";
							dados+="Média Salario: "+reg.getMediaSalario()+"\n\n";		
						}


					}//fim for
					inicio = fim + 1;
				}//fim while

				//imprimir na tela
				System.out.println(dados);
			}else{
				JOptionPane.showMessageDialog(null, "Os arquivos não foram lidos!",null, JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro na consulta!",null, JOptionPane.ERROR_MESSAGE);
		}

	}


	static void calcularMedia() {
		int inicio=0, fim = 0, ultimo, primeiro=0,idade;
		double soma=0, media=0,cont=0;
		if(memoria.length() != 0){
			while((inicio!= memoria.length())) {
				ultimo = memoria.indexOf("\t",inicio);
				primeiro = ultimo + 1;
				ultimo = memoria.indexOf("\t",primeiro);
				idade= Integer.parseInt(memoria.substring(primeiro, ultimo));
				Alunos reg = new Alunos();
				reg.setIdade(idade);
				soma+=reg.getIdade();
				primeiro = ultimo + 1;
				fim = memoria.indexOf("\n", primeiro);
				inicio = fim + 1;
				cont++;
			}
			media=soma/cont;
			System.out.println("A média das idades dos alunos é " +media +"\n");
		}
		else{
			JOptionPane.showMessageDialog(null, "Os arquivos não foram lidos!",null, JOptionPane.ERROR_MESSAGE);
		}

	}

	
	static void calcularMediaIMC(){
		int inicio = 0, ultimo, primeiro = 0, fim = 0;
		double media;		
		double soma = 0;
		inicio = 0;
		Double altura[] = new Double[20];
		Double peso[] = new Double[20];
		Double imc[] = new Double[20];
		
		int k=0;
		
		if (memoria.length() != 0) {
			while ((inicio != memoria.length())) {
				for (int i = 0; i <= 6; i++) {
					
					if(i==0){
						ultimo = memoria.indexOf("\t",inicio);
						primeiro = ultimo + 1;
					}
					
					if(i!=0 && i!=4 && i!=6 ){
						ultimo = memoria.indexOf("\t",primeiro);
						primeiro = ultimo + 1;
					}
					
					if(i==4){
						ultimo = memoria.indexOf("\t",primeiro);
						altura[k] = Double.parseDouble(memoria.substring(primeiro, ultimo));
						primeiro = ultimo + 1;
						}
					
					if (i == 6) {
						ultimo = memoria.indexOf("\t",primeiro);
						peso[k] = Double.parseDouble(memoria.substring(primeiro, ultimo));
						Alunos reg = new Alunos();
						reg.setPeso(peso[k]);
						reg.setAltura(altura[k]);
						imc[k]=reg.getPeso()/(Math.pow(reg.getAltura(),2));
						soma+=imc[k];																
					}													
				}
				fim = memoria.indexOf("\n", inicio);
				inicio = fim + 1;
				k++;
				
			}
			media=soma/k;
			System.out.println("IMC = peso/(altura^2)");
			System.out.println("A média dos IMC dos alunos é " +media);
			System.out.println("Entre 18,5 e 24,9 - Peso normal"+"\n");
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Os arquivos não foram lidos!",null, JOptionPane.ERROR_MESSAGE);
		}
	}
	

}


