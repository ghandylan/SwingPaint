import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StopWatch implements ActionListener {
	JLabel jlab;
	long start;

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() 	{
			public void run()	{
				new StopWatch();
			}
		});
	}

	StopWatch() {
		JFrame jframe = new JFrame("H. Singh's  Stop Watch");
		jframe.setLayout(new FlowLayout());
		jframe.setSize(280, 100);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");
		start.addActionListener(this);
		stop.addActionListener(this);
		jframe.add(start);
		jframe.add(stop);
		jlab = new JLabel("Press Start to begin timing.",
				SwingConstants.CENTER);
		jframe.add(jlab);
		jframe.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("Start")) {
			start = ae.getWhen();
			jlab.setText("StopWatch is running....");
		} else {
			long result = ((ae.getWhen()) - start) / 1000;
			jlab.setText("" + result + " seconds.");
		}

	}

}