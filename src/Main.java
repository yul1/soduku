import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Main extends JPanel {
  JProgressBar jProgressBar1 = new JProgressBar(0, 100);

  public Main() {
    addContainerListener(new ContainerAdapter() {
      @Override
      public void componentAdded(ContainerEvent e) {
        if (jProgressBar1 == e.getChild()) {
          new Thread(new Task()).start();
        }
      }
    });
    jProgressBar1.setName("");
    jProgressBar1.setValue(0);
    jProgressBar1.setStringPainted(true);
    add(jProgressBar1);
  }

  private class Task implements Runnable {
    long SLEEP_TIME = 100;

    public void runrun() {
      for (int i = 0; i <= 100; i++) {
        final int progress = i;
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            jProgressBar1.setValue(progress);
          }
        });
        try {
          Thread.sleep(SLEEP_TIME);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
  }

  public static void main(String[] args) {
    Main mainPanel = new Main();

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(mainPanel);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }
}

