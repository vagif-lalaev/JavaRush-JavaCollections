import java.math.BigInteger;

public class Fibonachi {
    public static void main(String[] args) {
//        System.out.println(fib(5));
        System.out.println(factorial(20));
        System.out.println(factorial3(100));
//        System.out.println(factorial2(20));

    }

    static int fib(int n) { //число фибоначи
        if (n < 2) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    static int factorial2(int n) {
        int count = 1;
//        if (n <= 1) return 1;
        for (int i = 1; i <= n; i++) {
            count *= i;
        }
        return count;
    }

    public static BigInteger factorial3(int n)
    {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) {
            ret = ret.multiply(BigInteger.valueOf(i));
        }
        return ret;
    }
}
