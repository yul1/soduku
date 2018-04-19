import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;

public class GUI implements ActionListener {
	/**
	 * @param args
	 * @author Henry
	 * @author temandr
	 */
	
	JButton submit = new JButton("Submit");
	JLabel CorrOrWrong = new JLabel("---");
	private static int[][] array;
	private static int[][] sudoku;
	private static JTextField[][] index = new JTextField[9][9];
	
	public GUI (){
		JFrame frame = new JFrame("Sudoku");
		//frame.getContentPane().add(draw);
		frame.setSize(500,500);
		submit.addActionListener(this);
		JPanel board = new JPanel();
		JPanel panel = new JPanel();
		board.setLayout(new GridLayout (9,9));
		for (int i =0;i<9;i++)
			for (int j=0;j<9;j++){
				index[i][j]=new JTextField(1);
				index[i][j].setText(""+sudoku[i][j]);
				if (sudoku[i][j]!=0)
					index[i][j].setEditable(false);
				board.add(index[i][j]);
			}
		frame.getContentPane().add(board);
		frame.getContentPane().add(submit,"South");
		frame.getContentPane().add(CorrOrWrong, "North");
	    frame.setVisible(true);
	}
	
	/**
	 * edited by temandr
	 */
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == submit)
		{
			int[][] temp=submit();
			write(temp);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException r) {
				r.printStackTrace();
			}
			if(testSudoku()) CorrOrWrong.setText("Correct!");
			else CorrOrWrong.setText("Wrong!");
		}
	}
	
	/**
	 * @author temandr
	 */
	public boolean testSudoku() {
		return SudokuChecker.main(null);
	}
	
	public static int[][] submit(){
		int[][]result = new int[9][9];
		for (int i =0;i<9;i++)
			for (int j=0;j<9;j++){
				try {
				result[i][j]=Integer.parseInt(index[i][j].getText());
				}catch (Exception e){
					result[i][j]=-1;
				}
			}
		return result;
	}
	
	public static int[][] readIn(){
		int[][] array = new int [9][9];
		int x=0;
		try
	      {
	         BufferedReader br = new BufferedReader(new FileReader("sudoku.txt"));
	         String s;
	         while ((s = br.readLine())!= null)
	         {
	            String[] parts = s.split(" ");
	            for (int y=0;y<9;y++)
	            	array[x][y]=Integer.parseInt(parts[y]);
	            x++;
	         }
	         br.close();
	      } catch (IOException e) 
			{
	    	  System.out.println("ERROR");
			}
		return array;
	}
	
	public static void write(int[][] array){
		try
	      {
	         PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
	         for (int i=0;i<9;i++)
	         {
	        	String s="";
	        	for (int j=0;j<9;j++)
	        		s+=array[i][j]+" ";
	            pw.println(s);
	         }
	         pw.close();
	      } catch (IOException e)
	         {
	            System.out.println("The following error occurred " + e);
	         }
	}
	
	public static int[][] generate(){
		int[][] temp = new int [9][9];
		for (int i =0;i<9;i++)
			for (int j=0;j<9;j++){
				temp[i][j]=array[i][j];
			}
		int num=41;
		while (num>0){
			int x=(int)(Math.random()*9);
			int y=(int)(Math.random()*9);
			if (temp[x][y]!=0){
				temp[x][y]=0;
				num--;
			}
		}
		return temp;
	}
	
	/**
	 * edited by temandr
	 */
	public static void main(String[] args) {
		//Create the sudoku
		//SudokuGenerator SDGN = new SudokuGenerator();
		SudokuGenerator.main(null);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		array=readIn();
		sudoku=generate();
		for (int i = 0; i<9;i++){
			for (int j=0;j<9;j++)
				System.out.print(array[i][j] + " ");
			System.out.println();
		}
		new GUI();
	}

}
