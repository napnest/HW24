
import java.util.*;

public class Main {
    private static Map<String, Set<String>> list = new TreeMap<>();
    private static final String INFO = "Введите имя контакта или номер телефона";

    public static void main(String[] args) {
        while (true) {
            System.out.println(INFO);
            String input = new Scanner(System.in).nextLine();
            if (input.equalsIgnoreCase("Печать")) {
                if (list.isEmpty()) {
                    System.out.println("Список пустой");
                } else {
                    print();
                }
            } else if (input.equalsIgnoreCase("Выход")) {
                return;
            }
            else if(input.matches("^[А-яЁё]+")){
                System.out.println("Введите номер телефона:");
                String number = new Scanner(System.in).nextLine();
                if(validationName(input)){
                    continue;
                }
                if(validationNumber(number)){
                    continue;
                }
                addNumber(input,number);
            }
            else if(input.matches("^\\d{10}$")&& input.startsWith("9")){
                System.out.println("Введите имя контакта:");
                String name = new Scanner(System.in).nextLine();
                if(validationName(name)){
                    continue;
                }
                if(validationNumber(input)){
                    continue;
                }
                addNumber(name,input);
            }
            else{
                System.out.println("Неправильный ввод!");
            }


        }
    }
    public static void print() {
        for (Map.Entry<String, Set<String>> entry : list.entrySet()) {
            String name = entry.getKey();
            System.out.print(name + " - ");
            for (String s : entry.getValue()) {
                System.out.print(s);
                System.out.println();
            }
        }
    }
    public static void addNumber(String name, String phoneNumber) {
        Set<String> contacts = new TreeSet<>();
        contacts.add(phoneNumber);
        list.put(name,contacts);
        System.out.println("Контакт добавлен!");
    }
    public static boolean validationName(String name){
        boolean flag = false;
        if(list.containsKey(name)){
            System.out.println("Данный контакт уже есть");
            for(Map.Entry <String,Set<String>> entry: list.entrySet()){
                String tempName = entry.getKey();
                System.out.print(tempName + " - ");
                for(String vals: entry.getValue()){
                    System.out.println(vals);
                }
            }
            flag=true;
        }
        return flag;
    }
    public static boolean validationNumber(String number){
        boolean flag = false;
            for(Map.Entry <String,Set<String>> entry: list.entrySet()){
                String name = entry.getKey();
                for(String vals: entry.getValue()){
                    if(vals.equals(number)){
                        flag=true;
                        System.out.println("Данный контакт уже имеется");
                        System.out.println(name+" - "+vals);
                    }
                }
            }


        return flag;
    }

    }


