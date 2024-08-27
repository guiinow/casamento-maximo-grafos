## O problema

Dado um grafo G = (V, E), determinar o maior subconjunto de aresta, E′ ⊆ E,
que formam um casamento. Ou seja, nenhum aresta de E′
compartilham v´ertices
em comum;

Usar a estrutura de dados DIMACS para representar o grafo. (?)

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

### Casamento Perfeito
Diz-se que um vértice que faz parte do casamento é um vértice saturado. Caso
contrário, o vértice é não saturado.
Um casamento perfeito (ou completo) ocorre quando todos os vértices são
saturados.

Ref: http://www.decom.ufop.br/marco/site_media/uploads/bcc204/11_aula_11.pdf


![alt text](image-1.png)

Aqui tem a complexidade de alguns algoritmos. 
Ref: https://paginas.fe.up.pt/~rossetti/rrwiki/lib/exe/fetch.php?media=teaching:1011:cal:09_2.grafos7.pdf


#### Otimos exemplos visuais do problema
https://visualgo.net/en/matching?slide=1

#### Algoritmo que resolve o problema e sua implementação:
https://www.geeksforgeeks.org/hopcroft-karp-algorithm-for-maximum-matching-set-1-introduction/
https://www.geeksforgeeks.org/hopcroft-karp-algorithm-for-maximum-matching-set-2-implementation/