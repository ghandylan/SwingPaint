/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

/**
 * @author CS128-8L_BM5 Group Project 1Q2022
 * <p>
 * Members: LARA, CHARLENE GRAZIELLE DELFIN, IVAN ZACHARRIA GARCIA, ERVIN
 * MIKHAIL INOCENCIO, ZARA NAOMI TAYAG, DYLAN LOUIS
 */
public class Main extends JFrame implements MouseListener {

    public String selectedTool = "Brush"; // default tool
    public Color toolColor = Color.BLACK; // default brush and shape color
    public Color canvasColor = Color.WHITE; // default canvas color
    public Color eraserColor = canvasColor; // default eraser color
    public int x, y = 0; // default brush and shape coordinates
    public int strokeSize = 7; // default stroke size
    public boolean drawingIsSaved = false;

    /**
     * Creates new form Main
     */
    public Main() {
        this.setTitle("Swing Paint v1.0.0.0-alpha");
        // set JFrame color to white
        this.getContentPane().setBackground(canvasColor);
        initComponents();

        MouseListener brush = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(selectedTool, "Brush")) {
                    selectedTool = "Brush";
                } else if (Objects.equals(selectedTool, "Eraser")) {
                    selectedTool = "Eraser";
                    toolColor = Color.WHITE;

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        addMouseListener(brush);
        MouseMotionListener brushMotion = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Objects.equals(selectedTool, "Brush")) {
                    // use graphics2d to draw
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    // use brush to draw
                    g2d.drawOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    ToolsPanel.repaint();
//                    Graphics g = getGraphics();
//                    g.setColor(col);
//                    g.fillOval(e.getX(), e.getY(), 12, 12);
//                    ToolsPanel.repaint();

                } else if (Objects.equals(selectedTool, "Eraser")) {
                    Graphics g = getGraphics();
                    g.setColor(eraserColor);
                    g.fillOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    ToolsPanel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        };
        addMouseMotionListener(brushMotion);

