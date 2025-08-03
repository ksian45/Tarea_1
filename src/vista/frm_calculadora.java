/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

/**
 *
 * @author UMG
 */

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class frm_calculadora extends javax.swing.JFrame {
    private static final String HISTORIAL_FILE = "bitacoraCalculadora";
    public float num1;
    public float num2;
    public String ope;
    
    public String entero(float resultado){
        String retorno="";
        retorno=Float.toString(resultado);
        
        if (resultado%1==0){
            retorno=retorno.substring(0, retorno.length()-2);
        }
        return retorno;
    }
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frm_calculadora.class.getName());

    /**
     * Creates new form Calculadora
     */
    // En el constructor de frm_calculadora():
public frm_calculadora() {
    initComponents();
    this.setLocationRelativeTo(null);
    crearMenu();
    configurarEventosTeclado(); // Reemplaza configurarAtajosTeclado()
}

// Nuevo método para configurar eventos de teclado
private void configurarEventosTeclado() {
    this.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            Toolkit.getDefaultToolkit().beep();
            
            switch(e.getKeyCode()) {
                // Teclado convencional (números arriba de las letras) + Numpad
                case KeyEvent.VK_0:
                case KeyEvent.VK_NUMPAD0:
                    addNum("0");
                    break;
                case KeyEvent.VK_1:
                case KeyEvent.VK_NUMPAD1:
                    addNum("1");
                    break;
                case KeyEvent.VK_2:
                case KeyEvent.VK_NUMPAD2:
                    addNum("2");
                    break;
                case KeyEvent.VK_3:
                case KeyEvent.VK_NUMPAD3:
                    addNum("3");
                    break;
                case KeyEvent.VK_4:
                case KeyEvent.VK_NUMPAD4:
                    addNum("4");
                    break;
                case KeyEvent.VK_5:
                case KeyEvent.VK_NUMPAD5:
                    addNum("5");
                    break;
                case KeyEvent.VK_6:
                case KeyEvent.VK_NUMPAD6:
                    addNum("6");
                    break;
                case KeyEvent.VK_7:
                case KeyEvent.VK_NUMPAD7:
                    addNum("7");
                    break;
                case KeyEvent.VK_8:
                case KeyEvent.VK_NUMPAD8:
                    addNum("8");
                    break;
                case KeyEvent.VK_9:
                case KeyEvent.VK_NUMPAD9:
                    addNum("9");
                    break;
                
                // Operadores (teclado normal + numpad)
                case KeyEvent.VK_ADD:
                case KeyEvent.VK_PLUS:  // Algunos teclados usan VK_PLUS para el numpad
                    setOperacion("+");
                    break;
                case KeyEvent.VK_SUBTRACT:
                case KeyEvent.VK_MINUS:  // Algunos teclados usan VK_MINUS para el numpad
                    setOperacion("-");
                    break;
                case KeyEvent.VK_MULTIPLY:
                    setOperacion("*");
                    break;
                case KeyEvent.VK_DIVIDE:
                    setOperacion("/");
                    break;
                    
                // Otras teclas
                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_EQUALS:  // Tecla '=' en algunos teclados
                    calcularResultado();
                    break;
                case KeyEvent.VK_DECIMAL:
                case KeyEvent.VK_PERIOD:  // Punto en el numpad
                    if(!txt_operacion.getText().contains(".")) {
                        addNum(".");
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                case KeyEvent.VK_CLEAR:  // Tecla Clear en algunos teclados
                    txt_operacion.setText("");
                    registrarOperacion("Clear - Pantalla borrada");
                    break;
            }
        }
    });
    
    this.setFocusable(true);
    this.requestFocusInWindow();
}

// Método auxiliar para configurar operaciones
private void setOperacion(String operador) {
    try {
        if (!txt_operacion.getText().isEmpty()) {
            num1 = Float.parseFloat(txt_operacion.getText());
            ope = operador;
            registrarOperacion("Operación seleccionada: " + ope + " (" + num1 + " " + ope + " )");
            txt_operacion.setText("");
        }
    } catch (NumberFormatException e) {
        txt_operacion.setText("Error");
    }
}

// Método auxiliar para calcular el resultado
private void calcularResultado() {
    num2 = Float.parseFloat(txt_operacion.getText());
    String operacion = num1 + " " + ope + " " + num2 + " = ";
    
    switch(ope) {
        case "+":
            txt_operacion.setText(entero(num1+num2));
            operacion += (num1+num2);
            break;
        case "-":
            txt_operacion.setText(entero(num1-num2));
            operacion += (num1-num2);
            break;
        case "*":
            txt_operacion.setText(entero(num1*num2));
            operacion += (num1*num2);
            break;
        case "/":
            if(num2==0){
                txt_operacion.setText("No se puede dividir por cero");
                operacion += "Error: División por cero";
            } else {
                txt_operacion.setText(entero(num1/num2));
                operacion += (num1/num2);
            }
            break;
    }
    
    registrarOperacion(operacion);
}

