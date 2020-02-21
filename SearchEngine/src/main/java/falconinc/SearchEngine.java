package falconinc;
import javax.swing.*;
import java.awt.*; 


/**
 *
 * @author Robert (Alex) Spangler
 */
public class SearchEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create frame
        JFrame frame = new JFrame("Falcon Search");
	frame.setLayout(new FlowLayout()); 
	frame.setSize(600,600);
        
        // add search button
	JButton btn = new JButton("Search");
        btn.setBounds(340, 150, 100, 40);
        
        // add search textbox
	JTextField txtbx = new JTextField();
        txtbx.setBounds(75, 150, 260, 40);
        
        // radio button for all searched terms
	JRadioButton allSrch = new JRadioButton();
        allSrch.setText("All Searhed terms");
	allSrch.setBounds(40, 190, 140, 40);
        
        // radio button for any of the searched terms
	JRadioButton anySrch = new JRadioButton();
        anySrch.setText("Any Searched terms");
	anySrch.setBounds(190, 190, 170, 40);
        
        // radio button for exact phrases
	JRadioButton exctPhrse = new JRadioButton();
        exctPhrse.setText("Exact Phrase");
	exctPhrse.setBounds(360, 190, 325, 40);
        
        // allows only one radio button to be sellected at a time
        ButtonGroup bg = new ButtonGroup();
        bg.add(allSrch);
        bg.add(anySrch);
        bg.add(exctPhrse);


        // font for title
	Font font = new Font("Monospace", Font.ITALIC, 36); 

	// title
	JLabel title = new JLabel();		
	title.setText("Falcon Search");
	title.setBounds(120, 80, 320, 40);	    
	title.setHorizontalAlignment(JLabel.CENTER);
	title.setVerticalAlignment(JLabel.CENTER);
	title.setFont(font);
        
        // button to connect to the Admin window
        JButton adminbtn = new JButton("Manage Files");
        adminbtn.setBounds(10, 500, 150, 40);

		
        // adding text, textbox, buttons, exc to frame
        frame.add(title);
	frame.add(txtbx);
	frame.add(btn);
        frame.add(allSrch);
        frame.add(anySrch);
        frame.add(exctPhrse);
        frame.add(adminbtn);

		
	frame.setLayout(null);
	frame.setVisible(true);

        // Closes window
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
}
