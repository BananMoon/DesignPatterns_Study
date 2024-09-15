package moonz.study.designpatterns.creation.factorymethodpattern;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * "Simple Factory Pattern" <br>
 * 여러개의 Creator 구현체가 있는게 아닌, 하나의 구현체가 있고 그 구현체에서 다양한 Product 구현체들을 반환하는 방식<br>
 * - 예 : Java 의 Calendar, NumberFormat<br>
 * Product는 Calendar. ConcreteProduct는 GregorianCalendar, JapaneseImperialCalendar.<br>
 * Creator는 Calendar (추상 클래스). Concrea GregorianCalendar 클래스
 */
@Slf4j
public class JavaExample {
    public static void main(String[] args) {
        // Calendar
        Calendar product = Calendar.getInstance();
        Calendar thProduct = Calendar.getInstance(Locale.forLanguageTag("th-TH-x-lvariant-TH"));
        Calendar jpProduct = Calendar.getInstance(Locale.forLanguageTag("ja-JP-x-lvariant-JP"));

        log.info("기본 Calendar 구현체 : {}", product.getClass());
        log.info("타이완 Calendar 구현체 : {}" , thProduct.getClass());
        log.info("일본 Calendar 구현체 : {}" , jpProduct.getClass());

        // NumberFormat
        NumberFormat defaultInstance = NumberFormat.getNumberInstance();
        NumberFormat compactNumberInstance = NumberFormat.getCompactNumberInstance();

        log.info("NumberFormat 구현체 - DecimalFormat : {}" , defaultInstance.getClass());
        log.info("NumberFormat 구현체 - compactNumberInstance : {}" , compactNumberInstance.getClass());

    }

}
