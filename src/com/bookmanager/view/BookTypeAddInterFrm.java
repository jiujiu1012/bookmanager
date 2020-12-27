package com.bookmanager.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookmanager.dao.BookTypeDao;
import com.bookmanager.model.BookType;
import com.bookmanager.util.DbUtil;
import com.bookmanager.util.StringUtil;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BookTypeAddInterFrm extends JInternalFrame {
	private JTextField bookTypeNameTxt;
    private JTextArea bookTypeDescTxt; 
    
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao=new BookTypeDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrm frame = new BookTypeAddInterFrm();
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
	public BookTypeAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		setBounds(100, 100, 1219, 658);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		
		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {//ͼ��������ӵġ����ӡ���ť
			public void actionPerformed(ActionEvent e) {
				bookTypeAddActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/images/booktypeadd.png")));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {//ͼ��������ӵġ����á���ť
			public void actionPerformed(ActionEvent e) {//��������д�������������װ������һ������
				resetValueActionPerformed(e);//�����¼�
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/images/edit.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(267, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, Alignment.TRAILING)
						.addComponent(lblNewLabel, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(bookTypeDescTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
						.addComponent(bookTypeNameTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
						.addComponent(btnNewButton_1))
					.addGap(625))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(184)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(67)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(216, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	/**
	 * ͼ����������¼�����
	 * @param e
	 */
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		//��ȡ�ı�
		String bookTypeName=this.bookTypeNameTxt.getText();
		String bookTypeDesc=this.bookTypeDescTxt.getText();
		//�ж�ͼ����������Ƿ�Ϊ��
		if(StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null,"ͼ��������Ʋ���Ϊ�գ�");
			return;
		}
		//��װʵ��
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		//����ʵ��
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=bookTypeDao.add(con,bookType);
			if(n==1) {
				JOptionPane.showMessageDialog(null,"ͼ��������ӳɹ���");
				resetValue();
			}else {
				JOptionPane.showMessageDialog(null,"ͼ���������ʧ�ܣ�");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"ͼ���������ʧ�ܣ�");
		}finally {
			try {
				dbUtil.closeCon(con);	//�ر����ݿ�		
			}catch(Exception e) {
				e.printStackTrace();
			}		
			
		}		
		
	}

	/**
	 * �����¼�����
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();		
	}

	/**
	 * ���ñ���
	 */
	private void resetValue() {
		//��ȡ���
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
		
	}
}