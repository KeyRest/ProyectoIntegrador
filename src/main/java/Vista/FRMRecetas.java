/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controladores.Vistas.ControladorFrameRecetas;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        this.panelBotones1.escuchar(controladorFrameRecetas);
        this.panelIngredientes2.escuchar(controladorFrameRecetas);
                this.setLocationRelativeTo(null);

    }

    public void escuchar(ControladorFrameRecetas controladorFrameRecetas) {
        panelBotones1.AgregarBTN.addActionListener(controladorFrameRecetas);
        panelBotones1.ModificarBTN.addActionListener(controladorFrameRecetas);
        panelBotones1.BuscarBTN.addActionListener(controladorFrameRecetas);
        panelBotones1.EliminarBTN.addActionListener(controladorFrameRecetas);
        panelBotones1.BTN_Regresar.addActionListener(controladorFrameRecetas);
        jmiSalir.addActionListener(controladorFrameRecetas);
        jmiRegresar.addActionListener(controladorFrameRecetas);
        jmiAdministrarUsuario.addActionListener(controladorFrameRecetas);
        this.addMouseListener(controladorFrameRecetas);
        this.panelTablaRecetas.addMouseListener(controladorFrameRecetas);
        this.Tablaingredientes.addMouseListener(controladorFrameRecetas);

    }

    public PanelBotones panelBotones1() {
        return panelBotones1;
    }

    public PanelIngresoDatos getpanelIngresoDatos1() {
        return panelIngresoDatos1;
    }

    public PanelTabla getPanelTablaRecetas() {
        return panelTablaRecetas;
    }

    public PanelIngredientes getpanelIngredientes1() {
        return panelIngredientes2;
    }

    public JTable getTablaingredientes() {
        return Tablaingredientes;
    }

    public static void mensaje(String msj) {
        JOptionPane.showMessageDialog(null, msj);
    }

    public String getTxtId() {
        return this.panelIngresoDatos1.IDReceta.getText().trim();
    }

    public void setTxtId(String ID) {
        this.panelIngresoDatos1.IDReceta.setText(ID);
    }

    public String getTxtnombreReceta() {
        return this.panelIngresoDatos1.nombreReceta.getText().trim();
    }

    public void setTxtnombreReceta(String nombreReceta) {
        this.panelIngresoDatos1.nombreReceta.setText(nombreReceta);
    }

    public String getTxttiempoCoccion() {
        return this.panelIngresoDatos1.tiempoCoccion.getText().trim();
    }

    public void setTxttiempoCoccion(String tiempoCoccion) {
        this.panelIngresoDatos1.tiempoCoccion.setText(tiempoCoccion);
    }

    public String getTxttiempoTotal() {
        return this.panelIngresoDatos1.tiempoTotal.getText().trim();
    }

    public void setTxttiempoTotal(String tiempoTotal) {
        this.panelIngresoDatos1.tiempoTotal.setText(tiempoTotal);
    }

    public String getTxtinstrucciones() {
        return this.panelIngresoDatos1.instrucciones.getText().trim();
    }

    public void setTxtinstrucciones(String instrucciones) {
        this.panelIngresoDatos1.instrucciones.setText(instrucciones);
    }

    public String getTxtdescripcion() {
        return this.panelIngresoDatos1.descripcion.getText().trim();
    }

    public void setTxtdescripcion(String descripcion) {
        this.panelIngresoDatos1.descripcion.setText(descripcion);
    }

    public String getTxttiempoPreparacion() {
        return this.panelIngresoDatos1.tiempoPreparacion.getText().trim();
    }

    public void setTxttiempoPreparacion(String tiempoPreparacion) {
        this.panelIngresoDatos1.tiempoPreparacion.setText(tiempoPreparacion);
    }

    public String getTxtporciones() {
        return this.panelIngresoDatos1.tiempoPreparacion.getText().trim();
    }

    public void setTxtporciones(String porciones) {
        this.panelIngresoDatos1.porciones.setText(porciones);
    }
    //TablaIngredientes

    public int getNombreIngredientesID() {
        return this.panelIngredientes2.jComboBoxIngredientes.getSelectedIndex()+1;
    }
    
    public String getNombreIngredientes() {
        return this.panelIngredientes2.jComboBoxIngredientes.getSelectedItem().toString();
    }

    public int getUnidadIngredientes() {
        return this.panelIngredientes2.jComboBoxMedidas.getSelectedIndex()+1;
    }

    public String getTxtCantidadIngredientes() { 
        return this.panelIngredientes2.txtCantidadIngredientes.getText().trim();
    }

    public void setTxtCantidadIngredientes(String cantidadIngredientes) {
        this.panelIngredientes2.txtCantidadIngredientes.setText(cantidadIngredientes);
    }

    public int getLevel(){
        return this.panelIngresoDatos1.jComboBoxLevel.getSelectedIndex()+1;
    }
    public void limpiar() {
        this.panelIngresoDatos1.nombreReceta.setText("");
        this.panelIngresoDatos1.tiempoCoccion.setText("");
        this.panelIngresoDatos1.tiempoTotal.setText("");
        this.panelIngresoDatos1.instrucciones.setText("");
        this.panelIngresoDatos1.descripcion.setText("");
        this.panelIngresoDatos1.IDReceta.setText("");
        this.panelIngresoDatos1.tiempoPreparacion.setText("");
        this.panelIngresoDatos1.porciones.setText("");
        this.panelIngredientes2.txtCantidadIngredientes.setText("");

    }

    public void setDatosTablaRecetas(String[][] datos, String[] etiquetas, String Titulo) {
        DefaultTableModel model = (DefaultTableModel) panelTablaRecetas.TablaReceta.getModel();

        // Limpiar la tabla
        model.setRowCount(0);
        model.setColumnCount(0);

        // Establecer los nuevos datos y etiquetas
        model.setDataVector(datos, etiquetas);

        // Actualizar la vista de la tabla
        panelTablaRecetas.PanelTabla_jScrollPane.setViewportView(panelTablaRecetas.TablaReceta);

    }

    public String[] getFilaTabla() {
        String[] datosFila = new String[this.panelTablaRecetas.TablaReceta.getColumnCount()];
        int numFila = this.panelTablaRecetas.TablaReceta.getSelectedRow();
        for (int i = 0; i < datosFila.length; i++) {
            datosFila[i] = this.panelTablaRecetas.TablaReceta.getModel().getValueAt(numFila, i).toString();
        }
        return datosFila;
    }
    //Tabla Ingredientes 

    public void setDatosTablaIngredientes(String[][] datos, String[] etiquetas, String Titulo) {
        DefaultTableModel model = (DefaultTableModel) Tablaingredientes.getModel();

        // Limpiar la tabla
        model.setRowCount(0);
        model.setColumnCount(0);

        // Establecer los nuevos datos y etiquetas
        model.setDataVector(datos, etiquetas);

        // Actualizar la vista de la tabla
        jScrollPane.setViewportView(Tablaingredientes);

    }

    public String[] getFilaTablaIngredientes() {
        String[] datosFila = new String[this.Tablaingredientes.getColumnCount()];
        int numFila = this.Tablaingredientes.getSelectedRow();
        for (int i = 0; i < datosFila.length; i++) {
            datosFila[i] = this.Tablaingredientes.getModel().getValueAt(numFila, i).toString();
        }
        return datosFila;
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
        jScrollPane = new javax.swing.JScrollPane();
        Tablaingredientes = new javax.swing.JTable();
        panelIngresoDatos1 = new Vista.PanelIngresoDatos();
        panelBotones1 = new Vista.PanelBotones();
        panelTablaRecetas = new Vista.PanelTabla();
        panelIngredientes2 = new Vista.PanelIngredientes();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiAdministrarUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Tablaingredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ingrediente", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(Tablaingredientes);

        panelBotones1.setBackground(new java.awt.Color(255, 255, 255));

        panelTablaRecetas.setBackground(new java.awt.Color(255, 255, 255));

        panelIngredientes2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(panelTablaRecetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBotones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelIngresoDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelIngredientes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(283, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBotones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTablaRecetas, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelIngresoDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelIngredientes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(389, Short.MAX_VALUE)))
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable Tablaingredientes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JMenuItem jmiAdministrarUsuario;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JMenuItem jmiSalir;
    private Vista.PanelBotones panelBotones1;
    public Vista.PanelIngredientes panelIngredientes2;
    private Vista.PanelIngresoDatos panelIngresoDatos1;
    public Vista.PanelTabla panelTablaRecetas;
    // End of variables declaration//GEN-END:variables
}
