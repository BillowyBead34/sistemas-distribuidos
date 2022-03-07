import java.io.*;
import java.net.*;

public class Connect extends Thread {
  private Socket client = null;
  private ObjectInputStream ois = null;
  private ObjectOutputStream oos = null;

  public Connect() {
  }

  public Connect(Socket clientSocket) {
    client = clientSocket;
    try {
      ois = new ObjectInputStream(client.getInputStream());
      oos = new ObjectOutputStream(client.getOutputStream());
    } catch (Exception e1) {
      try {
        client.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return;
    }
    this.start();
  }

  @Override
  public void run() {
    SerializedObject x = null;
    SerializedObject y = null;
    int[] dataset1 = new int[7];
    int[] dataset2 = new int[7];
    int[] result = new int[7];
    try {
      x = (SerializedObject) ois.readObject();
      y = (SerializedObject) ois.readObject();
      dataset1 = x.getArray();
      dataset2 = y.getArray();
      for (int i = 0; i < dataset1.length; i++) {
        result[i] = dataset1[i] * dataset2[i];
      }
      SerializedObject output = new SerializedObject();
      output.setArray(result);
      oos.writeObject(output);
      oos.flush();
      ois.close();
      oos.close();
      client.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