        MouseListener shapes = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(selectedTool, "Line")) {
                    selectedTool = "Line";
                    toolColor = Color.BLACK;
                } else if (Objects.equals(selectedTool, "Circle")) {
                    selectedTool = "Circle";
                    toolColor = Color.BLACK;
                } else if (Objects.equals(selectedTool, "Rectangle")) {
                    selectedTool = "Rectangle";
                    toolColor = Color.BLACK;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Objects.equals(selectedTool, "Line")) {
                    x = e.getX();
                    y = e.getY();
                } else if (Objects.equals(selectedTool, "Circle")) {
                    x = e.getX();
                    y = e.getY();
                } else if (Objects.equals(selectedTool, "Rectangle")) {
                    x = e.getX();
                    y = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (Objects.equals(selectedTool, "Line")) {
                    // graphics 2d
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    // draw line and set thickness
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawLine(x, y, e.getX(), e.getY());
                    ToolsPanel.repaint();
                } else if (Objects.equals(selectedTool, "Circle")) {
                    // graphics 2d
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    // draw circle and set thickness
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawOval(x, y, e.getX() - x, e.getY() - y);
                    ToolsPanel.repaint();
                } else if (Objects.equals(selectedTool, "Rectangle")) {
                    // graphics 2d
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    // draw rectangle and set thickness
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawRect(x, y, e.getX() - x, e.getY() - y);
                    ToolsPanel.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        addMouseListener(shapes);
        MouseMotionListener shapesMotion = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Objects.equals(selectedTool, "Line")) {
                    // graphics 2d
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    // draw line and set thickness
                    g2d.setStroke(new BasicStroke(strokeSize));
                    ToolsPanel.repaint();
                } else if (Objects.equals(selectedTool, "Circle")) {
                    // graphics 2d
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    // draw circle and set thickness
                    g2d.setStroke(new BasicStroke(strokeSize));
                    ToolsPanel.repaint();
                } else if (Objects.equals(selectedTool, "Rectangle")) {
                    // graphics 2d
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    // draw rectangle and set thickness
                    g2d.setStroke(new BasicStroke(strokeSize));
                    ToolsPanel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };
        addMouseMotionListener(shapesMotion);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (!drawingIsSaved) {
                    String[] ObjButtons = {"Save and exit", "Continue drawing"};
                    int PromptResult = JOptionPane.showOptionDialog(null, "Save the drawing first before exiting?", "Save file confirmation",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                    if (PromptResult == JOptionPane.YES_OPTION) {
                        saveImage();
                        dispose();
                    } else if (PromptResult == JOptionPane.NO_OPTION) {

                    }
                } else {
                    String[] ObjButtons = {"Yes", "No"};
                    int PromptResult = JOptionPane.showOptionDialog(null, "Do you really want to exit?", "Exit confirmation",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                    if (PromptResult == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StrokeSizeGroup = new javax.swing.ButtonGroup();
        ToolsPanel = new javax.swing.JPanel();
        BrushButton = new javax.swing.JButton();
        EraserButton = new javax.swing.JButton();
        LineButton = new javax.swing.JButton();
        CircleButton = new javax.swing.JButton();
        RectangleButton = new javax.swing.JButton();
        TimerPanel = new javax.swing.JPanel();
        TimerLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Clear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ColorChosenPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        ColorsMenu = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ColorSelectionItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        ViewDrawing = new javax.swing.JMenuItem();
        AboutMenu = new javax.swing.JMenu();
        Credits = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        ToolsPanel.setBackground(new java.awt.Color(204, 150, 92));

        BrushButton.setBackground(new java.awt.Color(107, 62, 41));
        BrushButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BrushButton.setForeground(new java.awt.Color(255, 255, 255));
        BrushButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/brush-pngrepo-com.png"))); // NOI18N
        BrushButton.setText("Brush");
        BrushButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrushButtonActionPerformed(evt);
            }
        });

        EraserButton.setBackground(new java.awt.Color(107, 62, 41));
        EraserButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EraserButton.setForeground(new java.awt.Color(255, 255, 255));
        EraserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eraser-pngrepo-com.png"))); // NOI18N
        EraserButton.setText("Eraser");
        EraserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EraserButtonActionPerformed(evt);
            }
        });

        LineButton.setBackground(new java.awt.Color(107, 62, 41));
        LineButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LineButton.setForeground(new java.awt.Color(255, 255, 255));
        LineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/line-alt-pngrepo-com.png"))); // NOI18N
        LineButton.setText("Line");
        LineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LineButtonActionPerformed(evt);
            }
        });

        CircleButton.setBackground(new java.awt.Color(107, 62, 41));
        CircleButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CircleButton.setForeground(new java.awt.Color(255, 255, 255));
        CircleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/circle-pngrepo-com.png"))); // NOI18N
        CircleButton.setText("Circle");
        CircleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CircleButtonActionPerformed(evt);
            }
        });

        RectangleButton.setBackground(new java.awt.Color(107, 62, 41));
        RectangleButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RectangleButton.setForeground(new java.awt.Color(255, 255, 255));
        RectangleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rectangle-one-pngrepo-com.png"))); // NOI18N
        RectangleButton.setText("Box");
        RectangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RectangleButtonActionPerformed(evt);
            }
        });

        TimerPanel.setBackground(new java.awt.Color(241, 226, 201));

        javax.swing.GroupLayout TimerPanelLayout = new javax.swing.GroupLayout(TimerPanel);
        TimerPanel.setLayout(TimerPanelLayout);
        TimerPanelLayout.setHorizontalGroup(
            TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimerPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(TimerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TimerPanelLayout.setVerticalGroup(
            TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimerPanelLayout.createSequentialGroup()
                .addComponent(TimerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SwingPaint-1.png"))); // NOI18N
        jLabel1.setText("SwingPaint");

        Clear.setBackground(new java.awt.Color(107, 62, 41));
        Clear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Clear.setForeground(new java.awt.Color(255, 255, 255));
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clear-pngrepo-com.png"))); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Selected color:");

        ColorChosenPanel.setBackground(new java.awt.Color(0, 0, 0));
        ColorChosenPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ColorChosenPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ColorChosenPanelLayout = new javax.swing.GroupLayout(ColorChosenPanel);
        ColorChosenPanel.setLayout(ColorChosenPanelLayout);
        ColorChosenPanelLayout.setHorizontalGroup(
            ColorChosenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );
        ColorChosenPanelLayout.setVerticalGroup(
            ColorChosenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stroke size:");

        StrokeSizeGroup.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Small");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        StrokeSizeGroup.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Medium");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        StrokeSizeGroup.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("Large");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Canvas color:");

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ToolsPanelLayout = new javax.swing.GroupLayout(ToolsPanel);
        ToolsPanel.setLayout(ToolsPanelLayout);
        ToolsPanelLayout.setHorizontalGroup(
            ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolsPanelLayout.createSequentialGroup()
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                        .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ToolsPanelLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(TimerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ToolsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                                        .addComponent(jRadioButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton3))
                                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel3))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ToolsPanelLayout.createSequentialGroup()
                                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel2))
                                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(ColorChosenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19))
                                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ToolsPanelLayout.createSequentialGroup()
                                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(RectangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CircleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(ToolsPanelLayout.createSequentialGroup()
                                .addComponent(BrushButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EraserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        ToolsPanelLayout.setVerticalGroup(
            ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ColorChosenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BrushButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EraserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CircleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RectangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(TimerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MainMenuBar.setBackground(new java.awt.Color(107, 62, 41));
        MainMenuBar.setForeground(new java.awt.Color(51, 51, 51));

        FileMenu.setText("File");
        FileMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem1.setText("New File");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        FileMenu.add(jMenuItem1);
        FileMenu.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        FileMenu.add(jMenuItem3);
        FileMenu.add(jSeparator2);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        FileMenu.add(jMenuItem2);

        MainMenuBar.add(FileMenu);

        ColorsMenu.setText("Colors");
        ColorsMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        ColorsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorsMenuActionPerformed(evt);
            }
        });

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem4.setText("Select canvas color");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        ColorsMenu.add(jMenuItem4);
        ColorsMenu.add(jSeparator3);

        ColorSelectionItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ColorSelectionItem.setText("Select color");
        ColorSelectionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorSelectionItemActionPerformed(evt);
            }
        });
        ColorsMenu.add(ColorSelectionItem);

        MainMenuBar.add(ColorsMenu);

        ViewMenu.setText("View");
        ViewMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        ViewDrawing.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ViewDrawing.setText("View painting");
        ViewDrawing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewDrawingActionPerformed(evt);
            }
        });
        ViewMenu.add(ViewDrawing);

        MainMenuBar.add(ViewMenu);

        AboutMenu.setText("About");
        AboutMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        Credits.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Credits.setText("View details");
        Credits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditsActionPerformed(evt);
            }
        });
        AboutMenu.add(Credits);

        MainMenuBar.add(AboutMenu);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ToolsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(664, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(ToolsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void saveImage() {
        // save my paint drawing as an image using Robot
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "swingpaint." + format;
            // capture only the drawing area
            Rectangle screenRect = new Rectangle(750, 250, 660, 534);
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
            // show JOptionPane message
            JOptionPane.showMessageDialog(null, "Image saved as " + fileName);
            System.out.println("A full screenshot saved!");
            drawingIsSaved = true;
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

    public void viewImage() {
        // view the saved image
        // if image is not saved, save it first
        try {
            File file = new File("swingpaint.jpg");
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                saveImage();
                Desktop.getDesktop().open(file);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Objects.equals(selectedTool, "Brush")) {
            g.setColor(toolColor);
            g.fillOval(x, y, strokeSize, strokeSize);
        } else if (Objects.equals(selectedTool, "Eraser")) {
            g.setColor(eraserColor);
            g.fillOval(x, y, strokeSize, strokeSize);
        } else if (Objects.equals(selectedTool, "Line")) {
            g.setColor(toolColor);
        } else if (Objects.equals(selectedTool, "Rectangle")) {
            g.setColor(toolColor);
        } else if (Objects.equals(selectedTool, "Circle")) {
            g.setColor(toolColor);
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    private void BrushButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrushButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Brush";
    }//GEN-LAST:event_BrushButtonActionPerformed

    private void EraserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EraserButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Eraser";
    }//GEN-LAST:event_EraserButtonActionPerformed

    private void LineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LineButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Line";
    }//GEN-LAST:event_LineButtonActionPerformed

    private void CircleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CircleButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Circle";
    }//GEN-LAST:event_CircleButtonActionPerformed

    private void RectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RectangleButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Rectangle";
    }//GEN-LAST:event_RectangleButtonActionPerformed

    private void ColorsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorsMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ColorsMenuActionPerformed

    private void ColorSelectionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorSelectionItemActionPerformed
        // TODO add your handling code here:
        Color color = JColorChooser.showDialog(this, "Choose a brush color", Color.BLACK);
        toolColor = color;
        ColorChosenPanel.setBackground(color);
    }//GEN-LAST:event_ColorSelectionItemActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you really want to clear your drawings?", "Confirmation", dialogButton);
        if (dialogResult == 0) {
            this.getContentPane().setBackground(canvasColor);
            // remove paint from the JFrame
            this.repaint();
        }


    }//GEN-LAST:event_ClearActionPerformed

    private void CreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditsActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "SwingPaint v1.0.0-alpha\n\nCS128-8L_BM5 Group Project 1Q2022\n\n"
                + " Members:\n"
                + "  LARA, CHARLENE GRAZIELLE\n"
                + "  DELFIN, IVAN ZACHARRIA\n"
                + "  GARCIA, ERVIN MIKHAIL\n"
                + "  INOCENCIO, ZARA NAOMI\n"
                + "  TAYAG, DYLAN LOUIS");
    }//GEN-LAST:event_CreditsActionPerformed

    private void ColorChosenPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ColorChosenPanelMouseClicked
        // TODO add your handling code here:
        Color color = JColorChooser.showDialog(this, "Choose a brush color", Color.BLACK);
        ColorChosenPanel.setBackground(color);
        toolColor = color;
    }//GEN-LAST:event_ColorChosenPanelMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // open new instance of SwingPaint
        Main newInstance = new Main();
        newInstance.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        saveImage();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void ViewDrawingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewDrawingActionPerformed
        // TODO add your handling code here:
        viewImage();
    }//GEN-LAST:event_ViewDrawingActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", dialogButton);
        if (dialogResult == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        // if selected, set stroke size to 5
        if (jRadioButton1.isSelected()) {
            strokeSize = 3;
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        // if selected, set stroke size to 15
        if (jRadioButton2.isSelected()) {
            strokeSize = 7;
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        // if selected, set stroke size to 30
        if (jRadioButton3.isSelected()) {
            strokeSize = 18;
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void ColorChosenPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ColorChosenPanel1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_ColorChosenPanel1MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        // add JOptionPane to ask user if they want to continue
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Changing canvas color may erase your drawings. Continue?", "Clear Canvas", dialogButton);
        if (dialogResult == 0) {
            // if yes, change canvas color
            canvasColor = JColorChooser.showDialog(this, "Choose a canvas color", Color.BLACK);
            jPanel1.setBackground(canvasColor);
            this.getContentPane().setBackground(canvasColor);
            eraserColor = canvasColor;
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Changing canvas color may erase your drawings. Continue?", "Clear Canvas", dialogButton);
        if (dialogResult == 0) {
            // if yes, change canvas color
            Color color = JColorChooser.showDialog(this, "Choose a canvas color", Color.BLACK);
            toolColor = color;
            ColorChosenPanel.setBackground(color);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AboutMenu;
    private javax.swing.JButton BrushButton;
    private javax.swing.JButton CircleButton;
    private javax.swing.JButton Clear;
    private javax.swing.JPanel ColorChosenPanel;
    private javax.swing.JMenuItem ColorSelectionItem;
    private javax.swing.JMenu ColorsMenu;
    private javax.swing.JMenuItem Credits;
    private javax.swing.JButton EraserButton;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JButton LineButton;
    private javax.swing.JMenuBar MainMenuBar;
    private javax.swing.JButton RectangleButton;
    private javax.swing.ButtonGroup StrokeSizeGroup;
    private javax.swing.JLabel TimerLabel;
    private javax.swing.JPanel TimerPanel;
    private javax.swing.JPanel ToolsPanel;
    private javax.swing.JMenuItem ViewDrawing;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
