/*
*Keiron Garro M
*C23212
*UCR
 */
package Controladores.Vistas;

import Controladores.DB.IngredientsJpaController;
import Controladores.DB.exceptions.IllegalOrphanException;
import Controladores.DB.exceptions.NonexistentEntityException;
import Entidades.Ingredients;
import Entidades.Recipes;
import Entidades.RegistroRecetas;
import Vista.FRMMenu;
import Vista.FRMRecetas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ControladorFrameRecetas implements ActionListener, MouseListener {

    private FRMRecetas fRMRecetas;
    private FRMMenu fRMMenu;
    private Recipes receta;
    private ArrayList<Ingredients> ingredients;
    private RegistroRecetas registroRecetas;

    public ControladorFrameRecetas(FRMRecetas fRMRecetas) {
        this.fRMRecetas = fRMRecetas;
        this.receta = new Recipes();
        this.ingredients = new ArrayList<>();
        this.registroRecetas = new RegistroRecetas();
        this.fRMRecetas.setDatosTablaRecetas(this.registroRecetas.getDatosTabla(), Recipes.ETIQUETAS_RECETA, "Reporte de Recetas");
        llenarCBoxIngredientes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Buscar" -> {
                System.out.println("Buscando");
                this.receta = (Recipes) this.registroRecetas.buscar(this.fRMRecetas.getTxtId());
                if (this.receta != null) {
                    this.fRMRecetas.setTxtnombreReceta(this.receta.getName());
                    this.fRMRecetas.setTxtdescripcion(this.receta.getDescription());
                    this.fRMRecetas.setTxtinstrucciones(this.receta.getPreparationInstructions());
                    this.fRMRecetas.setTxttiempoCoccion(String.valueOf(this.receta.getCookingTime()));
                    this.fRMRecetas.setTxttiempoTotal(String.valueOf(this.receta.getTotalTime()));
                    this.fRMRecetas.setTxtId(String.valueOf(this.receta.getId()));
                } else {
                    FRMRecetas.mensaje("La receta con el ID: " + this.fRMRecetas.getTxtId() + " no se encuentra registrado");
                    this.fRMRecetas.limpiar();
                }
            }

            case "Modificar" -> {
                System.out.println("Modificando");

                if (this.fRMRecetas.getTxtnombreReceta().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el Nombre");
                } else if (this.fRMRecetas.getTxtdescripcion().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar la descripción");
                } else if (this.fRMRecetas.getTxttiempoCoccion().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el tiempo de cocción");
                } else if (this.fRMRecetas.getTxttiempoTotal().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el tiempo total");
                } else if (this.fRMRecetas.getTxtinstrucciones().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar las instrucciones");
                } else if (this.fRMRecetas.getTxttiempoPreparacion().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el tiempo de preparación");
                } else if (this.fRMRecetas.getTxtporciones().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar las porciones");
                } else {
                    this.receta = (Recipes) this.registroRecetas.buscar(this.fRMRecetas.getTxtId());
                    if (this.receta != null) {
                        this.receta = new Recipes();
                        this.receta.setName(this.fRMRecetas.getTxtnombreReceta());
                        this.receta.setDescription(this.fRMRecetas.getTxtdescripcion());
                        this.receta.setCookingTime(Float.parseFloat(this.fRMRecetas.getTxttiempoCoccion()));
                        this.receta.setTotalTime(Float.parseFloat(this.fRMRecetas.getTxttiempoTotal()));
                        this.receta.setPreparationInstructions(this.fRMRecetas.getTxtinstrucciones());
                        this.receta.setId(Integer.valueOf(this.fRMRecetas.getTxtId()));
                        this.receta.setPreparationTime(Float.parseFloat(this.fRMRecetas.getTxttiempoPreparacion()));
                        this.receta.setPortions(Integer.parseInt(this.fRMRecetas.getTxtporciones()));
                        try {
                            this.registroRecetas.modificar(this.receta);
                        } catch (Exception ex) {
                            Logger.getLogger(ControladorFrameRecetas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        this.fRMRecetas.limpiar();
                        this.fRMRecetas.setDatosTablaRecetas(this.registroRecetas.getDatosTabla(), Recipes.ETIQUETAS_RECETA, "Reporte de Recetas");
                    }
                }

            }
            case "Agregar" -> {
                System.out.println("Agregar");
                if (this.fRMRecetas.getTxtnombreReceta().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el Nombre");
                } else if (this.fRMRecetas.getTxtdescripcion().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar la descripción");
                } else if (this.fRMRecetas.getTxttiempoCoccion().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el tiempo de cocción");
                } else if (this.fRMRecetas.getTxttiempoTotal().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el tiempo total");
                } else if (this.fRMRecetas.getTxtinstrucciones().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar las instrucciones");
                } else if (this.fRMRecetas.getTxttiempoPreparacion().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar el tiempo de preparación");
                } else if (this.fRMRecetas.getTxtporciones().equalsIgnoreCase("")) {
                    FRMRecetas.mensaje("Debe ingresar las porciones");
                } else {

                    Recipes recipe = new Recipes(0, this.fRMRecetas.getTxtnombreReceta(), "", this.fRMRecetas.getTxtdescripcion(), Float.parseFloat(this.fRMRecetas.getTxttiempoCoccion()), Float.parseFloat(this.fRMRecetas.getTxttiempoTotal()), Float.parseFloat(this.fRMRecetas.getTxttiempoPreparacion()), this.fRMRecetas.getTxtinstrucciones(), Integer.parseInt(this.fRMRecetas.getTxtporciones()), this.fRMRecetas.getLevel());
                    try {
                        this.registroRecetas.agregar(recipe, this.ingredients);
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorFrameRecetas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.fRMRecetas.setDatosTablaRecetas(this.registroRecetas.getDatosTabla(), Recipes.ETIQUETAS_RECETA, "Reporte de Recetas");
                    this.fRMRecetas.limpiar();
                }
            }

            case "Eliminar" -> {
                System.out.println("ELIMINAR");
                try {
                    FRMRecetas.mensaje(this.registroRecetas.eliminar(this.receta));
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(ControladorFrameRecetas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ControladorFrameRecetas.class.getName()).log(Level.SEVERE, null, ex);
                }//                
                this.fRMRecetas.limpiar();
                this.fRMRecetas.setDatosTablaRecetas(this.registroRecetas.getDatosTabla(), Recipes.ETIQUETAS_RECETA, "Reporte de Receta");
            }

            case "AGREGARING" -> {

                IngredientsJpaController ingredientsJpaController = new IngredientsJpaController();
                Ingredients i = ingredientsJpaController.findIngredients(this.fRMRecetas.getNombreIngredientesID());
                this.ingredients.add(i);
                DefaultTableModel model = (DefaultTableModel) this.fRMRecetas.Tablaingredientes.getModel();
                Object[] rowData = {this.fRMRecetas.getNombreIngredientesID(), this.fRMRecetas.getNombreIngredientes(), this.fRMRecetas.getTxtCantidadIngredientes()};
                model.addRow(rowData);

            }

            case "ELIMINARING" -> {

                IngredientsJpaController ingredientsJpaController = new IngredientsJpaController();
                Ingredients i = ingredientsJpaController.findIngredients((Integer) this.fRMRecetas.Tablaingredientes.getValueAt(0, this.fRMRecetas.Tablaingredientes.getSelectedRow()));
                this.ingredients.remove(i);
                DefaultTableModel model = (DefaultTableModel) this.fRMRecetas.Tablaingredientes.getModel();
                model.removeRow(this.fRMRecetas.Tablaingredientes.getSelectedRow());

            }
            case "Administrar Recetas" -> {
                System.out.println("AdmiUsuarios");
                fRMRecetas = new FRMRecetas();
                fRMRecetas.dispose();
            }
            case "Regresar" -> {
                System.out.println("pressed Atras");
                fRMMenu = new FRMMenu();
                fRMRecetas.dispose();

            }
            case "Salir" ->
                System.exit(0);

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow() != -1) {
            // Cargar los datos en los campos de texto correspondientes
            String[] vFila = this.fRMRecetas.getFilaTabla();
            this.fRMRecetas.setTxtId((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 0));
            this.fRMRecetas.setTxtnombreReceta((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 1));
            this.fRMRecetas.setTxttiempoCoccion((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 2));
            this.fRMRecetas.setTxttiempoPreparacion((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 3));
            this.fRMRecetas.setTxttiempoTotal((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 4));
            this.fRMRecetas.setTxtinstrucciones((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 5));
            this.fRMRecetas.setTxtdescripcion((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 6));
            this.fRMRecetas.setTxtporciones((String) this.fRMRecetas.panelTablaRecetas.TablaReceta.getValueAt(fRMRecetas.panelTablaRecetas.TablaReceta.getSelectedRow(), 7));
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public String[][] getDatosTablaIngredientes() {
        ArrayList<Ingredients> a = this.ingredients;
        String[][] matrizTabla = new String[a.size()][Ingredients.ETIQUETAS_INGREDIENTES.length];
        for (Ingredients ingredient : a) {
            Object[] rowData = {ingredient.getId(), ingredient.getName()};
        }
        return matrizTabla;
    }

    public void llenarCBoxIngredientes() {
        IngredientsJpaController i = new IngredientsJpaController();
        ArrayList<Ingredients> arrayList = (ArrayList<Ingredients>) i.findIngredientsEntities();
        for (Ingredients ingredient : arrayList) {
            this.fRMRecetas.panelIngredientes2.jComboBoxIngredientes.addItem(ingredient.getName());
        }

    }

}
