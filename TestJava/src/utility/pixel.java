package utility;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class pixel extends JComponent {
int piksel, x,x2;
public pixel(int piksel, int x, int x2) {
this.piksel = piksel;
this.x = x;
this.x2 = x2;
this.setPreferredSize(new Dimension(1000,1000));
}

public void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2d=(Graphics2D) g;
g2d.drawLine(piksel,x,piksel,x2);

}

public int getpiksel() {
return piksel;
}
public void setY(int piksel) {
this.piksel = piksel;
}
public int getX() {
return x;
}
public void setX(int x) {
this.x = x;
}

public int getX2() {
return x2;
}
public void setX2(int x2) {
this.x2 = x2;
}

} 