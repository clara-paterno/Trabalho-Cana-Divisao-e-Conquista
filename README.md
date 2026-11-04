# Trabalho Prático — Divisão e Conquista

Trabalho desenvolvido para a disciplina de Análise de Algoritmos, com o objetivo de implementar e comparar algoritmos baseados nas técnicas de força bruta e divisão e conquista.

O projeto aborda dois problemas: **Subarranjo de Soma Máxima** e **Par de Pontos Mais Próximos**, incluindo testes automatizados e análise experimental de desempenho.

## 1. Algoritmos implementados

### Subarranjo de Soma Máxima

O objetivo é encontrar o intervalo contínuo de um vetor que possui a maior soma possível.

- **Força bruta:** testa os intervalos possíveis. Complexidade O(n²).
- **Divisão e conquista:** divide o vetor recursivamente e compara os melhores resultados de cada metade com o intervalo que atravessa o meio. Complexidade O(n log n).

### Par de Pontos Mais Próximos

O objetivo é encontrar a menor distância entre dois pontos em um plano cartesiano.

- **Força bruta:** compara todos os pares de pontos. Complexidade O(n²).
- **Divisão e conquista:** divide os pontos em duas regiões e verifica os possíveis pares mais próximos, incluindo os que atravessam a divisão. Complexidade O(n log n).

## 2. Estrutura do projeto

```text
Trabalho-Cana-Divisao-e-Conquista/
├── Algoritmos/
│   ├── AnaliseExperimental.java
│   ├── PontosDivisaoConquista.java
│   ├── PontosForcaBruta.java
│   ├── SubarranjoDivisaoConquista.java
│   └── SubarranjoForcaBruta.java
├── testes/
│   └── TestesAlgoritmos.java
├── resultados/
│   ├── grafico_pontos.png
│   ├── grafico_subarranjos.png
│   ├── tabela_pontos.csv
│   └── tabela_subarranjos.csv
├── casos_teste.md
├── resultados.csv
├── gerar_graficos.py
└── README.md
```

## 3. Tecnologias utilizadas

- Java 21 — implementação dos algoritmos e testes.
- Python 3 — processamento dos dados experimentais.
- Pandas — organização dos resultados em tabelas.
- Matplotlib — geração dos gráficos.
- Git e GitHub — versionamento do projeto.


## 4. Como executar

É necessário ter o JDK instalado.

Na raiz do repositório, crie a pasta de compilação e compile os arquivos Java:

```bash
mkdir build
javac -d build Algoritmos/*.java testes/*.java
```

Para executar os testes automatizados:

```bash
java -cp build testes.TestesAlgoritmos
```

Para executar a análise experimental:

```bash
java -cp build Algoritmos.AnaliseExperimental
```

Para salvar as medições em um arquivo CSV:

```bash
java -cp build Algoritmos.AnaliseExperimental > resultados.csv
```

## 5. Testes automatizados

O projeto contém testes para verificar a correção dos algoritmos, incluindo situações como:

- Vetores com números positivos e negativos.
- Vetores com um único elemento.
- Empates entre subarranjos.
- Pontos com coordenadas negativas.
- Pontos repetidos e alinhados.
- Comparação entre as soluções de força bruta e divisão e conquista.

Os casos de teste e suas justificativas estão documentados no arquivo `casos_teste.md`.

## 6. Análise experimental

Foram utilizados tamanhos de entrada de 100, 200, 400, 800, 1.600, 3.200 e 6.400 elementos.

Cada algoritmo foi executado cinco vezes por tamanho de entrada, utilizando a média dos tempos, medida em milissegundos.

**Ambiente das medições:**

- Sistema operacional: Linux
- Java: 21.0.12.1
- Arquitetura: amd64
- Processadores disponíveis: 12

As medições brutas estão disponíveis em `resultados.csv`.

### Gerar tabelas e gráficos

Instale as dependências Python:

```bash
py -m pip install pandas matplotlib
```

No Windows, execute:

```bash
py gerar_graficos.py
```

Os resultados serão salvos na pasta `resultados/`.

## 7. Resultados

Os experimentos permitem observar que, em entradas pequenas, a força bruta pode apresentar menor tempo de execução devido à simplicidade da implementação.

Conforme o tamanho da entrada aumenta, os algoritmos de divisão e conquista tendem a apresentar melhor desempenho, de acordo com suas complexidades assintóticas.

A análise detalhada, incluindo gráficos, recorrências e discussão dos resultados, será apresentada no relatório do trabalho.

## 8. Integrantes

- Francisco Mário Bernardo da Silva Filho
- Maria Clara Paterno Maia
