package com.crazy.chapter15.duplicate.execise.notepad;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.crazy.chapter15.duplicate.execise.notepad.actionListener.CopyItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.CutItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.DateItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.DeleteItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.ExitItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.NewItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.OpenItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.PasteItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.SaveItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.SaveOtherItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.SelectAllItemActionListener;
import com.crazy.chapter15.duplicate.execise.notepad.actionListener.WordItemActionListener;

public class Notepad {

	JFrame f = new JFrame("记事本");

	JTextArea ta = new JTextArea(30, 100);

	JMenuBar mb = new JMenuBar();
	JMenu file = new JMenu("文件(F)");
	JMenuItem newItem = new JMenuItem("新建(N)");
	JMenuItem openItem = new JMenuItem("打开(O)...");
	JMenuItem saveItem = new JMenuItem("保存(S)");
	JMenuItem saveOtherItem = new JMenuItem("另存为(A)...");
	JMenuItem pageSetItem = new JMenuItem("页面设置(U)...");
	JMenuItem printItem = new JMenuItem("打印(U)...");
	JMenuItem exitItem = new JMenuItem("退出(X)...");

	JMenu edit = new JMenu("编辑(U)");
	JMenuItem cutItem = new JMenuItem("剪切(T)");
	JMenuItem copyItem = new JMenuItem("复制(C)");
	JMenuItem pasteItem = new JMenuItem("粘贴(P)");
	JMenuItem deleteItem = new JMenuItem("删除(D)");
	JMenuItem findItem = new JMenuItem("查找(F)...");
	JMenuItem findNextItem = new JMenuItem("查找下一个(N)");
	JMenuItem replaceItem = new JMenuItem("替换(R)...");
	JMenuItem gotoItem = new JMenuItem("转到(G)...");
	JMenuItem allSelectItem = new JMenuItem("全选(A)");
	JMenuItem dateItem = new JMenuItem("时间/日期(D)...");

	JMenu format = new JMenu("格式(O)");
	JCheckBoxMenuItem lineChangeItem = new JCheckBoxMenuItem("自行换行(W)...");
	JMenuItem wordItem = new JMenuItem("字体(F)...");

	JMenu check = new JMenu("查看(V)");
	JMenuItem statusLineItem = new JMenuItem("状态栏(S)");

	JMenu help = new JMenu("帮助(H)");
	JMenuItem helpItem = new JMenuItem("查看帮助(H)");
	JMenuItem aboutItem = new JMenuItem("关于记事本(A)");

	Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();// system
																		// clip
																		// board;

