
import java.util.*;
//программа телефонного справочника
public class Main {
    //константа регулярного выражения имени контакта
    private static final String NAME_REGEX = "[А-яЁё]+";
    //константа регулярного выражения номера телефона
    private static final String NUM_REGEX = "\\d{11}|\\d{10}";
    //создаем телефонный справочник типа Map
    private static Map<String, Set<String>> phoneBook = new TreeMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите имя или номер, или команды: выход, печать");
            //ввод
            String input = new Scanner(System.in).nextLine();
            //условие выхода из программы
            if (input.equalsIgnoreCase("выход")) {
                System.out.println("Вы вышли из программы.");
                return;
              //условие вывода на печать
            } else if (input.equalsIgnoreCase("печать")) {
                print();
              //добавляем контакт в справочник по имени абонента
            } else if (input.matches(NAME_REGEX)) {
                addByName(input);
              //добавляем контакт в справочник по номеру абонента
            } else if (input.replaceAll("\\D+", "").matches(NUM_REGEX)) {
                addByNum(input.replaceAll("\\D+", ""));
              //неправильный ввод
            } else {
                System.out.println("Неправильная команда: " + input + ". Попробуйте еще раз!");
            }
        }

    }
    //метод добавления контакта по имени абонента
    public static void addByNum(String num) {
        //если 11 цифр, то удаляем первую, для нормализации номера
        num = normalizeNum(num);
        //ищем совпадения контакта по номеру в справочнике
        for (Map.Entry <String, Set <String>> contacts: phoneBook.entrySet()){
            if (contacts.getValue().contains(num)){
                System.out.println("Номер " + num + " уже есть у другого абонента " + searchByNum(num));
                return;
            }
        }
        //вводим имя для абонента
        System.out.println("Введите имя абонента для номера: " + num);
        String name = new Scanner(System.in).nextLine();
        //сравниваем правильность ввода имени по регулярному выражению
        if (!name.matches(NAME_REGEX)) {
            System.out.println("Неправильный ввод имени!");
            return;
        }
        //добавляем контакт в справочник
        addToBook(name, num);
    }
    //метод добавления контакта в справочник по имени
    public static void addByName(String name) {
        //ищем совпадения по имени
        if (phoneBook.containsKey(name)) {
            System.out.println("Данный абонент " + name + " уже есть!");
            System.out.println("Номера: " + phoneBook.get(name));
        }
        //ввод номера абонента
        System.out.println("Введите номер для абонента: " + name);
        String num = new Scanner(System.in).nextLine();
        num.replaceAll("\\D+", "");
        if (!num.matches(NUM_REGEX)) {
            System.out.println("Неправильный ввод!");
            return;
        }
        num = normalizeNum(num);
        for (Map.Entry <String, Set <String>> contacts: phoneBook.entrySet()){
            if (contacts.getValue().contains(num)){
                System.out.println("Номер " + num + " уже есть у другого абонента " + searchByNum(num));
                return;
            }
        }
        addToBook(name, num);

    }
    //нормализуем номер
    public static String normalizeNum(String num){
        if (num.length() == 11) {
            num = num.substring(1);
        }
        return num;
    }
    //добавляем контакт в справочник
    public static void addToBook(String name, String num){
        //если имя совпадает с введенным, то добавляем к нему новый номер
        if (phoneBook.containsKey(name)){
            phoneBook.get(name).add(num);
            System.out.println("Абоненту " + name + " добавлен номер " + num);
        } else {
            Set <String> nums = new TreeSet<>();
            nums.add(num);
            phoneBook.put(name, nums);
            System.out.println("Абонент " + name + " с номером " + num + " добавлен!");
        }
    }
    //выводим на экран контакты справочника
    public static void print() {
        if (phoneBook.isEmpty()) {
            System.out.println("Список абонентов пуст!");
            return;
        }
        for (Map.Entry<String, Set<String>> contact : phoneBook.entrySet()) {
            System.out.println("Абонент: " + contact.getKey());
            for (String num: contact.getValue()){
                System.out.println("\t" + num);
            }
        }
    }
    //метод поиска по номеру
    public static String searchByNum(String num){
        String name = null;
        for (Map.Entry <String, Set<String>> contacts: phoneBook.entrySet()){
            if (contacts.getValue().contains(num)){
                name = contacts.getKey();
            }
        }
        return name;
    }
}

