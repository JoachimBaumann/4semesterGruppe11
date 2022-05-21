package dk.sdu.mmmi.cbse.osgienemy;


import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        EnemyControlSystem es = new EnemyControlSystem();
        context.registerService(IEntityProcessingService.class, es, null);
        context.registerService(EnemySPI.class, es, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop Enemy");
    }

}
