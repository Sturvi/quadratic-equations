import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        double[] variables = imputData();
        double d = discriminant(variables);
        if (variables[0] != 0) {
            if (d > 0) {
                ifDiscriminantGreaterThan0(variables, d);
            }
            if (d == 0) {
                ifDiscriminantEqualTo0(variables);
            }
            if (d < 0)
                System.out.println("Действительных корней нет");
        } else {
            double x = (-variables[2] / variables[1]);
            System.out.println("Не является квадратным уравнением, но вот тебе ответ:");
            System.out.println("X=" + x);
        }
    }

    /*Считывание данных с клавиатуры и добавление всех коифицентов уравнения в однельный массив.*/
    public static double[] imputData() {
        Scanner scan = new Scanner(System.in);
        boolean test=false;
        String equation="";

        /*Проверяется вводилась ли "=0" в конце уровнения.*/
        while (!test) {
            System.out.println("Введите уравнение:");
            equation = scan.nextLine();
            if (equation.contains("=0"))
                test=true;
            else
                System.out.println("Вы не ввели правую часть уравнения");
        }
        String temp = ("+" + equation).replaceAll("\\s", "");
        /*Следующие 2 строки добавляют цифру 1 перед переменными без множителя*/
        temp = temp.replaceAll("\\+x", "\\+1x");
        temp = temp.replaceAll("\\-x", "\\-1x");
        double[] variables = new double[3];
        variables[0]= determineTheCoefficient(temp,"([+-][0-9]*)x\\^2");   //a
        variables[1]= determineTheCoefficient(temp,"([+-][0-9]*)x(?!\\^)");    //b
        variables[2]= determineTheCoefficient(temp, "([+-][0-9]+)(?!x)");  //c
        return  variables;
    }

    public static double determineTheCoefficient(String equation, String regex) {
        Pattern patt = Pattern.compile(regex);
        Matcher match = patt.matcher(equation);
        double variables = 0;
        String temp = "+0";
        while (match.find())
        {
            temp= match.group(1);
            variables+=Double.parseDouble(temp);
        }
        return variables;
    }

    /*Вычисляем дискриминант уравнения*/
    public static double discriminant(double[] intvariables) {
        Double d = (intvariables[1] * intvariables[1]) - (4.0 * intvariables[0] * intvariables[2]);
        return (d);
    }

   /* Вычисление уравнения в случае когда Дискриминант равен 0*/
    public static void ifDiscriminantEqualTo0(double[] variables) {
        double x = ((-1.0) * variables[1]) / (2.0 * variables[0]);
        System.out.println("X=" + x);
            }

    /* Вычисление уравнения в случае когда Дискриминант больше 0*/
    public static void ifDiscriminantGreaterThan0(double[] intvariables, double d) {
        double[] x = new double[2];
        x[0] = (-intvariables[1] + Math.sqrt(d)) / (2 * intvariables[0]);
        x[1] = (-intvariables[1] - Math.sqrt(d)) / (2 * intvariables[0]);
        System.out.println("X1= " + x[0]);
        System.out.println("X2= " + x[1]);
    }
}

