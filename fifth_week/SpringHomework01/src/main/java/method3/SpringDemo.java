package method3;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

//use xbean-spring
public class SpringDemo {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("xbean.xml");
        Student student = (Student) ctx.getBean("student1");
        System.out.println(student.getStudentId());
        System.out.println(student.getName());
    }
}
