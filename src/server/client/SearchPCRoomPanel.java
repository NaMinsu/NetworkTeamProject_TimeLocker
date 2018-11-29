package client;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchPCRoomPanel extends JFrame
{
	private JTable dataset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPCRoomPanel frame = new SearchPCRoomPanel();
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
	public SearchPCRoomPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WORK\\\uAC00\uCC9C\uB300\uD559\uAD50\\2-2\\\uCEF4\uD4E8\uD130\uB124\uD2B8\uC6CC\uD06C \uBC0F \uC2E4\uC2B5(\uC774\uC8FC\uD615 \uAD50\uC218\uB2D8)\\Team Project\\Term project\\icon\\point.png"));
		setTitle("TimeLocker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		setLocationRelativeTo(null);   //창을 가운데에 뜨게함.
		setResizable(false);   //size변경하지 못하게 함.
		getContentPane().setLayout(null);


		JPanel Search = new JPanel();
		Search.setBounds(0, 0, 444, 401);
		getContentPane().add(Search);
		Search.setLayout(null);

		JLabel lblSearchPcRoom = new JLabel("Search PC Room Page");
		lblSearchPcRoom.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblSearchPcRoom.setBounds(12, 10, 203, 18);
		Search.add(lblSearchPcRoom);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("전국") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("서울");
						node_1.add(new DefaultMutableTreeNode("서울전체"));
						node_1.add(new DefaultMutableTreeNode("강남구"));
						node_1.add(new DefaultMutableTreeNode("강동구"));
						node_1.add(new DefaultMutableTreeNode("강북구"));
						node_1.add(new DefaultMutableTreeNode("\uAC15\uC11C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uAD00\uC545\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uAD11\uC9C4\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uAD6C\uB85C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uAE08\uCC9C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB178\uC6D0\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB3C4\uBD09\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB3D9\uB300\uBB38\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB3D9\uC791\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB9C8\uD3EC\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC11C\uB300\uBB38\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC11C\uCD08\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC131\uB3D9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC131\uBD81\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC1A1\uD30C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC591\uCC9C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC601\uB4F1\uD3EC\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC6A9\uC0B0\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC740\uD3C9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC885\uB85C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC911\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC911\uB791\uAD6C"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("경기");
						node_1.add(new DefaultMutableTreeNode("경기전체"));
						node_1.add(new DefaultMutableTreeNode("\uAC00\uD3C9\uAD70"));
						node_1.add(new DefaultMutableTreeNode("\uACE0\uC591\uC2DC \uB355\uC591\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uACE0\uC591\uC2DC \uC77C\uC0B0\uB3D9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uACE0\uC591\uC2DC \uC77C\uC0B0\uC11C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uACFC\uCC9C\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uAD11\uBA85\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uAD11\uC8FC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uAD6C\uB9AC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uAD70\uD3EC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uAE40\uD3EC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uB0A8\uC591\uC8FC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uB3D9\uB450\uCC9C\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uBD80\uCC9C\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC131\uB0A8\uC2DC \uBD84\uB2F9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC131\uB0A8\uC2DC \uC218\uC815\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC131\uB0A8\uC2DC \uC911\uC6D0\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC218\uC6D0\uC2DC \uAD8C\uC120\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC218\uC6D0\uC2DC \uC601\uD1B5\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC218\uC6D0\uC2DC \uC7A5\uC548\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC218\uC6D0\uC2DC \uD314\uB2EC\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC2DC\uD765\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC548\uC0B0\uC2DC \uB2E8\uC6D0\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC548\uC0B0\uC2DC \uC0C1\uB85D\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC548\uC131\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC548\uC591\uC2DC \uB3D9\uC548\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC548\uC591\uC2DC \uB9CC\uC548\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC591\uC8FC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC591\uD3C9\uAD70"));
						node_1.add(new DefaultMutableTreeNode("\uC5EC\uC8FC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC5F0\uCC9C\uAD70"));
						node_1.add(new DefaultMutableTreeNode("\uC624\uC0B0\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC6A9\uC778\uC2DC \uAE30\uD765\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC6A9\uC778\uC2DC \uC218\uC9C0\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC6A9\uC778\uC2DC \uCC98\uC778\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC758\uC655\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC758\uC815\uBD80\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uC774\uCC9C\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uD30C\uC8FC\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uD3C9\uD0DD\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uD3EC\uCC9C\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uD558\uB0A8\uC2DC"));
						node_1.add(new DefaultMutableTreeNode("\uD654\uC131\uC2DC"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("인천");
						node_1.add(new DefaultMutableTreeNode("인천전체"));
						node_1.add(new DefaultMutableTreeNode("\uAC15\uD654\uAD70"));
						node_1.add(new DefaultMutableTreeNode("\uACC4\uC591\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB0A8\uB3D9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uB3D9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uBBF8\uCD94\uD640\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uBD80\uD3C9\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC11C\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC5F0\uC218\uAD6C"));
						node_1.add(new DefaultMutableTreeNode("\uC639\uC9C4\uAD70"));
						node_1.add(new DefaultMutableTreeNode("\uC911\uAD6C"));
						add(node_1);
					}
				}
				));
		tree.setBounds(12, 38, 420, 152);
		Search.add(tree);



		JButton GoToNextPage = new JButton("Next");
		GoToNextPage.setBounds(335, 368, 97, 23);
		Search.add(GoToNextPage);

	}
}