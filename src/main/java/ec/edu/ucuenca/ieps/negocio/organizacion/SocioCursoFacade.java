/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.organizacion;

import ec.edu.ucuenca.ieps.modelado.organizacion.SocioCurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jfarf
 */
@Stateless
public class SocioCursoFacade extends AbstractFacade<SocioCurso> {

    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioCursoFacade() {
        super(SocioCurso.class);
    }
    
}
