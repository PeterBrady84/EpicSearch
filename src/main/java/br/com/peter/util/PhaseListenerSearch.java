package br.com.peter.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

public class PhaseListenerSearch implements PhaseListener {

    //Before Phase
    @Override
    public void beforePhase(PhaseEvent phase) {
        System.out.println("Before Phase: " + phase.getPhaseId());
        if (phase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);            
        }
    }
    
    //After Phase
    @Override
    public void afterPhase(PhaseEvent phase) {
        System.out.println("After Phase: " + phase.getPhaseId());
        if (phase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally{
                session.close();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
}
