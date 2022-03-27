import com.gu.dto.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamMapping {
    @Test
    public void createStream(){
        //1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream(); //获取一个顺序流
        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);

        //4. 创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }
    /*
    * 全局变量
    * */
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.上班),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.工作),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.下班),
            new Employee(106, "赵六", 8, 7777.77, Employee.Status.下班),
            new Employee(107, "张三", 8, 7777.77, Employee.Status.上班),
            new Employee(108, "赵六", 8, 7777.77, Employee.Status.下班),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.上班)
    );
    @Test
    public void collect(){

        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(list);//[李四, 张三, 王五, 赵六, 张三, 赵六, 田七]
        System.out.println("----------------------------------");
        /*
        * 获取相同年龄的姓名
        *
        * */

        Map<Integer, Set<String>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getAge,
                        Collectors.mapping(Employee::getName,
                                Collectors.toSet())));
        System.out.println(collect); // {18=[张三], 38=[田七], 8=[张三, 赵六], 59=[李四], 28=[王五]}


    }
    @Test
    public void end(){
        /*allMatch*/
        boolean b = emps.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.上班));
        System.out.println("是否全部上班 = " + b); //是否全部上班 = false
        /*anyMatch*/
        boolean b1 = emps.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.上班));
        System.out.println("是否有人上班 = " + b1); //是否有人上班 = true
        /*max*/
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(max.get()); //9999.99
        /*reduce*/
        Optional<Integer> reduce = emps.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum);
        System.out.println(reduce.get()); //167

    }

    @Test
    public void sorted(){
        /*自然顺序排序*/
        List<Integer> collect = emps.stream()
                .map(Employee::getAge)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect); //[8, 8, 8, 18, 28, 38, 59]
        System.out.println("------------------------------------");
        /*比较器顺序排序*/
        List<Employee> collect1 = emps.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).collect(Collectors.toList());
        System.out.println(collect1);

    }
    @Test
    public void flatmap(){
        /*
         * flatmap 映射
         * parameter FUNCTION
         * 相当于 将list中的每个元素取出来 放到另一个list中
         * */
        List<List<String>> list = Arrays.asList(
                Arrays.asList("1", "2", "3", "4", "5"),
                Arrays.asList("6", "7", "8", "9", "10")
        );
        List<String> collect1 = list.stream()
                .flatMap(s->s.stream()) //s相当于遍历的 item
                .collect(Collectors.toList());
        System.out.println(collect1);  //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        System.out.println("---------------------------------------------");
        List<String> collect = list.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(collect);  //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    }
    @Test
    public void map(){
        /*
        * map 映射
        * parameter FUNCTION
        * */
        List<String> collect1 = emps.stream()
                .map(employee -> employee.getName())
                .collect(Collectors.toList()); //将结果转成 list
        collect1.forEach(System.out::println);
        System.out.println("-------------------------------------------");
        List<String> collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect2.forEach(System.out::println); //将结果转成 list

    }

}
