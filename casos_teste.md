# Casos de teste — Divisão e Conquista

Os casos são executados em `TestesAlgoritmos.java`, usando as duas implementações de cada problema. Índices começam em zero.

## Etapa 1 — Subarranjo de Soma Máxima

| Caso | Entrada | Saída esperada (soma, início, fim) | Justificativa |
|---|---|---|---|
| Vetor comum | [-2, 3, -1, 5, -4] | (7, 1, 3) | Intervalo interno ótimo |
| Apenas negativos | [-5, -2, -8] | (-2, 1, 1) | Melhor intervalo é um elemento negativo |
| Um positivo | [7] | (7, 0, 0) | Caso mínimo |
| Um negativo | [-7] | (-7, 0, 0) | Caso mínimo negativo |
| Todos positivos | [1, 2, 3] | (6, 0, 2) | Intervalo total |
| Todos zeros | [0, 0, 0] | (0, 0, 0) | Empate, menor início e menor fim |
| Empate entre intervalos | [2, -2, 2] | (2, 0, 0) | Critério de desempate |
| Intervalo cruza o meio | [-3, 4, -1, 5, -2] | (8, 1, 3) | Combinação das metades |
| Soma maior que int | [2147483647, 2147483647] | (4294967294, 0, 1) | Acúmulo em long |
| Vetor vazio | [] | IllegalArgumentException | Entrada inválida |
| Vetor nulo | null | IllegalArgumentException | Entrada inválida |

## Etapa 2 — Par de Pontos Mais Próximos

| Caso | Entrada | Distância mínima esperada | Justificativa |
|---|---|---|---|
| Dois pontos | [(0,0), (3,4)] | 5 | Caso mínimo |
| Três pontos | [(0,0), (3,4), (1,1)] | sqrt(2) | Ponto interno mais próximo |
| Pontos repetidos | [(5,5), (1,2), (5,5)] | 0 | Duplicatas |
| Pontos alinhados | [(0,0), (0,4), (0,5), (0,12)] | 1 | Mesma coordenada x |
| Coordenadas negativas | [(-5,-5), (-4,-4), (8,9)] | sqrt(2) | Valores negativos |
| Par cruzando a divisão | [(-8,20), (-3,0), (2,30), (3,0), (9,20), (15,15)] | 6 | Faixa central |
| Coordenadas grandes | [(INT_MIN,0), (INT_MAX,0)] | 4294967295 | Evitar overflow da diferença |
| Um ponto | [(1,2)] | IllegalArgumentException | Entrada inválida |
| Nulo | null | IllegalArgumentException | Entrada inválida |

Na Etapa 2, quando houver empates, o teste aceita qualquer par de índices válido cuja distância seja a menor.

## Testes aleatórios reproduzíveis

- 200 vetores aleatórios de 1 a 25 elementos, com valores de -10 a 10; comparação com solução de referência exaustiva, incluindo índices e desempate.
- 200 conjuntos de 2 a 40 pontos com coordenadas de -50 a 50; comparação com distância mínima de referência exaustiva e validação dos índices devolvidos.
- Semente fixa: `2026`.
- Total da suíte: **420 casos**.

## Como executar

Coloque `TestesAlgoritmos.java` junto dos outros arquivos `.java`, na pasta `java-simples/src`.

```bash
cd java-simples/src
javac *.java
java TestesAlgoritmos
```

O programa termina com status 1 se houver falha e imprime detalhes do caso. O resultado "TODOS OS TESTES PASSARAM" corresponde apenas à execução realizada, não garante ausência de defeitos em toda entrada possível.
