package Eleicoes;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Leitura {
    private Partido criaPartido(Scanner linhaScannerP) {
        int numPartido = Integer.parseInt(linhaScannerP.next());
        int votosLegenda = Integer.parseInt(linhaScannerP.next());
        String nomePartido = linhaScannerP.next();
        String sigla = linhaScannerP.next();
        Partido partido = new Partido(nomePartido, sigla, votosLegenda, numPartido);
//        System.out.println(nomePartido);
        return partido;
    }

    public Set<Partido> lePartido(Scanner s) {
        Set<Partido> partidos = new HashSet<Partido>();
        s.nextLine();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScannerP = new Scanner(linha);
            linhaScannerP.useDelimiter(",");

            Partido partido = criaPartido(linhaScannerP);
            partidos.add(partido);

            linhaScannerP.close();
        }

        return partidos;
    }

    public Set<Partido> abrePartidos(String caminhoPartidos, FileInputStream arquivoP) {

            Scanner sP = new Scanner(arquivoP, "UTF-8");
            Leitura leituraPartido = new Leitura();
            Set<Partido> partidos = leituraPartido.lePartido(sP);
            sP.close();
       
        return partidos;
    }

    public Candidato criaCandidato(Scanner linhaScanner, DateTimeFormatter formatoData, Set<Partido> partidos) {
        int numero = Integer.parseInt(linhaScanner.next());
        int votosNominais = Integer.parseInt(linhaScanner.next());
        String situacao = linhaScanner.next();
        String nome = linhaScanner.next();
        String nomeUrna = linhaScanner.next();
        String genero = linhaScanner.next();

        String aniversarioStr = linhaScanner.next();
        LocalDate aniversario = LocalDate.parse(aniversarioStr, formatoData);

        String destVoto = linhaScanner.next();
        int numPartido = Integer.parseInt(linhaScanner.next());

        Candidato candidato = new Candidato(nome, genero, aniversario, situacao, nomeUrna, votosNominais, numero,
                destVoto);
//        System.out.println(candidato);
        
        for(Partido p: partidos) {
        	if(p.getNumPartido() == numPartido) {
        		p.insereCandidato(candidato);
        	}
        }
        
        return candidato;
    }

    public Set<Candidato> leCandidato(Scanner s, DateTimeFormatter formatoData, Set<Partido> partidos) {
        Set<Candidato> candidatos = new HashSet<Candidato>();
        s.nextLine();

        while (s.hasNextLine()) {
            String linha = s.nextLine();

            Scanner linhaScanner = new Scanner(linha);
            linhaScanner.useDelimiter(",");

            Candidato candidato = criaCandidato(linhaScanner, formatoData, partidos);
            candidatos.add(candidato);

            linhaScanner.close();
        }

        return candidatos;
    }

    public Set<Candidato> abreCandidato(String caminhoCandidatos, FileInputStream arquivo, DateTimeFormatter formatoData, Set<Partido> partidos) {

            Scanner s = new Scanner(arquivo, "UTF-8");
            s.nextLine();
            Set<Candidato> candidatos = leCandidato(s, formatoData, partidos);
            s.close();

        return candidatos;
    }
}
