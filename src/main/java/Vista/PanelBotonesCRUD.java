/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

/**
 *
 * @author Administrator
 */
public class PanelBotonesCRUD extends javax.swing.JPanel {

    /**
     * Creates new form PanelBotonesCRUD
     */
    public PanelBotonesCRUD() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ModificarBTN = new javax.swing.JButton();
        BuscarBTN = new javax.swing.JButton();
        ActualizarBTN = new javax.swing.JButton();
        EliminarBTN = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ModificarBTN.setBackground(new java.awt.Color(0, 0, 0));
        ModificarBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ModificarBTN.setForeground(new java.awt.Color(255, 255, 255));
        ModificarBTN.setText("Modificar");

        BuscarBTN.setBackground(new java.awt.Color(0, 0, 0));
        BuscarBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BuscarBTN.setForeground(new java.awt.Color(255, 255, 255));
        BuscarBTN.setText("Buscar");

        ActualizarBTN.setBackground(new java.awt.Color(0, 0, 0));
        ActualizarBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ActualizarBTN.setForeground(new java.awt.Color(255, 255, 255));
        ActualizarBTN.setText("Actualizar");

        EliminarBTN.setBackground(new java.awt.Color(0, 0, 0));
        EliminarBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EliminarBTN.setForeground(new java.awt.Color(255, 255, 255));
        EliminarBTN.setText("Eliminar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModificarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BuscarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ActualizarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(EliminarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BuscarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ModificarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ActualizarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ActualizarBTN;
    public javax.swing.JButton BuscarBTN;
    public javax.swing.JButton EliminarBTN;
    public javax.swing.JButton ModificarBTN;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
