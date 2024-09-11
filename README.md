# DesignPatterns_Study
예시를 통해 디자인 패턴을 익혀보는 공간의 프로젝트

## Creational Patterns 생성 패턴

### 1) Singleton Pattern 싱글톤 패턴
>필요한 상황<br>
시스템 런타임, 환경 세팅에 대한 정보 등, 인스턴스가 여러개 일 때 문제가 생길 수 있는
경우가 있다. 인스턴스를 오직 한개만 만들어 제공하는 클래스가 필요하다.

예제 코드
- `/creation/singletonpattern/Settings` 클래스

멀티 쓰레드 환경이 기본이다.
#### 방법 1. 메서드에 `synchronized` 키워드 적용하기
* null 체크 통해 신규 or 기존 인스턴스 반환.
* Issue : 한 스레드에서 null 체크를 통과한 시점에 다른 스레드에서 17 라인을 수행하며 인스턴스를 생성해버리면, 싱글톤이 깨진다.<br>
  -> 멀티 쓰레드 환경에서 안전하지 않은 모습.
```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // getInstance() 외 방식으로 인스턴스 생성되지 않기 위함.
public class Settings {
    private static Settings instance;

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
```

#### 방법2. Eager Intialization 이른 초기화 적용하기.
- 해당 방법은 초기화 작업이 부담되지 않은 객체에만 가능하다.
- 클래스 초기화 시점에 필드들도 초기화되기 때문에 멀티 스레드환경에도 안전하다.
- 이럴 경우 `final`로 선언해주는 것이 좋다.
```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {
    private static final Settings INSTANCE = new Settings();

    public static Settings getInstance() {
        return INSTANCE;
    }
}
```

#### 방법3. double checked locking
- 첫번째 스레드가 첫번째 Null 체크에서 통과되서 들어가면 두번째 스레드는 첫번째 Null 체크에서 통과되어도 synchronized 블록을 대기하게 된다.
- 첫번째 스레드가 인스턴스를 생성하고 빠져나가면, 두번째 스레드가 두번째 null 체크했을 때 인스턴스가 생성되어 있으므로 인스턴스를 생성하지 않고 나온다.
- 이미 인스턴스가 생성되었다면 아예 `synchronized` 블록에 들어가지도 않기 때문에 동기화 메커니즘을 쓰지 않게 된다.
- 일부 극히 인스턴스가 없을 때! 여러 스레드가 동시에 접근할 때에만 `synchronized` 블록에 접근하게 되기 때문에 성능에 유리해진다.
- `volatile` 키워드가 매우 복잡한 개념임.
```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {
    private static volatile Settings instance;
    
    public static Settings getInstanceWithDoubleCheckedLocking() {
        if (instance == null) {
            synchronized (Settings.class) {
                if (instance == null) {
                    instance = new Settings();
                }
            }
        }
        return instance;
    }
}
```

#### 방법4. static inner class 사용하기
- 이너 클래스를 두고, 그 내부에 Settings 인스턴스를 이른 초기화되도록 한다.
- `Settings` 클래스에서 lazy loading으로 초기화될 때 이너 클래스가 호출된다.
```java
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();

        public static Settings getInstance() {
            return INSTANCE;
        }
    }
}
```
But...
싱글톤 패턴으로 객체를 선언했더라도, 이 방법을 깨트릴 수 있다. (이탈 행위)
1) 리플렉션을 사용해 다른 객체로 생성할 수 있다.
2) 직렬화, 역직렬화를 사용하는 클래스에 대해 파일에 객체를 쓰고, 다시 파일을 읽어서 객체로 만들면, 다른 객체로 생성된다.


#### (리플렉션도 뭇 뚫는) 제일 안전한 방법 !! Enum class
- enum은 리플렉션에서 enum의 인스턴스를 만들지 못하도록 막아놨기 때문에 유일한 인스턴스가 보장된다.
  - `IllegalArgumentException` 발생하며 enum 객체는 리플렉션으로 생성하지 못한다는 에러 발생.
