import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class landa {
    @Test
    public void test1(){
        Thread thread = new Thread(() -> {
            System.out.println("test1");
        });
        thread.start();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        new Thread(runnable).start();

    }
}
