import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowsHackProjectComponent implements ProjectComponent {
  public ArrowsHackProjectComponent(Project project) {
  }

  public void initComponent() {
    WindowManager wm = WindowManager.getInstance();
    JFrame frame = wm.getFrame(null);

    frame.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped: " + e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed: " + e);
      }

      @Override
      public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased: " + e);
      }
    });

    System.out.println("oke!!");
  }

  public void disposeComponent() {
    // TODO: insert component disposal logic here

    System.out.println("disposing");
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
