import com.gu.dto.Trader;
import com.gu.dto.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestsTreamAPI {
    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
    /* 去重*/
    @Test
    public void test12(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("11","11");


    }

    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test1(){
        transactions.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted((x,y)->Integer.compare(x.getValue(),y.getValue()))
                .forEach(System.out::println);
    }
    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test2(){
        Map<String, List<String>> collect = transactions.stream()
                .map(Transaction::getTrader)
                .collect(Collectors.groupingBy(Trader::getName,
                        Collectors.mapping(Trader::getCity,
                                Collectors.toList())));
        System.out.println(collect);
        //{Brian=[Cambridge], Raoul=[Cambridge, Cambridge], Alan=[Cambridge], Mario=[Milan, Milan]}
    }
    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test3(){
        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
        //Trader(name=Alan, city=Cambridge)
        //Trader(name=Brian, city=Cambridge)
        //Trader(name=Raoul, city=Cambridge)
    }

    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test5(){
        boolean bl = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equals("Milan"));

        System.out.println(bl);
    }

    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6(){
        Optional<Integer> sum = transactions.stream()
                .filter((e) -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(sum.get());
    }


    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test7(){
        Optional<Integer> max = transactions.stream()
                .map((t) -> t.getValue())
                .max(Integer::compare);

        System.out.println(max.get());
    }

    //8. 找到交易额最小的交易
    @Test
    public void test8(){
        Optional<Transaction> op = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));

        System.out.println(op.get());
    }

    private class BeanUtil {
    }
}
