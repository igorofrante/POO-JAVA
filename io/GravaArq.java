package io;

import java.io.*;


public class GravaArq {
	private FileWriter writer; 
	private	PrintWriter saida;
	private File file;

	/**
	 * Construtor da classe
	 * @param nome => nome do arquivo que serï¿½ abero para gravaï¿½ï¿½o
	 * @throws IOException => Exceï¿½ï¿½o se houver algum problema se o 
	 * 					 arquivo nï¿½o puder ser aberto para gravaï¿½ï¿½o
	 */
	public GravaArq (String nome, boolean append) throws IOException{
		try{
			this.file = new File(nome); 
			this.file.delete(); //deletar arquivo se já existir
			this.writer = new FileWriter(new File(nome),append);
			saida = new PrintWriter (writer);
		}
		catch (IOException e){
			throw new IOException ("ARQUIVO NAO PODE SER ABERTO PARA GRAVACAO");
		}
	}
	
	public void gravaArquivo (String str)throws IOException	{
				this.saida.print(str);
	}
	
	public void fechaArquivo ()throws IOException{
		try{
			this.saida.close();
			this.writer.close();
		}
		catch (IOException e){
			throw new IOException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
