import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        double[] variables = VvodDannix();
        double d = D(variables);
        System.out.println(variables[0]);
        System.out.println(variables[1]);
        System.out.println(variables[2]);
        if (variables[0] != 0) {
            if (d > 0) {
                DBolshe0(variables, d);
            }
            if (d == 0) {
                DRavno0(variables);
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
    public static double[] VvodDannix() {
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
        /*Следующие 4 строки добавляют цифру 1 перед переменными без множителя*/
        temp = temp.replaceAll("\\+x\\^2", "\\+1x\\^2");
        temp = temp.replaceAll("\\-x\\^2", "\\-1x\\^2");
        temp = temp.replaceAll("\\+x[^\\^]", "\\+1x");
        temp = temp.replaceAll("\\-x[^\\^]", "\\-1x");
        double[] variables = new double[3];
        variables[0]=Variables(temp,"([+-][0-9]*)x\\^2");   //a
        variables[1]=Variables(temp,"([+-][0-9]*)x(?!\\^)");    //b
        variables[2]=Variables(temp, "([+-][0-9]+)(?!x)");  //c
        return  variables;
    }

    public static double Variables (String equation, String regex) {
        Pattern patt = Pattern.compile(regex);
        Matcher match = patt.matcher(equation);
        double variables = 0;
        String temp = "+0";
        while (match.find())
        {
            temp= match.group(1);
            variables+=Double.parseDouble(temp);
        }
        return (temp.length() == 1) ? (variables + 1) : variables;
    }

    /*Вычисляем дискриминант уравнения*/
    public static double D(double[] intvariables) {
        Double d = (intvariables[1] * intvariables[1]) - (4.0 * intvariables[0] * intvariables[2]);
        System.out.println("D"+ d);
        return (d);
    }

   /* Вычисление уравнения в случае когда Дискриминант равен 0*/
    public static void  DRavno0(double[] variables) {
        double x = ((-1.0) * variables[1]) / (2.0 * variables[0]);
        System.out.println("X=" + x);
            }

    /* Вычисление уравнения в случае когда Дискриминант больше 0*/
    public static void  DBolshe0(double[] intvariables, double d) {
        double[] x = new double[2];
        x[0] = (-intvariables[1] + Math.sqrt(d)) / (2 * intvariables[0]);
        x[1] = (-intvariables[1] - Math.sqrt(d)) / (2 * intvariables[0]);
        System.out.println("X1= " + x[0]);
        System.out.println("X2= " + x[1]);
    }
}

