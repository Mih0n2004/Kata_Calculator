import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args)  {
    System.out.println("Calculate!");
    Scanner scan = new Scanner(System.in);

        try {
            System.out.println (calc(scan.nextLine()));
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        }
        catch (NoSuchElementException e) {
            System.out.println("throws Exception //т.к. строка не соответсвует формату математической операции (два операнда и оператор)");
        }
    }
    //ниже используем FileNotFoundException, т.к. для данной задачи возникнуть не может исходя из условий и реализации,
    //но подойдёт для целей информирования пользователей о текущих ошибках. Может быть заменено на собственное исключение.
    public static String calc (String input) throws FileNotFoundException {

        Scanner scanner = new Scanner(input);
        boolean isRomanian = false;
        int number1 = 0;
        int number2 = 0;
        String operators = "+ - / *";
        String operator = "";
        String resultOfOperation = "";

        String operand1 = scanner.next();
        operator = scanner.next();

        if (!operators.contains(operator)) {
            //исключение о том, что операция не является математической
            throw new FileNotFoundException("throws Exception //т.к. строка не является математической операцией");
        }

        String operand2 = scanner.next();

        if (operand1.matches("-?\\d+") && operand2.matches("-?\\d+")){

            number1 = Integer.valueOf(operand1);
            //проверка на совпадение с условием задачи о приёме на вход чисел от 1 до 10 включительно
            if (number1 > 10 || number1 < 1) throw new FileNotFoundException("throws Exception //т.к. аргумент не удовлетворяет условиям задания (значение от 1 до 10 включительно)");
            // ---operator = scanner.next();

            number2 = Integer.valueOf(operand2);
            //проверка на совпадение с условием задачи о приёме на вход чисел от 1 до 10 включительно
            if (number2 > 10 || number2 < 1) throw new FileNotFoundException("throws Exception //т.к. аргумент не удовлетворяет условиям задания (значение от 1 до 10 включительно)");

        } else {

            isRomanian = true;
            String romeDigits = "I II III IV V VI VII VIII IX X";

            if (romeDigits.contains(operand1) && romeDigits.contains(operand2)) {

                number1 = convertOperandToArabic(operand1);
                number2 = convertOperandToArabic(operand2);

            } else if (romeDigits.contains(operand1) || romeDigits.contains(operand2)){
                //исключение о несовпадении систем исчисления для операндов
                throw new FileNotFoundException("throws Exception //т.к. используются одновременно разные системы счисления");

            } else {
                //исключение о неправильной строке
                throw new FileNotFoundException("throws Exception //т.к. строка содержит операторы не являющиеся арабскими или римскими цифрами");
            }


        }

        if (scanner.hasNext()) throw new FileNotFoundException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        switch (operator) {
            case "+":
                resultOfOperation = String.valueOf(number1 + number2);
                break;
            case "-":
                resultOfOperation = String.valueOf(number1 - number2);
                break;
            case "*":
                resultOfOperation = String.valueOf(number1 * number2);
                break;
            case "/":
                resultOfOperation = String.valueOf(number1 / number2);
                break;

        }

        if (isRomanian && Byte.valueOf(resultOfOperation) > 0)  {
            resultOfOperation = convertResultToRomanian(Byte.valueOf(resultOfOperation));
        } else if (isRomanian && Byte.valueOf(resultOfOperation) < 1) {
            throw new FileNotFoundException("throws Exception //т.к. в римской системе нет отрицательных чисел (и нуля)");
        }

        return resultOfOperation;
        //System.out.println(resultOfOperation);

    }
        static int convertOperandToArabic (String romeDigit){
            switch (romeDigit) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
            return 99; //никогда не получим данное число исходя из условий и текущей реализации калькулятора
        }

        static String convertResultToRomanian (int result){

            //if (result == 0) return "N //устаревшее обозначение нуля в римской системе исчисления ";

            String romanian = "";
            byte tens = (byte) (result / 10);
            byte ones = (byte) (result % 10);

            switch (tens) {
                case 10:
                    romanian = romanian + "C";
                    break;
                case 9:
                    romanian = romanian + "XC";
                    break;
                case 8:
                    romanian = romanian + "LXXX";
                    break;
                case 7:
                    romanian = romanian + "LXX";
                    break;
                case 6:
                    romanian = romanian + "LX";
                    break;
                case 5:
                    romanian = romanian + "L";
                    break;
                case 4:
                    romanian = romanian + "XL";
                    break;
                case 3:
                    romanian = romanian + "XXX";
                    break;
                case 2:
                    romanian = romanian + "XX";
                    break;
                case 1:
                    romanian = romanian + "X";
                    break;
            }

            switch (ones) {
                case 9:
                    romanian = romanian + "IX";
                    break;
                case 8:
                    romanian = romanian + "VIII";
                    break;
                case 7:
                    romanian = romanian + "VII";
                    break;
                case 6:
                    romanian = romanian + "VI";
                    break;
                case 5:
                    romanian = romanian + "V";
                    break;
                case 4:
                    romanian = romanian + "IV";
                    break;
                case 3:
                    romanian = romanian + "III";
                    break;
                case 2:
                    romanian = romanian + "II";
                    break;
                case 1:
                    romanian = romanian + "I";
                    break;
            }
            return romanian;
        }
    }




