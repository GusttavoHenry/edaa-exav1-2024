import java.util.*;

 
// Esta função irá ler os dados do array de strings e armazená-los em um mapa
public class atividade02  {
    private static Map<String, Double> readDataFromFile(String[] data) {
        Map<String, Double> pais = new LinkedHashMap<>();
        for (String line : data) {
            String[] partes = line.split(",");
            String nomePais = partes[0];
            double ibopeTv = Double.parseDouble(partes[1]);
            pais.put(nomePais, ibopeTv);
        }
        return pais;
    }

// Função para imprimir os países e suas audiências de TV
    private static void printCountries(Map<String, Double> pais) {
        System.out.println("Países e audiência de TV:");
        for (Map.Entry<String, Double> entry : pais.entrySet()) {
            System.out.printf("%s: %.1f%%\n", entry.getKey(), entry.getValue());
        }
    }

// Dados dos países e suas audiências de TV
    public static void main(String[] args) {
        String[] data = {
            "China,14.8",
            "Brazil,7.1",
            "United States,4.3",
            "India,2.2",
            "South Africa,1.5",
            "Others,69.9"
        };
    
// Lê os dados dos países e suas audiências de TV do array de strings
        Map<String, Double> paises = readDataFromFile(data);
        Scanner scanner = new Scanner(System.in);
        int choice;

// Loop principal para interação com o usuário        
        do {
            System.out.println("Digite a opção desejada:");
            System.out.println("(1) Ordenar por Ordem Alfabética");
            System.out.println("(2) Ordenar por audiência TV");
            System.out.println("(3) Encerrar o progrma");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    paises = new TreeMap<>(paises);
                    printCountries(paises);
                    break;
                case 2:
                    paises = sortByValue(paises);
                    printCountries(paises);
                    // ira indicar Indicar a mudança do Brasil de 5ª para 2ª posição
                    System.out.println("Após a ordenação, o Brasil está em 2ª posição.");
                    break;
                case 3:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (choice != 3);

        scanner.close();
    }
// Função para ordenar o mapa por valores (audiências de TV)
    private static Map<String, Double> sortByValue(Map<String, Double> paises) {
        List<Map.Entry<String, Double>> list = new LinkedList<>(paises.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
