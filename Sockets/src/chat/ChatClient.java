package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.Objects;

public class ChatClient extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private String clientName;
  private JTextArea enteredText = new JTextArea(10, 32);
  private JTextField typedText = new JTextField(32);
  private Socket socket;
  private Out out;
  private In in;

  public ChatClient(String screenName, String hostName) {
    try {
      socket = new Socket(hostName, 4444);
      out = new Out(socket);
      in = new In(socket);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    this.clientName = screenName;

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        out.close();
        in.close();
        try {
          socket.close();
        } catch (Exception ioe) {
          ioe.printStackTrace();
        }
      }
    });

    enteredText.setEditable(false);
    enteredText.setBackground(Color.LIGHT_GRAY);
    typedText.addActionListener(this);
    Container content = getContentPane();
    content.add(new JScrollPane(enteredText), BorderLayout.CENTER);
    content.add(typedText, BorderLayout.SOUTH);
    setTitle("Chat Client 1.0: [" + screenName + "]");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    typedText.requestFocusInWindow();
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    out.println("[" + clientName + "]: " + typedText.getText());
    typedText.setText("");
    typedText.requestFocusInWindow();
  }

  public void listen() {
    String s;
    while ((s = in.readLine()) != null) {
      enteredText.insert(s + "\n", enteredText.getText().length());
      enteredText.setCaretPosition(enteredText.getText().length());
    }

    out.close();
    in.close();

    try {
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.err.println("Closed client socket");
  }

  public static void main(String[] args) {
    ChatClient client = new ChatClient(args[0], args[1]);
    client.listen();
  }
}
