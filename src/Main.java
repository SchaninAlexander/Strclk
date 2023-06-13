import java.util.Scanner;

class Calc {
    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример");
        String line = scanner.nextLine();
        Calculation.parse(line.replaceAll("\"", ""));

    }
}
class Calculation{
    public static void parse(String line) throws Exception {
        String s1;
        String s2;
        String operation;
        String[] data = line.split("[+\\-*/] ");
        if (data.length != 2) {
            throw new Exception("Должно быть два операнда и математический знак");

        }
        s1 = data[0].replaceAll(" ","");
        s2 = data[1].replaceAll(" ", "");
        if (s1.length() > 10 || s2.length() > 10)
            throw new Exception("Длинна вводимой строки должна быть не более 10 символов");
        operation = detectOperation(line);
        if (operation == null) {
            throw new Exception("Ошибка ввода операции");
        }

        selectOperation(operation,s1,s2);

    }

    static String detectOperation(String operation) {
        if (operation.contains("+")) return "+";
        else if (operation.contains("-")) return "-";
        else if (operation.contains("*")) return "*";
        else if (operation.contains("/")) return "/";
        else return null;

    }

    static void selectOperation(String operation, String s1, String s2) {
        switch (operation) {
            case "+" -> Quotes(s1.concat(s2));
            case "-" -> {
                int index = s1.indexOf(s2);
                if (index == -1) {
                    Quotes(s1);
                } else {
                    String result = s1.substring(0, index);
                    result += s1.substring(index + s2.length());
                    Quotes(result);
                }
            }
            case "*" -> {
                try {
                    int multiplier = Integer.parseInt(s2);
                    if (multiplier > 10 || multiplier < 1) {
                        throw new RuntimeException("Умножать строчку можно на число от 1 до 10-ти ");
                    }
                    Quotes(String.valueOf(s1).repeat(multiplier));

                } catch (Exception e) {
                    throw new RuntimeException("Строчку можно умножать только на целое число");
                }
            }
            case "/" -> {
                try {
                    int division = Integer.parseInt(s2);
                    if (division > 10 || division < 1) {
                        throw new RuntimeException("Делить строчку можно на число от 1 до 10-ти ");
                    }
                    division = s1.length() / Integer.parseInt(s2);
                    String result = s1.substring(0, division);
                    Quotes(result);

                } catch (Exception e) {
                    throw new RuntimeException("Строчку можно делить только на целое число");
                }
            }
        }
    }

    static void Quotes(String result) {

        if (result.length() > 40) {
            StringBuilder sb = new StringBuilder();
            String s = sb.append(result, 0, 40) + "(...)";
            System.out.println("\"" + s + "\"");
        } else {

            System.out.println ("\"" + result + "\"");

        }
    }
}







