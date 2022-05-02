package dk.sdu.mmmi.cbse.OSGILaser;


import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.weapon.WeaponSPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {

        LaserSystem ls = new LaserSystem();
        context.registerService(IEntityProcessingService.class, ls, null);
        context.registerService(WeaponSPI.class, ls, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop Bullet");
    }

}