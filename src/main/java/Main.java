import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    // nie zmieniaj nic w main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.run(scanner);
    }

    void run(Scanner scanner) {
        String fileName = "countries.csv";
        try {
            Map<String, Country> countriesMap = readFileAndGetCountriesMap(fileName);
            System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
            String codeFromUser = scanner.nextLine();
            checkOrContainsCountry(countriesMap, codeFromUser);
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku " + fileName + ".");
        }
    }

    private static void checkOrContainsCountry(Map<String, Country> countriesMap, String codeFromUser) {
        if (countriesMap.containsKey(codeFromUser)) {
            System.out.println(countriesMap.get(codeFromUser));
        } else {
            System.out.println("Kod kraju " + codeFromUser + " nie został znaleziony.");
        }
    }

    private Map<String, Country> readFileAndGetCountriesMap(String fileName) throws FileNotFoundException {
        Map<String, Country> countryMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                String code = split[0];
                String name = split[1];
                int population = Integer.parseInt(split[2]);
                Country country = new Country(code, name, population);
                countryMap.put(code, country);
            }
        }
        return countryMap;
    }

}