	/**
	 * Initialize the context.
	 */
	public void init() {
		Box bottom = Box.createHorizontalBox();
		JScrollPane jsp = new JScrollPane(ta);// add textArea
		bottom.add(jsp);// add jscrollpane
		f.add(bottom);
		ta.setFont(new Font("宋体", Font.PLAIN, 15));// set default font.
		/**
		 * add key stroke for menu item.
		 */
		addStroke();

		/**
		 * add menuItem to menu
		 */
		addMenuItem();

		initDisableItem();

		addActionListener();
		// set menu bar
		f.setJMenuBar(mb);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * disable the specific menu item.
	 */
	private void initDisableItem() {
		gotoItem.setEnabled(false);
		statusLineItem.setEnabled(false);
		cutItem.setEnabled(false);
		copyItem.setEnabled(false);
		deleteItem.setEnabled(false);
	}

	private void disableTextItem() {
		cutItem.setEnabled(false);
		copyItem.setEnabled(false);
		deleteItem.setEnabled(false);
	}

	private void enableTextItem() {
		cutItem.setEnabled(true);
		copyItem.setEnabled(true);
		deleteItem.setEnabled(true);
	}

	/**
	 * add menuItem to menu.
	 */
	private void addMenuItem() {
		file.add(newItem);
		file.add(openItem);
		file.add(saveItem);
		file.add(saveOtherItem);
		file.addSeparator();
		file.add(pageSetItem);
		file.add(printItem);
		file.addSeparator();
		file.add(exitItem);
		edit.add(cutItem);
		edit.addSeparator();
		edit.add(copyItem);
		edit.add(pasteItem);
		edit.add(deleteItem);
		edit.addSeparator();
		edit.add(findItem);
		edit.add(findNextItem);
		edit.add(replaceItem);
		edit.add(gotoItem);
		edit.addSeparator();
		edit.add(allSelectItem);
		edit.add(dateItem);
		format.add(lineChangeItem);
		format.add(wordItem);
		check.add(statusLineItem);
		help.add(helpItem);
		help.addSeparator();
		help.add(aboutItem);

		mb.add(file);
		mb.add(edit);
		mb.add(format);
		mb.add(check);
		mb.add(help);
	}

	/**
	 * add stroke to Menu item.
	 */
	private void addStroke() {
		newItem.setAccelerator(KeyStroke
				.getKeyStroke('N', InputEvent.CTRL_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke('O',
				InputEvent.CTRL_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S',
				InputEvent.CTRL_MASK));
		saveOtherItem.setAccelerator(KeyStroke.getKeyStroke('A',
				InputEvent.CTRL_MASK));
		pageSetItem.setAccelerator(KeyStroke.getKeyStroke('U',
				InputEvent.CTRL_MASK));
		printItem.setAccelerator(KeyStroke.getKeyStroke('P',
				InputEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke('I',
				InputEvent.CTRL_MASK));

		cutItem.setAccelerator(KeyStroke
				.getKeyStroke('X', InputEvent.CTRL_MASK));
		copyItem.setAccelerator(KeyStroke.getKeyStroke('C',
				InputEvent.CTRL_MASK));
		pasteItem.setAccelerator(KeyStroke.getKeyStroke('P',
				InputEvent.CTRL_MASK));
		deleteItem.setAccelerator(KeyStroke.getKeyStroke('D',
				InputEvent.CTRL_MASK));
		findItem.setAccelerator(KeyStroke.getKeyStroke('F',
				InputEvent.CTRL_MASK));
		findNextItem.setAccelerator(KeyStroke.getKeyStroke('N',
				InputEvent.CTRL_MASK));
		replaceItem.setAccelerator(KeyStroke.getKeyStroke('R',
				InputEvent.CTRL_MASK));
		gotoItem.setAccelerator(KeyStroke.getKeyStroke('G',
				InputEvent.CTRL_MASK));
		allSelectItem.setAccelerator(KeyStroke.getKeyStroke('A',
				InputEvent.CTRL_MASK));
		dateItem.setAccelerator(KeyStroke.getKeyStroke('D',
				InputEvent.CTRL_MASK));

		lineChangeItem.setAccelerator(KeyStroke.getKeyStroke('W',
				InputEvent.CTRL_MASK));
		wordItem.setAccelerator(KeyStroke.getKeyStroke('T',
				InputEvent.CTRL_MASK));

		statusLineItem.setAccelerator(KeyStroke.getKeyStroke('S',
				InputEvent.CTRL_MASK));

		helpItem.setAccelerator(KeyStroke.getKeyStroke('H',
				InputEvent.CTRL_MASK));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A',
				InputEvent.CTRL_MASK));
	}

	/**
	 * add action listener to the component, add new actionListener must
	 * register here.
	 */
	public void addActionListener() {
		newItem.addActionListener(new NewItemActionListener(f, ta));
		openItem.addActionListener(new OpenItemActionListener(f, ta));
		saveItem.addActionListener(new SaveItemActionListener(ta));
		saveOtherItem.addActionListener(new SaveOtherItemActionListener(f, ta));
		exitItem.addActionListener(new ExitItemActionListener(f, ta));
		wordItem.addActionListener(new WordItemActionListener(f, ta));
		copyItem.addActionListener(new CopyItemActionListener(ta, clip));
		pasteItem.addActionListener(new PasteItemActionListener(ta, clip));
		deleteItem.addActionListener(new DeleteItemActionListener(ta));
		allSelectItem.addActionListener(new SelectAllItemActionListener(ta));
		dateItem.addActionListener(new DateItemActionListener(ta));
		cutItem.addActionListener(new CutItemActionListener(ta, clip));
		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (ta.getSelectedText() != null
						&& !ta.getSelectedText().isEmpty()) {
					enableTextItem();// if text selected then enable cut, copy
										// and paste
				} else {
					disableTextItem();
				}
			}
		});
	}

	public static void main(String[] args) {
		new Notepad().init();
	}
}
