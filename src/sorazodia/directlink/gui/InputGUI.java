/**
 * 
 */
package sorazodia.directlink.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
  /** Label describing the input line */
  private JLabel inputDia = new JLabel();
  /** Label describing the output line */
  private JLabel outputDia = new JLabel();
  /** Where users type/paste in their Dropbox download */
  private JTextField input = new JTextField(100);
  /** Where the direct download is placed */
  private JTextField output = new JTextField(100);
  /** Holds the direct download URL */
  private String directLink = "";

  /**
   * Creates a panel holding the input line and output line
   */
  protected InputGUI() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setPreferredSize(new Dimension(600, 80));
    this.writeLabel();
    this.prepareTextField();
    this.prepareCompoundLayout();
  }


  /**
   * Readies the input and output line for user interaction
   */
  private void prepareTextField() {
    input.addActionListener((ActionEvent action) -> {
      if (input.getText().isEmpty())
        return;

      directLink = DirectLink.convertLink(input.getText());
      if (!directLink.equalsIgnoreCase(DirectLink.INVALID))
        DirectLink.addToClipboard(directLink);

      output.setText(directLink);
    });
    input.setMaximumSize(input.getPreferredSize());
    input.addFocusListener(this);
    output.setMaximumSize(output.getPreferredSize());
    output.addFocusListener(this);
    // output.setEditable(false);
  }


  /**
   * Adds the label to the label so the user can see the labels... I'll take my leave now
   */
  private void writeLabel() {
    inputDia.setText("Link To Dropbox File");
    outputDia.setText("Direct Download Link");
  }


  /**
   * Add the GUI parts to the panel
   */
  private void prepareCompoundLayout() {
    this.add(inputDia);
    this.add(input);
    this.add(outputDia);
    this.add(output);
  }


  /**
   * Creates the GUI
   */
  public static void drawGUI() {
    InputGUI inputPanel = new InputGUI();
    
    JFrame window = new JFrame("Dropbox Link Convertor");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(inputPanel);
    window.setMinimumSize(new Dimension(600, 125));
    window.setIconImage(Toolkit.getDefaultToolkit().getImage(inputPanel.getClass().getResource("/resources/icon.png")));
    
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
      DirectLink.addToClipboard(output.getSelectedText());
    }

  }


  /**
   * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
   */
  @Override
  public void focusLost(FocusEvent e) {

  }

}
