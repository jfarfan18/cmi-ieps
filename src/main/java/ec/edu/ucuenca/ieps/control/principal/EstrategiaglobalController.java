package ec.edu.ucuenca.ieps.control.principal;



import ec.edu.ucuenca.ieps.control.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.modelado.principal.EstrategiaGlobal;
import ec.edu.ucuenca.ieps.negocio.principal.EstrategiaglobalFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "estrategiaglobalController")
@SessionScoped
public class EstrategiaglobalController implements Serializable {

    @EJB
    private EstrategiaglobalFacade ejbFacade;
    private List<EstrategiaGlobal> items = null;
    private EstrategiaGlobal selected;
    private String estrategia;

    public EstrategiaglobalController() {
    }
    
    public void guardar(){
        this.setSelected(new EstrategiaGlobal());
        this.getSelected().setEstrategia(estrategia);
        this.create();
    }
    
    public void preparaNuevo(){
        System.out.println("nuevo");
        estrategia="";
    }

    public EstrategiaGlobal getSelected() {
        return selected;
    }

    public void setSelected(EstrategiaGlobal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstrategiaglobalFacade getFacade() {
        return ejbFacade;
    }

    public EstrategiaGlobal prepareCreate() {
        
        System.out.println("dsfds");
        selected = new EstrategiaGlobal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        
        persist(PersistAction.CREATE, "Estrategia Global Guardada");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Estrategia Global Actualizado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Estrategia Global Eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EstrategiaGlobal> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<EstrategiaGlobal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EstrategiaGlobal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the estrategia
     */
    public String getEstrategia() {
        return estrategia;
    }

    /**
     * @param estrategia the estrategia to set
     */
    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    @FacesConverter(forClass = EstrategiaGlobal.class)
    public static class EstrategiaglobalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstrategiaglobalController controller = (EstrategiaglobalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estrategiaglobalController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof EstrategiaGlobal) {
                EstrategiaGlobal o = (EstrategiaGlobal) object;
                return getStringKey(o.getIdEstrategiaGlobal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstrategiaGlobal.class.getName()});
                return null;
            }
        }

    }

}
