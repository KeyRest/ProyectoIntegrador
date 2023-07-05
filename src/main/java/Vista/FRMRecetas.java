/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ControladorFrameRecetas;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FRMRecetas extends javax.swing.JFrame {

    /**
     * Creates new form FRMRecetas
     */
    public FRMRecetas() {
        initComponents();
        this.setVisible(true);
        ControladorFrameRecetas controladorFrameRecetas = new ControladorFrameRecetas(this);
        escuchar(controladorFrameRecetas);
    }
    public void escuchar(ControladorFrameRecetas controladorFrameRecetas) {
        panelBotonesCRUD1.AgregarBTN.addActionListener(controladorFrameRecetas);
        panelBotonesCRUD1.ModificarBTN.addActionListener(controladorFrameRecetas);
        panelBotonesCRUD1.BuscarBTN.addActionListener(controladorFrameRecetas);
        panelBotonesCRUD1.EliminarBTN.addActionListener(controladorFrameRecetas);
        panelIngresoDatos2.agregarBTN.addActionListener(controladorFrameRecetas);
        panelIngresoDatos2.verIngredientesBTN.addActionListener(controladorFrameRecetas);
        panelIngresoDatos2.agregarBTN.addActionListener(controladorFrameRecetas);
        jmiSalir.addActionListener(controladorFrameRecetas);
        jmiRegresar.addActionListener(controladorFrameRecetas);
        jmiAdministrarUsuario.addActionListener(controladorFrameRecetas);
        BTN_Regresar.addActionListener(controladorFrameRecetas);

    }
    public PanelBotonesCRUD getPanelBotonesCRUD1() {
        return panelBotonesCRUD1;
    }

    public PanelIngresoDatos getPanelIngresoDatos2() {
        return panelIngresoDatos2;
    }

    public PanelTabla getPanelTabla1() {
        return panelTabla1;
    }
    public static void mensaje(String msj)
    {
        JOptionPane.showMessageDialog(null,msj);
    }
    public String getTxtId() {
        return this.panelIngresoDatos2.IDReceta.getText().trim();
    }

    public void setTxtId(String ID) {
        this.panelIngresoDatos2.IDReceta.setText(ID);
    }

    public String getTxtnombreReceta() {
        return this.panelIngresoDatos2.nombreReceta.getText().trim();
    }

    public void setTxtnombreReceta(String nombreReceta) {
        this.panelIngresoDatos2.nombreReceta.setText(nombreReceta);
    }

    public String getTxttiempoCoccion() {
        return this.panelIngresoDatos2.tiempoCoccion.getText().trim();
    }

    public void setTxttiempoCoccion(String tiempoCoccion ) {
        this.panelIngresoDatos2.tiempoCoccion.setText(tiempoCoccion);
    }

    public String getTxttiempoTotal() {
        return this.panelIngresoDatos2.tiempoTotal.getText().trim();
    }

    public void setTxttiempoTotal(String tiempoTotal) {
        this.panelIngresoDatos2.tiempoTotal.setText(tiempoTotal);
    }

    public String getTxtinstrucciones() {
        return this.panelIngresoDatos2.instrucciones.getText().trim();
    }

    public void setTxtinstrucciones(String instrucciones) {
        this.panelIngresoDatos2.instrucciones.setText(instrucciones);
    }
    public String getTxtdescripcion() {
        return this.panelIngresoDatos2.descripcion.getText().trim();
    }

    public void setTxtdescripcion(String descripcion) {
        this.panelIngresoDatos2.descripcion.setText(descripcion);
    }
    
    public String getTxttiempoPreparacion() {
        return this.panelIngresoDatos2.tiempoPreparacion.getText().trim();
    }

    public void setTxttiempoPreparacion(String tiempoPreparacion) {
        this.panelIngresoDatos2.tiempoPreparacion.setText(tiempoPreparacion);
    }
    
    public String getTxtporciones() {
        return this.panelIngresoDatos2.tiempoPreparacion.getText().trim();
    }

    public void setTxtporciones(String porciones) {
        this.panelIngresoDatos2.porciones.setText(porciones);
    }
    
    public void limpiar()
    {
        this.panelIngresoDatos2.nombreReceta.setText("");
        this.panelIngresoDatos2.tiempoCoccion.setText("");
        this.panelIngresoDatos2.tiempoTotal.setText("");
        this.panelIngresoDatos2.instrucciones.setText("");
        this.panelIngresoDatos2.descripcion.setText("");
        this.panelIngresoDatos2.IDReceta.setText("");
        this.panelIngresoDatos2.tiempoPreparacion.setText("");
        this.panelIngresoDatos2.porciones.setText("");
        
 
    }
    public void setDatosTabla(String[][]datos,String[] etiquetas,String Titulo)
    {
        this.panelTabla1.jTable1.setModel(new DefaultTableModel(datos, etiquetas));
        this.panelTabla1.jScrollPane1.setViewportView(this.panelTabla1);
    }
    public void escucharTabla(MouseListener control)
    {
        this.panelTabla1.addMouseListener(control);
    }   
    public String getFilaTabla()
    {
        int fila=this.panelTabla1.jTable1.getSelectedRow();
        return this.panelTabla1.jTable1.getModel().getValueAt(fila, 0).toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIngresoDatos2 = new Vista.PanelIngresoDatos();
        panelBotonesCRUD1 = new Vista.PanelBotonesCRUD();
        panelTabla1 = new Vista.PanelTabla();
        BTN_Regresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiAdministrarUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTN_Regresar.setBackground(new java.awt.Color(0, 0, 0));
        BTN_Regresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BTN_Regresar.setForeground(new java.awt.Color(255, 255, 255));
        BTN_Regresar.setText("Regresar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ingrediente", "Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jMenu1.setText("Archivo");

        jmiRegresar.setText("Regresar");
        jMenu1.add(jmiRegresar);

        jmiSalir.setText("Salir");
        jMenu1.add(jmiSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Módulos");

        jmiAdministrarUsuario.setText("Administrar Usuario");
        jMenu2.add(jmiAdministrarUsuario);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(panelIngresoDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BTN_Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelBotonesCRUD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelIngresoDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBotonesCRUD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BTN_Regresar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem jmiAdministrarUsuario;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JMenuItem jmiSalir;
    private Vista.PanelBotonesCRUD panelBotonesCRUD1;
    public Vista.PanelIngresoDatos panelIngresoDatos2;
    private Vista.PanelTabla panelTabla1;
    // End of variables declaration//GEN-END:variables
}
