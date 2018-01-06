package ec.edu.ucuenca.ieps.control.principal;



import ec.edu.ucuenca.ieps.control.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.modelado.principal.Historial;
import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategicoIndicador;
import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategicoIndicadorPK;
import ec.edu.ucuenca.ieps.modelado.principal.Semaforo;
import ec.edu.ucuenca.ieps.negocio.principal.HistorialFacade;
import ec.edu.ucuenca.ieps.negocio.principal.ObjetivoestrategicoindicadorFacade;
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


@ManagedBean(name = "objetivoestrategicoindicadorController")
@SessionScoped
public class ObjetivoEstrategicoIndicadorController implements Serializable {

    @EJB
    private ObjetivoestrategicoindicadorFacade ejbFacade;
    @EJB
    private HistorialFacade ejbFacadeHistorial;
    @EJB
    private SemaforoFacade ejbFacadeSemaforo;
    private List<ObjetivoEstrategicoIndicador> items = null;
    private ObjetivoEstrategicoIndicador selected;

   
        
    public ObjetivoEstrategicoIndicadorController() {
    }

    public ObjetivoEstrategicoIndicador getSelected() {
        return selected;
    }

    public void setSelected(ObjetivoEstrategicoIndicador selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getObjetivoEstrategicoIndicadorPK().setIdIndicador(selected.getIndicador().getIdIndicador());
        selected.getObjetivoEstrategicoIndicadorPK().setIdObjetivoEstrategico(selected.getObjetivoEstrategico().getIdObjetivoEstrategico());
    }

    protected void initializeEmbeddableKey() {
        selected.setObjetivoEstrategicoIndicadorPK(new ObjetivoEstrategicoIndicadorPK());
    }

    private ObjetivoestrategicoindicadorFacade getFacade() {
        return ejbFacade;
    }

    public ObjetivoEstrategicoIndicador prepareCreate() {
        selected = new ObjetivoEstrategicoIndicador();
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

    public List<ObjetivoEstrategicoIndicador> getItems() {
        System.out.println("ACTUALIZO DATOS");
        items = getFacade().findAll();
        for (int i=0;i<items.size();i++){
            List <Historial> histrorial = ejbFacadeHistorial.getSemaforosIndicador(items.get(i).getIndicador().getIdIndicador());
            List <Semaforo> semaforos = ejbFacadeSemaforo.getSemaforosIndicador(items.get(i).getIndicador().getIdIndicador());
            items.get(i).getIndicador().setHistorialList(histrorial);
            items.get(i).getIndicador().setSemaforoList(semaforos);
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

    public List<ObjetivoEstrategicoIndicador> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ObjetivoEstrategicoIndicador> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ObjetivoEstrategicoIndicador.class)
    public static class ObjetivoEstrategicoIndicadorControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetivoEstrategicoIndicadorController controller = (ObjetivoEstrategicoIndicadorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetivoestrategicoindicadorController");
            return controller.getFacade().find(getKey(value));
        }

        ObjetivoEstrategicoIndicadorPK getKey(String value) {
            ObjetivoEstrategicoIndicadorPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ObjetivoEstrategicoIndicadorPK();
            key.setIdObjetivoEstrategico(Integer.parseInt(values[0]));
            key.setIdIndicador(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(ObjetivoEstrategicoIndicadorPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdObjetivoEstrategico());
            sb.append(SEPARATOR);
            sb.append(value.getIdIndicador());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ObjetivoEstrategicoIndicador) {
                ObjetivoEstrategicoIndicador o = (ObjetivoEstrategicoIndicador) object;
                return getStringKey(o.getObjetivoEstrategicoIndicadorPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ObjetivoEstrategicoIndicador.class.getName()});
                return null;
            }
        }

    }

}
