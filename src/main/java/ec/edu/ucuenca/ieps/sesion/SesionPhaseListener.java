/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.sesion;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class SesionPhaseListener implements PhaseListener {

    private static final String sesionExpiradaPagina = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlExpiraSesion");

    @Override
    public void afterPhase(PhaseEvent event) {
        //Do anything
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        ExternalContext ext = context.getExternalContext();
        HttpSession session = (HttpSession) ext.getSession(false);
        boolean newSession = (session == null) || (session.isNew());
        boolean postback = !ext.getRequestParameterMap().isEmpty();
        boolean timedout = postback && newSession;
        
        if (timedout) {
            Application app = context.getApplication();
            ViewHandler viewHandler = app.getViewHandler();
            UIViewRoot view = viewHandler.createView(context, "/" + sesionExpiradaPagina);
            context.setViewRoot(view);
            context.renderResponse();
        //    ////System.out.println("Time Out 1");
            try {

                ext.redirect(sesionExpiradaPagina);
                session.invalidate();

            } catch (IOException ex) {
                Logger.getLogger(SesionPhaseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
           // ////System.out.println("Time Out 2");
            try {
                viewHandler.renderView(context, view);
                context.responseComplete();
             //   ////System.out.println("Time Out 3");

            } catch (IOException t) {
                throw new FacesException("Session timed out", t);
            } catch (FacesException t) {
                throw new FacesException("Session timed out", t);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
