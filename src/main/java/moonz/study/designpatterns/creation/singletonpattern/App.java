package moonz.study.designpatterns.creation.singletonpattern;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
@Slf4j
public class App {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException, ClassNotFoundException {
        App app = new App();

        app.useReflection();

        app.serializeAndDeserialize();

        app.useBestSettingsWithSerialize();

        app.useBestSettingsWithReflection();
    }

    /**
     * 싱글톤을 깨트리는 리플렉션 기능
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public void useReflection() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Settings settings = Settings.getInstance();

        // reflection
        Constructor<Settings> declaredConstructor = Settings.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Settings settings1 = declaredConstructor.newInstance();

        log.info("리플렉션 이용해 생성했는데, 같은 객체니 ? {} ", settings == settings1);
    }

    /**
     * 싱글톤을 깨트리는 역/직렬화
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serializeAndDeserialize() throws IOException, ClassNotFoundException {
        Settings settings = Settings.getInstance();
        Settings settings1 = null;
        try (ObjectOutput output = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
            output.writeObject(settings);
        }
        try (ObjectInput input = new ObjectInputStream(new FileInputStream("settings.obj"))) {
            settings1 = (Settings) input.readObject();
        }
        log.info("직렬화 후 역직렬화했는데 같은 객체니 ? {} ", settings == settings1);
    }


    public void useBestSettingsWithSerialize() throws IOException, ClassNotFoundException {
        BestSettings settings = BestSettings.INSTANCE;
        BestSettings settings1 = null;

        try (ObjectOutput output = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
            output.writeObject(settings);
        }
        try (ObjectInput input = new ObjectInputStream(new FileInputStream("settings.obj"))) {
            settings1 = (BestSettings) input.readObject();
        }

        log.info("Enum Settings를 직렬화 후 역직렬화했는데 같은 객체니 ? {} ", settings == settings1);
    }

    public void useBestSettingsWithReflection() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        BestSettings settings = BestSettings.INSTANCE;
        BestSettings settings1 = null;

        // reflection
        Constructor<?>[] declaredConstructors = BestSettings.class.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            declaredConstructor.setAccessible(true);
            // Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
            settings1 = (BestSettings) declaredConstructor.newInstance("INSTANCE");
        }

        log.info("리플렉션 이용해 Enum Settings 생성했는데, 같은 객체니 ? {} ", settings == settings1);
    }
}
