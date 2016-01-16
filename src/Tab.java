import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicButtonUI;

public class Tab {
	JTextArea textArea;
	JScrollPane scrollPane;
	JTabbedPane tabbedPane;
	JLabel label;
	boolean newFile;
	String filename;
	JPanel panel;
	ArrayList<Tab> tablist;

	public Tab(JTabbedPane tabbedPane) {
		panel = new JPanel();
		this.tabbedPane = tabbedPane;
		newFile = true;
		filename = "untitled.txt";

	}

	public Tab addTab(ArrayList<Tab> tablist) {
		this.tablist = tablist;
		textArea = new JTextArea();
		textArea.getDocument();
		scrollPane = new JScrollPane();
		tabbedPane.addTab(null, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				addPaneltoTabs());
		tabbedPane.setBackgroundAt(tabbedPane.getSelectedIndex(), Color.lightGray);
		scrollPane.setViewportView(textArea);
		return this;
	}

	public JPanel addPaneltoTabs(){
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		panel.setOpaque(false);
		label = new JLabel(this.filename);
		panel.add(label);
		label.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
		
		final JButton button = new JButton("btn");
		panel.add(button);
		button.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));
		button.setSize(new Dimension(5,5));
		button.setToolTipText("close this tab");
		button.setUI(new BasicButtonUI());
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setBorder(BorderFactory.createEtchedBorder());
		button.setBorderPainted(false);
		button.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				button.setText("noo");
			}
			public void mouseExited(MouseEvent e){
				button.setText("btn");
			}
			public void mouseClicked(MouseEvent e){
				tabbedPane.remove(tabbedPane.getSelectedIndex());
				try{
				tablist.remove(tabbedPane.getSelectedIndex());
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
		});
		return panel;
	}
	
	public void showTabList(){
		JPanel tabPanel = new JPanel();
		tabPanel.setSize(200, tablist.size()*10);
	}
}
