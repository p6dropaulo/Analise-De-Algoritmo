import java.io.*;
import java.util.*;

public class MergeSort {

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
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
        File pastaSaida = new File("dados_saida/merge");
        if (!pastaSaida.exists()) pastaSaida.mkdirs();
        String arquivoSaida = "dados_saida/merge/" + quantidade + "_" + tipoNome + "_ordenado_merge.txt";

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
        mergeSort(array, 0, array.length - 1);
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
        System.out.printf("Tempo de execução MergeSort (%d números): %d ns%n", array.length, tempoNs);
    }
}