package io;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import dados.Banco;

public class Arquivo {
	public static boolean leArquivoEmprego (ArrayList<Banco>  pessoa, String nome){
		LeArquivo entrada;
		boolean erro = true, continua = false;
		do{
			try{
				entrada = new LeArquivo(nome);
				entrada.leArquivoBanco(pessoa);
				entrada.fechaArquivo();
				erro = false;
				continua = true;
			}
			catch (FileNotFoundException e){
				InOut.MsgDeErro("ERRO", e.getMessage());
				return false;
			}
			catch (IOException e){
				InOut.MsgDeErro("ERRO",e.getMessage());
				return false;
			}
			catch (NoSuchElementException e){
				InOut.MsgDeErro("ERRO",e.getMessage());
				return false;
			}
			catch (NumberFormatException  e){
				InOut.MsgDeErro("ERRO",e.getMessage());
				return false;
			}
		}while (erro);
		return continua;
	}
	public static void leArquivo (ArrayList<String> lista, String nome){
		LeArquivo entrada;
		boolean erro = true;
		do{
			try{
				entrada = new LeArquivo(nome);
				entrada.leArquivo(lista);
				entrada.fechaArquivo();
				erro = false;
			}
			catch (FileNotFoundException e){
				InOut.MsgDeErro("ERRO", e.getMessage());
			}
			catch (IOException e){
				InOut.MsgDeErro("ERRO",e.getMessage());
			}
			catch (NoSuchElementException e){
				InOut.MsgDeErro("ERRO",e.getMessage());
			}
			catch (NumberFormatException  e){
				InOut.MsgDeErro("ERRO",e.getMessage());
			}
		}while (erro);
	}

	public static void gravaArquivo (String result, String arquivo, boolean append){
		GravaArq saida;
		try{
			saida = new GravaArq(arquivo, append);
			saida.gravaArquivo(result);
			saida.fechaArquivo();
		}
		catch (IOException e){
			InOut.MsgDeErro("ERRO",e.getMessage());
		}
	}

}
