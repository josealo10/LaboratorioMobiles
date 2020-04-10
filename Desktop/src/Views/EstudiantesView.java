package Views;

import Controllers.EstudiantesController;
import Logic.Carrera;
import Logic.Curso;
import Logic.Estudiante;
import Logic.Usuario;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudiantesView extends javax.swing.JFrame implements Observer {

    private EstudiantesController estudiantesController;
    private int row = -1;

    public EstudiantesView() {
        initComponents();
        setLocationRelativeTo(null);
        jd_crearEstudiante.setLocationRelativeTo(null);
        jd_actualizarEstudiante.setLocationRelativeTo(null);

        ((DefaultTableCellRenderer) jt_estudiantes.getCellRenderer(0, 0)).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setController(EstudiantesController estudiantesController) {
        this.estudiantesController = estudiantesController;
    }

    public void mostrarEstudiante(Estudiante estudiante) {
        jtf_email1.setText(estudiante.getEmail());
        jtf_nombre1.setText(estudiante.getNombre());
        jtf_telefono1.setText(estudiante.getTelefono());

        for (int i = 0; i < jcb_carrera1.getItemCount(); ++i) {
            if (estudiante.getCarrera().getNombre().equals(jcb_carrera1.getItemAt(i))) {
                jcb_carrera1.setSelectedIndex(i);
                break;
            }
        }
    }

    public void limpiarDialog() {
        jtf_email.setText("");
        jtf_nombre.setText("");
        jtf_telefono.setText("");
        jtf_usuario.setText("");
        jpf_clave.setText("");
        jcb_carrera.setSelectedIndex(0);

        jl_email.setForeground(Color.BLACK);
        jl_nombre.setForeground(Color.BLACK);
        jl_telefono.setForeground(Color.BLACK);
        jl_usuario.setForeground(Color.BLACK);
        jl_clave.setForeground(Color.BLACK);
        jl_carrera.setForeground(Color.BLACK);
    }

    public void limpiarErroresCrear() {
        jl_carrera.setForeground(Color.BLACK);
        jl_cedula.setForeground(Color.BLACK);
        jl_clave.setForeground(Color.BLACK);
        jl_email.setForeground(Color.BLACK);
        jl_nombre.setForeground(Color.BLACK);
        jl_telefono.setForeground(Color.BLACK);
        jl_usuario.setForeground(Color.BLACK);
    }

    public boolean camposVaciosCrear() {
        boolean vacio = false;

        if (jtf_cedula.getText().length() == 0) {
            jl_cedula.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_email.getText().length() == 0) {
            jl_email.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_nombre.getText().length() == 0) {
            jl_nombre.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_telefono.getText().length() == 0) {
            jl_telefono.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_usuario.getText().length() == 0) {
            jl_usuario.setForeground(Color.red);
            vacio = true;
        }

        if (jpf_clave.getText().length() == 0) {
            jl_clave.setForeground(Color.red);
            vacio = true;
        }

        if (jcb_carrera.getSelectedIndex() == 0) {
            jl_carrera.setForeground(Color.red);
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidosCrear() {
        boolean invalido = false;

        if (!numeroValido(jtf_telefono.getText())) {
            jl_telefono.setForeground(Color.red);
            invalido = true;
        }

        if (!numeroValido(jtf_cedula.getText())) {
            jl_cedula.setForeground(Color.red);
            invalido = true;
        }

        return invalido;
    }

    public void limpiarErroresActualizar() {
        jl_carrera1.setForeground(Color.BLACK);
        jl_email1.setForeground(Color.BLACK);
        jl_nombre1.setForeground(Color.BLACK);
        jl_telefono1.setForeground(Color.BLACK);
    }

    public boolean camposVaciosActualizar() {
        boolean vacio = false;

        if (jtf_email1.getText().length() == 0) {
            jl_email1.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_nombre1.getText().length() == 0) {
            jl_nombre1.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_telefono1.getText().length() == 0) {
            jl_telefono1.setForeground(Color.red);
            vacio = true;
        }

        if (jcb_carrera1.getSelectedIndex() == 0) {
            jl_carrera1.setForeground(Color.red);
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidosActualizar() {
        boolean invalido = false;

        if (!numeroValido(jtf_telefono1.getText())) {
            jl_telefono1.setForeground(Color.red);
            invalido = true;
        }

        return invalido;
    }

    public boolean numeroValido(String numero) {
        if (numero == null || numero.length() == 0) {
            return false;
        }

        for (char c : numero.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_crearEstudiante = new javax.swing.JDialog();
        jl_nombre = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jl_email = new javax.swing.JLabel();
        jtf_email = new javax.swing.JTextField();
        jl_telefono = new javax.swing.JLabel();
        jtf_telefono = new javax.swing.JTextField();
        jl_carrera = new javax.swing.JLabel();
        jcb_carrera = new javax.swing.JComboBox<>();
        jl_clave = new javax.swing.JLabel();
        jtf_usuario = new javax.swing.JTextField();
        jl_usuario = new javax.swing.JLabel();
        jpf_clave = new javax.swing.JPasswordField();
        jb_crear = new javax.swing.JButton();
        jl_cedula = new javax.swing.JLabel();
        jtf_cedula = new javax.swing.JTextField();
        jd_actualizarEstudiante = new javax.swing.JDialog();
        jl_email1 = new javax.swing.JLabel();
        jtf_email1 = new javax.swing.JTextField();
        jl_telefono1 = new javax.swing.JLabel();
        jb_actualizar = new javax.swing.JButton();
        jtf_telefono1 = new javax.swing.JTextField();
        jl_carrera1 = new javax.swing.JLabel();
        jcb_carrera1 = new javax.swing.JComboBox<>();
        jl_nombre1 = new javax.swing.JLabel();
        jtf_nombre1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_estudiantes = new javax.swing.JTable();
        jb_crearEstudiante = new javax.swing.JButton();
        jb_eliminarEstudiante = new javax.swing.JButton();
        jb_actualizarEstudiante = new javax.swing.JButton();

        jd_crearEstudiante.setTitle("Crear estudiante");
        jd_crearEstudiante.setMinimumSize(new java.awt.Dimension(550, 278));
        jd_crearEstudiante.setResizable(false);

        jl_nombre.setText("Nombre:");

        jl_email.setText("Email:");

        jl_telefono.setText("Teléfono:");

        jl_carrera.setText("Carrera:");

        jcb_carrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONE --" }));

        jl_clave.setText("Contraseña:");

        jl_usuario.setText("Usuario:");

        jb_crear.setText("Crear estudiante");
        jb_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_crearActionPerformed(evt);
            }
        });

        jl_cedula.setText("Cédula:");

        javax.swing.GroupLayout jd_crearEstudianteLayout = new javax.swing.GroupLayout(jd_crearEstudiante.getContentPane());
        jd_crearEstudiante.getContentPane().setLayout(jd_crearEstudianteLayout);
        jd_crearEstudianteLayout.setHorizontalGroup(
            jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_crearEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_crearEstudianteLayout.createSequentialGroup()
                        .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jl_nombre)
                            .addComponent(jl_email)
                            .addComponent(jtf_email, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(jtf_nombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_crearEstudianteLayout.createSequentialGroup()
                                .addComponent(jl_carrera)
                                .addGap(192, 192, 192))
                            .addGroup(jd_crearEstudianteLayout.createSequentialGroup()
                                .addComponent(jl_cedula)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_crearEstudianteLayout.createSequentialGroup()
                                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtf_cedula)
                                    .addComponent(jcb_carrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(jd_crearEstudianteLayout.createSequentialGroup()
                        .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_telefono)
                            .addComponent(jtf_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl_usuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_clave, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpf_clave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))))
            .addGroup(jd_crearEstudianteLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jb_crear)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jd_crearEstudianteLayout.setVerticalGroup(
            jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_crearEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre)
                    .addComponent(jl_cedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_email)
                    .addComponent(jl_carrera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_clave)
                    .addComponent(jl_telefono)
                    .addComponent(jl_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_crearEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpf_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jb_crear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jd_actualizarEstudiante.setTitle("Actualizar estudiante");
        jd_actualizarEstudiante.setMinimumSize(new java.awt.Dimension(550, 205));

        jl_email1.setText("Email:");

        jl_telefono1.setText("Teléfono:");

        jb_actualizar.setText("Actualizar estudiante");
        jb_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_actualizarActionPerformed(evt);
            }
        });

        jl_carrera1.setText("Carrera:");

        jcb_carrera1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONE --" }));

        jl_nombre1.setText("Nombre:");

        javax.swing.GroupLayout jd_actualizarEstudianteLayout = new javax.swing.GroupLayout(jd_actualizarEstudiante.getContentPane());
        jd_actualizarEstudiante.getContentPane().setLayout(jd_actualizarEstudianteLayout);
        jd_actualizarEstudianteLayout.setHorizontalGroup(
            jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_actualizarEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jl_nombre1)
                    .addComponent(jl_email1)
                    .addComponent(jtf_email1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(jtf_nombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_actualizarEstudianteLayout.createSequentialGroup()
                        .addComponent(jl_telefono1)
                        .addGap(182, 182, 182))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_actualizarEstudianteLayout.createSequentialGroup()
                        .addComponent(jl_carrera1)
                        .addGap(192, 192, 192))
                    .addGroup(jd_actualizarEstudianteLayout.createSequentialGroup()
                        .addComponent(jcb_carrera1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_actualizarEstudianteLayout.createSequentialGroup()
                        .addComponent(jtf_telefono1)
                        .addContainerGap())))
            .addGroup(jd_actualizarEstudianteLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jb_actualizar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jd_actualizarEstudianteLayout.setVerticalGroup(
            jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_actualizarEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nombre1)
                    .addComponent(jl_carrera1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_carrera1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_email1)
                    .addComponent(jl_telefono1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_actualizarEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_actualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setTitle("Estudiantes");
        setResizable(false);

        jt_estudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cédula", "Nombre", "Email", "Teléfono", "Carrera"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_estudiantes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_estudiantes);

        jb_crearEstudiante.setText("Crear estudiantes");
        jb_crearEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_crearEstudianteActionPerformed(evt);
            }
        });

        jb_eliminarEstudiante.setText("Eliminar estudiante");
        jb_eliminarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminarEstudianteActionPerformed(evt);
            }
        });

        jb_actualizarEstudiante.setText("Actualizar estudiante");
        jb_actualizarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_actualizarEstudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jb_crearEstudiante)
                        .addGap(134, 134, 134)
                        .addComponent(jb_actualizarEstudiante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                        .addComponent(jb_eliminarEstudiante)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_crearEstudiante)
                    .addComponent(jb_eliminarEstudiante)
                    .addComponent(jb_actualizarEstudiante))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_crearEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_crearEstudianteActionPerformed
        limpiarDialog();
        jd_crearEstudiante.setVisible(true);
    }//GEN-LAST:event_jb_crearEstudianteActionPerformed

    private void jb_actualizarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_actualizarEstudianteActionPerformed
        if (jt_estudiantes.getSelectedRowCount() == 1) {
            row = jt_estudiantes.getSelectedRow();
            mostrarEstudiante(this.estudiantesController.getEstudiantesModel().getEstudiantes().get(row));
            jd_actualizarEstudiante.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_actualizarEstudianteActionPerformed

    private void jb_eliminarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminarEstudianteActionPerformed
        if (jt_estudiantes.getSelectedRowCount() == 1) {
            row = jt_estudiantes.getSelectedRow();
            try {
                this.estudiantesController.deleteEstudianteRequest(this.estudiantesController.getEstudiantesModel().getEstudiantes().get(row).getCedula());

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_eliminarEstudianteActionPerformed

    private void jb_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_actualizarActionPerformed
        limpiarErroresActualizar();

        if (camposVaciosActualizar()) {
            JOptionPane.showMessageDialog(null, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (camposInvalidosActualizar()) {
            JOptionPane.showMessageDialog(null, "Campos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Estudiante estudiante = this.estudiantesController.getEstudiantesModel().getEstudiantes().get(row);
            Estudiante estudianteActualizado = new Estudiante(estudiante.getCedula(), jtf_nombre1.getText(), jtf_telefono1.getText(), jtf_email1.getText(), null, null, null);

            for (Carrera carrera : this.estudiantesController.getEstudiantesModel().getCarreras()) {
                if (carrera.getNombre().equals(jcb_carrera1.getSelectedItem().toString())) {
                    estudianteActualizado.setCarrera(carrera);
                    break;
                }
            }

            this.estudiantesController.putEstudianteRequest(estudianteActualizado, row);
            jd_actualizarEstudiante.setVisible(false);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_actualizarActionPerformed

    private void jb_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_crearActionPerformed
        limpiarErroresCrear();

        if (camposVaciosCrear()) {
            JOptionPane.showMessageDialog(null, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (camposInvalidosCrear()) {
            JOptionPane.showMessageDialog(null, "Campos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Estudiante estudiante = new Estudiante(jtf_cedula.getText(), jtf_nombre.getText(), jtf_telefono.getText(), jtf_email.getText(), null, null, new Usuario(jtf_usuario.getText(), jpf_clave.getText(), "Alumno"));

            for (Carrera carrera : this.estudiantesController.getEstudiantesModel().getCarreras()) {
                if (carrera.getNombre().equals(jcb_carrera.getSelectedItem().toString())) {
                    estudiante.setCarrera(carrera);
                    break;
                }
            }

            this.estudiantesController.postEstudianteRequest(estudiante);
            jd_crearEstudiante.setVisible(false);

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_crearActionPerformed

    @Override
    public void setVisible(boolean b) {
        if (b) {
            try {
                this.estudiantesController.getEstudiantesRequest();
                this.estudiantesController.getCarrerasRequest();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        super.setVisible(b);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((String) arg) {
            case "Estudiantes":
                this.estudiantesController.getEstudiantesModel().setEstudiantesTableModel((DefaultTableModel) jt_estudiantes.getModel());
                this.estudiantesController.getEstudiantesModel().llenarTablaEstudiantes();

                break;

            case "Carreras":
                jcb_carrera.removeAllItems();
                jcb_carrera1.removeAllItems();
                jcb_carrera.addItem("-- SELECCIONAR --");
                jcb_carrera1.addItem("-- SELECCIONAR --");

                for (Carrera carrera : this.estudiantesController.getEstudiantesModel().getCarreras()) {
                    jcb_carrera.addItem(carrera.getNombre());
                    jcb_carrera1.addItem(carrera.getNombre());
                }

                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_actualizar;
    private javax.swing.JButton jb_actualizarEstudiante;
    private javax.swing.JButton jb_crear;
    private javax.swing.JButton jb_crearEstudiante;
    private javax.swing.JButton jb_eliminarEstudiante;
    private javax.swing.JComboBox<String> jcb_carrera;
    private javax.swing.JComboBox<String> jcb_carrera1;
    private javax.swing.JDialog jd_actualizarEstudiante;
    private javax.swing.JDialog jd_crearEstudiante;
    private javax.swing.JLabel jl_carrera;
    private javax.swing.JLabel jl_carrera1;
    private javax.swing.JLabel jl_cedula;
    private javax.swing.JLabel jl_clave;
    private javax.swing.JLabel jl_email;
    private javax.swing.JLabel jl_email1;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_nombre1;
    private javax.swing.JLabel jl_telefono;
    private javax.swing.JLabel jl_telefono1;
    private javax.swing.JLabel jl_usuario;
    private javax.swing.JPasswordField jpf_clave;
    private javax.swing.JTable jt_estudiantes;
    private javax.swing.JTextField jtf_cedula;
    private javax.swing.JTextField jtf_email;
    private javax.swing.JTextField jtf_email1;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTextField jtf_nombre1;
    private javax.swing.JTextField jtf_telefono;
    private javax.swing.JTextField jtf_telefono1;
    private javax.swing.JTextField jtf_usuario;
    // End of variables declaration//GEN-END:variables
}
