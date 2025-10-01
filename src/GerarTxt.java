import java.io.*;
import java.util.*;

public class GerarTxt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Perguntar a quantidade de números
        System.out.print("Digite a quantidade de números a gerar: ");
        int quantidadeNumeros = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        // Perguntar o tipo de geração
        System.out.println("Escolha o tipo de números:");
        System.out.println("1 - Crescente com repetição");
        System.out.println("2 - Decrescente com repetição");
        System.out.println("3 - Aleatório com repetição");
        System.out.println("4 - Crescente sem repetição");
        System.out.println("5 - Decrescente sem repetição");
        System.out.println("6 - Aleatório sem repetição");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        // Definir nome do arquivo
        String arquivoSaida = switch (tipo) {
            case 1 -> "dados_entrada/crescente_com_repeticao.txt";
            case 2 -> "dados_entrada/decrescente_com_repeticao.txt";
            case 3 -> "dados_entrada/aleatorio_com_repeticao.txt";
            case 4 -> "dados_entrada/crescente_sem_repeticao.txt";
            case 5 -> "dados_entrada/decrescente_sem_repeticao.txt";
            case 6 -> "dados_entrada/aleatorio_sem_repeticao.txt";
            default -> "dados_entrada/dados.txt";
        };

        Random rand = new Random();
        Set<Integer> numerosGerados = new HashSet<>();
        List<Integer> numeros = new ArrayList<>();

        // Gerar números de acordo com o tipo
        switch (tipo) {
            case 1 -> { // Crescente com repetição
                for (int i = 0; i < quantidadeNumeros; i++) {
                    numeros.add(rand.nextInt(quantidadeNumeros / 10 + 1)); // força repetições
                }
                Collections.sort(numeros);
            }
            case 2 -> { // Decrescente com repetição
                for (int i = 0; i < quantidadeNumeros; i++) {
                    numeros.add(rand.nextInt(quantidadeNumeros / 10 + 1));
                }
                numeros.sort(Collections.reverseOrder());
            }
            case 3 -> { // Aleatório com repetição
                for (int i = 0; i < quantidadeNumeros; i++) {
                    numeros.add(rand.nextInt(quantidadeNumeros * 2) + 1);
                }
            }
            case 4 -> { // Crescente sem repetição
                for (int i = 1; i <= quantidadeNumeros; i++) {
                    numeros.add(i);
                }
            }
            case 5 -> { // Decrescente sem repetição
                for (int i = quantidadeNumeros; i >= 1; i--) {
                    numeros.add(i);
                }
            }
            case 6 -> { // Aleatório sem repetição
                while (numeros.size() < quantidadeNumeros) {
                    int numero = rand.nextInt(quantidadeNumeros * 2) + 1;
                    if (!numerosGerados.contains(numero)) {
                        numerosGerados.add(numero);
                        numeros.add(numero);
                    }
                }
                Collections.shuffle(numeros); // embaralhar
            }
        }

        // Escrever no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (int i = 0; i < numeros.size(); i++) {
                writer.write(numeros.get(i) + " ");
                if ((i + 1) % 100 == 0) {
                    writer.newLine();
                }
            }
            System.out.println("Arquivo gerado com sucesso: " + arquivoSaida);
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}