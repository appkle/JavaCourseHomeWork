package method2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDemo {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
        Student student = (Student) ctx.getBean("student1");
        student.print();
    }

}
