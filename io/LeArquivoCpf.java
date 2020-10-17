package io;
/**
 * @author Cinthia
 *
 * TODO Classe para criar um objeto do tipo arquivo de leitura
 */
import java.io.*;
import java.util.*;

import dados.Banco;


public class LeArquivoCpf {
	private Scanner entrada;
	/**
	 * Construtor
	 * @param nome => Nome do arquivo que sera aberto para leitura
	 * @throws FileNotFoundException => Excecao se nao encontrar o arquivo
	 */
	public LeArquivoCpf (String nome) throws FileNotFoundException{
		try{
			this.entrada = new Scanner (new File (nome));
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException ("ARQUIVO nao ENCONTRADO");
		}
	}
	/**
	 * Metodo para ler os dados contidos no arquivo
	 * @param alunos => Vetor de alunos que sera preenchido durante a 
	 * 						leitura do arquivo
	 * @throws IllegalStateException => Excecao se houver erro ao ler o arquivo 
	 */
	public ArrayList<String> leArquivo (int tam)throws NoSuchElementException, ArrayIndexOutOfBoundsException{
		ArrayList<String> cadastro =  new ArrayList<String>(tam);
		int i;
		try{
			for (i=0; i<tam; i++){
				cadastro.add(this.entrada.nextLine());
			}
			return cadastro;
		}
		catch (ArrayIndexOutOfBoundsException e){
			throw new ArrayIndexOutOfBoundsException("Arquivo corrompido");
		}
	}

	/**
	 * Metodo para fechar o arquivo de leitura
	 * @throws IllegalStateException => Excecao causada se nao conseguir fechar o arquivo.
	 */
	public void fechaArquivo ()throws IllegalStateException{
		try{
			this.entrada.close();
		}
		catch (IllegalStateException e){
			throw new IllegalStateException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
