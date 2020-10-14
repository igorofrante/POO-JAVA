package io;
import java.text.*;
public class Data {
	private int dia, mes, ano;
	
	public Data (int d, int m, int a) {
		this.dia = d;
		this.mes = m;
		this.ano = a;
	}
	
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	private boolean isBissexto (){
		if ((this.ano % 4 == 0 && this.ano % 100 != 0) || 
				this.ano % 400 == 0)
			return true;
		return false;
	}

	public boolean dataValida (){
		if (this.mes<1 || this.mes>12 || 
			this.dia<1 || this.dia > 31)
			return false;
		switch (this.mes){
		case 2:
			if (this.isBissexto()){
				if (this.dia > 29) 
					return false;}
			else 
				if (this.dia > 28)
					return false;
			break;
		case 4:
		case 6:	
		case 9:
		case 11:
			if (this.dia > 30) 
				return false;
			break;
		}
		return true;
	}
	
	public String toString(){
		DecimalFormat a2 = new DecimalFormat("00");
		DecimalFormat a4 = new DecimalFormat("0000");
		return (a2.format(this.dia)+"/"+a2.format(this.mes)+"/"+
				a4.format(this.ano));
	}
	
	public boolean igual(Data dt1){
		if ((this.dia == dt1.dia) && (this.mes==dt1.mes) && 
				(this.ano==dt1.ano))
			return true;
		return false;
	}

	public boolean maior(Data dt1){
		if (this.ano>dt1.ano)
			return true;
		else
			if (this.ano<dt1.ano)
				return false;
			else
				if (this.mes>dt1.mes)
					return true;
				else
					if(this.mes<dt1.mes)
						return false;
					else
						if (this.dia>dt1.dia)
							return true;
						else
							return false;
	}	
	
	public boolean menor(Data dt1){
		if (this.igual(dt1) || this.maior(dt1))
				return false;
			else
				return true;
	}	
	
}
