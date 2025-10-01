import java.io.*;
import java.util.*;

public class InsertionSort {

    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de números: ");
        int quantidade = scanner.nextInt();

        System.out.println("Escolha o tipo de dados:");
        System.out.println("1 - Crescente com repetição");
        System.out.println("2 - Decrescente com repetição");
        System.out.println("3 - Aleatório com repetição");
        System.out.println("4 - Crescente sem repetição");
        System.out.println("5 - Decrescente sem repetição");
        System.out.println("6 - Aleatório sem repetição");
        int tipo = scanner.nextInt();
        scanner.close();

        String tipoNome = switch (tipo) {
            case 1 -> "crescente_com_repeticao";
            case 2 -> "decrescente_com_repeticao";
            case 3 -> "aleatorio_com_repeticao";
            case 4 -> "crescente_sem_repeticao";
            case 5 -> "decrescente_sem_repeticao";
            case 6 -> "aleatorio_sem_repeticao";
            default -> "desconhecido";
        };

        String arquivoEntrada = "dados_entrada/" + tipoNome + ".txt";
        File pastaSaida = new File("dados_saida/insertion");
        if (!pastaSaida.exists()) pastaSaida.mkdirs();
        String arquivoSaida = "dados_saida/insertion/" + quantidade + "_" + tipoNome + "_ordenado_insertion.txt";

        // Ler apenas a quantidade desejada
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

        long start = System.nanoTime();
        insertionSort(array);
        long end = System.nanoTime();

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
        long tempoNs = end - start;
        System.out.printf("Tempo de execução InsertionSort (%d números): %d ns%n", array.length, tempoNs);
    }
}