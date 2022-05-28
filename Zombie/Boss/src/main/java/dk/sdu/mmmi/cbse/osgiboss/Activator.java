package dk.sdu.mmmi.cbse.osgiboss;


import dk.sdu.mmmi.cbse.common.enemy.BossSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        BossControlSystem es = new BossControlSystem();
        context.registerService(IEntityProcessingService.class, es, null);
        context.registerService(BossSPI.class, es, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop Enemy");
    }

}
