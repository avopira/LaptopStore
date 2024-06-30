import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("Lenovo T14","black", "Windows 10", "2 TB", 64);
        Laptop laptop2 = new Laptop("Lenovo T490", "black", "Windows 11", "512 GB", 32);
        Laptop laptop3 = new Laptop("MacBook Air", "navy", "iOS 17.0", "1 TB", 64);
        Laptop laptop4 = new Laptop("ASOS", "gray", "Windows 10", "1 TB", 32);
        Laptop laptop5 = new Laptop("HP", "gray", "Windows XP", "256 GB", 16);
        Laptop laptop6 = new Laptop("Lenovo T14", "black", "Windows 11", "512 GB", 32);
        Laptop laptop7 = new Laptop("Acer", "gray", "Windows XP", "256 GB", 16);
        Laptop laptop8 = new Laptop("MacBook Air", "white", "iOS 17.0", "256 TB", 32);
        Laptop laptop9 = new Laptop("HP", "white", "Windows 10", "256 GB", 32);
        Laptop laptop10 = new Laptop("HP", "gray", "Windows 11", "512 GB", 32);

        Set<Laptop> laptops = new HashSet<>(Arrays.asList(laptop1, laptop2, laptop3, laptop4, laptop5, laptop6, laptop7,
                laptop8, laptop9, laptop10));

        Map<Integer, String> filters = filter(); // вывод списка фильтров
        System.out.println("Enter the number of criteria to be filtered (separated by commas): ");
        for (Map.Entry<Integer, String> entry : filters.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        Scanner scanner = new Scanner(System.in); //запрашиваем критерии филттрации у пользователя и записываем их в виде строки
        String criteriaNumbers = scanner.nextLine();

        String[] criteriaArray = criteriaNumbers.split(","); //разбивка строки на массив
        Map<String, Object> criteria = new HashMap<>(); // создала мэп критериев
        for (String criteriaNumber : criteriaArray) { //заполнение мэпа критериями, которые внес пользователь
            switch (criteriaNumber.trim()) {
                case "1":
                    criteria.put("model", filterByModel());
                    break;
                case "2":
                    criteria.put("operatingSystem", filterByOperatingSystem());
                    break;
                case "3":
                    criteria.put("color", filterByColor());
                    break;
                case "4":
                    criteria.put("hardDiskVolume", filterByHardDiskVolume());
                    break;
                case "5":
                    criteria.put("RAM", filterByRAM());
                    break;
                default:
                    System.out.println("No criteria has been chosen or incorrect criteria number. Please try again.");
                    break;
            }
            
            filterByCriteria(laptops, criteria); //метод, вызывающий список лэптопов, соответствующих критерию
        }
        scanner.close();
    }

    private static void filterByCriteria(Set<Laptop> laptops, Map<String, Object> criteria) { //проверяем совпадения с критериями пользователя
        System.out.println("Filtered laptops:");
        for (Laptop laptop : laptops) {
            boolean match = true;
            for (Map.Entry<String, Object> criterion : criteria.entrySet()) {
                switch (criterion.getKey()) {
                    case "model":
                        if (!laptop.getModel().equals(criterion.getValue())) {
                            match = false;
                        }
                        break;
                    case "operatingSystem":
                        if (!laptop.getOS().equals(criterion.getValue())) {
                            match = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equals(criterion.getValue())) {
                            match = false;
                        }
                        break;
                    case "hardDiskVolume":
                        if (laptop.getHardDiskVolumeGB() < getVolumeInGB((String) criterion.getValue())) {
                            match = false;
                        }
                        break;
                    case "RAM":
                        if (laptop.getRAMsizeGB() < (int) criterion.getValue()) {
                            match = false;
                        }
                        break;
                    default:
                        System.out.println("Unknown criterion: " + criterion.getKey());
                        break;
                }
            }
            if (match) {
                System.out.println(laptop);
                System.out.println();
            }
        }
    }

    
    private static Map<Integer, String> filter() { //создаем мэп с доутспынми критериями фильтрации
        Map<Integer, String> filterOptions = new HashMap<>();
        filterOptions.put(1, "Model");
        filterOptions.put(2, "Operating system");
        filterOptions.put(3, "Color");
        filterOptions.put(4, "Hard disk volume");
        filterOptions.put(5, "RAM");
        return filterOptions;
    }
    
    private static String filterByHardDiskVolume() { //метод для критерия фильтрации объема ЖД (вводится минимальное значение)
        System.out.println("Please enter minimum hard disk volume from the dropdown: ");
        Map<Integer, String> volumes = new HashMap<>();
        volumes.put(1, "256 GB");
        volumes.put(2, "512 GB");
        volumes.put(3, "1 TB");
        volumes.put(4, "2 TB");
        
        for (Map.Entry<Integer, String> entry : volumes.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        Scanner volumeScanner = new Scanner(System.in);
        int volumeChoice = volumeScanner.nextInt();
        volumeScanner.nextLine();
        // volumeScanner.close();
        return volumes.get(volumeChoice);
    }
    
    private static int filterByRAM() { //метод для критерия фильтрации ОЗУ (вводится минимальное значение)
        System.out.println("Please enter minimum for RAM size in GB from the dropdown: ");
        Map<Integer, Integer> rams = new HashMap<>();
        rams.put(1, 16);
        rams.put(2, 32);
        rams.put(3, 64);
        
        for (Map.Entry<Integer, Integer> entry : rams.entrySet()) {
            System.out.println(entry.getValue() + " GB");
        }
        
        Scanner ramScanner = new Scanner(System.in);
        int minRAMsizeGB = ramScanner.nextInt();
        ramScanner.nextLine();
        // ramScanner.close();
        return minRAMsizeGB;
    }
    
    private static String filterByColor() { //метод для критерия фильтрации цвета
        System.out.println("Please  enter number of a color from the dropdown: ");
        Map<Integer, String> colors = new HashMap<>();
        colors.put(1, "black");
        colors.put(2, "navy");
        colors.put(3, "gray");
        colors.put(4, "white");
        
        for (Map.Entry<Integer, String> entry : colors.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        Scanner colorScanner = new Scanner(System.in);
        int colorChoice = colorScanner.nextInt();
        colorScanner.nextLine();
        // colorScanner.close();
        return colors.get(colorChoice);
    }
    
    private static String filterByOperatingSystem() {   //метод для критерия фильтрации ОС
        System.out.println("Please  enter number of an operating system from the dropdown: ");
        Map<Integer, String> OSs = new HashMap<>();
        OSs.put(1, "Windows 10");
        OSs.put(2, "Windows 11");
        OSs.put(3, "Windows XP");
        OSs.put(4, "iOS 17.0");
        
        for (Map.Entry<Integer, String> entry : OSs.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        Scanner opSysScanner = new Scanner(System.in);
        int opSysChoice = opSysScanner.nextInt();
        opSysScanner.nextLine();
        // opSysScanner.close();
        return OSs.get(opSysChoice);
    }
    
    private static String filterByModel() { //метод для критерия фильтрации модели
        System.out.println("Please  enter number of a model from the dropdown: ");
        Map<Integer, String> models = new HashMap<>();
        models.put(1, "Lenovo T14");
        models.put(2, "Lenovo T490");
        models.put(3, "MacBook Air");
        models.put(4, "ASOS");
        models.put(5, "HP");
        models.put(6, "Acer");
        
        for (Map.Entry<Integer, String> entry : models.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        Scanner modelScanner = new Scanner(System.in);
        int modelChoice = modelScanner.nextInt();
        modelScanner.nextLine();
        // modelScanner.close();
        return models.get(modelChoice);
    }
    
    private static int getVolumeInGB(String volumeString) { //перевод объема ЖД из Тб в Гб
    switch (volumeString) {
            case "256 GB":
                return 256;
            case "512 GB":
                return 512;
            case "1 TB":
                return 1024;
            case "2 TB":
                return 2048;
            default:
                return 0;
        }
    }
}
