
import java.util.*;

public class Main {
    private static final String NAME_REGEX = "[А-яЁё]+";
    private static final String NUM_REGEX = "\\d{11}|\\d{10}";
    private static Map<String, String> phoneBook = new TreeMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите имя или номер, или команды: выход, печать");
            String input = new Scanner(System.in).nextLine();
            if (input.equalsIgnoreCase("выход")) {
                System.out.println("Вы вышли из программы.");
                return;
            } else if (input.equalsIgnoreCase("печать")) {
                print();
            } else if (input.matches(NAME_REGEX)) {
                addByName(input);
            } else if (input.replaceAll("\\D+", "").matches(NUM_REGEX)) {
                addByNum(input.replaceAll("\\D+", ""));
            } else {
                System.out.println("Неправильная команда: " + input + ". Попробуйте еще раз!");
            }
        }

    }

    public static void addByNum(String num) {
        num = normalizeNum(num);
        if (phoneBook.containsValue(num)) {
            System.out.println("Номер " + num + " уже есть у другого абонента!");
            return;
        }
        System.out.println("Введите имя абонента для номера: " + num);
        String name = new Scanner(System.in).nextLine();
        if (!name.matches(NAME_REGEX)) {
            System.out.println("Неправильный ввод имени!");
            return;
        }
        if (phoneBook.containsKey(name)) {
            System.out.println("Абонент с именем " + name + "уже существует!");
            return;
        }
        addToBook(name, num);

    }

    public static void addByName(String name) {
        if (phoneBook.containsKey(name)) {
            System.out.println("Данный абонент " + name + " уже есть!");
            return;
        }
        System.out.println("Введите номер для абонента: " + name);
        String num = new Scanner(System.in).nextLine();
        num.replaceAll("\\D+", "");
        if (!num.matches(NUM_REGEX)) {
            System.out.println("Неправильный ввод!");
            return;
        }
        num = normalizeNum(num);
        if (phoneBook.containsValue(num)) {
            System.out.println("Номер " + num + " уже есть у другого абонента ");
            return;
        }
        addToBook(name, num);

    }
    public static String normalizeNum(String num){
        if (num.length() == 11) {
            num = num.substring(1);
        }
        return num;
    }
    public static void addToBook(String name, String num){
        phoneBook.put(name, num);
        System.out.println("Абонент " + name + " с номером " + num + " добавлен!");
    }
    public static void print() {
        if (phoneBook.isEmpty()) {
            System.out.println("Список абонентов пуст!");
            return;
        }
        for (Map.Entry<String, String> contact : phoneBook.entrySet()) {
            System.out.println("Абонент: " + contact.getKey() + ", номер: " + contact.getValue());
        }
    }
}

