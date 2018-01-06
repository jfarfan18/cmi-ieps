/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.principal;

import ec.edu.ucuenca.ieps.modelado.principal.Semaforo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.edu.ucuenca.ieps.negocio.AbstractFacade;

/**
 *
 * @author Farfan
 */
@Stateless
public class SemaforoFacade extends AbstractFacade<Semaforo> {
    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemaforoFacade() {
        super(Semaforo.class);
    }
       
    public List<Semaforo> getSemaforosIndicador(Integer  idIndicador) {
        Query query = this.em.createNamedQuery(Semaforo.findByIdIndicador);
        query.setParameter("idIndicador",idIndicador);
        return query.getResultList();
    }
}
