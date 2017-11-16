package ec.edu.ucuenca.ieps.control.clientes;

import com.google.gson.Gson;
import ec.edu.ucuenca.ieps.modelado.clientes.PersonaNatural;
import ec.edu.ucuenca.ieps.control.clientes.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.clientes.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.modelado.clientes.Persona;
import ec.edu.ucuenca.ieps.modelado.clientes.TipoPersona;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaNaturalFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.TipoPersonaFacade;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FlowEvent;

@Named("personaNaturalController")
@SessionScoped
public class PersonaNaturalController implements Serializable {

    @EJB
    private ec.edu.ucuenca.ieps.negocio.clientes.PersonaNaturalFacade ejbFacade;
    
    @EJB
    private PersonaFacade ejbFacadePersona;
    
    @EJB
    private TipoPersonaFacade ejbFacadeTiPersonaFacade;
    private List<PersonaNatural> items = null;
    private PersonaNatural selected;

    public PersonaNaturalController() {
    }

    public PersonaNatural getSelected() {
        return selected;
    }

    public void setSelected(PersonaNatural selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonaNaturalFacade getFacade() {
        return ejbFacade;
    }
    
    public String onFlowProcess(FlowEvent event) {
            return event.getNewStep();
    }

    public PersonaNatural prepareCreate() {
        selected = new PersonaNatural();
        selected.setPersona(new Persona());
        TipoPersona tipo=this.ejbFacadeTiPersonaFacade.find(1);
        
        selected.getPersona().setCodigoTipoPersona(tipo);
        initializeEmbeddableKey();
        return selected;
    }

    public void buscaPersona(){
        PersonaNatural p=this.ejbFacade.buscarPorIdentificacion(this.selected.getPersona().getIdentificacion());
        if (p!=null){
            this.selected=p;
        }
    }
    
    public void create() {
        selected.getPersona().setFechaActualizacion(new Date());
        selected.getPersona().setNombreCompleto(selected.getPrimerApellido()+" "+
                                                selected.getSegundoApellido()+" "+
                                                selected.getPrimerNombre()+" "+
                                                selected.getSegundoNombre()
                                                );
        
        Gson gson = new Gson();
        System.out.println(gson.toJson(selected.getPersona()));
        this.ejbFacadePersona.create(selected.getPersona());
        selected.setCodigoPersona(selected.getPersona().getCodigo());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle1").getString("PersonaNaturalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.getPersona().setFechaActualizacion(new Date());
        selected.getPersona().setNombreCompleto(selected.getPrimerApellido()+" "+
                                                selected.getSegundoApellido()+" "+
                                                selected.getPrimerNombre()+" "+
                                                selected.getSegundoNombre()
                                                );
        this.ejbFacadePersona.edit(selected.getPersona());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle1").getString("PersonaNaturalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle1").getString("PersonaNaturalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PersonaNatural> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PersonaNatural getPersonaNatural(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PersonaNatural> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PersonaNatural> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PersonaNatural.class)
    public static class PersonaNaturalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaNaturalController controller = (PersonaNaturalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaNaturalController");
            return controller.getPersonaNatural(getKey(value));
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
            if (object instanceof PersonaNatural) {
                PersonaNatural o = (PersonaNatural) object;
                return getStringKey(o.getCodigoPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaNatural.class.getName()});
                return null;
            }
        }

    }

}
