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

## 2. Análise teórica dos algoritmos

Além da implementação, foi realizada uma análise teórica das complexidades dos algoritmos, considerando o crescimento do tempo de execução conforme o tamanho da entrada aumenta.

### Subarranjo de Soma Máxima

**Força Bruta:**
- Percorre todas as possíveis posições iniciais e finais do vetor.
- Calcula as somas dos intervalos e identifica o maior resultado.
- Complexidade de tempo: O(n²).

**Divisão e Conquista:**
- Divide o vetor em duas metades.
- Resolve recursivamente o problema em cada metade.
- Calcula o melhor subarranjo que atravessa o ponto médio.
- Compara os três resultados para encontrar a maior soma.

Recorrência:

T(n) = 2T(n/2) + O(n)

Complexidade de tempo: O(n log n).

### Par de Pontos Mais Próximos

**Força Bruta:**
- Calcula a distância entre todos os pares possíveis de pontos.
- Mantém a menor distância encontrada.
- Complexidade de tempo: O(n²).

**Divisão e Conquista:**
- Divide os pontos em duas regiões.
- Encontra recursivamente a menor distância em cada região.
- Verifica os pontos próximos à linha de divisão para identificar possíveis pares que estejam em lados diferentes.
- Retorna a menor distância encontrada.

Recorrência:

T(n) = 2T(n/2) + O(n)

Complexidade de tempo: O(n log n), considerando a organização dos pontos e a verificação eficiente da região próxima à divisão.

### Comparação entre as abordagens

A força bruta apresenta uma implementação mais simples e pode ser competitiva para entradas pequenas. Entretanto, seu tempo de execução cresce quadraticamente.

A divisão e conquista reduz o crescimento assintótico do tempo de execução para O(n log n), sendo mais adequada para entradas maiores, desde que sua implementação utilize as otimizações necessárias.

## 3. Estrutura do projeto

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

## 4. Tecnologias utilizadas

- Java 21 — implementação dos algoritmos e testes.
- Python 3 — processamento dos dados experimentais.
- Pandas — organização dos resultados em tabelas.
- Matplotlib — geração dos gráficos.
- Git e GitHub — versionamento do projeto.


## 5. Como executar

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

## 6. Testes automatizados

O projeto contém testes para verificar a correção dos algoritmos, incluindo situações como:

- Vetores com números positivos e negativos.
- Vetores com um único elemento.
- Empates entre subarranjos.
- Pontos com coordenadas negativas.
- Pontos repetidos e alinhados.
- Comparação entre as soluções de força bruta e divisão e conquista.

Os casos de teste e suas justificativas estão documentados no arquivo `casos_teste.md`.

## 7. Análise experimental

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

## 8. Resultados

Os experimentos permitem observar que, em entradas pequenas, a força bruta pode apresentar menor tempo de execução devido à simplicidade da implementação.

Conforme o tamanho da entrada aumenta, os algoritmos de divisão e conquista tendem a apresentar melhor desempenho, de acordo com suas complexidades assintóticas.

A análise detalhada, incluindo gráficos, recorrências e discussão dos resultados, será apresentada no relatório do trabalho.

## 9. Integrantes

- Francisco Mário Bernardo da Silva Filho
- Maria Clara Paterno Maia
