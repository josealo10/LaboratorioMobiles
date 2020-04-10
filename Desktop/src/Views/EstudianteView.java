package Views;

import Controllers.EstudianteController;
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
public class EstudianteView extends javax.swing.JFrame implements Observer {

    private EstudianteController estudianteController;

    public EstudianteView() {
        initComponents();
        setLocationRelativeTo(null);
        
        ((DefaultTableCellRenderer) jt_estudiantes.getCellRenderer(0, 0)).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setController(EstudianteController estudianteController) {
        this.estudianteController = estudianteController;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_estudiantes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jl_alumno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jl_carrera = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estudiante");
        setResizable(false);

        jt_estudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Créditos", "Horas semanales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_estudiantes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_estudiantes);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Estudiante:");

        jl_alumno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jl_alumno.setText("Alessandro Fazio Pérez");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Carrera:");

        jl_carrera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jl_carrera.setText("Informática");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Cursos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl_alumno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl_carrera))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jl_alumno)
                    .addComponent(jLabel2)
                    .addComponent(jl_carrera))
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jl_alumno;
    private javax.swing.JLabel jl_carrera;
    private javax.swing.JTable jt_estudiantes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setVisible(boolean b) {
        if (b) {
            try {
                this.estudianteController.getEstudianteRequest();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        super.setVisible(b);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.estudianteController.getEstudiantesModel().setCursosTableModel((DefaultTableModel) jt_estudiantes.getModel());
        this.estudianteController.getEstudiantesModel().llenarTabla();

        jl_alumno.setText(this.estudianteController.getEstudiantesModel().getEstudiante().getNombre());
        jl_carrera.setText(this.estudianteController.getEstudiantesModel().getEstudiante().getCarrera().getNombre());
    }
}
