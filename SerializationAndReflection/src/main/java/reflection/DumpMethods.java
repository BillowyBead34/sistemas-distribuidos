package reflection;

import java.lang.reflect.*;

public class DumpMethods {

  public static void main(String[] args) {
    try {
      Class<?> c = Class.forName("java.io.ObjectOutput");
      Method[] m = c.getDeclaredMethods();
      for (Method method : m) {
        System.out.println(method.toString());
      }
    } catch (Throwable e) {
      System.err.println(e);
    }
  }
}