/**
 * 
 */
package sorazodia.directlink.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sorazodia.directlink.DirectLink;

/**
 * Draws out the GUI
 * 
 * @author SoraZodia
 *
 */
@SuppressWarnings("serial")
public class InputGUI extends JPanel implements FocusListener {
  private JLabel inputDia = new JLabel();
  private JLabel outputDia = new JLabel();
  private JTextField input = new JTextField(100);
  private JTextField output = new JTextField(100);
  private String directLink = "";


  private InputGUI() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setPreferredSize(new Dimension(600, 80));
    this.writeLabel();
    this.prepareTextField();
    this.prepareCompoundLayout();
  }


  private void prepareTextField() {
    input.addActionListener((ActionEvent action) -> {
      directLink = DirectLink.convertLink(input.getText());
      DirectLink.addToClipboard(directLink);
      output.setText(directLink);
    });
    input.setMaximumSize(input.getPreferredSize());
    input.addFocusListener(this);
    output.setMaximumSize(output.getPreferredSize());
    output.addFocusListener(this);
    //output.setEditable(false);
  }


  private void writeLabel() {
    inputDia.setText("Link To Dropbox File");
    outputDia.setText("Direct Download Link");
  }


  private void prepareCompoundLayout() {
    this.add(inputDia);
    this.add(input);
    this.add(outputDia);
    this.add(output);
  }


  public static void drawGUI() {
    JFrame window = new JFrame("Dropbox Link Convertor");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(new InputGUI());
    
    window.pack();
    window.setVisible(true);
  }


  /**
   * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
   */
  @Override
  public void focusGained(FocusEvent e) {
    if (input.isFocusOwner()) {
      input.selectAll();
    }
    else if (output.isFocusOwner()) {
      output.selectAll();
    }

  }


  /**
   * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
   */
  @Override
  public void focusLost(FocusEvent e) {

  }

}
