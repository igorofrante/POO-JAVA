import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.*;

public class IdadeMain {
	static Scanner entrada = new Scanner(System.in);
	static Scanner scan = new Scanner(System.in);
	static StringBuffer memoria = new StringBuffer();

	public static void main(String[] args) {
		int menu;

		do {
			System.out.println("----MENU----\n"
					+ "1- Adicionar dado\n"
					+ "2- Alterar dados\n"
					+ "3- Consultar dados \n"
					+ "4- Excluir dados\n"
					+ "0- sair \n\n");
			menu = entrada.nextInt();

			switch (menu) {

			case 1:
				AdicionarDado(); 
				break;
			case 2:
				AlterarDado(); 
				break;
			case 3:
				ConsultarDados();
				break;
			case 4:
				ExcluirDado();
				break;
			case 0:
				System.out.println("FIM");
				break;
			default :
				System.out.println("TENTE NOVAMENTE");
				break;
			}

		}while(menu!=0);

	}
	
	static void AdicionarDado(){
		try{
			Idade reg = new Idade();
			BufferedWriter saida = new BufferedWriter(new FileWriter("1.txt", true));

			System.out.println("Digite a matricula");
			reg.setMatricula(entrada.nextInt());

			System.out.println("Digite a idade");
			reg.setIdade(entrada.nextInt()); 

			saida.write(reg.toString());
			saida.flush();
			saida.close();
		}
		catch(Exception erro4){
			JOptionPane.showMessageDialog(null, "Erro ao gravar, tente novamente.",null, JOptionPane.ERROR_MESSAGE);
		}
	}

	static void iniciarArquivo(){
		String linha;
		try{
			BufferedReader arqEntrada;
			arqEntrada = new BufferedReader (new FileReader("1.txt"));
			linha = "";
			memoria.delete(0,memoria.length());
			while( (linha=arqEntrada.readLine()) != null){
				memoria.append(linha+"\n");
			}
			arqEntrada.close();
		}
		catch(FileNotFoundException erro1){
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado",null, JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro de leitura",null, JOptionPane.ERROR_MESSAGE);
		}
	}


	static void gravarDados(){
		try{
			BufferedWriter arqSaida;
			arqSaida = new BufferedWriter(new FileWriter ("1.txt"));
			arqSaida.write(memoria.toString());
			arqSaida.flush();  //salva no dispositivo
			arqSaida.close();
		}catch(Exception erro3){
			JOptionPane.showMessageDialog(null, "Erro de gravação!",null, JOptionPane.ERROR_MESSAGE);
		}
	}

	static void AlterarDado() {
		String matricula, idade;
		int inicio, fim, ultimo, primeiro;
		iniciarArquivo();
		try{
			if (memoria.length()!=0) {
				System.out.println("Digite a matrícula");
				matricula = scan.next();
				idade = "";				
				inicio = memoria.indexOf (matricula);
				if (inicio != -1){
					ultimo = memoria.indexOf("\t",inicio);
					matricula = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					idade = memoria.substring(primeiro, fim);
					Idade reg = new Idade ();
					reg.setMatricula(Integer.parseInt(matricula));
					reg.setIdade(Integer.parseInt(idade));
					System.out.println("Deseja alterar?"+"\n"+"Digite S ou N"+"\n\n"+
							"Matricula: " +reg.getMatricula()+"\n"+
							"Idade: "+reg.getIdade());
					char resp = Character.toUpperCase(scan.next().charAt(0));		
					if (resp == 'S'){
						System.out.println("Entre com a nova idade");
						idade = scan.next();
						reg.setIdade(Integer.parseInt(idade));
						memoria.replace(inicio, fim+1,reg.toString());
						System.out.println("Registro alterado.");
					} else{
						System.out.println("Alteração cancelada");
					}	
					gravarDados();
				}else{
					JOptionPane.showMessageDialog(null, "Matricula não encontrada",null, JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Arquivo Vazio",null, JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro na alteração!",null, JOptionPane.ERROR_MESSAGE);
		}

	}


 static void ConsultarDados() {
		String matricula,idade;
		String dados="\nMatriculas:\n\n";
		int inicio, fim, ultimo, primeiro;
		iniciarArquivo();
		inicio = 0;
		try{
			if(memoria.length() != 0){
				while(inicio != memoria.length()){
					ultimo = memoria.indexOf("\t",inicio);
					matricula = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					idade = memoria.substring(primeiro, fim);
					Idade reg = new Idade ();
					reg.setMatricula(Integer.parseInt(matricula));
					reg.setIdade(Integer.parseInt(idade));					
					dados+="Matricula: "+reg.getMatricula()+"\n"+"Idade: "+reg.getIdade()+"\n\n";
					inicio = fim + 1;
				}
				System.out.println(dados);
			}else{
				JOptionPane.showMessageDialog(null, "Arquivo Vazio!",null, JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro na consulta!",null, JOptionPane.ERROR_MESSAGE);
		}

	}

	static void ExcluirDado() {
		String matricula,idade;
		int inicio, fim, ultimo, primeiro;
		iniciarArquivo();
		try{
			if (memoria.length()!=0) {
				System.out.println("Digite a matrícula que deseja excluir");
				matricula= scan.next();
				idade = "";
				inicio = memoria.indexOf (matricula);
				if (inicio != -1){
					ultimo = memoria.indexOf("\t",inicio);
					matricula = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					idade = memoria.substring(primeiro, fim);
					Idade reg = new Idade ();
					reg.setMatricula(Integer.parseInt(matricula));
					reg.setIdade(Integer.parseInt(idade));	
					System.out.println("Deseja excluir?"+"\n"+"Digite S ou N"+"\n\n"+
							"Matricula: " +reg.getMatricula()+"\n"+
							"Idade: "+reg.getIdade());
					char resp = Character.toUpperCase(scan.next().charAt(0));	
					if (resp == 'S'){
						memoria.delete (inicio, fim + 1);	
						System.out.println("Registro excluido.");
						gravarDados(); 
					} else{
						System.out.println("Exclusão cancelada.");
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Matrícula não encontrada!",null, JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Arquivo Vazio!",null, JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception erro2){
			JOptionPane.showMessageDialog(null, "Erro na exclusão!",null, JOptionPane.ERROR_MESSAGE);
		}
	}

	}