- 또다른 프로퍼티도 쉽게 세팅할 수 있다.
- 단, 클래스가 로딩되는 순간 초기화 되어버린다.
- Enum 타입으로 선언하면 기본적으로 `Enum` 이라는 클래스를 자동 상속받게 되는데, 여기에 `Serializable`을 구현하고 있기 때문에 자동으로 역/직렬화가 된다.
```java
public enum Settings {
    INSTANCE;
}
```

#### 복습
1. private 메서드와 static 메서드를 사용하는 것의 단점?
2. enum을 사용해 싱글톤 패턴을 구현하는 것의 장/단점?
  - 이른 초기화가 된다. 상속이 불가하다.
  - 상속을 해야한다거나 lazy loading을 해야 한다면 inner class를 이용하는 방법을 선택해야할 것이다.

### 예제
- Java `Runtime` 클래스 (애플리케이션이 실행되고 있는 문맥 정보를 알수 있는 클래스)
- **Singleton Scope**
  - Application Context 안에서 유일한 인스턴스로 관리할 수 있개 해주는 Scope.
  - Spring 을 사용할 때에는 bean으로 등록함으로써 하나의 인스턴스로 관리되도록 하고 있다.

### 2) Factory method Pattern 팩토리 메서드 패턴
목적 : 인스턴스를 생성하는 책임을 구체적인 클래스(concrete class) 가 아닌 추상적인 인터페이스의 메서드로 감싸고자 함.

방식 : 추상화되어있는 팩토리를 만든다.
- 팩토리 인터페이스 안에 구현부 (default method) 및 하위클래스에서 작성해야하는 메서드의 경우 구현부는 없는 형태로 생성한다. -> `Creator` 인터페이스
- 그리고 구현체에서 인터페이스를 구현하면서 그 구현체에 맞게 메서드들을 구현한다.   -> `ConcreteCreator` 클래스
- 생성 대상이 되는 객체도 인터페이스로 생성하고, 이를 구현하도록 한다.   -> `Product` 인터페이스와 `ConcreteProduct` 클래스
- 결국 팩토리 인터페이스의 구현체가 인터페이스 객체의 구현체인 객체를 생성하게 된다.  -> `ConcreteCreator` 클래스가 `ConcreteProduct` 클래스를 생성한다.

예제 : Ship, ShipFactory
제품 군에도, 생성 클래스에도 게층 구조로 생성한다. (추상 클래스 하위에 상속 클래스를 만들거나, 인터페이스 하위에 구현체를 만든다.)


### 복습
1. 팩토리 메서드 패턴을 적용했을 때 단점.
- 각자의 역할을 나누다보니 구현체만 존재하던 것보다는 파일 갯수가 많아진다.

2. 팩토리 메서드 패턴을 적용했을 때 장점.
- 비슷한 종류의 새 인스턴스를 생성할 때 기존 코드를 건들이지 않고 생성할 수 있어 확장성이 좋다.
  - 코드가 간결해지고, 복잡해지지 않을 수 있다.
- product와 creator 간 느슨하게 연결되어 있다. (느슨한 결합)

3. 확장(extension)에 열려있고 변경(modification)에 닫혀 있는 객 체지향 원칙을 설명하세요
- 변경에 닫혀 있다 -> 새로운 기능을 추가할 때 기존 클래스에 변경이 발생하지 않아야 한다.
- 확장에 열려 있다 -> 새로운 기능을 추가할 때 쉽게 기능 확장이 가능하다. 단 기존 코드 변경 없이.

4. 자바 8에 추가된 default 메서드에 대해 설명하세요.
- 인터페이스에 만들 수 있는 기본 구현체
- 인터페이스를 상속받은 또 다른 인터페이스, 인터페이스를 구현하는 클래스가 default 메서드를 사용할 수 있다.
  - 덕분에 추상 클래스를 사용할 일이 적어졌다.
- 자바 9에서는 인터페이스에 private 메서드 선언 가능한 기능이 추가되었음.

### 실무에서는?
