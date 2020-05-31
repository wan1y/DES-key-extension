package jiemian;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DesPc extends JFrame {

	private JPanel contentPane;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_6;
	private JTextField textField_7;
	private int[] LFT={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	public int k=0;
	public int xiayige=7;
	public int s_key=48;
	PC pc= new PC();
	hexStr2Byte h2b=new hexStr2Byte();
	Byte2hexStr b2h=new Byte2hexStr();	
	private JTextField textField_8;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesPc frame = new DesPc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public static String randomHexString(int len)  {
		try {
			StringBuffer result = new StringBuffer();
			for(int i=0;i<len;i++) {
				result.append(Integer.toHexString(new Random().nextInt(16)));
			}
			return result.toString().toUpperCase();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return null;
	}

	public DesPc() {
		setTitle("DES\u5BC6\u94A5\u6269\u5C55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JButton btnNewButton = new JButton("\u968F\u673A\u5BC6\u94A5");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.setBounds(520, 16, 88, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k=1;
				xiayige=7;
				s_key=48;
				String a=randomHexString(16);
				textField.setText(a);
				textField_1.setText(String.valueOf(k));// 子密钥轮数
				textField_3.setText("1");//左移位数
				int[] k_bit=new int[64]; 
				int[] s_bit=new int[48];
				char[] key = a.toCharArray();//随机密钥变成char数组
				char[] key_56=new char[14];
				char[] key_1=new char[7];
				char[] key_2=new char[7];
				char[] key_3=new char[7];
				char[] key_4=new char[7];
				char[] key_48=new char[12];
				
				k_bit=h2b.h2bdeal(key,64);//存放64位二进制
				pc.deal(k_bit);//PC-1
				key_56=b2h.b2hdeal(pc.k_new_bit, 56);//PC-1压缩密钥
				for(int i=0;i<48;i++) {
					s_bit[i]=pc.sub_key[i];
				}
				key_48=b2h.b2hdeal(s_bit, 48);//得到子密钥十六进制
				String str_48 = new String(key_48);
				String str_56 = new String(key_56);
				for(int i=0;i<7;i++)
				{
					key_1[i]=pc.L[i];
					key_2[i]=pc.R[i];
					key_3[i]=pc.L[i+7];
					key_4[i]=pc.R[i+7];
				}
				String strl = new String(key_1);
				String strr = new String(key_2);
				String strl1 = new String(key_3);
				String strr1 = new String(key_4);
				textField_2.setText(str_56);
				textField_4.setText(strl1);
				textField_5.setText(strr1);
				textField_6.setText(strl);
				textField_7.setText(strr);
				textField_8.setText(str_48);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u5B50\u5BC6\u94A5\u5E8F\u53F7i:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 9, 73, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("56\u4F4D\u538B\u7F29\u5BC6\u94A5:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(274, 95, 79, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PC-2");
		lblNewLabel_2.setBounds(267, 226, 52, 23);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 12));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5B50\u5BC6\u94A5:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 230, 42, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PC-1");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(425, 62, 58, 15);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("\u4E0A\u4E00\u4E2A");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton_1.setBounds(344, 340, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(k==0)
				{
					JOptionPane.showMessageDialog(null, "未选择随机密钥", "警告",JOptionPane.WARNING_MESSAGE);
				}		
				if(k==1)
				{
					JOptionPane.showMessageDialog(null, "没有上一个子密钥", "警告",JOptionPane.WARNING_MESSAGE);
				}
				if(1<k&&k<17)
				{
					k--;
					int n=0;
					int p=0;
					char[] key_12=new char[7];
					char[] key_13=new char[7];
					char[] key_14=new char[7];
					char[] key_15=new char[7];
					char[] key_48_2=new char[12];
					int[] s_bit2=new int[48];
					textField_3.setText(String.valueOf(LFT[k-1]));
					textField_1.setText(String.valueOf(k));
					xiayige=xiayige-14;
					s_key=s_key-96;
					while(p!=48) {
						s_bit2[p]=pc.sub_key[s_key];
						p++;
						s_key++;
					}
					key_48_2=b2h.b2hdeal(s_bit2, 48);
					String str_48_2 = new String(key_48_2);
					while(n!=7)
					{
						key_12[n]=pc.L[xiayige];
						key_13[n]=pc.R[xiayige];
						key_14[n]=pc.L[xiayige+7];
						key_15[n]=pc.R[xiayige+7];
						n++;
						xiayige++;
					}
					String strl4 = new String(key_12);
					String strr4 = new String(key_13);
					String strl5 = new String(key_14);
					String strr5 = new String(key_15);
					textField_4.setText(strl5);
					textField_5.setText(strr5);
					textField_6.setText(strl4);
					textField_7.setText(strr4);
					textField_8.setText(str_48_2);
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u4E0B\u4E00\u4E2A");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton_2.setBounds(486, 340, 97, 23);
		//��һ����ť������
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(k==0)
				{
					JOptionPane.showMessageDialog(null, "未选择随机密钥", "警告",JOptionPane.WARNING_MESSAGE);
				}	
				if(k==16)
				{
					JOptionPane.showMessageDialog(null, "DES只有16轮子密钥", "警告",JOptionPane.WARNING_MESSAGE);
				}
				if(0<k&&k<16)
				{
					k++;
					int n=0;
					int p=0;
					char[] key_8=new char[7];
					char[] key_9=new char[7];
					char[] key_10=new char[7];
					char[] key_11=new char[7];
					char[] key_48_1=new char[12];
					int[] s_bit1=new int[48];

					textField_3.setText(String.valueOf(LFT[k-1]));
					textField_1.setText(String.valueOf(k));
					while(p!=48) {
						s_bit1[p]=pc.sub_key[s_key];
						p++;
						s_key++;
					}
					key_48_1=b2h.b2hdeal(s_bit1, 48);
					String str_48_1 = new String(key_48_1);
					while(n!=7)
					{
						key_8[n]=pc.L[xiayige];
						key_9[n]=pc.R[xiayige];
						if(k<16)
						{
							key_10[n]=pc.L[xiayige+7];
							key_11[n]=pc.R[xiayige+7];
						}
						n++;
						xiayige++;
					}
					String strl2 = new String(key_8);
					String strr2 = new String(key_9);
					String strl3 = new String(key_10);
					String strr3 = new String(key_11);
					textField_4.setText(strl3);
					textField_5.setText(strr3);
					textField_6.setText(strl2);
					textField_7.setText(strr2);
					textField_8.setText(str_48_1);
				}
			}
		});
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("64\u4F4D\u5BC6\u94A5:");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(295, 19, 58, 17);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblCi = new JLabel("Ci+1:");
		lblCi.setFont(new Font("宋体", Font.PLAIN, 12));
		lblCi.setBounds(310, 312, 32, 15);
		contentPane.add(lblCi);
		
		JLabel lblNewLabel_6 = new JLabel("Di+1:");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(451, 312, 32, 15);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u5DE6\u79FB");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(383, 197, 58, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("\u53F3\u79FB");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(515, 197, 58, 15);
		contentPane.add(lblNewLabel_8);
		
		textField_3 = new JTextField();
		textField_3.setBounds(260, 155, 32, 21);
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setEditable(false);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("\u5DE6\u79FB\u4F4D\u6570:");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(192, 158, 58, 15);
		contentPane.add(lblNewLabel_9);
		
		textField_4 = new JTextField();
		textField_4.setBounds(344, 309, 97, 21);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setEditable(false);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(486, 309, 97, 21);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setEditable(false);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 17, 21, 21);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(360, 17, 159, 21);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(360, 92, 159, 21);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setEditable(false);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(344, 155, 97, 21);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setEditable(false);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(486, 155, 97, 21);
		textField_7.setBackground(Color.LIGHT_GRAY);
		textField_7.setEditable(false);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("C_i:");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(310, 158, 32, 15);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("D_i:");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(451, 158, 26, 15);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("\u2193");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(383, 130, 58, 15);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("\u2193");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(502, 130, 58, 15);
		contentPane.add(lblNewLabel_13);
		
		JLabel label = new JLabel("\u2193");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(360, 230, 58, 15);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label);
		
		JLabel lblNewLabel_14 = new JLabel("C_i");
		lblNewLabel_14.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_14.setBounds(383, 255, 58, 15);
		contentPane.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("D_i");
		lblNewLabel_15.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_15.setBounds(520, 255, 58, 15);
		contentPane.add(lblNewLabel_15);
		
		JLabel label_1 = new JLabel("\u2193");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(502, 230, 58, 15);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u2193");
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setBounds(360, 284, 58, 15);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u2193");
		label_3.setFont(new Font("宋体", Font.PLAIN, 12));
		label_3.setBounds(502, 284, 58, 15);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label_3);
		
		textField_8 = new JTextField();
		textField_8.setBounds(62, 227, 144, 21);
		textField_8.setBackground(Color.LIGHT_GRAY);
		textField_8.setEditable(false);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel label_4 = new JLabel("\u2193");
		label_4.setFont(new Font("宋体", Font.PLAIN, 12));
		label_4.setBounds(435, 48, 58, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u2193");
		label_5.setFont(new Font("宋体", Font.PLAIN, 12));
		label_5.setBounds(435, 73, 58, 15);
		contentPane.add(label_5);
		
		JLabel lblNewLabel_16 = new JLabel("\u2190");
		lblNewLabel_16.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_16.setBounds(229, 230, 21, 15);
		contentPane.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("\u2190");
		lblNewLabel_17.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_17.setBounds(327, 230, 26, 15);
		contentPane.add(lblNewLabel_17);
	}
}
