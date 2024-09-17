package moonz.study.designpatterns.creation.factorymethodpattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * - Creator 인터페이스가 BeanFactory. ConcreteCreator 클래스가 ClassPathXmlApplicationContext, AnnotationConfigApplicationContext<br>
 * - Product 인터페이스가 Object. ConocreteProduct 클래스가 String (변수 beanByXmlConfig, beanByAnnotationConfig)
 */
@Slf4j
public class SpringBeanFactoryExample {

    public static void main(String[] args) {
        BeanFactory xmlApplicationContext = new ClassPathXmlApplicationContext("config.xml");
        String beanByXmlConfig1 = xmlApplicationContext.getBean("beanByXmlConfig", String.class);
        String beanByXmlConfig2 = xmlApplicationContext.getBean("beanByXmlConfig", String.class);

        log.info("빈이 'beanByXmlConfig' 인가? : {}", "beanByXmlConfig".equals(beanByXmlConfig1));
        log.info("두번 호출했을 때 동일한가? : {}", beanByXmlConfig1 == beanByXmlConfig2);

        BeanFactory annotationApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        String beanByAnnotationConfig1 = annotationApplicationContext.getBean("beanByAnnotationConfig", String.class);
        String beanByAnnotationConfig2 = annotationApplicationContext.getBean("beanByAnnotationConfig", String.class);

        log.info("빈이 'beanByAnnotationConfig' 인가? : {}", "beanByAnnotationConfig".equals(beanByAnnotationConfig1));
        log.info("두번 호출했을 때 동일한가? : {}", beanByAnnotationConfig1 == beanByAnnotationConfig2);

    }
}
