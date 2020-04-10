package Views;

import Controllers.CursosController;
import Logic.Carrera;
import Logic.Curso;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alessandro Fazio
 */
public class CursosView extends javax.swing.JFrame implements Observer {

    private CursosController cursosController;
    private int row = -1;

    public CursosView() {
        initComponents();
        setLocationRelativeTo(null);

        jd_curso.setLocationRelativeTo(null);
        ((DefaultTableCellRenderer) jt_cursos.getCellRenderer(0, 0)).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setController(CursosController cursosController) {
        this.cursosController = cursosController;
    }

    public void mostrarCurso(Curso curso) {
        jtf_creditos.setText(curso.getCreditos() + "");
        jtf_horasSemanales.setText(curso.getHorasSemanales() + "");
        jtf_nombre.setText(curso.getNombre());

        for (int i = 0; i < jcb_carrera.getItemCount(); ++i) {
            if (curso.getCarrera().getNombre().equals(jcb_carrera.getItemAt(i))) {
                jcb_carrera.setSelectedIndex(i);
                break;
            }
        }
    }

    public void limpiarDialog() {
        jtf_creditos.setText("");
        jtf_horasSemanales.setText("");
        jtf_nombre.setText("");
        jcb_carrera.setSelectedIndex(0);

        jl_carrera.setForeground(Color.BLACK);
        jl_creditos.setForeground(Color.BLACK);
        jl_horasSemanales.setForeground(Color.BLACK);
        jl_nombre.setForeground(Color.BLACK);
    }

    public void limpiarErrores() {
        jl_carrera.setForeground(Color.BLACK);
        jl_creditos.setForeground(Color.BLACK);
        jl_horasSemanales.setForeground(Color.BLACK);
        jl_nombre.setForeground(Color.BLACK);
    }

    public boolean camposVacios() {
        boolean vacio = false;

        if (jtf_creditos.getText().length() == 0) {
            jl_creditos.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_horasSemanales.getText().length() == 0) {
            jl_horasSemanales.setForeground(Color.red);
            vacio = true;
        }

        if (jtf_nombre.getText().length() == 0) {
            jl_nombre.setForeground(Color.red);
            vacio = true;
        }

        if (jcb_carrera.getSelectedIndex() == 0) {
            jl_carrera.setForeground(Color.red);
            vacio = true;
        }

        return vacio;
    }

    public boolean camposInvalidos() {
        boolean invalido = false;

        if (!numeroValido(jtf_creditos.getText())) {
            jl_creditos.setForeground(Color.red);
            invalido = true;
        }

        if (!numeroValido(jtf_horasSemanales.getText())) {
            jl_horasSemanales.setForeground(Color.red);
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

        jd_curso = new javax.swing.JDialog();
        jl_nombre = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jl_creditos = new javax.swing.JLabel();
        jtf_horasSemanales = new javax.swing.JTextField();
        jl_horasSemanales = new javax.swing.JLabel();
        jtf_creditos = new javax.swing.JTextField();
        jl_carrera = new javax.swing.JLabel();
        jcb_carrera = new javax.swing.JComboBox<>();
        jb_cancelar = new javax.swing.JButton();
        jb_crear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_cursos = new javax.swing.JTable();
        jb_crearCurso = new javax.swing.JButton();
        jb_eliminarCurso = new javax.swing.JButton();
        jb_actualizarCurso = new javax.swing.JButton();

        jd_curso.setTitle("Crear curso");
        jd_curso.setMinimumSize(new java.awt.Dimension(549, 205));
        jd_curso.setResizable(false);

        jl_nombre.setText("Nombre:");

        jl_creditos.setText("Créditos:");

        jl_horasSemanales.setText("Horas semanales:");

        jl_carrera.setText("Carrera:");

        jcb_carrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONE --" }));

        jb_cancelar.setText("Cancelar");

        jb_crear.setText("Crear");
        jb_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_crearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jd_cursoLayout = new javax.swing.GroupLayout(jd_curso.getContentPane());
        jd_curso.getContentPane().setLayout(jd_cursoLayout);
        jd_cursoLayout.setHorizontalGroup(
            jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_cursoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jd_cursoLayout.createSequentialGroup()
                            .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jl_creditos)
                                .addComponent(jtf_creditos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtf_horasSemanales, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jl_horasSemanales))))
                    .addComponent(jl_nombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jl_carrera)
                    .addGroup(jd_cursoLayout.createSequentialGroup()
                        .addComponent(jb_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcb_carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jd_cursoLayout.setVerticalGroup(
            jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_cursoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_nombre)
                    .addComponent(jl_carrera, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_horasSemanales)
                    .addComponent(jl_creditos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_cancelar)
                    .addComponent(jtf_horasSemanales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_creditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_crear))
                .addContainerGap())
        );

        setTitle("Cursos");
        setResizable(false);

        jt_cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Créditos", "Horas semanales", "Carrera"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_cursos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_cursos);

        jb_crearCurso.setText("Crear curso");
        jb_crearCurso.setMaximumSize(new java.awt.Dimension(145, 29));
        jb_crearCurso.setMinimumSize(new java.awt.Dimension(145, 29));
        jb_crearCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_crearCursoActionPerformed(evt);
            }
        });

        jb_eliminarCurso.setText("Eliminar curso");
        jb_eliminarCurso.setMaximumSize(new java.awt.Dimension(145, 29));
        jb_eliminarCurso.setMinimumSize(new java.awt.Dimension(145, 29));
        jb_eliminarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminarCursoActionPerformed(evt);
            }
        });

        jb_actualizarCurso.setText("Actualizar curso");
        jb_actualizarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_actualizarCursoActionPerformed(evt);
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
                        .addComponent(jb_crearCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jb_actualizarCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(jb_eliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_crearCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_eliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_actualizarCurso))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_crearCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_crearCursoActionPerformed
        limpiarDialog();
        jd_curso.setTitle("Crear curso");
        jb_crear.setText("Crear");
        jd_curso.setVisible(true);
    }//GEN-LAST:event_jb_crearCursoActionPerformed

    private void jb_actualizarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_actualizarCursoActionPerformed
        limpiarDialog();
        if (jt_cursos.getSelectedRowCount() == 1) {
            row = jt_cursos.getSelectedRow();
            mostrarCurso(this.cursosController.getCursosModel().getCursos().get(jt_cursos.getSelectedRow()));
            jd_curso.setTitle("Actualizar curso");
            jb_crear.setText("Actualizar");
            jd_curso.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jb_actualizarCursoActionPerformed

    private void jb_eliminarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminarCursoActionPerformed

    }//GEN-LAST:event_jb_eliminarCursoActionPerformed

    private void jb_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_crearActionPerformed
        limpiarErrores();

        if (camposVacios()) {
            JOptionPane.showMessageDialog(null, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (camposInvalidos()) {
            JOptionPane.showMessageDialog(null, "Campos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (jb_crear.getText().equals("Actualizar")) {
            try {
                Curso curso = new Curso(this.cursosController.getCursosModel().getCursos().get(row).getCodigo(),
                        Integer.parseInt(jtf_creditos.getText()), Integer.parseInt(jtf_creditos.getText()), jtf_nombre.getText(), null);

                for (Carrera carrera : this.cursosController.getCursosModel().getCarreras()) {
                    if (carrera.getNombre().equals(jcb_carrera.getSelectedItem().toString())) {
                        curso.setCarrera(carrera);
                        break;
                    }
                }

                this.cursosController.putCursoRequest(curso, row);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            try {
                this.cursosController.postCursoRequest(this.cursosController.getCursosModel().getCursos().get(row));

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jb_crearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_actualizarCurso;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_crear;
    private javax.swing.JButton jb_crearCurso;
    private javax.swing.JButton jb_eliminarCurso;
    private javax.swing.JComboBox<String> jcb_carrera;
    private javax.swing.JDialog jd_curso;
    private javax.swing.JLabel jl_carrera;
    private javax.swing.JLabel jl_creditos;
    private javax.swing.JLabel jl_horasSemanales;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JTable jt_cursos;
    private javax.swing.JTextField jtf_creditos;
    private javax.swing.JTextField jtf_horasSemanales;
    private javax.swing.JTextField jtf_nombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setVisible(boolean b) {
        if (b) {
            try {
                this.cursosController.getCursosRequest();
                this.cursosController.getCarrerasRequest();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        super.setVisible(b);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((String) arg) {
            case "Cursos":
                this.cursosController.getCursosModel().setCursosTableModel((DefaultTableModel) jt_cursos.getModel());
                this.cursosController.getCursosModel().getCursosTableModel().setRowCount(0);
                this.cursosController.getCursosModel().llenarTabla();
                break;

            case "Carreras":
                jcb_carrera.removeAllItems();
                jcb_carrera.addItem("-- SELECCIONAR --");

                for (Carrera carrera : this.cursosController.getCursosModel().getCarreras()) {
                    jcb_carrera.addItem(carrera.getNombre());
                }

                break;
        }
    }
}
