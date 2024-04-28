import java.io.*;
import java.util.*;

//Esta Class esta representando um item/valor de um arquivo CSV

class Item {
    String jogos, categoria;
    double nota;
    Item(String j, String c, double n) { jogos=j; categoria=c; nota=n; }
}

// A calss Main irá exibir um menu de opções
public class atividade01 {
    static List<Item> itens = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            menu();
            switch (scanner.nextInt()) {
                case 1: lerArquivo(); break;
                case 2: ordenarPor("JogosOrdenadosporCategoria.csv", Comparator.comparing(i -> i.categoria)); break;
                case 3: ordenarPor("JogosOrdenadosporNota.csv", Comparator.comparingDouble(i -> -i.nota)); break;
                case 4: System.out.println("Encerrando o programa!"); scanner.close(); return;
                default: System.out.println("Opção inválida. Por favor, escolha uma opção válida!.");
            }
        }
    }

    static void menu() {
        System.out.println("(1) Ler um arquivo\n(2) Ordenar por categoria\n (3) Ordenar por nota\n(4) Encerrar o programa");
        System.out.print("Escolha uma das opções abaixo: ");
    }

    // A função lerArquivo(), realiza a lietura dos dados do arquivo CSV e os armazena em um lista de itens
    static void lerArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("JogosDesordenados.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                itens.add(new Item(partes[0], partes[1], Double.parseDouble(partes[2])));
            }
            System.out.println("Arquivo lido com sucesso.");
        } catch (IOException e) { System.out.println("Erro ao ler o arquivo: " + e.getMessage()); }
    }
    
// Ordena os itens conforme o comparador especificado e os salva em um novo arquivo CSV
    static void ordenarPor(String nomeArquivo, Comparator<Item> comparator) {
        Collections.sort(itens, comparator);
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Item item : itens) writer.println(item.jogos + "," + item.categoria + "," + item.nota);
            System.out.println("Dados ordenados salvos em '" + nomeArquivo + "'.");
        } catch (IOException e) { System.out.println("Erro ao salvar o arquivo: " + e.getMessage()); }
    }
}
