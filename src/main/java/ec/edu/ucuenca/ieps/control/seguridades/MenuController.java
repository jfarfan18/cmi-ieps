package ec.edu.ucuenca.ieps.control.seguridades;

import ec.edu.ucuenca.ieps.modelado.seguridades.Menu;
import ec.edu.ucuenca.ieps.control.seguridades.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.seguridades.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.negocio.seguridades.MenuFacade;
import ec.edu.ucuenca.ieps.sesion.Sesion;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named("menuController")
@SessionScoped
public class MenuController implements Serializable {

    @EJB
    private ec.edu.ucuenca.ieps.negocio.seguridades.MenuFacade ejbFacade;
    private List<Menu> items = null;
    private Menu selected;
    private List<Menu> modulos;
    private Menu modulo;
    private RequestContext context;
    private MenuModel menu;


    public MenuController() {
    }

    @PostConstruct
    public void init() {

        setModulos(ejbFacade.getItemsMenu("D"));
        System.out.println(getModulos().size());

    }

    public void validaAcceso(){
        System.out.println("ec.edu.ucuenca.ieps.control.seguridades.MenuController.accion()");
        try {
            String url = ResourceBundle.getBundle("/propiedadesObjetos").getString("InicioMatenimientos");
            Sesion.redireccionaPagina(url);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void irMenu() throws IOException {
        String url = ResourceBundle.getBundle("/propiedadesObjetos").getString("UrlMenu");
        //String url = ResourceBundle.getBundle("/propiedadesObjetos").getString("InicioMatenimientos");
        context = RequestContext.getCurrentInstance();
        this.contruyeMenu();            
        Sesion.redireccionaPagina(url);
        

    }
    
    public void contruyeMenu() throws IOException {
        System.out.println("Construye menu");
        List<Menu> listMenu = this.ejbFacade.getItemsMenuPorModulo(modulo);
        System.out.println("Punto 1 "+listMenu.size());
        this.setMenu(new DefaultMenuModel());
        DefaultSubMenu subMenu;
        System.out.println("Punto 2");
        DefaultMenuItem item = new DefaultMenuItem();
        item.setUrl(ResourceBundle.getBundle("/propiedadesObjetos").getString("UrlModulos"));
        //item.setIcon(ResourceBundle.getBundle("/propiedadesObjetosEC").getString("IcoInicio"));
        //item.setTitle(ResourceBundle.getBundle("/propiedadesTituloEC").getString("IrModulos"));
        this.getMenu().addElement(item);

        for (Menu submenu : listMenu) {
            System.out.println("For 1");
            subMenu = new DefaultSubMenu(submenu.getNombre());
            List<Menu> listOpcionesMenu = this.ejbFacade.getItemsMenuPorModulo(submenu);
            ////System.out.println(submenu.getNombre() + " " + listOpcionesMenu);
            for (Menu opcion : listOpcionesMenu) {

                item = new DefaultMenuItem(opcion.getNombre());
                item.setStyle("width: 100%;'");
                item.setAjax(true);
                item.setTitle(opcion.getNombre());
                String url = "/faces/" + this.modulo.getAplicacion() + "/" + submenu.getAplicacion() + "/" + opcion.getAplicacion() + "/inicio.xhtml";
                //urlAyuda = "/faces/" + this.modulo.getAplicacion() + "/" + submenu.getAplicacion() + "/" + opcion.getAplicacion() + "/ayuda.xhtml";

                item.setCommand("#{menuController.irOpcion('" + opcion.getNombre() + "','" + url + "','" + url + "'," + opcion.getCodigo() + ",'" + "" + "')}");
                subMenu.addElement(item);
            }
            
            if (subMenu.getElements().size() > 0) {
                this.getMenu().addElement(subMenu);
            }
        }

        if (this.getMenu().getElements().size() <= 1) {
            //String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            //ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioSinPermisosModulo"));
            //Accediendo a la ventana de no permisos            
            //Sesion.redireccionaPagina(url);
            System.out.println("Sin Menu");
        }
    }
    
    public void irOpcion(String tituloVentana, String url, String urlAyuda, Long codigoOpcion, String controlador)
            throws IOException {
//        setTituloVentana(tituloVentana);
//        setUrlAyuda(urlAyuda);

        // Obteniendo los permiso delos tipos de operaciones en caso de tener se coloca en false para 
        // inidicar que no se deshabiliten los botones
        //List<OpcionOperacion> listOpcionOperacion = this.ejbFacade.getOpcionOperacion(codigoRol, codigoIfip, codigoOpcion);
        //////System.out.println(" listOpcionOperacion "+listOpcionOperacion);
        //this.setPermisoOperacion(new PermisoOperacion());
//        for (OpcionOperacion operacion : listOpcionOperacion) {
//
//            switch (Integer.parseInt(operacion.getCodigoTipoOperacion().getCodigo().toString())) {
//                case 1:
//                    this.getPermisoOperacion().setNuevo(false);
//                    break;
//
//                case 2:
//                    this.getPermisoOperacion().setEditar(false);
//                    break;
//
//                case 3:
//                    this.getPermisoOperacion().setEliminar(false);
//                    break;
//
//                case 4:
//                    this.getPermisoOperacion().setImprimir(false);
//                    break;
//
//                case 5:
//                    this.getPermisoOperacion().setConsultar(false);
//                    break;
//
//                case 6:
//                    this.getPermisoOperacion().setVer(false);
//                    break;
//
//                case 7:
//                    this.getPermisoOperacion().setImprimirComprobante(false);
//                    break;
//
//                case 8:
//                    this.getPermisoOperacion().setGuardar(false);
//                    break;
//
//                case 9:
//                    this.getPermisoOperacion().setRetencion(false);
//                    break;
//
//                case 10:
//                    this.getPermisoOperacion().setContabilizar(false);
//                    break;
//
//                case 11:
//                    this.getPermisoOperacion().setAnular(false);
//                    break;
//
//                case 12:
//                    this.getPermisoOperacion().setPrecancelar(false);
//                    break;
//
//                case 13:
//                    this.getPermisoOperacion().setConceder(false);
//                    break;
//
//                case 14:
//                    this.getPermisoOperacion().setPagare(false);
//                    break;
//                case 15:
//                    this.getPermisoOperacion().setEntregar(false);
//                    break;
//                case 16:
//                    this.getPermisoOperacion().setCambiar(false);
//                    break;
//
//            }
//
//        }
        // Colocando Variable de Sesion        
//        ActivacionUsuario.setControlador(controlador);
//        ActivacionUsuario.setCodigoRol(codigoRol);
//        ActivacionUsuario.setCodigoOpcion(codigoOpcion);

        // Direccionando a la Pagina
        Sesion.redireccionaPagina(url);

    }
    
    public Menu getSelected() {
        return selected;
    }

    public void setSelected(Menu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MenuFacade getFacade() {
        return ejbFacade;
    }

    public Menu prepareCreate() {
        selected = new Menu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("MenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("MenuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("MenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Menu> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Menu getMenu(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Menu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Menu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the modulos
     */
    public List<Menu> getModulos() {
        return modulos;
    }

    /**
     * @param modulos the modulos to set
     */
    public void setModulos(List<Menu> modulos) {
        this.modulos = modulos;
    }

    /**
     * @return the modulo
     */
    public Menu getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(Menu modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the menu
     */
    public MenuModel getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    @FacesConverter(forClass = Menu.class)
    public static class MenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MenuController controller = (MenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "menuController");
            return controller.getMenu(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Menu) {
                Menu o = (Menu) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Menu.class.getName()});
                return null;
            }
        }

    }

}
