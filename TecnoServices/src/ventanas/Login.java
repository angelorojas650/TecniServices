package ventanas;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexion;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Login extends javax.swing.JFrame {
    
    public static String user = "";
    String pass = "";

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setSize(400, 550);
        setResizable(false);
        setTitle("Acceso al Sistema");
        setLocationRelativeTo(null);

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_wallpaper.getWidth(),
                label_wallpaper.getHeight(),Image.SCALE_DEFAULT ));
        label_wallpaper.setIcon(icono);
        this.repaint();
        
        ImageIcon wallpaper_logo = new ImageIcon("src/images/TS.png");
        Icon icono_logo = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(label_Logo.getWidth(),
                label_Logo.getHeight(), Image.SCALE_DEFAULT));
        label_Logo.setIcon(icono_logo);
        this.repaint();
    }
    
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_Logo = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        buttonAcceder = new javax.swing.JButton();
        labelFooter = new javax.swing.JLabel();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(label_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 250, 250));

        txtUsuario.setBackground(new java.awt.Color(153, 153, 255));
        txtUsuario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 330, 210, -1));

        txtPassword.setBackground(new java.awt.Color(153, 153, 255));
        txtPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 370, 210, -1));

        buttonAcceder.setBackground(new java.awt.Color(153, 153, 255));
        buttonAcceder.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        buttonAcceder.setForeground(new java.awt.Color(255, 255, 255));
        buttonAcceder.setText("Acceder");
        buttonAcceder.setBorder(null);
        buttonAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccederActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAcceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 420, 210, 30));

        labelFooter.setText("Creado por Angelo Rojas ??");
        getContentPane().add(labelFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, -1, -1));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccederActionPerformed
        user = txtUsuario.getText().trim();
        pass = txtPassword.getText().trim();
        
        if (!user.equals("") || !pass.equals("")) {
            
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select tipo_nivel, estatus from usuarios where username = '" + user
                            + "' and password = '" + pass + "'");
                
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    
                    String tipo_nivel = rs.getString("tipo_nivel");
                    String estatus = rs.getString("estatus");
                    
                    if (tipo_nivel.equalsIgnoreCase("Administrador") && estatus.equalsIgnoreCase("Activo")) {
                        //este metodo cierra la inetrfaz de login
                        dispose();
                        new Administrador().setVisible(true);
                        
                    } else if (tipo_nivel.equalsIgnoreCase("Capturista") && estatus.equalsIgnoreCase("Activo")) {
                        
                        dispose();
                        new Capturista().setVisible(true);
                        
                    } else if (tipo_nivel.equalsIgnoreCase("Tecnico") && estatus.equalsIgnoreCase("Activo")) {
                        
                        dispose();
                        new Tecnico().setVisible(true);
                        
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "????Error!!, Datos de acceso incorrectos.");
                    txtUsuario.setText("");
                    txtPassword.setText("");
                }
                
            } catch (HeadlessException | SQLException e) {
                System.err.println("Error en el bot??n Acceder. " + e);
                JOptionPane.showMessageDialog(null, "????Error al iniciar sesion!!, contacte al administrador.");
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
        }
    }//GEN-LAST:event_buttonAccederActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAcceder;
    private javax.swing.JLabel labelFooter;
    private javax.swing.JLabel label_Logo;
    private javax.swing.JLabel label_wallpaper;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
