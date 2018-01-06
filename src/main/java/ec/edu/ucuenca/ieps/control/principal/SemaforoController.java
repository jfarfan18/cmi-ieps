package ec.edu.ucuenca.ieps.control.principal;

import ec.edu.ucuenca.ieps.control.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.modelado.principal.Semaforo;
import ec.edu.ucuenca.ieps.negocio.principal.SemaforoFacade;
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

@ManagedBean(name = "semaforoController")
@SessionScoped
public class SemaforoController implements Serializable {

    @EJB
    private SemaforoFacade ejbFacade;
    private List<Semaforo> items = null;
    private Semaforo selected;

    public SemaforoController() {
    }

    public Semaforo getSelected() {
        return selected;
    }

    public void setSelected(Semaforo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SemaforoFacade getFacade() {
        return ejbFacade;
    }

    public Semaforo prepareCreate() {
        selected = new Semaforo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Guardado");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Actualizado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Eliminado");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Semaforo> getItems() {
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

    public List<Semaforo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Semaforo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Semaforo.class)
    public static class SemaforoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SemaforoController controller = (SemaforoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "semaforoController");
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
            if (object instanceof Semaforo) {
                Semaforo o = (Semaforo) object;
                return getStringKey(o.getIdSemaforo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Semaforo.class.getName()});
                return null;
            }
        }

    }

}
