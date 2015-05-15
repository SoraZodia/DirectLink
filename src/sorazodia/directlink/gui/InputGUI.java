/**
 * 
 */
package sorazodia.directlink.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
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
  private JTextField input = new JTextField(20);
  private JTextField output = new JTextField(20);
  private GroupLayout layoutManager;
  private String directLink = "";


  private InputGUI() {
    layoutManager = new GroupLayout(this);
    this.setLayout(layoutManager);
    this.setPreferredSize(new Dimension(600, 250));
  }


  private void prepareTextField() {
    input.addActionListener((ActionEvent action) -> {
      directLink = DirectLink.convertLink(input.getText());
      output.setText(directLink);
    });
    input.addFocusListener(this);
    output.setEditable(false);
  }


  private void writeLabel() {
    inputDia.setText("Link To Dropbox File");
    outputDia.setText("Direct Download Link");
  }


  private void prepareCompoundLayout() {

  }


  public static void drawGUI() {
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(new InputGUI());
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
