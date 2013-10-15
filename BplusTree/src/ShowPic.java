import java.awt.Frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShowPic {
	 public void shwpic(String filepath)
	 {
		 System.out.println(filepath);
		 final Frame f = new Frame("Result");
		  final JLabel lbl = new JLabel();
		  f.setSize(600, 400);
		  f.setLocation(100, 100);
		  f.addWindowListener(new WindowAdapter() {
		   public void windowClosing(WindowEvent e) {
		    System.exit(0);
		   }
		  });
		  ImageIcon img=new ImageIcon(filepath);
		  lbl.setIcon(img);
		  f.add(lbl);
		  f.show();
	 }
}