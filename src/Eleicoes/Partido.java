package Eleicoes;

import java.util.*;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.LinkedList;

public class Partido {
	private String nome;
	private String sigla;
	private int votosLegenda;
	private int votosNominais;
	private int votosTotal;
	private int numPartido;
	private LinkedList<Candidato> candidatos;
	private int vagas;

	public Partido(String nome, String sigla, int vtL, int numP) {
		this.nome = nome;
		this.sigla = sigla;
		this.votosLegenda = vtL;
		this.numPartido = numP;
		this.candidatos = new LinkedList<Candidato>();

		this.votosTotal = -1;
		this.vagas = 0;
		this.votosNominais = -1;
	}

	public String getNome () {
		return nome;
	}

	public void setNome (String nome) {
		this.nome = nome;
	}

	public String getSigla () {
		return sigla;
	}

	public void setSigla (String sigla) {
		this.sigla = sigla;
	}

	public int getVotosLegenda () {
		return votosLegenda;
	}

	public void setVotosLegenda (int votosLegenda) {
		this.votosLegenda = votosLegenda;
	}

	public int getVotosNominais () {
		return votosNominais;
	}

	public void setVotosNominais (int votosNominais) {
		this.votosNominais = votosNominais;
	}

	public int getVotosTotal () {
		return votosTotal;
	}

	public void setVotosTotal (int votosTotal) {
		this.votosTotal = votosTotal;
	}

	public int getNumPartido () {
		return numPartido;
	}

	public void setNumPartido (int numPartido) {
		this.numPartido = numPartido;
	}

	public int getVagas() {
		return this.vagas;
	}
	
	public int getFirstCandidato() {
//		System.out.println("\nO que é? " + this.candidatos.getFirst().getVotosTotal());
		return this.candidatos.getFirst().getVotosTotal();
	}
	
	public int getLastCandidato() {
		return this.candidatos.getLast().getVotosTotal();
	}
	
	public LinkedList<Candidato> getCandidatos () {
		return candidatos;
	}

	public void setCandidatos (LinkedList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public void setVotosTotais () {
		int votosCandidatos = 0;

		for (Candidato candidato : this.candidatos) {
			votosCandidatos += candidato.getVotosTotal();
		}

		this.votosTotal = votosCandidatos + votosLegenda;
		this.votosNominais = votosCandidatos;
	}

    public void insereCandidato (Candidato candidato) {
        this.candidatos.add(candidato);
		if (candidato.ehEleito())
			vagas++;
    }

	public void calculaVagas () {
		int vagas = 0;

		for (Candidato c: this.candidatos) {
			if (c.ehEleito())
				vagas++;
		}

		this.vagas = vagas;
	}

	public void ordenaCandidatos () {
		Collections.sort(this.candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                int votos1 = c1.getVotosTotal();
                int votos2 = c2.getVotosTotal();
                if (votos1 > votos2)
                    return -1;
                else if(votos1 == votos2) {                	
                	return c1.comparaNome(c2); 
                }
                return 1;
            }
        });
	}

	@Override
	public String toString () {
		String fraseCandidatoEleito = " candidato eleito";
		String fraseVoto = " voto";
		String fraseNominal = " nominal";

		if (this.getVagas() > 1) {
			fraseCandidatoEleito = " candidatos eleitos";
		}

		if (this.votosTotal > 1)
			fraseVoto = " votos";

		if (this.votosNominais > 1)
			fraseNominal = " nominais";

		return this.sigla.toUpperCase() + " - " + this.numPartido + ", " + this.votosTotal + fraseVoto + " (" + this.votosNominais + fraseNominal + " e " + this.votosLegenda + " de legenda), " + this.vagas + fraseCandidatoEleito;
	}
}
