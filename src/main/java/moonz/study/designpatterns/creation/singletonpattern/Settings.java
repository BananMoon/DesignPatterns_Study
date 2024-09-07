package moonz.study.designpatterns.creation.singletonpattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 설정 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)    //  기본 생성자 접근 불가.
public class Settings implements Serializable {

    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    public static Settings getInstance() {
        return SettingsHolder.INSTANCE;
    }

    // 역직렬화 시 사용되는 메서드 시그니처를 Override하여, 역직렬화 시 이 메서드가 수행되어 싱글톤 패턴을 유지시킬 수 있다.
    protected Object readResolve() {
        return SettingsHolder.INSTANCE;
    }

}
