import com.gu.interfaces.Test01;
import org.junit.Test;

public class tett {
    @Test
    public void test(){
        Test01 test01 = System.out::println;

        test01.test("hello word");
    }
}
