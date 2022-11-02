package ventanas;

import java.sql.*;
import clases.Conexion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import static ventanas.GestionarClientes.IDcliente_update;

/**
 *
 * @author Asus
 */
public class InformacionCliente extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();

    int IDcliente_update = 0;
    public static int IDequipo = 0;
    String user = "";

    /**
     * Creates new form InformacionCliente
     */
    public InformacionCliente() {
        initComponents();
        user = Login.user;
        IDcliente_update = GestionarClientes.IDcliente_update;

        setSize(630, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(labelWallpaper.getWidth(),
                labelWallpaper.getHeight(), Image.SCALE_DEFAULT));
        labelWallpaper.setIcon(icono);
        this.repaint();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select * from clientes where id_cliente = '" + IDcliente_update + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                setTitle("Informacion del cliente " + rs.getString("nombre_cliente") + " - Sesión de " + user);
                labelTitulo.setText("Información del cliente " + rs.getString("nombre_cliente"));

                txtNombre.setText(rs.getString("nombre_cliente"));
                txtMail.setText(rs.getString("email_cliente"));
                txtTelefono.setText(rs.getString("tel_cliente"));
                txtDireccion.setText(rs.getString("dir_cliente"));
                txtUltimaModificacion.setText(rs.getString("ultima_modificacion"));
            }
            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al cargar usuario. " + e);
            JOptionPane.showMessageDialog(null, "!!Error al cargar¡¡ contacte al administrador");
        }

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = '"
                    + IDcliente_update + "'");

            ResultSet rs = pst.executeQuery();

            tableEquipos = new JTable(model);
            jScrollPane1.setViewportView(tableEquipos);

            model.addColumn("ID equipo");
            model.addColumn(" Tipo de equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus");

            while (rs.next()) {

                Object[] fila = new Object[4];

                for (int i = 0; i < 4; i++) {

                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);

            }
            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al llenar la tabla equipos. " + e);

        }

        tableEquipos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = tableEquipos.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    IDequipo = (int) model.getValueAt(fila_point, columna_point);
                    InformacionEquipo informacionEquipo = new InformacionEquipo();
                    informacionEquipo.setVisible(true);

                }

            }
        });

    }

    @Override
    public Image getIconImage() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableEquipos = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelNombre1 = new javax.swing.JLabel();
        labelNombre2 = new javax.swing.JLabel();
        labelNombre3 = new javax.swing.JLabel();
        labelNombre4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        labelFooter = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtUltimaModificacion = new javax.swing.JTextField();
        btnRegistrarEquipo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnImprimirReporte = new javax.swing.JButton();
        labelWallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableEquipos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 370, 180));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("Información del Cliente");
        getContentPane().add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        labelNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre.setText("Nombre:");
        getContentPane().add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        labelNombre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNombre1.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre1.setText("Em@il:");
        getContentPane().add(labelNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        labelNombre2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNombre2.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre2.setText("Télefono:");
        getContentPane().add(labelNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        labelNombre3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNombre3.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre3.setText("Dirección:");
        getContentPane().add(labelNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        labelNombre4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNombre4.setForeground(new java.awt.Color(255, 255, 255));
        labelNombre4.setText("Última Modificación por:");
        getContentPane().add(labelNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txtNombre.setBackground(new java.awt.Color(153, 153, 255));
        txtNombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        labelFooter.setText("Creado Por Angelo Rojas ®");
        getContentPane().add(labelFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, -1));

        txtMail.setBackground(new java.awt.Color(153, 153, 255));
        txtMail.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtMail.setForeground(new java.awt.Color(255, 255, 255));
        txtMail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        txtTelefono.setBackground(new java.awt.Color(153, 153, 255));
        txtTelefono.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        txtDireccion.setBackground(new java.awt.Color(153, 153, 255));
        txtDireccion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        txtUltimaModificacion.setBackground(new java.awt.Color(153, 153, 255));
        txtUltimaModificacion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtUltimaModificacion.setForeground(new java.awt.Color(255, 255, 255));
        txtUltimaModificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUltimaModificacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtUltimaModificacion.setEnabled(false);
        getContentPane().add(txtUltimaModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

        btnRegistrarEquipo.setBackground(new java.awt.Color(153, 153, 255));
        btnRegistrarEquipo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        btnRegistrarEquipo.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarEquipo.setText("Registrar Equipo");
        btnRegistrarEquipo.setBorder(null);
        btnRegistrarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarEquipoActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrarEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 210, 35));

        btnActualizar.setBackground(new java.awt.Color(153, 153, 255));
        btnActualizar.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar Cliente");
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 210, 35));

        btnImprimirReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/impresora.png"))); // NOI18N
        btnImprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirReporteActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimirReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 120, 100));
        getContentPane().add(labelWallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEquipoActionPerformed

        RegistrarEquipo registrarRquipo = new RegistrarEquipo();
        registrarRquipo.setVisible(true);

    }//GEN-LAST:event_btnRegistrarEquipoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        int validacion = 0;
        String nombre, mail, telefono, direccion;

        nombre = txtNombre.getText().trim();
        mail = txtMail.getText().trim();
        telefono = txtTelefono.getText().trim();
        direccion = txtDireccion.getText().trim();

        if (nombre.equals("")) {
            txtNombre.setBackground(Color.red);
            validacion++;
        }
        if (mail.equals("")) {
            txtMail.setBackground(Color.red);
            validacion++;
        }
        if (telefono.equals("")) {
            txtTelefono.setBackground(Color.red);
            validacion++;
        }
        if (direccion.equals("")) {
            txtDireccion.setBackground(Color.red);
            validacion++;
        }

        if (validacion == 0) {

            try {

                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "update clientes set nombre_cliente=?, email_cliente=?, tel_cliente=?, dir_cliente=?, ultima_modificacion=? "
                        + "where id_cliente = '" + IDcliente_update + "'");

                pst.setString(1, nombre);
                pst.setString(2, mail);
                pst.setString(3, telefono);
                pst.setString(4, direccion);
                pst.setString(5, user);

                pst.executeUpdate();
                cn.close();

                Limpiar();

                txtNombre.setBackground(Color.green);
                txtMail.setBackground(Color.green);
                txtTelefono.setBackground(Color.green);
                txtDireccion.setBackground(Color.green);
                txtUltimaModificacion.setText(user);

                JOptionPane.showMessageDialog(null, "Actualización correcta.");
                this.dispose();

            } catch (SQLException e) {
                System.err.println("Error en actualizar cliente" + e);
                JOptionPane.showMessageDialog(null, "!!ERROR al actualizar cliente¡¡ contacte al administrador.");

            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos.");
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnImprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirReporteActionPerformed

        Document documento = new Document();

        try {

            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Documents/" + txtNombre.getText().trim() + ".pdf"));

            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.jpg");
            header.scaleToFit(550, 700);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Información del cliente\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();

            documento.add(header);
            documento.add(parrafo);

            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.addCell("ID");
            tablaCliente.addCell("Nombre");
            tablaCliente.addCell("Em@il");
            tablaCliente.addCell("Télefono");
            tablaCliente.addCell("Dirección");

            try {

                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from clientes where id_cliente = '" + IDcliente_update + "'");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    do {

                        tablaCliente.addCell(rs.getString(1));
                        tablaCliente.addCell(rs.getString(2));
                        tablaCliente.addCell(rs.getString(3));
                        tablaCliente.addCell(rs.getString(4));
                        tablaCliente.addCell(rs.getString(5));

                    } while (rs.next());

                    documento.add(tablaCliente);
                }

                Paragraph parrafo2 = new Paragraph();
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n \n Equipos registrados. \n \n ");
                parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

                documento.add(parrafo2);

                PdfPTable tablaEquipos = new PdfPTable(4);
                tablaEquipos.addCell("ID equipo");
                tablaEquipos.addCell("Tipo");
                tablaEquipos.addCell("Marca");
                tablaEquipos.addCell("Estatus");

                try {

                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                            "select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = '" + IDcliente_update + "'");

                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next()) {

                        do {

                            tablaEquipos.addCell(rs2.getString(1));
                            tablaEquipos.addCell(rs2.getString(2));
                            tablaEquipos.addCell(rs2.getString(3));
                            tablaEquipos.addCell(rs2.getString(4));

                        } while (rs2.next());
                        
                        documento.add(tablaEquipos);
                    }

                } catch (SQLException e) {
                    System.err.println("Error al obtener los datos del equipo del cliente. " + e);
                }

            } catch (SQLException e) {
                System.err.println("Error al obtener los datos del cliente. " + e);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente.");

        } catch (DocumentException | IOException e) {
            System.err.println("Error en pdf o ruta de imagen. " + e);
            JOptionPane.showMessageDialog(null, "!!ERROR al general el documento¡¡ contacte al administrador..");

        }
    }//GEN-LAST:event_btnImprimirReporteActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnImprimirReporte;
    private javax.swing.JButton btnRegistrarEquipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFooter;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelNombre1;
    private javax.swing.JLabel labelNombre2;
    private javax.swing.JLabel labelNombre3;
    private javax.swing.JLabel labelNombre4;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelWallpaper;
    private javax.swing.JTable tableEquipos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUltimaModificacion;
    // End of variables declaration//GEN-END:variables

    public void Limpiar() {
        txtNombre.setText("");
        txtMail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

}
