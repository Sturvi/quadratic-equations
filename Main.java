import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        double[] variabler = VvodDannix();
        System.out.println(variabler[0]);
        System.out.println(variabler[1]);
        System.out.println(variabler[2]);
    }

    public static double[] VvodDannix() {
        Scanner scan = new Scanner(System.in);
        String equation = scan.nextLine();
        String temp = ("+" + equation).replaceAll("\\s", "");
        temp = temp.replaceAll("\\+x\\^2", "\\+1x\\^2");
        temp = temp.replaceAll("\\-x\\^2", "\\-1x\\^2");
        double[] variables = new double[3];
        variables[0]=Variables(temp,"([+-][0-9]*)x\\^2");
        variables[1]=Variables(temp,"([+-][0-9]*)x(?!\\^)");
        variables[2]=Variables(temp, "([0-9]+)(?!x)");
        return  variables;
    }

    public static double Variables (String equation, String regex) {
        Pattern patt = Pattern.compile(regex);
        Matcher match = patt.matcher(equation);
        double variables = 0;
        String temp = "";
        while (match.find())
        {
            temp= match.group(1);
            variables+=Double.parseDouble(temp);
        }
        return variables;
    }

}

