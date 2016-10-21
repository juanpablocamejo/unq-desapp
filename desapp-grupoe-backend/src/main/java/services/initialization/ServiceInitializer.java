package services.initialization;

public class ServiceInitializer {
    public ServiceInitializer(Initializable... services) {
        for (Initializable o : services) {
            o.initialize();
        }
    }
}
