
import java.awt.Color;
import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Smurf
 */
public class Circle {
    int x, y, radius;
    Color color;

    public void draw(Graphics g) {
        color = Color.BLACK;
        g.drawOval(x, y, radius, radius);
    }
}
