
from pathlib import Path
import pandas as pd
import matplotlib.pyplot as plt

pasta = Path(__file__).resolve().parent
df = pd.read_csv(pasta / "resultados.csv", comment="#")

# Considerar apenas as médias
medias = df[df["repeticao"] == "media"].copy()

problemas = {
    "subarranjos": {
        "titulo": "Subarranjo de Soma Máxima",
        "algoritmos": [
            "subarranjo_bruto",
            "subarranjo_divisao_conquista"
        ]
    },
    "pontos": {
        "titulo": "Par de Pontos Mais Próximos",
        "algoritmos": [
            "pontos_bruto",
            "pontos_divisao_conquista"
        ]
    }
}

saida = pasta / "resultados"
saida.mkdir(exist_ok=True)

for nome, dados in problemas.items():
    tabela = medias[
        medias["algoritmo"].isin(dados["algoritmos"])
    ].pivot(
        index="n",
        columns="algoritmo",
        values="tempo_ms"
    )

    tabela = tabela[dados["algoritmos"]]
    tabela.columns = ["Força bruta", "Divisão e conquista"]
    tabela = tabela.sort_index()

    # Salvar tabela
    tabela.to_csv(saida / f"tabela_{nome}.csv")

    # Gerar gráfico
    fig, ax = plt.subplots(figsize=(8, 5))

    for coluna in tabela.columns:
        ax.plot(
            tabela.index,
            tabela[coluna],
            marker="o",
            linewidth=2,
            label=coluna
        )

    ax.set_title(dados["titulo"])
    ax.set_xlabel("Tamanho da entrada (n)")
    ax.set_ylabel("Tempo médio (ms)")
    ax.set_xticks(tabela.index)
    ax.tick_params(axis="x", rotation=45)
    ax.grid(True, alpha=0.3)
    ax.legend()

    fig.tight_layout()
    fig.savefig(saida / f"grafico_{nome}.png", dpi=300)
    plt.close(fig)

print("Tabelas e gráficos gerados com sucesso!")
