import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Ноутбук {
    private String модель;
    private int объемОперативнойПамяти;
    private int объемЖесткогоДиска;
    private String операционнаяСистема;
    private String цвет;

    public Ноутбук(String модель, int объемОперативнойПамяти, int объемЖесткогоДиска,
                   String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.объемОперативнойПамяти = объемОперативнойПамяти;
        this.объемЖесткогоДиска = объемЖесткогоДиска;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    public String getМодель() {
        return модель;
    }

    public int getОбъемОперативнойПамяти() {
        return объемОперативнойПамяти;
    }

    public int getОбъемЖесткогоДиска() {
        return объемЖесткогоДиска;
    }

    public String getОперационнаяСистема() {
        return операционнаяСистема;
    }

    public String getЦвет() {
        return цвет;
    }

    @Override
    public String toString() {
        return "Ноутбук " +
                 модель + '\'' +
                ", объем ОЗУ = " + объемОперативнойПамяти +
                ", объем жесткого диска = " + объемЖесткогоДиска +
                ", операционная система = '" + операционнаяСистема + '\'' +
                ", цвет = '" + цвет + '\'';
    }
}

public class ItogDZ {
    private static List<Ноутбук> ноутбуки;
    private static Map<Integer, String> критерииФильтрации;
    private static Map<Integer, Integer> параметрыФильтрации;

    public static void main(String[] args) {
        ноутбуки = создатьМножествоНоутбуков();
        критерииФильтрации = создатьКритерииФильтрации();
        параметрыФильтрации = new HashMap<>();
        
        запроситьПараметрыФильтрации();
        List<Ноутбук> отфильтрованныеНоутбуки = фильтроватьНоутбуки();
        вывестиНоутбуки(отфильтрованныеНоутбуки);
    }

    private static List<Ноутбук> создатьМножествоНоутбуков() {
        List<Ноутбук> ноутбуки = new ArrayList<>();
        ноутбуки.add(new Ноутбук("Acer", 4, 512, "Windows", "Серый"));
        ноутбуки.add(new Ноутбук("Lenovo", 16, 1024, "MacOS", "Серебристый"));
        ноутбуки.add(new Ноутбук("HP", 8, 256, "Linux", "Черный"));

        return ноутбуки;
    }

    private static Map<Integer, String> создатьКритерииФильтрации() {
        Map<Integer, String> критерииФильтрации = new HashMap<>();
        критерииФильтрации.put(1, "ОЗУ");
        критерииФильтрации.put(2, "Объем жесткого диска");
        критерииФильтрации.put(3, "Операционная система");
        критерииФильтрации.put(4, "Цвет");

        return критерииФильтрации;
    }

    private static void запроситьПараметрыФильтрации() {
        Scanner scanner = new Scanner(System.in);

        for (Map.Entry<Integer, String> entry : критерииФильтрации.entrySet()) {
            boolean вводЗавершен = false;

            while (!вводЗавершен) {
                System.out.print("Введите минимальное значение для критерия \"" + entry.getValue() + "\": ");

                if (scanner.hasNextInt()) {
                    int минимальноеЗначение = scanner.nextInt();
                    параметрыФильтрации.put(entry.getKey(), минимальноеЗначение);
                    вводЗавершен = true;
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
                    scanner.next();
                }
            }
        }
    }

    private static List<Ноутбук> фильтроватьНоутбуки() {
        List<Ноутбук> отфильтрованныеНоутбуки = new ArrayList<>();

        for (Ноутбук ноутбук : ноутбуки) {
            boolean подходитПоКритериям = true;

            for (Map.Entry<Integer, String> entry : критерииФильтрации.entrySet()) {
                int критерий = entry.getKey();
                String значение = entry.getValue();

                if (!соответствуетКритерию(ноутбук, критерий, параметрыФильтрации.get(критерий))) {
                    подходитПоКритериям = false;
                    break;
                }
            }

            if (подходитПоКритериям) {
                отфильтрованныеНоутбуки.add(ноутбук);
            }
        }

        return отфильтрованныеНоутбуки;
    }

    private static boolean соответствуетКритерию(Ноутбук ноутбук, int критерий, int значение) {
        switch (критерий) {
            case 1:
                return ноутбук.getОбъемОперативнойПамяти() >= значение;
            case 2:
                return ноутбук.getОбъемЖесткогоДиска() >= значение;
            default:
                return true;
        }
    }

    private static void вывестиНоутбуки(List<Ноутбук> ноутбуки) {
        System.out.println("Результаты фильтрации:");
        for (Ноутбук ноутбук : ноутбуки) {
            System.out.println(ноутбук);
        }
    }
}