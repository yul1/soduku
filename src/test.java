import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class test {
	
	public static void updateProgress() {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					//a.setText("" + b);// TODO Auto-generated method stub
					System.out.println("*");
				}
				});
			Thread.sleep(100);
		}catch (InterruptedException e) {		
		}
	}
	
	public static void main(String[]args) {
		updateProgress();
	}

}
