## Para rodar o código:
```bash
make run
```

## O problema

Dado um grafo G = (V, E), determinar o maior subconjunto de aresta, E′ ⊆ E,
que formam um casamento. Ou seja, nenhum aresta de E′
compartilham v´ertices
em comum;

## Definições

### Casamento
Dado um grafo, um casamento (também conhecido como emparelhamento ou
matching) é um conjunto independente de arestas, ou seja, um conjunto de
arestas sem vértices em comum.
![alt text](image.png)

### Casamento Máximo e Maximal
Um casamento é considerado maximal caso a adição de alguma aresta
descaracterize o casamento.
Um casamento é considerado máximo caso possua o maior número de arestas
possível, ou seja, caso seja o maior casamento possível no grafo


#### Para nosso caso específico
https://en.wikipedia.org/wiki/Maximum_cardinality_matching#:~:text=for%20bipartite%20graphs-,Flow%2Dbased%20algorithm,of%201%20to%20each%20edge

https://en.wikipedia.org/wiki/Blossom_algorithm

https://depth-first.com/articles/2019/04/02/the-maximum-matching-problem/#:~:text=Blossoms,published%20by%20Micali%20and%20Variani.

https://en.wikipedia.org/wiki/Maximum_cardinality_matching#:~:text=for%20bipartite%20graphs-,Flow%2Dbased%20algorithm,of%201%20to%20each%20edge.