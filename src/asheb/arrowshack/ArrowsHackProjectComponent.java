package asheb.arrowshack;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.impl.EditorComponentImpl;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class ArrowsHackProjectComponent implements ProjectComponent {
  public ArrowsHackProjectComponent(Project project) {
  }

  public void initComponent() {

//    FocusManager.setCurrentManager(new FocusManager() {
//      private int i = 0;
//
//      @Override
//      public void processKeyEvent(Component focusedComponent, KeyEvent e) {
//        System.out.println((i++) + " -- " + e);
//        super.processKeyEvent(focusedComponent, e);
//      }
//    });

    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

      private int i = 0;

      @SuppressWarnings("deprecation")
      @Override
      public void eventDispatched(AWTEvent e) {

        KeyEvent ke = (KeyEvent) e;
        char c = Character.toLowerCase(ke.getKeyChar());

        int keyCode = -1;
        int addMods = 0;
        switch (c) {
          case 'i':   keyCode = KeyEvent.VK_UP;     break;
          case 'j':   keyCode = KeyEvent.VK_LEFT;   break;
          case 'k':   keyCode = KeyEvent.VK_DOWN;   break;
          case 'l':   keyCode = KeyEvent.VK_RIGHT;  break;

          case '"':
          case '\'':  keyCode = KeyEvent.VK_END;    break;
          case 'h':   keyCode = KeyEvent.VK_HOME;   break;

          case ':':
          case ';':   keyCode = KeyEvent.VK_ENTER;  break;

          case '[':   keyCode = KeyEvent.VK_BACK_SPACE;  break;
          case ']':   keyCode = KeyEvent.VK_DELETE;      break;

          case '{':   keyCode = KeyEvent.VK_BACK_SPACE;  addMods = KeyEvent.CTRL_MASK;   break;
          case '}':   keyCode = KeyEvent.VK_DELETE;      addMods = KeyEvent.CTRL_MASK;   break;
          case 'u':   keyCode = KeyEvent.VK_LEFT;        addMods = KeyEvent.CTRL_MASK;   break;
          case 'o':   keyCode = KeyEvent.VK_RIGHT;       addMods = KeyEvent.CTRL_MASK;   break;
        }


        Component focused = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        if (focused instanceof EditorComponentImpl) {
          // leave event untouched
        }
        else {

          if (keyCode >= 0 && ke.isAltDown()) {
            ke.setKeyCode(keyCode);
            ke.setKeyChar(KeyEvent.CHAR_UNDEFINED);
            ke.setModifiers(ke.getModifiers() & ~KeyEvent.ALT_MASK | addMods);
          }

          if ((ke.getKeyCode() == KeyEvent.VK_ALT) ||
              (ke.getKeyCode() == 0 && Character.getNumericValue(ke.getKeyChar()) == -1)) {
            ke.consume();
            System.out.println("-- consumed --");
          }
        }

        System.out.println((i++) + " :--: " + e);//e.paramString().substring(4, 9) + " keyCode: " + ke.getKeyCode() + " keyChar: " + ke.getKeyChar());
      }

    }, AWTEvent.KEY_EVENT_MASK);

  }

  public void disposeComponent() {
    //>
  }

  @NotNull
  public String getComponentName() {
    return "ArrowsHackProjectComponent";
  }

  public void projectOpened() {
    // called when project is opened
  }

  public void projectClosed() {
    // called when project is being closed
  }
}
