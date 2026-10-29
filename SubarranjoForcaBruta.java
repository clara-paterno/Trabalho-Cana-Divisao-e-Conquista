public class SubarranjoForcaBruta {
    public static long[] resolver(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("Vetor vazio.");
        }
        long melhorSoma = vetor[0];
        int melhorInicio = 0, melhorFim = 0;
        for (int inicio = 0; inicio < vetor.length; inicio++) {
            long soma = 0;
            for (int fim = inicio; fim < vetor.length; fim++) {
                soma += vetor[fim];
                if (soma > melhorSoma) {
                    melhorSoma = soma;
                    melhorInicio = inicio;
                    melhorFim = fim;
                }
            }
        }
        return new long[]{melhorSoma, melhorInicio, melhorFim};
    }
}
