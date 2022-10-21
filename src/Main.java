/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.imageio.ImageIO;   // saves jpeg image of drawing
import javax.swing.*;           // swing components such as buttons (and menu) 
import java.awt.AWTException;   // used for error handling
import java.awt.BasicStroke;    // sets brush thickness
import java.awt.Color;          // used for color
import java.awt.Desktop;        // allows for screenshot capability
import java.awt.Graphics;       // for drawing line
import java.awt.Graphics2D;     // for drawing circle and rectangle
import java.awt.Rectangle;      // sets dimension for rectangle
import java.awt.Robot;                  // automates screenshot
import java.awt.event.MouseEvent;       // draws on the jframe based on choice
import java.awt.event.MouseListener;        // listens to mouse input of user
import java.awt.event.MouseMotionListener;  // captures coords when dragging
import java.awt.event.WindowAdapter;        // for exit confirmation
import java.awt.event.WindowEvent;   // 
import java.awt.image.BufferedImage; // for screenshot
import java.io.File;        // saving to disk
import java.io.IOException; // file error handling
import java.util.Objects;   // for comparing string literal tools
import java.util.logging.Level; 
import java.util.logging.Logger;
import java.util.Timer;     // for timer event
import java.util.TimerTask; // for updating the UI based on time
import javax.swing.JFrame; 

/**
 * @author 
 * CS128-8L_BM5 Group Project 1Q2022
 *
 * Members: 
 * LARA, CHARLENE GRAZIELLE 
 * DELFIN, IVAN ZACHARRIA 
 * GARCIA, ERVIN MIKHAIL 
 * INOCENCIO, ZARA NAOMI 
 * TAYAG, DYLAN LOUIS
 */
public final class Main extends JFrame implements MouseListener {

    public String selectedTool = "Brush"; // default tool
    public Color toolColor = Color.BLACK; // default brush and shape color
    public Color canvasColor = Color.WHITE; // default canvas color
    public Color eraserColor = canvasColor; // default eraser color
    public int x, y = 0; // default brush and shape coordinates
    public int strokeSize = 7; // default stroke size 
    public boolean drawingIsSaved = false;   
    public static int INCREMENT = 1000;
    public static int BASE_TIME = 0;
    
    public Timer timer = new Timer();
    /**
     * Creates new form Main
     */
    
