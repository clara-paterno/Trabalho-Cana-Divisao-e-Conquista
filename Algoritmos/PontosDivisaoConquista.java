package Algoritmos;
import java.util.Arrays;
import java.util.Comparator;

public class PontosDivisaoConquista {
    private static class Ponto {
        int x, y, indice;
        Ponto(int x, int y, int indice) {
            this.x = x;
            this.y = y;
            this.indice = indice;
        }
    }
    private static final Comparator<Ponto> POR_X =
        Comparator.comparingInt((Ponto p) -> p.x).thenComparingInt(p -> p.y);
    private static final Comparator<Ponto> POR_Y =
        Comparator.comparingInt((Ponto p) -> p.y).thenComparingInt(p -> p.x);

    public static double[] resolver(int[][] pontos) {
        if (pontos == null || pontos.length < 2) {
            throw new IllegalArgumentException("Sao necessarios pelo menos dois pontos.");
        }
        Ponto[] porX = new Ponto[pontos.length];
        for (int i = 0; i < pontos.length; i++) {
            porX[i] = new Ponto(pontos[i][0], pontos[i][1], i);
        }
        Arrays.sort(porX, POR_X);
        for (int i = 1; i < porX.length; i++) {
            if (porX[i].x == porX[i - 1].x && porX[i].y == porX[i - 1].y) {
                return new double[]{0, porX[i - 1].indice, porX[i].indice};
            }
        }
        return resolver(porX, porX.clone(), new Ponto[pontos.length], 0, pontos.length);
    }
    private static double[] resolver(Ponto[] porX, Ponto[] porY,
                                     Ponto[] auxiliar, int inicio, int fim) {
        if (fim - inicio <= 3) {
            double[] melhor = {Double.POSITIVE_INFINITY, -1, -1};
            for (int i = inicio; i < fim; i++) {
                for (int j = i + 1; j < fim; j++) {
                    double d = distancia(porX[i], porX[j]);
                    if (d < melhor[0]) {
                        melhor = new double[]{d, porX[i].indice, porX[j].indice};
                    }
                }
            }
            Arrays.sort(porY, inicio, fim, POR_Y);
            return melhor;
        }
        int meio = inicio + (fim - inicio) / 2;
        int linhaX = porX[meio].x;
        double[] esquerda = resolver(porX, porY, auxiliar, inicio, meio);
        double[] direita = resolver(porX, porY, auxiliar, meio, fim);
        double[] melhor = esquerda[0] <= direita[0] ? esquerda : direita;
        int i = inicio, j = meio, k = inicio;
        while (i < meio && j < fim) {
            if (POR_Y.compare(porY[i], porY[j]) <= 0) auxiliar[k++] = porY[i++];
            else auxiliar[k++] = porY[j++];
        }
        while (i < meio) auxiliar[k++] = porY[i++];
        while (j < fim) auxiliar[k++] = porY[j++];
        System.arraycopy(auxiliar, inicio, porY, inicio, fim - inicio);
        int quantidade = 0;
        for (i = inicio; i < fim; i++) {
            if (Math.abs((double) porY[i].x - linhaX) <= melhor[0]) {
                auxiliar[inicio + quantidade++] = porY[i];
            }
        }
        for (i = 0; i < quantidade; i++) {
            for (j = i + 1; j < quantidade && j <= i + 7; j++) {
                Ponto a = auxiliar[inicio + i], b = auxiliar[inicio + j];
                if ((double) b.y - a.y > melhor[0]) break;
                double d = distancia(a, b);
                if (d < melhor[0]) melhor = new double[]{d, a.indice, b.indice};
            }
        }
        return melhor;
    }
    private static double distancia(Ponto a, Ponto b) {
        return Math.hypot((double) a.x - b.x, (double) a.y - b.y);
    }
}
