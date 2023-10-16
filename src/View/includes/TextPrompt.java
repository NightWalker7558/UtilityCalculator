package View.includes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * The `TextPrompt` class displays a prompt over a text component when its document is empty.
 * The prompt's visibility and appearance can be customized. It will automatically hide when text is entered.
 *
 * @see JLabel
 * @see FocusListener
 * @see DocumentListener
 */
public class TextPrompt
  extends JLabel
  implements FocusListener, DocumentListener {

  /**
   * The `Show` enum represents the visibility options for the `TextPrompt`.
   * It defines when the prompt should be displayed.
   */
  public enum Show {
    /**
     * The `ALWAYS` option indicates that the prompt should always be displayed.
     */
    ALWAYS,

    /**
     * The `FOCUS_GAINED` option indicates that the prompt should be displayed when
     * the associated `JTextComponent` gains focus and hidden when it loses focus.
     */
    FOCUS_GAINED,

    /**
     * The `FOCUS_LOST` option indicates that the prompt should be displayed when
     * the associated `JTextComponent` loses focus and hidden when it gains focus.
     */
    FOCUS_LOST,
  }

  /**
   * The `component` field represents the associated `JTextComponent` where the prompt is displayed.
   */
  private JTextComponent component;

  /**
   * The `document` field represents the `Document` of the associated `JTextComponent`.
   */
  private Document document;

  /**
   * The `show` field is of the `Show` enum type and controls when the prompt is displayed.
   * Possible values are `ALWAYS`, `FOCUS_GAINED`, and `FOCUS_LOST`.
   */
  private Show show;

  /**
   * The `showPromptOnce` field determines whether the prompt should be shown only once.
   * If set to `true`, the prompt is shown only once when the component gains/loses focus.
   */
  private boolean showPromptOnce;

  /**
   * The `focusLost` field keeps track of the number of times focus has been lost on the component.
   * It is used to manage the prompt's visibility based on focus events.
   */
  private int focusLost;

  /**
   * Constructs a `TextPrompt` with the specified text and text component, using the default show behavior "ALWAYS."
   *
   * @param text      The text to be displayed as the prompt.
   * @param component The text component for which the prompt is displayed.
   * @see Show
   */
  public TextPrompt(String text, JTextComponent component) {
    this(text, component, Show.ALWAYS);
  }

  /**
   * Constructs a `TextPrompt` with the specified text, text component, and show behavior.
   *
   * @param text      The text to be displayed as the prompt.
   * @param component The text component for which the prompt is displayed.
   * @param show      When to show the prompt (ALWAYS, FOCUS_GAINED, or FOCUS_LOST, as defined in the Show enum).
   * @see Show
   */
  public TextPrompt(String text, JTextComponent component, Show show) {
    this.component = component;
    setShow(show);
    document = component.getDocument();

    setText(text);
    setFont(component.getFont());
    setForeground(component.getForeground());
    setBorder(new EmptyBorder(component.getInsets()));
    setHorizontalAlignment(JLabel.LEADING);

    component.addFocusListener(this);
    document.addDocumentListener(this);

    component.setLayout(new BorderLayout());
    component.add(this);
    checkForPrompt();
  }

  /**
   *  Convenience method to change the alpha value of the current foreground
   *  Color to the specifice value.
   *
   *  @param alpha value in the range of 0 - 1.0.
   */
  public void changeAlpha(float alpha) {
    changeAlpha((int) (alpha * 255));
  }

  /**
   *  Convenience method to change the alpha value of the current foreground
   *  Color to the specifice value.
   *
   *  @param alpha value in the range of 0 - 255.
   */
  public void changeAlpha(int alpha) {
    alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

    Color foreground = getForeground();
    int red = foreground.getRed();
    int green = foreground.getGreen();
    int blue = foreground.getBlue();

    Color withAlpha = new Color(red, green, blue, alpha);
    super.setForeground(withAlpha);
  }

  /**
   *  Convenience method to change the style of the current Font. The style
   *  values are found in the Font class. Common values might be:
   *  Font.BOLD, Font.ITALIC and Font.BOLD + Font.ITALIC.
   *
   *  @param style value representing the the new style of the Font.
   */
  public void changeStyle(int style) {
    setFont(getFont().deriveFont(style));
  }

  /**
   *  Get the Show property
   *
   *  @return the Show property.
   */
  public Show getShow() {
    return show;
  }

  /**
   *  Set the prompt Show property to control when the promt is shown.
   *  Valid values are:
   *
   *  Show.AWLAYS (default) - always show the prompt
   *  Show.Focus_GAINED - show the prompt when the component gains focus
   *      (and hide the prompt when focus is lost)
   *  Show.Focus_LOST - show the prompt when the component loses focus
   *      (and hide the prompt when focus is gained)
   *
   *  @param show a valid Show enum
   */
  public void setShow(Show show) {
    this.show = show;
  }

  /**
   *  Get the showPromptOnce property
   *
   *  @return the showPromptOnce property.
   */
  public boolean getShowPromptOnce() {
    return showPromptOnce;
  }

  /**
   *  Show the prompt once. Once the component has gained/lost focus
   *  once, the prompt will not be shown again.
   *
   *  @param showPromptOnce  when true the prompt will only be shown once,
   *                         otherwise it will be shown repeatedly.
   */
  public void setShowPromptOnce(boolean showPromptOnce) {
    this.showPromptOnce = showPromptOnce;
  }

  /**
   *	Check whether the prompt should be visible or not. The visibility
   *  will change on updates to the Document and on focus changes.
   */
  private void checkForPrompt() {
    if (document.getLength() > 0) {
      setVisible(false);
      return;
    }

    //  Prompt has already been shown once, remove it

    if (showPromptOnce && focusLost > 0) {
      setVisible(false);
      return;
    }

    //  Check the Show property and component focus to determine if the
    //  prompt should be displayed.

    if (component.hasFocus()) {
      if (show == Show.ALWAYS || show == Show.FOCUS_GAINED) setVisible(
        true
      ); else setVisible(false);
    } else {
      if (show == Show.ALWAYS || show == Show.FOCUS_LOST) setVisible(
        true
      ); else setVisible(false);
    }
  }

  /**
   * Invoked when the associated text component gains focus. This method checks whether the prompt should be displayed.
   *
   * @param e The `FocusEvent` associated with the focus gain.
   */
  public void focusGained(FocusEvent e) {
    checkForPrompt();
  }

  /**
   * Invoked when the associated text component loses focus. This method increments the focusLost count and checks whether the prompt should be displayed.
   *
   * @param e The `FocusEvent` associated with the focus loss.
   */
  public void focusLost(FocusEvent e) {
    focusLost++;
    checkForPrompt();
  }

  /**
   * Invoked when text is inserted into the associated text component's document. This method checks whether the prompt should be displayed.
   *
   * @param e The `DocumentEvent` associated with text insertion.
   */
  public void insertUpdate(DocumentEvent e) {
    checkForPrompt();
  }

  /**
   * Invoked when text is removed from the associated text component's document. This method checks whether the prompt should be displayed.
   *
   * @param e The `DocumentEvent` associated with text removal.
   */
  public void removeUpdate(DocumentEvent e) {
    checkForPrompt();
  }

  /**
   * Invoked when there is a change in the associated text component's document. This method does not affect prompt visibility and is left empty.
   *
   * @param e The `DocumentEvent` associated with the document change.
   */
  public void changedUpdate(DocumentEvent e) {}
}
