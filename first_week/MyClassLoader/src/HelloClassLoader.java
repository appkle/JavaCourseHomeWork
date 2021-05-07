import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try{
            Class c = new HelloClassLoader().findClass("Hello");
            Object obj = c.newInstance();
            Method hello = c.getDeclaredMethod("hello",null);
            hello.invoke(obj, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public byte[] readFile(String url) throws IOException {
        FileInputStream input = new FileInputStream(url);
        byte[] buffer = new byte[1024];
        int len = input.read(buffer);
        input.close();
        byte[] newBuffer = new byte[len];
        for(int i=0; i<len; i++){
            newBuffer[i] = (byte) (255 - (buffer[i] & 0xFF));
        }
        return newBuffer;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = null;
        try {
            bytes = readFile("src/Hello.xlass");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
