import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.ScheduledFuture;
@SpringBootTest
public class landa {

    @Autowired
    TaskScheduler taskScheduler;
    private ScheduledFuture future;
    @Test
    public void test1(){
        System.out.println("taskScheduler = " + taskScheduler);
        future = taskScheduler.schedule(()->{
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(LocalDate.now()));
        },new CronTrigger("0/10 * * * * ?"));

    }
}
