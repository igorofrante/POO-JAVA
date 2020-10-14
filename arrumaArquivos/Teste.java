package arrumaArquivos;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import io.*;
import item.Banco;
import vetor.CadBanco;
public class Teste {
	public static void main (String[] args){
		int[] tam = {500, 1000, 5000, 10000};//, 50000};
		ArrayList<Banco> result = new ArrayList<Banco>();
		ArrayList<Banco> banco;
		String[] cpf = new String[400];
		GravaArq arq;
		double total=0;
		CadBanco cad = null;
		String msg="";	
		LeArquivo entrada;
		try {
			LeArquivoCpf entradaCpf = new LeArquivoCpf("Conta.txt");
			cpf = entradaCpf.leArquivo(400);
			entradaCpf.fechaArquivo();		
			for (int i=0; i<tam.length; i++){
				entrada = new LeArquivo("conta"+tam[i]+"ord.txt");
				banco = new ArrayList<Banco>(tam[i]);	
				entrada.leArquivoBanco(banco);
				cad = new CadBanco(banco);	
				arq = new GravaArq ("pesquisa"+tam[i]+".txt", false);
				for (int j=0; j<cpf.length; j++){
					result = cad.pesqBin(cpf[j]);
					if (result == null || result.size()==0){
						msg = "NAO HA NENHUM REGISTRO COM O CPF "+ cpf[j]+"\n\n";
					}else{
						total = 0;
						msg = "CPF "+cpf[j]+"\n";
						for (Banco b : result){
							msg +=b.toString()+"\n";
							total += b.getSaldo();
						}
						msg += "TOTAL DE SALDO = R$"+total+"\n\n";
					}
					arq.gravaArquivo(msg);
				}
				arq.fechaArquivo();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao existe");
		}
		catch (IOException erro){}

	}
}
