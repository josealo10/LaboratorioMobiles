package Views;

import Controllers.LoginController;
import static desktop.Application.ESTUDIANTES_CONTROLLER;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alessandro Fazio
 */
public class LoginView extends javax.swing.JFrame implements Observer {

    private LoginController loginController;

    public LoginView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void setController(LoginController loginController) {
        this.loginController = loginController;
    }

    public boolean camposVacios() {
        boolean vacio = false;

        if (jtf_usuario.getText().length() == 0) {
            jl_usuario.setForeground(Color.red);
            vacio = true;
        }

        if (jpf_clave.getText().length() == 0) {
            jl_clave.setForeground(Color.red);
            vacio = true;
        }

        return vacio;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtf_usuario = new javax.swing.JTextField();
        jl_usuario = new javax.swing.JLabel();
        jl_clave = new javax.swing.JLabel();
        jb_ingresar = new javax.swing.JButton();
        jpf_clave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jtf_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtf_usuarioKeyPressed(evt);
            }
        });

        jl_usuario.setText("Usuario:");

        jl_clave.setText("Contraseña:");

        jb_ingresar.setText("Ingresar");
        jb_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ingresarActionPerformed(evt);
            }
        });

        jpf_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpf_claveKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jl_clave)
                    .addComponent(jl_usuario)
                    .addComponent(jtf_usuario)
                    .addComponent(jpf_clave)
                    .addComponent(jb_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_usuario)
                .addGap(1, 1, 1)
                .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jl_clave)
                .addGap(1, 1, 1)
                .addComponent(jpf_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jb_ingresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ingresarActionPerformed
        if (camposVacios()) {
            JOptionPane.showMessageDialog(null, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            this.loginController.login(jtf_usuario.getText(), jpf_clave.getText());

        } catch (Exception exception) {
            jpf_clave.setText("");
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_ingresarActionPerformed

    private void jtf_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_usuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(null, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                this.loginController.login(jtf_usuario.getText(), jpf_clave.getText());

            } catch (Exception exception) {
                jpf_clave.setText("");
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jtf_usuarioKeyPressed

    private void jpf_claveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpf_claveKeyPressed
        this.jtf_usuarioKeyPressed(evt);
    }//GEN-LAST:event_jpf_claveKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jb_ingresar;
    private javax.swing.JLabel jl_clave;
    private javax.swing.JLabel jl_usuario;
    private javax.swing.JPasswordField jpf_clave;
    private javax.swing.JTextField jtf_usuario;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
    }
}
