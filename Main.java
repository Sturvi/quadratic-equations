import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] intvariables = VvodDannix();
        double d = D(intvariables);
        if (intvariables[0] != 0) {
            if (d > 0) {
                double[] x = DBolshe0(intvariables, d);
                System.out.println("X1= " + x[0]);
                System.out.println("X2= " + x[1]);
            }
            if (d == 0) {
                double x = DRavno0(intvariables);
                System.out.println("X=" + x);
            }
            if (d < 0)
                System.out.println("Действительных корней нет");

        } else {
            double x = (-intvariables[2] / intvariables[1]);
            System.out.println("Не является квадратным уравнением, но вот тебе ответ.");
            System.out.println("X=" + x);
        }

    }

    public static double DRavno0(int[] intvariables) {
        double x = ((-1.0) * intvariables[1]) / (2.0 * intvariables[0]);

        return (x);
    }

    public static double[] DBolshe0(int[] intvariables, double d) {
        double[] x = new double[2];
        x[0] = (-intvariables[1] + Math.sqrt(d)) / (2 * intvariables[0]);
        x[1] = (-intvariables[1] - Math.sqrt(d)) / (2 * intvariables[0]);
        return (x);
    }

    public static double D(int[] intvariables) {
        Double d = (intvariables[1] * intvariables[1]) - (4.0 * intvariables[0] * intvariables[2]);

        return (d);
    }

    public static int[] VvodDannix() {
        Scanner scan = new Scanner(System.in);
        String equation = scan.nextLine();
        String equation2 = equation;
        equation = equation.replace("^2", "");
        String[] variables = equation.split("[^\\d-]+");


        boolean[] testkoef = KoeficentTest(variables.length, equation2);

        int[] intvariables = new int[3];
        int j = 0, i = 0;
        while (i < variables.length) {
            if (testkoef[j] == true) {
                intvariables[j] = Integer.parseInt(variables[i]);
                j++;
                i++;
            } else {
                intvariables[j] = 0;
                j++;
            }

        }
        return (intvariables);
    }


    public static boolean[] KoeficentTest(int variableslength, String equation) {
        boolean[] test = {false, false, false};
        if (variableslength == 3) {
            test[0] = test[1] = test[2] = true;
        } else if (variableslength == 2) {
            if (equation.contains("x^2")) {
                test[0] = true;
                equation = equation.replace("x^2", "");
                if (equation.contains("x")) {
                    test[1] = true;
                    test[2] = false;
                } else {
                    test[1] = false;
                    test[2] = true;
                }
            } else {
                test[0] = false;
                test[1] = test[2] = true;
            }
        } else {
            if (equation.contains("x^2")) {
                test[0] = true;
                test[1] = test[2] = false;
            } else if (equation.contains("x")) {
                test[1] = true;
                test[0] = test[2] = false;
            } else
                System.out.println("Вы не ввели уравнение");
        }
        return (test);
    }
}

