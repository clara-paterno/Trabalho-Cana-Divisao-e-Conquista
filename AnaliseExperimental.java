import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AnaliseExperimental {
    private static volatile Object resultado;

    public static void main(String[] args) {
        int[] tamanhos = {100, 200, 400, 800, 1600, 3200, 6400};
        int repeticoes = 5;
        String[] nomes = {"subarranjo_bruto", "subarranjo_divisao_conquista",
                          "pontos_bruto", "pontos_divisao_conquista"};
        System.out.println("# Java: " + System.getProperty("java.version"));
        System.out.println("# Sistema: " + System.getProperty("os.name"));
        System.out.println("# Arquitetura: " + System.getProperty("os.arch"));
        System.out.println("# Processadores disponiveis: "
            + Runtime.getRuntime().availableProcessors());
        aquecer();
        System.out.println("algoritmo,n,repeticao,tempo_ms");
        for (int n : tamanhos) {
            double[] totais = new double[4];
            for (int repeticao = 1; repeticao <= repeticoes; repeticao++) {
                Random random = new Random(42L + n * 10L + repeticao);
                int[] vetor = new int[n];
                int[][] pontos = new int[n][2];
                for (int i = 0; i < n; i++) {
                    vetor[i] = random.nextInt(2001) - 1000;
                    pontos[i][0] = random.nextInt(2000001) - 1000000;
                    pontos[i][1] = random.nextInt(2000001) - 1000000;
                }
                List<Integer> ordem = new ArrayList<>();
                for (int algoritmo = 0; algoritmo < 4; algoritmo++) ordem.add(algoritmo);
                Collections.shuffle(ordem, random);
                for (int algoritmo : ordem) {
                    long inicio = System.nanoTime();
                    executar(algoritmo, vetor, pontos);
                    long fim = System.nanoTime();
                    double tempoMs = (fim - inicio) / 1_000_000.0;
                    totais[algoritmo] += tempoMs;
                    System.out.printf(Locale.ROOT, "%s,%d,%d,%.6f%n",
                        nomes[algoritmo], n, repeticao, tempoMs);
                }
            }
            for (int algoritmo = 0; algoritmo < 4; algoritmo++) {
                System.out.printf(Locale.ROOT, "%s,%d,media,%.6f%n",
                    nomes[algoritmo], n, totais[algoritmo] / repeticoes);
            }
        }
    }
    private static void executar(int algoritmo, int[] vetor, int[][] pontos) {
        switch (algoritmo) {
            case 0 -> resultado = SubarranjoForcaBruta.resolver(vetor);
            case 1 -> resultado = SubarranjoDivisaoConquista.resolver(vetor);
            case 2 -> resultado = PontosForcaBruta.resolver(pontos);
            case 3 -> resultado = PontosDivisaoConquista.resolver(pontos);
        }
    }
    private static void aquecer() {
        Random random = new Random(42);
        int[] vetor = new int[100];
        int[][] pontos = new int[100][2];
        for (int i = 0; i < 100; i++) {
            vetor[i] = random.nextInt(2001) - 1000;
            pontos[i][0] = random.nextInt(20001) - 10000;
            pontos[i][1] = random.nextInt(20001) - 10000;
        }
        for (int repeticao = 0; repeticao < 500; repeticao++) {
            for (int algoritmo = 0; algoritmo < 4; algoritmo++) {
                executar(algoritmo, vetor, pontos);
            }
        }
    }
}
