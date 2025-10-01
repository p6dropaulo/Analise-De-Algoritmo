import java.io.*;
import java.util.*;

public class BubbleSort {

    // ---------------- BubbleSort (sempre otimizado) ----------------
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    trocou = true;
                }
            }
            if (!trocou) break; // otimização: para se já está ordenado
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Perguntar quantidade de números
        System.out.print("Digite a quantidade de números: ");
        int quantidade = scanner.nextInt();

        // Perguntar tipo de dados
        System.out.println("Escolha o tipo de dados:");
        System.out.println("1 - Crescente com repetição");
        System.out.println("2 - Decrescente com repetição");
        System.out.println("3 - Aleatório com repetição");
        System.out.println("4 - Crescente sem repetição");
        System.out.println("5 - Decrescente sem repetição");
        System.out.println("6 - Aleatório sem repetição");
        int tipo = scanner.nextInt();

        scanner.close();

        // Nome do tipo de dado
        String tipoNome = switch (tipo) {
            case 1 -> "crescente_com_repeticao";
            case 2 -> "decrescente_com_repeticao";
            case 3 -> "aleatorio_com_repeticao";
            case 4 -> "crescente_sem_repeticao";
            case 5 -> "decrescente_sem_repeticao";
            case 6 -> "aleatorio_sem_repeticao";
            default -> "desconhecido";
        };

        // Arquivo de entrada
        String arquivoEntrada = "dados_entrada/" + tipoNome + ".txt";

        // Pasta de saída específica do algoritmo
        File pastaSaida = new File("dados_saida/bubble");
        if (!pastaSaida.exists()) pastaSaida.mkdirs();

        // Arquivo de saída
        String arquivoSaida = "dados_saida/bubble/" + quantidade + "_" + tipoNome + ".txt";

        // Ler números do arquivo de entrada (apenas a quantidade desejada)
        List<Integer> lista = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(arquivoEntrada))) {
            int count = 0;
            while (fileScanner.hasNextInt() && count < quantidade) {
                lista.add(fileScanner.nextInt());
                count++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            return;
        }

        int[] array = lista.stream().mapToInt(i -> i).toArray();

        // Medir tempo de execução apenas da ordenação
        long start = System.nanoTime();
        bubbleSort(array);
        long end = System.nanoTime();

        // Tempo em nanossegundos
        long tempoNs = end - start;

        // Salvar arquivo de saída
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(array[i] + " ");
                if ((i + 1) % 100 == 0) writer.newLine();
            }
            System.out.println("Arquivo ordenado gerado: " + arquivoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }

        // Exibir apenas o tempo em nanossegundos
        System.out.printf("Tempo de execução BubbleSort (%d números): %d ns%n", array.length, tempoNs);
    }
}