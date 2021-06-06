package method2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("student1")
    public Student getStudent(){
        return new Student(123, "xiaoming");
    }

}
