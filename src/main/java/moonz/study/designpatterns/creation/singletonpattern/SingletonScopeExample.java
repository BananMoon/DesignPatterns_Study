package moonz.study.designpatterns.creation.singletonpattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class SingletonScopeExample {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello = applicationContext.getBean("hello", String.class);
        String hello1 = applicationContext.getBean("hello", String.class);

        log.info("ApplicationContext에서 빈 조회 시 동일한 객체인가? : {}", hello == hello1);
    }

    @Configuration
    public static class SpringConfig {
        @Bean
        public String hello() {
            return "hello";
        }
    }

}
