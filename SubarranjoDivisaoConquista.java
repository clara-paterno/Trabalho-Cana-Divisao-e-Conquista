public class SubarranjoDivisaoConquista {
    public static long[] resolver(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("Vetor vazio.");
        }
        return resolver(vetor, 0, vetor.length - 1);
    }
    private static long[] resolver(int[] vetor, int inicio, int fim) {
        if (inicio == fim) return new long[]{vetor[inicio], inicio, fim};
        int meio = inicio + (fim - inicio) / 2;
        long[] esquerda = resolver(vetor, inicio, meio);
        long[] direita = resolver(vetor, meio + 1, fim);
        long soma = 0, somaEsquerda = Long.MIN_VALUE;
        int melhorInicio = meio;
        for (int i = meio; i >= inicio; i--) {
            soma += vetor[i];
            if (soma >= somaEsquerda) {
                somaEsquerda = soma;
                melhorInicio = i;
            }
        }
        soma = 0;
        long somaDireita = Long.MIN_VALUE;
        int melhorFim = meio + 1;
        for (int i = meio + 1; i <= fim; i++) {
            soma += vetor[i];
            if (soma > somaDireita) {
                somaDireita = soma;
                melhorFim = i;
            }
        }
        long[] cruzado = {somaEsquerda + somaDireita, melhorInicio, melhorFim};
        return melhor(melhor(esquerda, direita), cruzado);
    }
    private static long[] melhor(long[] a, long[] b) {
        if (a[0] != b[0]) return a[0] > b[0] ? a : b;
        if (a[1] != b[1]) return a[1] < b[1] ? a : b;
        return a[2] <= b[2] ? a : b;
    }
}
