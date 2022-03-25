import com.gu.interfaces.Test01;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.*;

public class FiveFunction {
    @Test
    public void test(){
        /*
        * Consumer 消费型
        * parameter T
        * return null
        * */
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello word");
        
        /*
        * Supplier 生产型
        * parameter null
        * return T
        * */
        Date date = new Date();
        Supplier<Long> stringSupplier = date::getTime;
        System.out.println("stringSupplier.get() = " + stringSupplier.get());
        /*
         * Function
         * parameter
         * return
         * */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Function<Date,String> function = format::format;
        System.out.println(function.apply(new Date()));

        /*
         * Function
         * parameter <T, U>
         * return bool
         * */
        BiPredicate<String,String> predicate = String::equals;
        System.out.println(predicate.test("aa","aa"));
    }
}
