/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controladores.Vistas.ControladorFrameRecetas;
import java.awt.event.MouseListener;
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
        this.escucharTabla(controladorFrameRecetas);
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
    }

    public PanelBotones panelBotones1() {
        return panelBotones1;
    }

    public PanelIngresoDatos getpanelIngresoDatos1() {
        return panelIngresoDatos1;
    }

    public PanelTabla getPanelTabla1() {
        return panelTabla1;
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

    public void limpiar() {
        this.panelIngresoDatos1.nombreReceta.setText("");
        this.panelIngresoDatos1.tiempoCoccion.setText("");
        this.panelIngresoDatos1.tiempoTotal.setText("");
        this.panelIngresoDatos1.instrucciones.setText("");
        this.panelIngresoDatos1.descripcion.setText("");
        this.panelIngresoDatos1.IDReceta.setText("");
        this.panelIngresoDatos1.tiempoPreparacion.setText("");
        this.panelIngresoDatos1.porciones.setText("");

    }

    public void setDatosTabla(String[][] datos, String[] etiquetas, String Titulo) {
        this.panelTabla1.jTable1.setModel(new DefaultTableModel(datos, etiquetas));
        this.panelTabla1.jScrollPane1.setViewportView(this.panelTabla1);
    }

    public void escucharTabla(MouseListener control) {
        this.panelTabla1.addMouseListener(control);
    }

    public String[] getFilaTabla() {
        String[] datosFila = new String[this.panelTabla1.jTable1.getColumnCount()];
        int numFila = this.panelTabla1.jTable1.getSelectedRow();
        for (int i = 0; i < datosFila.length; i++) {
            datosFila[i] = this.panelTabla1.jTable1.getModel().getValueAt(numFila, i).toString();
        }
        return datosFila;
    }
    public JTable getTblReporte() {
        return panelTabla1.jTable1;
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
        jTable1 = new javax.swing.JTable();
        panelIngresoDatos1 = new Vista.PanelIngresoDatos();
        panelIngredientes1 = new Vista.PanelIngredientes();
        panelBotones1 = new Vista.PanelBotones();
        panelTabla1 = new Vista.PanelTabla();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiRegresar = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiAdministrarUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

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
                .addComponent(panelIngresoDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelIngredientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelBotones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelIngresoDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelIngredientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBotones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addComponent(panelTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem jmiAdministrarUsuario;
    private javax.swing.JMenuItem jmiRegresar;
    private javax.swing.JMenuItem jmiSalir;
    private Vista.PanelBotones panelBotones1;
    private Vista.PanelIngredientes panelIngredientes1;
    private Vista.PanelIngresoDatos panelIngresoDatos1;
    public Vista.PanelTabla panelTabla1;
    // End of variables declaration//GEN-END:variables
}
