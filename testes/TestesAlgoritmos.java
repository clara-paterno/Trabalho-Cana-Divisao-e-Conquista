package testes;
import java.util.Arrays;
import java.util.Random;

import Algoritmos.PontosDivisaoConquista;
import Algoritmos.PontosForcaBruta;
import Algoritmos.SubarranjoDivisaoConquista;
import Algoritmos.SubarranjoForcaBruta;

/** Testes sem dependencias externas para o trabalho de Divisao e Conquista. */
public class TestesAlgoritmos {
    private static int passaram = 0;
    private static int falharam = 0;
    private static final double EPS = 1e-9;

    public static void main(String[] args) {
        System.out.println("=== ETAPA 1: SUBARRANJO DE SOMA MAXIMA ===");
        testarSubarranjo("Vetor comum", new int[]{-2, 3, -1, 5, -4}, 7, 1, 3);
        testarSubarranjo("Somente negativos", new int[]{-5, -2, -8}, -2, 1, 1);
        testarSubarranjo("Um elemento positivo", new int[]{7}, 7, 0, 0);
        testarSubarranjo("Um elemento negativo", new int[]{-7}, -7, 0, 0);
        testarSubarranjo("Todos positivos", new int[]{1, 2, 3}, 6, 0, 2);
        testarSubarranjo("Todos zeros / empate", new int[]{0, 0, 0}, 0, 0, 0);
        testarSubarranjo("Empate entre intervalos", new int[]{2, -2, 2}, 2, 0, 0);
        testarSubarranjo("Melhor cruza o meio", new int[]{-3, 4, -1, 5, -2}, 8, 1, 3);
        testarSubarranjo("Soma excede int", new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE},
                2L * Integer.MAX_VALUE, 0, 1);
        testarExcecaoSubarranjo("Vetor vazio", new int[]{});
        testarExcecaoSubarranjo("Vetor nulo", null);

        System.out.println("\n=== ETAPA 2: PAR DE PONTOS MAIS PROXIMOS ===");
        testarPontos("Dois pontos", new int[][]{{0, 0}, {3, 4}}, 5.0);
        testarPontos("Tres pontos", new int[][]{{0, 0}, {3, 4}, {1, 1}}, Math.sqrt(2));
        testarPontos("Pontos repetidos", new int[][]{{5, 5}, {1, 2}, {5, 5}}, 0.0);
        testarPontos("Pontos alinhados", new int[][]{{0, 0}, {0, 4}, {0, 5}, {0, 12}}, 1.0);
        testarPontos("Coordenadas negativas", new int[][]{{-5, -5}, {-4, -4}, {8, 9}}, Math.sqrt(2));
        testarPontos("Par atravessa a divisao", new int[][]{{-8, 20}, {-3, 0}, {2, 30}, {3, 0}, {9, 20}, {15, 15}}, 6.0);
        testarPontos("Coordenadas grandes", new int[][]{{Integer.MIN_VALUE, 0}, {Integer.MAX_VALUE, 0}}, 4294967295.0);
        testarExcecaoPontos("Um unico ponto", new int[][]{{1, 2}});
        testarExcecaoPontos("Pontos nulos", null);

        // Semente fixa: resultados reproduziveis em qualquer computador.
        Random sorteio = new Random(2026);
        for (int caso = 0; caso < 200; caso++) {
            int n = 1 + sorteio.nextInt(25);
            int[] vetor = new int[n];
            for (int i = 0; i < n; i++) vetor[i] = sorteio.nextInt(21) - 10;
            long[] esperado = referenciaSubarranjo(vetor);
            testarSubarranjo("Aleatorio subarranjo #" + (caso + 1), vetor,
                    esperado[0], (int) esperado[1], (int) esperado[2]);
        }
        for (int caso = 0; caso < 200; caso++) {
            int n = 2 + sorteio.nextInt(39);
            int[][] pontos = new int[n][2];
            for (int i = 0; i < n; i++) {
                pontos[i][0] = sorteio.nextInt(101) - 50;
                pontos[i][1] = sorteio.nextInt(101) - 50;
            }
            testarPontos("Aleatorio pontos #" + (caso + 1), pontos,
                    referenciaDistanciaPontos(pontos));
        }

