/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.seguridades;

import ec.edu.ucuenca.ieps.modelado.seguridades.Menu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jfarf
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    public List<Menu> getItemsMenu(String tipo) {
        Query query = em.createNamedQuery(Menu.findByTipo);
        query.setParameter("tipo", tipo);            
        query.setParameter("eliminado", "N");     
        return query.getResultList();
    }  
    
    public List<Menu> getItemsMenuPorModulo(Menu modulo) {
        System.out.println("modulo "+modulo.getCodigo()+modulo.getNombre());
        Query query = em.createNamedQuery(Menu.findByModulo);
        query.setParameter("tipo", "M");            
        query.setParameter("eliminado", "N");
        query.setParameter("codigoPadre", modulo.getCodigo());        
        return query.getResultList();
    } 
}