    public Main() {
        MouseListener brush = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(selectedTool, "Brush")) {
                    selectedTool = "Brush";
                    System.out.println("User has used brush");
                } else if (Objects.equals(selectedTool, "Eraser")) {
                    selectedTool = "Eraser";
                    toolColor = Color.WHITE;
                    System.out.println("User has erased something");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (Objects.equals(selectedTool, "Brush")) {
                    System.out.println("User has used brush");
                } else if (Objects.equals(selectedTool, "Eraser")) {
                    System.out.println("User has used eraser");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        MouseMotionListener brushMotion = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Objects.equals(selectedTool, "Brush")) {
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawOval(e.getX(), e.getY(), strokeSize, strokeSize);
                    ToolsPanel.repaint();
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
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawLine(x, y, e.getX(), e.getY());
                    ToolsPanel.repaint();
                    System.out.println("User has drawn a Line");
                } else if (Objects.equals(selectedTool, "Circle")) {
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawOval(x, y, e.getX() - x, e.getY() - y);
                    ToolsPanel.repaint();
                    System.out.println("User has drawn a Circle");
                } else if (Objects.equals(selectedTool, "Rectangle")) {
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    g2d.drawRect(x, y, e.getX() - x, e.getY() - y);
                    System.out.println("User has drawn a Rectangle");
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

        MouseMotionListener shapesMotion = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Objects.equals(selectedTool, "Line")) {
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    ToolsPanel.repaint();
                } else if (Objects.equals(selectedTool, "Circle")) {
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    ToolsPanel.repaint();
                } else if (Objects.equals(selectedTool, "Rectangle")) {
                    Graphics2D g2d = (Graphics2D) getGraphics();
                    g2d.setColor(toolColor);
                    g2d.setStroke(new BasicStroke(strokeSize));
                    ToolsPanel.repaint();
                }
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (!drawingIsSaved) {
                    String[] ObjButtons = {"Save and exit", "Exit application"};
                    int PromptResult = JOptionPane.showOptionDialog(null, "Save the drawing first before exiting?", "Save file confirmation",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                    if (PromptResult == JOptionPane.YES_OPTION) {
                        try {
                            saveImage();
                        } catch (IOException | AWTException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dispose();
                    } else if (PromptResult == JOptionPane.NO_OPTION) {
                        System.out.println("User has ended the program");
                        dispose();
                    }
                } else {
                    String[] ObjButtons = {"Yes", "No"};
                    int PromptResult = JOptionPane.showOptionDialog(null, "Do you really want to exit?", "Exit confirmation",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                    if (PromptResult == JOptionPane.YES_OPTION) {
                        System.out.println("User has ended the program");
                        dispose();
                    }
                }
            }
        });
        
        
        initComponents();         
        addMouseListener(brush);
        addMouseListener(shapes);
        addMouseMotionListener(brushMotion);
        addMouseMotionListener(shapesMotion);      
        this.setTitle("Swing Paint v1.0.0.0-alpha");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(canvasColor);
        timer.schedule(new ElapsedTime(), BASE_TIME, INCREMENT);
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
        elapsedIndicator = new javax.swing.JLabel();
        TimeLabel = new javax.swing.JLabel();
        LogoLabel = new javax.swing.JLabel();
        Clear = new javax.swing.JButton();
        SelectedColorLabel = new javax.swing.JLabel();
        ColorChosenPanel = new javax.swing.JPanel();
        StrokeSizeLabel = new javax.swing.JLabel();
        SmallStrokeButton = new javax.swing.JRadioButton();
        MediumStrokeButton = new javax.swing.JRadioButton();
        LargeStrokeButton = new javax.swing.JRadioButton();
        CanvasColorLabel = new javax.swing.JLabel();
        CanvasColorChosenPanel = new javax.swing.JPanel();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        NewFileItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        SaveItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        ExitItem = new javax.swing.JMenuItem();
        ColorsMenu = new javax.swing.JMenu();
        ColorSelectionItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        CanvasColorItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        ViewPaintingItem = new javax.swing.JMenuItem();
        AboutMenu = new javax.swing.JMenu();
        CreditsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

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

        elapsedIndicator.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        elapsedIndicator.setForeground(new java.awt.Color(0, 0, 0));
        elapsedIndicator.setText("Time elapsed:");

        TimeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TimeLabel.setForeground(new java.awt.Color(0, 0, 0));
        TimeLabel.setText("pogi ako");

        javax.swing.GroupLayout TimerPanelLayout = new javax.swing.GroupLayout(TimerPanel);
        TimerPanel.setLayout(TimerPanelLayout);
        TimerPanelLayout.setHorizontalGroup(
            TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimerPanelLayout.createSequentialGroup()
                .addGroup(TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TimerPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(elapsedIndicator))
                    .addGroup(TimerPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(TimeLabel)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        TimerPanelLayout.setVerticalGroup(
            TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(elapsedIndicator)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TimeLabel)
                .addGap(18, 18, 18))
        );

        LogoLabel.setBackground(new java.awt.Color(255, 255, 255));
        LogoLabel.setFont(new java.awt.Font("Arial Black", 2, 24)); // NOI18N
        LogoLabel.setForeground(new java.awt.Color(255, 255, 255));
        LogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swingpaint-logo.png"))); // NOI18N

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

        SelectedColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        SelectedColorLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        SelectedColorLabel.setForeground(new java.awt.Color(255, 255, 255));
        SelectedColorLabel.setText("Selected color:");

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

        StrokeSizeLabel.setBackground(new java.awt.Color(255, 255, 255));
        StrokeSizeLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        StrokeSizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        StrokeSizeLabel.setText("Stroke size:");

        StrokeSizeGroup.add(SmallStrokeButton);
        SmallStrokeButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SmallStrokeButton.setForeground(new java.awt.Color(255, 255, 255));
        SmallStrokeButton.setText("Small");
        SmallStrokeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmallStrokeButtonActionPerformed(evt);
            }
        });

        StrokeSizeGroup.add(MediumStrokeButton);
        MediumStrokeButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MediumStrokeButton.setForeground(new java.awt.Color(255, 255, 255));
        MediumStrokeButton.setSelected(true);
        MediumStrokeButton.setText("Medium");
        MediumStrokeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MediumStrokeButtonActionPerformed(evt);
            }
        });

        StrokeSizeGroup.add(LargeStrokeButton);
        LargeStrokeButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LargeStrokeButton.setForeground(new java.awt.Color(255, 255, 255));
        LargeStrokeButton.setText("Large");
        LargeStrokeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LargeStrokeButtonActionPerformed(evt);
            }
        });

        CanvasColorLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        CanvasColorLabel.setForeground(new java.awt.Color(255, 255, 255));
        CanvasColorLabel.setText("Canvas color:");

        CanvasColorChosenPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CanvasColorChosenPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout CanvasColorChosenPanelLayout = new javax.swing.GroupLayout(CanvasColorChosenPanel);
        CanvasColorChosenPanel.setLayout(CanvasColorChosenPanelLayout);
        CanvasColorChosenPanelLayout.setHorizontalGroup(
            CanvasColorChosenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );
        CanvasColorChosenPanelLayout.setVerticalGroup(
            CanvasColorChosenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ToolsPanelLayout = new javax.swing.GroupLayout(ToolsPanel);
        ToolsPanel.setLayout(ToolsPanelLayout);
        ToolsPanelLayout.setHorizontalGroup(
            ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ToolsPanelLayout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(TimerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(ToolsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                        .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RectangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CircleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                        .addComponent(BrushButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EraserButton))
                    .addGroup(ToolsPanelLayout.createSequentialGroup()
                        .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ToolsPanelLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(ColorChosenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(CanvasColorChosenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ToolsPanelLayout.createSequentialGroup()
                                    .addComponent(SelectedColorLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CanvasColorLabel))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ToolsPanelLayout.createSequentialGroup()
                                    .addComponent(SmallStrokeButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(StrokeSizeLabel)
                                        .addGroup(ToolsPanelLayout.createSequentialGroup()
                                            .addComponent(MediumStrokeButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(LargeStrokeButton))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ToolsPanelLayout.setVerticalGroup(
            ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ToolsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(StrokeSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MediumStrokeButton)
                    .addComponent(SmallStrokeButton)
                    .addComponent(LargeStrokeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelectedColorLabel)
                    .addComponent(CanvasColorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ToolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ColorChosenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CanvasColorChosenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(18, 18, 18)
                .addComponent(TimerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        MainMenuBar.setBackground(new java.awt.Color(107, 62, 41));
        MainMenuBar.setForeground(new java.awt.Color(51, 51, 51));

        FileMenu.setText("File");
        FileMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        NewFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        NewFileItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NewFileItem.setText("New File");
        NewFileItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewFileItemActionPerformed(evt);
            }
        });
        FileMenu.add(NewFileItem);
        FileMenu.add(jSeparator1);

        SaveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SaveItem.setText("Save");
        SaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveItemActionPerformed(evt);
            }
        });
        FileMenu.add(SaveItem);
        FileMenu.add(jSeparator2);

        ExitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        ExitItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExitItem.setText("Exit");
        ExitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitItemActionPerformed(evt);
            }
        });
        FileMenu.add(ExitItem);

        MainMenuBar.add(FileMenu);

        ColorsMenu.setText("Colors");
        ColorsMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        ColorsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorsMenuActionPerformed(evt);
            }
        });

        ColorSelectionItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ColorSelectionItem.setText("Select color");
        ColorSelectionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorSelectionItemActionPerformed(evt);
            }
        });
        ColorsMenu.add(ColorSelectionItem);
        ColorsMenu.add(jSeparator3);

        CanvasColorItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CanvasColorItem.setText("Select canvas color");
        CanvasColorItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanvasColorItemActionPerformed(evt);
            }
        });
        ColorsMenu.add(CanvasColorItem);

        MainMenuBar.add(ColorsMenu);

        ViewMenu.setText("View");
        ViewMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        ViewPaintingItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ViewPaintingItem.setText("View painting");
        ViewPaintingItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPaintingItemActionPerformed(evt);
            }
        });
        ViewMenu.add(ViewPaintingItem);

        MainMenuBar.add(ViewMenu);

        AboutMenu.setText("About");
        AboutMenu.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N

        CreditsItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CreditsItem.setText("View details");
        CreditsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditsItemActionPerformed(evt);
            }
        });
        AboutMenu.add(CreditsItem);

        MainMenuBar.add(AboutMenu);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ToolsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(667, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(ToolsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private class ElapsedTime extends TimerTask {
        private int seconds = 0; private int minutes = 0; private int hours = 0;
        @Override
        public void run() {
            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            } if (minutes == 60) {
                minutes = 0;
                hours++;
            }
            TimeLabel.setText(time);
        }
    }
    
    public void saveImage() throws IOException, AWTException {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "swingpaint." + format;
            Rectangle screenRect = new Rectangle(750, 250, 660, 534);
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
            JOptionPane.showMessageDialog(null, "Image saved as " + fileName);
            System.out.println("File saved!");
            drawingIsSaved = true;
        } catch (AWTException ex) {
            System.err.println(ex);
        }
    }

    public void viewImage() throws AWTException {
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

    @Override
    public void mouseClicked(MouseEvent e) {

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

    private void BrushButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrushButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Brush";
        System.out.println(selectedTool + " is selected");
    }//GEN-LAST:event_BrushButtonActionPerformed

    private void EraserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EraserButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Eraser";
        System.out.println(selectedTool + " is selected");
    }//GEN-LAST:event_EraserButtonActionPerformed

    private void LineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LineButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Line";
        System.out.println(selectedTool + " is selected");
    }//GEN-LAST:event_LineButtonActionPerformed

    private void CircleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CircleButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Circle";
        System.out.println(selectedTool + " is selected");
    }//GEN-LAST:event_CircleButtonActionPerformed

    private void RectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RectangleButtonActionPerformed
        // TODO add your handling code here:
        selectedTool = "Rectangle";
        System.out.println(selectedTool + " is selected");
    }//GEN-LAST:event_RectangleButtonActionPerformed

    private void ColorsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorsMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ColorsMenuActionPerformed

    private void ColorSelectionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorSelectionItemActionPerformed
        // TODO add your handling code here:
        Color color = JColorChooser.showDialog(this, "Choose a brush color", Color.BLACK);
        toolColor = color;
        System.out.println("Tool color set to " + toolColor);
        ColorChosenPanel.setBackground(color);
    }//GEN-LAST:event_ColorSelectionItemActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you really want to clear your drawings?", "Confirmation", dialogButton);
        if (dialogResult == 0) {
            System.out.println("Canvas cleared.");
            this.getContentPane().setBackground(canvasColor);
            this.repaint();
        }
    }//GEN-LAST:event_ClearActionPerformed

    private void CreditsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditsItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "SwingPaint v1.0.0-alpha\n\nCS128-8L_BM5 Group Project 1Q2022\n\n"
                + " Members:\n"
                + "  LARA, CHARLENE GRAZIELLE\n"
                + "  DELFIN, IVAN ZACHARRIA\n"
                + "  GARCIA, ERVIN MIKHAIL\n"
                + "  INOCENCIO, ZARA NAOMI\n"
                + "  TAYAG, DYLAN LOUIS");
    }//GEN-LAST:event_CreditsItemActionPerformed

    private void ColorChosenPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ColorChosenPanelMouseClicked
        // TODO add your handling code here:
        Color color = JColorChooser.showDialog(this, "Choose a brush color", Color.BLACK);
        ColorChosenPanel.setBackground(color);
        toolColor = color;
        System.out.println("Tool color set to " + toolColor);
    }//GEN-LAST:event_ColorChosenPanelMouseClicked

    private void NewFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewFileItemActionPerformed
        // TODO add your handling code here:
        Main newInstance = new Main();
        newInstance.setVisible(true);
    }//GEN-LAST:event_NewFileItemActionPerformed

    private void SaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveItemActionPerformed
        // TODO add your handling code here:
        try {          
            saveImage();
        } catch (IOException | AWTException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SaveItemActionPerformed

    private void ViewPaintingItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPaintingItemActionPerformed
        // TODO add your handling code here:
        try {           
            viewImage();
        } catch (AWTException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ViewPaintingItemActionPerformed

    private void ExitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitItemActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", dialogButton);
        if (dialogResult == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_ExitItemActionPerformed

    private void SmallStrokeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmallStrokeButtonActionPerformed
        // TODO add your handling code here:
        if (SmallStrokeButton.isSelected()) {
            strokeSize = 3;
            System.out.println("Stroke size:" + strokeSize);
        }
    }//GEN-LAST:event_SmallStrokeButtonActionPerformed

    private void MediumStrokeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediumStrokeButtonActionPerformed
        // TODO add your handling code here:
        if (MediumStrokeButton.isSelected()) {
            strokeSize = 7;
            System.out.println("Stroke size:" + strokeSize);
        }
    }//GEN-LAST:event_MediumStrokeButtonActionPerformed

    private void LargeStrokeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LargeStrokeButtonActionPerformed
        // TODO add your handling code here:
        if (LargeStrokeButton.isSelected()) {
            strokeSize = 18;
            System.out.println("Stroke size:" + strokeSize);
        }
    }//GEN-LAST:event_LargeStrokeButtonActionPerformed
    
    private void CanvasColorChosenPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CanvasColorChosenPanelMouseClicked
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Changing canvas color may erase your drawings. Continue?", "Clear Canvas", dialogButton);
        if (dialogResult == 0) {
            // if yes, change canvas color
            canvasColor = JColorChooser.showDialog(this, "Choose a canvas color", Color.BLACK);
            CanvasColorChosenPanel.setBackground(canvasColor);
            System.out.println("Canvas color set to " +canvasColor);
            this.getContentPane().setBackground(canvasColor);
            eraserColor = canvasColor;
        }
    }//GEN-LAST:event_CanvasColorChosenPanelMouseClicked

    private void CanvasColorItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanvasColorItemActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Changing canvas color may erase your drawings. Continue?", "Clear Canvas", dialogButton);
        if (dialogResult == 0) {
            // if yes, change canvas color
            Color color = JColorChooser.showDialog(this, "Choose a canvas color", Color.BLACK);
            toolColor = color;
            System.out.println("Canvas color set to " +color);
            ColorChosenPanel.setBackground(color);
        }
    }//GEN-LAST:event_CanvasColorItemActionPerformed

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

        System.out.println("**** LOGS ****\n");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AboutMenu;
    private javax.swing.JButton BrushButton;
    private javax.swing.JPanel CanvasColorChosenPanel;
    private javax.swing.JMenuItem CanvasColorItem;
    private javax.swing.JLabel CanvasColorLabel;
    private javax.swing.JButton CircleButton;
    private javax.swing.JButton Clear;
    private javax.swing.JPanel ColorChosenPanel;
    private javax.swing.JMenuItem ColorSelectionItem;
    private javax.swing.JMenu ColorsMenu;
    private javax.swing.JMenuItem CreditsItem;
    private javax.swing.JButton EraserButton;
    private javax.swing.JMenuItem ExitItem;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JRadioButton LargeStrokeButton;
    private javax.swing.JButton LineButton;
    private javax.swing.JLabel LogoLabel;
    private javax.swing.JMenuBar MainMenuBar;
    private javax.swing.JRadioButton MediumStrokeButton;
    private javax.swing.JMenuItem NewFileItem;
    private javax.swing.JButton RectangleButton;
    private javax.swing.JMenuItem SaveItem;
    private javax.swing.JLabel SelectedColorLabel;
    private javax.swing.JRadioButton SmallStrokeButton;
    private javax.swing.ButtonGroup StrokeSizeGroup;
    private javax.swing.JLabel StrokeSizeLabel;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JPanel TimerPanel;
    private javax.swing.JPanel ToolsPanel;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem ViewPaintingItem;
    private javax.swing.JLabel elapsedIndicator;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
