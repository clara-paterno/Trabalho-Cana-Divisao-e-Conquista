package Algoritmos;
public class PontosForcaBruta {
    public static double[] resolver(int[][] pontos) {
        if (pontos == null || pontos.length < 2) {
            throw new IllegalArgumentException("Sao necessarios pelo menos dois pontos.");
        }
        double menorDistancia = Double.POSITIVE_INFINITY;
        int primeiro = 0, segundo = 1;
        for (int i = 0; i < pontos.length; i++) {
            for (int j = i + 1; j < pontos.length; j++) {
                double dx = (double) pontos[i][0] - pontos[j][0];
                double dy = (double) pontos[i][1] - pontos[j][1];
                double distancia = Math.hypot(dx, dy);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    primeiro = i;
                    segundo = j;
                }
            }
        }
        return new double[]{menorDistancia, primeiro, segundo};
    }
}
