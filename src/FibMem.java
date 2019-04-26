import java.util.Arrays;

public class FibMem {
// проверяем Мемоизацию на практике
    public static void main(String[] args) {
        int n = 100;
        long [] mem = new long[n+1];
        Arrays.fill(mem, -1); //заполняем созданный массив значением "-1"
        System.out.println(fibonachiRecursia(n, mem));
    }

    public static long fibonachiRecursia(int n, long[] mem) {
        if (mem[n] != -1) { //данное условие не позволяет прозводить повторные расчет (если подобгное было в рекурсии)
            return mem[n];
        }
        if (n <= 1) { //если передали "1" или "0"
            return n;
        }
        long result = fibonachiRecursia(n - 1, mem) + fibonachiRecursia(n - 2, mem);
        //после первого расчета значение поподает в массив "mem[n]"
        // далее если произойдет подобный расчет,
        // то сработает первое условие, где значение не будет перерасчитыватся,
        // а возьмет за основу предедущее расчитанное значение
        mem[n] = result;
        return result;
    }
}
