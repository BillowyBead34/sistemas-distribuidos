import java.io.*;

public class SerializedObject implements Serializable {
  private int[] array = null;

  public SerializedObject() {
  }

  public int[] getArray() {
    return array;
  }

  public void setArray(int[] array) {
    this.array = array;
  }
}
