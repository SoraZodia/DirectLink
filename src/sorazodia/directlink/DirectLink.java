/**
 * 
 */
package sorazodia.directlink;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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


  /**
   * Draws the GUI and ready it for user's input
   */
  public static void main(String args[]) {
    if (args.length == 0)
      System.err.println("Please Input a Dropbox link");

    String output = convertLink(args[0]);
    addToClipboard(output);

    System.out.println(output);

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
    return publicLink.substring(0, publicLink.lastIndexOf("?")).replaceFirst(PUBLIC_ADDRESS, DIRECT_ADDRESS);
  }

}