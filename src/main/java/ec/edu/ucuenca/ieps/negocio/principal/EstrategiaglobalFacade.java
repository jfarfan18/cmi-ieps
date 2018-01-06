/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.principal;

import ec.edu.ucuenca.ieps.modelado.principal.EstrategiaGlobal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.edu.ucuenca.ieps.negocio.AbstractFacade;

/**
 *
 * @author Farfan
 */
@Stateless
public class EstrategiaglobalFacade extends AbstractFacade<EstrategiaGlobal> {
    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstrategiaglobalFacade() {
        super(EstrategiaGlobal.class);
    }
    
}
