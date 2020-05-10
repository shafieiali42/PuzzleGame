package View;

import javax.swing.*;
import java.awt.*;

public class MyMainFrame extends JFrame {


    public MyMainFrame() {
        int screenWidth, screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setPreferredSize(new Dimension(screenWidth/3,screenHeight/3));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new MyKeyListener());
        add(GamePanel.getInstance());
        pack();
        setVisible(true);
    }


}
