import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.httpMappings.BadRequest;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.httpMappings.Unauthorized;
import play.Application;
import play.GlobalSettings;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

/**
 * Created by Marcin on 20.
 */
public class Global extends GlobalSettings {

    private static final String[] PACKAGES_TO_SCAN = new String[]{
            "controllers.pl.edu.agh.ki.sm.assetsManagemnet.server",
            "pl.edu.agh.ki.sm.assetsManagemnet.server.daos",
            "pl.edu.agh.ki.sm.assetsManagemnet.server.services",
            "pl.edu.agh.ki.sm.assetsManagemnet.server.acl"
    };

    final private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    @Override
    public void onStart(Application app) {
        super.onStart(app);

        ctx.scan(PACKAGES_TO_SCAN);
        ctx.refresh();
        ctx.start();
    }

    @Override
    public void onStop(Application app) {
        ctx.close();
        super.onStop(app);
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        if (clazz.isAnnotationPresent(Component.class)
                || clazz.isAnnotationPresent(Controller.class)
                || clazz.isAnnotationPresent(Service.class)
                || clazz.isAnnotationPresent(Repository.class)) {
            return ctx.getBean(clazz);
        } else {
            return null;
        }
    }

    @Override
    public F.Promise<Result> onError(Http.RequestHeader request, Throwable t) {
        if (request.path().contains("android-api")) {
            return translateAndroidApiException(t);
        }

        return super.onError(request, t);
    }

    private F.Promise<Result> translateAndroidApiException(Throwable t) {
        if (t instanceof BadRequest) {
            return F.Promise.<Result>pure(Results.badRequest(t.getMessage()));
        }
        if (t instanceof Unauthorized) {
            return F.Promise.<Result>pure(Results.unauthorized(t.getMessage()));
        }
        return F.Promise.<Result>pure(Results.internalServerError(t.getMessage()));
    }
}
