import java.io.*;
import java.util.*;

public class QuickSort {

    // Escolhe um pivô aleatório e faz a partição
    public static int partition(int[] array, int low, int high) {
        int randomIndex = low + (int) (Math.random() * (high - low + 1));
        int temp = array[randomIndex];
        array[randomIndex] = array[high];
        array[high] = temp;

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }

        int t = array[i + 1];
        array[i + 1] = array[high];
        array[high] = t;

        return i + 1;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
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
        File pastaSaida = new File("dados_saida/quick");
        if (!pastaSaida.exists()) pastaSaida.mkdirs();
        String arquivoSaida = "dados_saida/quick/" + quantidade + "_" + tipoNome + "_ordenado_quick.txt";

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
        quickSort(array, 0, array.length - 1);
        long end = System.nanoTime();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(array[i] + " ");
                if ((i + 1) % 100 == 0) writer.newLine();
            }
            System.out.println("Arquivo ordenado gerado: " + arquivoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }

        long tempoNs = end - start;
        System.out.printf("Tempo de execução QuickSort (%d números): %d ns%n", array.length, tempoNs);
    }
}