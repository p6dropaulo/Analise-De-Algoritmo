
# Análise de Algoritmos de Ordenação em Java

Este repositório contém a implementação e análise de performance de seis algoritmos de ordenação fundamentais. O projeto foi desenvolvido como parte da disciplina de Análise de Algoritmos, com o objetivo de comparar a eficiência de diferentes abordagens (iterativas e divisivas) sob diversas condições de entrada.

## 🚀 Algoritmos Implementados

O projeto inclui os seguintes algoritmos de ordenação:

1. **Bubble Sort**: Algoritmo iterativo simples com otimização (interrupção prematura caso o array já esteja ordenado).
2. **Insertion Sort**: Ordenação por inserção, eficiente para conjuntos de dados pequenos ou quase ordenados.
3. **Selection Sort**: Seleção direta do menor elemento a cada iteração.
4. **Merge Sort**: Algoritmo de "Dividir e Conquistar" com complexidade $O(n \log n)$.
5. **Quick Sort**: Implementação com pivô aleatório para evitar o pior caso em dados já ordenados.
6. **Heap Sort**: Baseado na estrutura de dados Heap (árvore binária completa).

## 📊 Estrutura do Projeto

O sistema é dividido em duas partes principais:

### 1. Gerador de Dados (`GerarTxt.java`)

Utilitário para criar arquivos `.txt` na pasta `dados_entrada/`. Ele permite gerar diferentes cenários de teste:

* Crescente/Decrescente (com e sem repetição).
* Aleatório (com e sem repetição).

### 2. Executores de Ordenação

Cada algoritmo possui sua própria classe (ex: `QuickSort.java`). Ao executar:

1. O programa solicita a quantidade de números a serem lidos.
2. Carrega os dados da pasta `dados_entrada/`.
3. Mede o tempo de execução em nanossegundos ($ns$) utilizando `System.nanoTime()`.
4. Salva o resultado ordenado na pasta `dados_saida/[algoritmo]/`.

## 🛠️ Como Executar

### Passo a Passo

1. Compilar os arquivos:
```bash
javac *.java
```

2. Gerar dados de teste:
```bash
java GerarTxt
```

3. Executar um algoritmo (ex: QuickSort):
```bash
java QuickSort
```

## 📈 Análise de Performance

Os tempos de execução são exibidos diretamente no console após a ordenação.