        System.out.println("\n=== RESUMO ===");
        System.out.println("Passaram: " + passaram + " | Falharam: " + falharam);
        if (falharam > 0) {
            System.err.println("Ha testes falhando: verifique os casos acima.");
            System.exit(1);
        }
        System.out.println("TODOS OS TESTES PASSARAM.");
    }

    private static void testarSubarranjo(String nome, int[] entrada,
                                         long soma, int inicio, int fim) {
        long[] esperado = {soma, inicio, fim};
        try {
            long[] bruto = SubarranjoForcaBruta.resolver(entrada);
            long[] divisao = SubarranjoDivisaoConquista.resolver(entrada);
            boolean ok = Arrays.equals(esperado, bruto) && Arrays.equals(esperado, divisao);
            registrar(nome, ok, "esperado=" + Arrays.toString(esperado)
                    + ", bruto=" + Arrays.toString(bruto)
                    + ", divisao=" + Arrays.toString(divisao)
                    + ", entrada=" + Arrays.toString(entrada));
        } catch (Exception erro) {
            registrar(nome, false, erro.toString());
        }
    }

    // Oraculo independente: enumera os intervalos e desempata por menor inicio,
    // depois por menor fim. Este criterio precisa ser seguido pelos dois algoritmos.
    private static long[] referenciaSubarranjo(int[] vetor) {
        long melhorSoma = Long.MIN_VALUE;
        int melhorI = 0, melhorJ = 0;
        for (int i = 0; i < vetor.length; i++) {
            long soma = 0;
            for (int j = i; j < vetor.length; j++) {
                soma += vetor[j];
                if (soma > melhorSoma) {
                    melhorSoma = soma;
                    melhorI = i;
                    melhorJ = j;
                }
            }
        }
        return new long[]{melhorSoma, melhorI, melhorJ};
    }

    private static void testarPontos(String nome, int[][] pontos, double distanciaEsperada) {
        try {
            double[] bruto = PontosForcaBruta.resolver(pontos);
            double[] divisao = PontosDivisaoConquista.resolver(pontos);
            boolean ok = resultadoPontosValido(pontos, bruto, distanciaEsperada)
                    && resultadoPontosValido(pontos, divisao, distanciaEsperada);
            registrar(nome, ok, "esperado=" + distanciaEsperada
                    + ", bruto=" + Arrays.toString(bruto)
                    + ", divisao=" + Arrays.toString(divisao)
                    + ", pontos=" + Arrays.deepToString(pontos));
        } catch (Exception erro) {
            registrar(nome, false, erro.toString());
        }
    }

    // Pares empatados podem ter indices diferentes; validamos a distancia E
    // se os indices devolvidos representam de fato o par informado.
    private static boolean resultadoPontosValido(int[][] pontos, double[] r, double esperado) {
        if (r == null || r.length != 3 || !Double.isFinite(r[0])) return false;
        int i = (int) r[1], j = (int) r[2];
        if (r[1] != i || r[2] != j || i < 0 || j < 0 ||
                i >= pontos.length || j >= pontos.length || i == j) return false;
        double d = Math.hypot((double) pontos[i][0] - pontos[j][0],
                              (double) pontos[i][1] - pontos[j][1]);
        double tolerancia = EPS * Math.max(1.0, Math.abs(esperado));
        return Math.abs(r[0] - esperado) <= tolerancia
                && Math.abs(d - esperado) <= tolerancia;
    }

    private static double referenciaDistanciaPontos(int[][] pontos) {
        double menor = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pontos.length; i++) {
            for (int j = i + 1; j < pontos.length; j++) {
                menor = Math.min(menor, Math.hypot(
                        (double) pontos[i][0] - pontos[j][0],
                        (double) pontos[i][1] - pontos[j][1]));
            }
        }
        return menor;
    }

    private static void testarExcecaoSubarranjo(String nome, int[] vetor) {
        boolean a = lancaIllegalArgument(() -> SubarranjoForcaBruta.resolver(vetor));
        boolean b = lancaIllegalArgument(() -> SubarranjoDivisaoConquista.resolver(vetor));
        registrar(nome, a && b, "ambos devem lancar IllegalArgumentException");
    }

    private static void testarExcecaoPontos(String nome, int[][] pontos) {
        boolean a = lancaIllegalArgument(() -> PontosForcaBruta.resolver(pontos));
        boolean b = lancaIllegalArgument(() -> PontosDivisaoConquista.resolver(pontos));
        registrar(nome, a && b, "ambos devem lancar IllegalArgumentException");
    }

    private static boolean lancaIllegalArgument(Runnable codigo) {
        try {
            codigo.run();
            return false;
        } catch (IllegalArgumentException esperado) {
            return true;
        } catch (Exception outroErro) {
            return false;
        }
    }

    private static void registrar(String nome, boolean ok, String detalhes) {
        if (ok) {
            passaram++;
            // Uma linha por caso, inclusive aleatorios, para documentar a bateria.
            System.out.println("[PASSOU] " + nome);
        } else {
            falharam++;
            System.out.println("[FALHOU] " + nome + " | " + detalhes);
        }
    }
}
