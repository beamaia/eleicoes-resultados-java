package Eleicoes;

import java.time.LocalDate;
import java.time.Period;

public class Pessoa {
	private String nome;
	private String genero;
	private LocalDate nascimento;
	
	public Pessoa(String nome, String genero, LocalDate nascimento) {
		this.nome = nome;
		this.genero = genero;
		this.nascimento = nascimento;
	}
	
	public String getNome() {
		return nome;
	}

	public String getGenero() {
		return genero;
	}
	
	public boolean ehGeneroF () {
		if (this.genero.equals("F"))
			return true;
		else 
			return false;
	}
	

	public LocalDate getNascimento() {
		return nascimento;
	}
	
	public String toString () {
		return this.nome.toUpperCase();
	}

	public int getIdade (LocalDate dia) {
		return Period.between(this.nascimento, dia).getYears();
	}

//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//	public void setGenero(String genero) {
//		this.genero = genero;
//	}
//	public void setNascimento(LocalDate nascimento) {
//		this.nascimento = nascimento;
//	}
//	
	
}
