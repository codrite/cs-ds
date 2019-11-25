import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Stack;

public class Katsurba {

    public static void main(String[] args) {
        final String number1 = "3141592653589793238462643383279502884197169399375105820974944592";
        final String number2 = "2718281828459045235360287471352662497757247093699959574966967627";
//        final String number1 = "22222222";
//        final String number2 = "88888888";

//        System.out.println(Long.parseLong(number1) * Long.parseLong(number2));
        System.out.println(multiply(number1, number2));
    }

    //3141592653589793238462643383279502884197169399375105820974944592
    //2718281828459045235360287471352662497757247093699959574966967627

    public static String multiply(String n1, String n2) {
        return katsurbaMultiply(n1, n2);
    }

    static String katsurbaMultiply(String N, String M) {
        if(N.length() > 14){
            return "(" + katsurbaMultiply(leftHalf(N), leftHalf(M)) + zeros(N.length())+
                    "+" +
                    "("+katsurbaMultiply(leftHalf(N), rightHalf(M)) + "+" + katsurbaMultiply(rightHalf(N), rightHalf(M)) + ")" + zeros(N.length()/2) +
                    "+" +
                    katsurbaMultiply(rightHalf(N), rightHalf(M)) +
                    ")";
        } else {
            return katsurba(N, M).toString();
        }
    }

//    static String katsurbaMultiply(String N, String M) {
//        if(N.length() > 14){
//            return "(" + power10(N.length())+"*"+katsurbaMultiply(leftHalf(N), leftHalf(M)) +
//                    "+" +
//                    power10(N.length()/2)+"*("+katsurbaMultiply(leftHalf(N), rightHalf(M)) + "+" + katsurbaMultiply(rightHalf(N), rightHalf(M))+")" +
//                    "+" +
//                    katsurbaMultiply(rightHalf(N), rightHalf(M)) +
//                    ")";
//        } else {
//            return katsurba(N, M).toString();
//        }
//    }

    static BigDecimal katsurba(String N, String M) {
        System.out.println(M + " * " + N);
        Long a = Long.parseLong(N.substring(0, N.length() / 2));
        Long b = Long.parseLong(N.substring(N.length() / 2));

        Long c = Long.parseLong(M.substring(0, M.length() / 2));
        Long d = Long.parseLong(M.substring(M.length() / 2));

        int LEN = N.length();
        return new BigDecimal(Math.pow(10, LEN) * a * c + Math.pow(10, LEN/2) * (a * d + b * c) + d * b)
                        .round(new MathContext(0, RoundingMode.DOWN));
    }

    static String zeros(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while(n-->0)
            stringBuilder.append("0");

        return stringBuilder.toString();
    }

    static String leftHalf(String s) {
        return s.substring(0, s.length()/2);
    }

    static String rightHalf(String s) {
        return s.substring(s.length()/2);
    }

    static String power10(int v) {
        return new BigDecimal(10).pow(v).toString();
    }

}