// Modificar el método addNum para la JLabel
public void addNum(String val) {
    txt_operacion.setText(txt_operacion.getText() + val);
}
    
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menú Opciones
        JMenu menuOpciones = new JMenu("Opciones");
        JMenuItem itemNuevo = new JMenuItem("Nuevo");
        JMenuItem itemHistorial = new JMenuItem("Historial");
        
        itemNuevo.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            registrarOperacion("Nuevo - Calculadora reiniciada");
            txt_operacion.setText("");
        });
        
        itemHistorial.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            mostrarHistorial();
        });
        
        menuOpciones.add(itemNuevo);
        menuOpciones.add(itemHistorial);
        
        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemManual = new JMenuItem("Manual de Usuario");
        
        itemManual.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, 
                "Manual de Usuario:\n\n" +
                "1. Ingrese números con los botones o teclado\n" +
                "2. Seleccione operación (+, -, *, /)\n" +
                "3. Presione = para obtener resultado\n" +
                "4. Use Clear para borrar\n" +
                "5. Acceda al historial desde el menú Opciones", 
                "Manual de Usuario", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        menuAyuda.add(itemManual);
        
        menuBar.add(menuOpciones);
        menuBar.add(menuAyuda);
        
        this.setJMenuBar(menuBar);
    }
    

    
    private void registrarOperacion(String operacion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORIAL_FILE, true))) {
            String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(fecha + " - " + operacion);
            writer.newLine();
        } catch (IOException e) {
            logger.severe("Error al escribir en el archivo de historial: " + e.getMessage());
        }
    }
    
    private void mostrarHistorial() {
    try {
        java.io.File file = new java.io.File(HISTORIAL_FILE);
        
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No hay historial disponible", "Historial", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Crear un panel personalizado con botón de eliminar
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textoHistorial = new JTextArea(15, 40);
        textoHistorial.setEditable(false);
        
        // Leer el historial
        java.util.Scanner scanner = new java.util.Scanner(file);
        StringBuilder historial = new StringBuilder();
        while (scanner.hasNextLine()) {
            historial.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        textoHistorial.setText(historial.toString());
        
        // Botón para eliminar historial
        JButton btnEliminar = new JButton("Eliminar Historial");
        btnEliminar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                "¿Estás seguro de eliminar todo el historial?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (file.delete()) {
                    JOptionPane.showMessageDialog(this, "Historial eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    panel.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el historial", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(new JScrollPane(textoHistorial), BorderLayout.CENTER);
        panel.add(btnEliminar, BorderLayout.SOUTH);
        
        // Mostrar el diálogo
        JOptionPane.showMessageDialog(
            this, 
            panel, 
            "Historial de Operaciones", 
            JOptionPane.PLAIN_MESSAGE
        );
        
    } catch (IOException e) {
        logger.severe("Error al leer el archivo de historial: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Error al leer el historial", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_operacion = new javax.swing.JLabel();
        btn_siete = new javax.swing.JButton();
        btn_cuatro = new javax.swing.JButton();
        btn_uno = new javax.swing.JButton();
        btn_cero = new javax.swing.JButton();
        btn_ocho = new javax.swing.JButton();
        btn_cinco = new javax.swing.JButton();
        btn_dos = new javax.swing.JButton();
        btn_punto = new javax.swing.JButton();
        btn_nueve = new javax.swing.JButton();
        btn_seis = new javax.swing.JButton();
        btn_tres = new javax.swing.JButton();
        btn_igual = new javax.swing.JButton();
        btn_suma = new javax.swing.JButton();
        btn_resta = new javax.swing.JButton();
        btn_multi = new javax.swing.JButton();
        btn_division = new javax.swing.JButton();
        btn_clear = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(330, 450));
        setPreferredSize(new java.awt.Dimension(330, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_operacion.setBackground(new java.awt.Color(204, 255, 204));
        txt_operacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_operacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_operacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        txt_operacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_operacionKeyPressed(evt);
            }
        });
        getContentPane().add(txt_operacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 280, 40));

        btn_siete.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_siete.setText("7");
        btn_siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sieteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_siete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 51, 48));

        btn_cuatro.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_cuatro.setText("4");
        btn_cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cuatroActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 51, 48));

        btn_uno.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_uno.setText("1");
        btn_uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_unoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 51, 48));

        btn_cero.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_cero.setText("0");
        btn_cero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ceroActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 51, 48));

        btn_ocho.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_ocho.setText("8");
        btn_ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ochoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 51, 48));

        btn_cinco.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_cinco.setText("5");
        btn_cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cincoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 51, 48));

        btn_dos.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_dos.setText("2");
        btn_dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dosActionPerformed(evt);
            }
        });
        getContentPane().add(btn_dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 51, 48));

        btn_punto.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_punto.setText(".");
        btn_punto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_puntoActionPerformed(evt);
            }
        });
        getContentPane().add(btn_punto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 51, 48));

        btn_nueve.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_nueve.setText("9");
        btn_nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nueveActionPerformed(evt);
            }
        });
        getContentPane().add(btn_nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 51, 48));

        btn_seis.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_seis.setText("6");
        btn_seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seisActionPerformed(evt);
            }
        });
        getContentPane().add(btn_seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 51, 48));

        btn_tres.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_tres.setText("3");
        btn_tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tresActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 51, 48));

        btn_igual.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_igual.setText("=");
        btn_igual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_igualActionPerformed(evt);
            }
        });
        getContentPane().add(btn_igual, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 51, 48));

        btn_suma.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_suma.setText("+");
        btn_suma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sumaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_suma, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 51, 48));

        btn_resta.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_resta.setText("-");
        btn_resta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_resta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 51, 48));

        btn_multi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_multi.setText("*");
        btn_multi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_multiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_multi, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 51, 48));

        btn_division.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_division.setText("/");
        btn_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_divisionActionPerformed(evt);
            }
        });
        getContentPane().add(btn_division, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 51, 48));

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        getContentPane().add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 70, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ceroActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("0");
    }//GEN-LAST:event_btn_ceroActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        Toolkit.getDefaultToolkit().beep();
        registrarOperacion("Clear - Pantalla borrada");
        txt_operacion.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_unoActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("1");
    }//GEN-LAST:event_btn_unoActionPerformed

    private void btn_dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dosActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("2");
    }//GEN-LAST:event_btn_dosActionPerformed

    private void btn_tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tresActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("3");
    }//GEN-LAST:event_btn_tresActionPerformed

    private void btn_cuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cuatroActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("4");
    }//GEN-LAST:event_btn_cuatroActionPerformed

    private void btn_cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cincoActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("5");
    }//GEN-LAST:event_btn_cincoActionPerformed

    private void btn_seisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seisActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("6");
    }//GEN-LAST:event_btn_seisActionPerformed

    private void btn_sieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sieteActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("7");
    }//GEN-LAST:event_btn_sieteActionPerformed

    private void btn_ochoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ochoActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("8");
    }//GEN-LAST:event_btn_ochoActionPerformed

    private void btn_nueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nueveActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        addNum("9");
    }//GEN-LAST:event_btn_nueveActionPerformed

    private void btn_puntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_puntoActionPerformed
        // TODO add your handling code here:
        Toolkit.getDefaultToolkit().beep();
        if(!(txt_operacion.getText().contains("."))){
        addNum(".");
    }//GEN-LAST:event_btn_puntoActionPerformed
    }
    private void btn_igualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_igualActionPerformed
        // TODO add your handling code here:
         calcularResultado();
    Toolkit.getDefaultToolkit().beep();    
    }//GEN-LAST:event_btn_igualActionPerformed

    private void btn_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_divisionActionPerformed
        // TODO add your handling code here:
        setOperacion("/");
    Toolkit.getDefaultToolkit().beep();
    }//GEN-LAST:event_btn_divisionActionPerformed

    private void btn_multiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_multiActionPerformed
        // TODO add your handling code here:
        setOperacion("*");
    Toolkit.getDefaultToolkit().beep();
    }//GEN-LAST:event_btn_multiActionPerformed

    private void btn_restaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restaActionPerformed
        // TODO add your handling code here:
        setOperacion("-");
    Toolkit.getDefaultToolkit().beep();
    }//GEN-LAST:event_btn_restaActionPerformed

    private void btn_sumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sumaActionPerformed
        // TODO add your handling code here:
        setOperacion("+");
    Toolkit.getDefaultToolkit().beep();
    }//GEN-LAST:event_btn_sumaActionPerformed

    private void txt_operacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_operacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_operacionKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frm_calculadora().setVisible(true));
    }
/*
    public void addNum(String val){
        txt_operacion.setText(txt_operacion.getText()+val);
    }
 
    public void addOpe(String ope){
        txt_operacion.setText(txt_operacion.getText()+ope);
    }*/
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cero;
    private javax.swing.JButton btn_cinco;
    private javax.swing.JToggleButton btn_clear;
    private javax.swing.JButton btn_cuatro;
    private javax.swing.JButton btn_division;
    private javax.swing.JButton btn_dos;
    private javax.swing.JButton btn_igual;
    private javax.swing.JButton btn_multi;
    private javax.swing.JButton btn_nueve;
    private javax.swing.JButton btn_ocho;
    private javax.swing.JButton btn_punto;
    private javax.swing.JButton btn_resta;
    private javax.swing.JButton btn_seis;
    private javax.swing.JButton btn_siete;
    private javax.swing.JButton btn_suma;
    private javax.swing.JButton btn_tres;
    private javax.swing.JButton btn_uno;
    private javax.swing.JLabel txt_operacion;
    // End of variables declaration//GEN-END:variables
}
