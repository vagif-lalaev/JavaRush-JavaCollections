import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regulyarProverka {
    public static void main(String[] args) {
        String s = "C:\\Users\\Вагиф\\YandexDisk\\JAVA\\Проэкты от JavaRush\\Проэкты от JavaRush (полностью рабочая)\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3109\\properties.xml";
        Pattern pp = Pattern.compile("\\.xml\\b");
        Matcher mm = pp.matcher(s);
        String xx = null;
        if (mm.find()) {
            xx = mm.group();
        }
        System.out.println(xx);
    }
}
