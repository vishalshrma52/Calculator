import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Calculator2 
{
	JFrame frame=new JFrame("Calculator");
	JTextField textbox=new JTextField("0");
	JButton[] buttons=new JButton[20];
	JPanel panel=new JPanel();
	JLabel label=new JLabel();
	public Calculator2()
	{
		frame.setSize(400,490);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);//This method will terminate JVM on close click of frame
		frame.setLayout(null);
		addTextBox();
		frame.setVisible(true);
	}
	private void addTextBox()
	{
		label.setFont(new Font("arial",0,18));
		label.setBounds(20,10,360,45);
		label.setHorizontalAlignment(4);
		frame.add(label);
		textbox.setBounds(20,50,360,45);
		frame.add(textbox);
		textbox.setFont(new Font("arial",0,25));
		textbox.setHorizontalAlignment(4);
		textbox.setEditable(false);
		textbox.setBackground(Color.white);
		textbox.setBorder(BorderFactory.createLineBorder(Color.black,1));
		addButtons();
	}
	private void addButtons()
	{
		panel.setBounds(20,125,360,300);
		//panel.setBackground(Color.yellow);
		frame.add(panel);
		panel.setLayout(new GridLayout(5,4,5,5));
		Font font=new Font("arial",0,20);
		String[] str= {"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		CalListener listener=new CalListener();
		for(int i=0;i<20;i++)
		{
			buttons[i]=new JButton(str[i]);
			buttons[i].addActionListener(listener);
			panel.add(buttons[i]);
			buttons[i].setFont(font);
			if(i==3 || i==7 || i==11 || i==15 || i==19)
				buttons[i].setForeground(Color.red);
			else
				buttons[i].setForeground(Color.blue);
		}
		buttons[17].setFont(new Font("arial",1,30));
	}
	class CalListener implements ActionListener
	{
		private float oldNum;
		private int flag=0;
		private String op=null;
		public void actionPerformed(ActionEvent evt) 
		{
			JButton bb=(JButton)evt.getSource();
			String buttonText=bb.getText();
			String textboxText=textbox.getText();
			if(bb==buttons[17])
			{
				int n=textboxText.indexOf('.');
				if(n!=-1)
					return;
			}
			if(bb==buttons[7] || bb==buttons[11] || bb==buttons[15] || bb==buttons[19])
			{
				if(op!=null)
					cal();
				oldNum=Float.parseFloat(textbox.getText());
				flag=1;
				op=buttonText;
				label.setText(textbox.getText()+" "+op);
			}
			if(bb==buttons[4] || bb==buttons[5] || bb==buttons[6] || bb==buttons[8] || bb==buttons[9] || bb==buttons[10] || bb==buttons[12] || bb==buttons[13] || bb==buttons[14] || bb==buttons[16] || bb==buttons[17])
			{
				if(textboxText.equals("0") || flag==1)
				{
					textbox.setText(buttonText);
					flag=0;
				}
				else
				{
					textbox.setText(textboxText+buttonText);
				}
			}
			if(bb==buttons[18])//equal button
			{
				label.setText(label.getText()+" "+textbox.getText()+" =");
				cal();
			}
			if(bb==buttons[2])//C button
			{
				textbox.setText("0");
				oldNum=0;
				flag=0;
				op=null;
				label.setText("");
			}
			if(bb==buttons[1])//CE button
			{
				textbox.setText("0");
			}
			if(bb==buttons[0])//Back button
			{
				String str=textbox.getText();
				int num=Integer.parseInt(str);
				if(num==0)
					return;
				num=num/10;
				textbox.setText(String.valueOf(num));
				
			}
		}//end of actionPerformed() method
		private void cal()
		{
			float newNum=Float.parseFloat(textbox.getText());
			if(op.equals("+"))
			{
				float res=oldNum+newNum;
				setResult(res);
			}
			else if(op.equals("-"))
			{
				float res=oldNum-newNum;
				setResult(res);
			}
			else if(op.equals("*"))
			{
				float res=oldNum*newNum;
				setResult(res);
			}
			else if(op.equals("/"))
			{
				float res=oldNum/newNum;
				setResult(res);
			}
		}
		private void setResult(float res)
		{
			int x=(int)res;
			if((float)x==res)
				textbox.setText(String.valueOf(x));
			else
				textbox.setText(String.valueOf(res));
		}
	}//end of CalListener class
	public static void main(String[] args) 
	{
		new Calculator2();
	}
}
