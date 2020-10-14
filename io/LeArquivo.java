package io;
import java.io.*;
import java.util.*;
import item.Banco;

public class LeArquivo {
	private Scanner entrada;
	
	public LeArquivo (String nome) throws FileNotFoundException{
		try{
			this.entrada = new Scanner (new FileReader (nome));
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException ("ARQUIVO NAO ENCONTRADO");
		}
	}
	
	public void leArquivoBanco (ArrayList<Banco> lista)throws IllegalStateException	{
		String linha;
		try{
			while (this.entrada.hasNext()){
				linha = this.entrada.nextLine();
				if (!linha.equals("")){
					lista.add(leLinhaCliente(linha));
				}
			}
		}
		catch (IllegalStateException e){
			throw new IllegalStateException ("ERRO DE LEITURA DO ARQUIVO");
		}
	}
	private Banco leLinhaCliente (String linha)throws NoSuchElementException{
		String[] dados;
		double saldo;
		
		try{
			dados = linha.split(";");
			saldo = Double.parseDouble(dados[4]);
			return (new Banco (Integer.parseInt(dados[2]), dados[3], saldo, dados[0], dados[1]));
		}
		catch (NoSuchElementException e){
			throw new NoSuchElementException ("ARQUIVO DIFERENTE DO REGISTRO ");
		}
	}

	public void leArquivo (ArrayList<String> lista)throws IllegalStateException	{
		String linha;
		try{
			while (this.entrada.hasNext()){
				linha = this.entrada.nextLine();
				if ((linha!=null) && !linha.equals("")){
					lista.add(linha);
				}
			}
		}
		catch (IllegalStateException e){
			throw new IllegalStateException ("ERRO DE LEITURA DO ARQUIVO");
		}
	}

	public void fechaArquivo ()throws IOException{
		try{
			this.entrada.close();
		}
		catch (IllegalStateException e){
			throw new IOException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
