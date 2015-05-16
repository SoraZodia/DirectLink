/**
 * 
 */
package sorazodia.directlink;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import sorazodia.directlink.gui.InputGUI;

/**
 * Main Class that draws the GUI and convert Dropbox's public link to its direct link
 * 
 * @author SoraZodia
 *
 */
public class DirectLink {

  /** Dropbox's direct download address */
  private static final String DIRECT_ADDRESS = "https://dl.dropboxusercontent.com";
  /** Dropbox's normal share link */
  private static final String PUBLIC_ADDRESS = "https://www.dropbox.com";
  /**Marker to tell of a bad link*/
  public static final String INVALID = "Invalid Dropbox Link";


  /**
   * Draws the GUI and ready it for user's input
   */
  public static void main(String args[]) {
    
    InputGUI.drawGUI();
    
//    if (args.length == 0)
//      System.err.println("Please Input a Dropbox link");
//
//    String output = convertLink(args[0]);
//    addToClipboard(output);
//
//    System.out.println(output);

  }

  /**
   * Determine if content should be copy to clipboard
   */
  public static void addToClipboard(String directLink) {
    StringSelection toCopy = new StringSelection(directLink);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(toCopy, toCopy);
  }


  /**
   * Takes a public link and output its public download counterpart
   */
  public static String convertLink(String publicLink) {
    if(publicLink.contains("?"))
      publicLink = publicLink.substring(0, publicLink.lastIndexOf("?"));
    if(publicLink.contains(DIRECT_ADDRESS))
      return publicLink;
    if(!publicLink.contains(PUBLIC_ADDRESS))
      return INVALID;
    
    return publicLink.replaceFirst(PUBLIC_ADDRESS, DIRECT_ADDRESS);
  }

}
