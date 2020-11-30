/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.io.IOException;

import javax.swing.JOptionPane;

import principal.Principal;

/**
 *
 * @author igoro
 */
public class NewJFrame extends javax.swing.JFrame {

	/**
	 * Creates new form NewJFrame
	 */
	public NewJFrame() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jFrame1 = new javax.swing.JFrame();
		buttonGroup1 = new javax.swing.ButtonGroup();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		Run01 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		exit01 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		run02 = new javax.swing.JButton();
		heap = new javax.swing.JRadioButton();
		quick = new javax.swing.JRadioButton();
		ABB = new javax.swing.JRadioButton();
		AVL = new javax.swing.JRadioButton();
		Hash = new javax.swing.JRadioButton();
		exit02 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

		javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
		jFrame1.getContentPane().setLayout(jFrame1Layout);
		jFrame1Layout.setHorizontalGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jFrame1Layout.setVerticalGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Contas Bancarias");

		jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		Run01.setText("Executar Automatizado");
		Run01.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					Run01ActionPerformed(evt);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro nos arquivos", "Erro", 
		  					  JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		exit01.setText("Sair");
		exit01.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exit01ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(176, 176, 176).addComponent(jLabel1))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(122, 122, 122).addComponent(Run01))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(174, 174, 174).addComponent(exit01)))
						.addContainerGap(130, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addComponent(Run01).addGap(18, 18, 18).addComponent(exit01).addGap(90, 90, 90)));

		jTabbedPane1.addTab("Automatizado", jPanel1);

		run02.setText("Executar");
		run02.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt)  {
				try {
					run02ActionPerformed(evt);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"Erro nos arquivos", "Erro", 
		  					  JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buttonGroup1.add(heap);
		heap.setText("HeapSort");

		buttonGroup1.add(quick);
		quick.setText("QuickSort");

		buttonGroup1.add(ABB);
		ABB.setText("Arvore Binaria de Busca");

		buttonGroup1.add(AVL);
		AVL.setText("AVL");

		buttonGroup1.add(Hash);
		Hash.setText("Hashing Vetor Encadeado");

		exit02.setText("Sair");
		exit02.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exit02ActionPerformed(evt);
			}
		});

		jLabel2.setText("Selecione um metodo:");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(124, 124, 124).addGroup(jPanel2Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(Hash).addComponent(ABB, javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(AVL, javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(heap).addComponent(quick)))
								.addGroup(jPanel2Layout.createSequentialGroup().addGap(1, 1, 1).addComponent(jLabel2))))
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(169, 169, 169).addComponent(exit02))
						.addGroup(jPanel2Layout.createSequentialGroup().addGap(158, 158, 158).addComponent(run02)))
						.addContainerGap(114, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(12, 12, 12).addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(heap)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(quick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(ABB)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(AVL)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(Hash)
						.addGap(24, 24, 24).addComponent(run02)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(exit02)
						.addContainerGap()));

		jTabbedPane1.addTab("Modularizado", jPanel2);

		jTextArea1.setEditable(false);
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setText(
				"FAESA CENTRO UNIVERSITARIO \nUNIDADE DE ENGENHARIA E COMPUTACAO\nBACHARELADO EM SISTEMAS DE INFORMACAO\n\nPESQUISA E ORDENACAO\nLINGUAGEM DE PROGRAMACAO ORIENTADA A OBJETOS\n\nPROFESSORA CINTHIA CALIARI\n\nAUTORES:\nIgor Ofrante, Karen Alcantara, Lucas Sarmento,\nMackweyd Gomes, Pedro Henrique Fernandes.");
		jScrollPane1.setViewportView(jTextArea1);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE));

		jTabbedPane1.addTab("Sobre", jPanel3);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jTabbedPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void run02ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		if (heap.isSelected()) {
			Principal.primeiraEtapa();
			JOptionPane.showMessageDialog(null, "M�todo HeapSort foi executado com sucesso!");
		} else if (quick.isSelected()) {
			Principal.segundaEtapa();
			JOptionPane.showMessageDialog(null, "M�todo QuickSort foi executado com sucesso!");
		} else if (ABB.isSelected()) {
			Principal.terceiraEtapa();
			JOptionPane.showMessageDialog(null, "M�todo ABB foi executado com sucesso!");
		} else if (AVL.isSelected()) {
			Principal.quartaEtapa();
			JOptionPane.showMessageDialog(null, "M�todo AVL foi executado com sucesso!");
		} else if (Hash.isSelected()) {
			Principal.quintaEtapa();
			JOptionPane.showMessageDialog(null, "M�todo Hashing foi executado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um m�todo!");
		}
	}

	private void exit02ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void exit01ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void Run01ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		Principal.primeiraEtapa();
		Principal.segundaEtapa();
		Principal.terceiraEtapa();
		Principal.quartaEtapa();
		Principal.quintaEtapa();
		JOptionPane.showMessageDialog(null, "Todos m�todos executados com sucesso!");
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JRadioButton ABB;
	private javax.swing.JRadioButton AVL;
	private javax.swing.JRadioButton Hash;
	private javax.swing.JButton Run01;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton exit01;
	private javax.swing.JButton exit02;
	private javax.swing.JRadioButton heap;
	private javax.swing.JFrame jFrame1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JRadioButton quick;
	private javax.swing.JButton run02;
	// End of variables declaration
}
