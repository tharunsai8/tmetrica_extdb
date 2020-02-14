package model.factory;

import model.service.Service;
import model.service.impl.ActivityLogServiceImpl;
import model.service.impl.ActivityServiceImpl;
import model.service.impl.OrderServiceImpl;
import model.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Service factory.
 */
public final class ServiceFactory {
    private static Map<ServiceType, Service> serviceMap = new HashMap<>();

    /**
     * Gets service.
     *
     * @param type the type
     * @return the service
     */
    public static Service getService(ServiceType type) {
        if (serviceMap.get(type) == null) {
            putService(type);
        }
        return serviceMap.get(type);
    }

    private static void putService(ServiceType type) {
        switch (type) {
            case ORDERS:
                serviceMap.put(ServiceType.ORDERS, new OrderServiceImpl());
                break;
            case USERS:
                serviceMap.put(ServiceType.USERS, new UserServiceImpl());
                break;
            case LOGS:
                serviceMap.put(ServiceType.LOGS, new ActivityLogServiceImpl());
                break;
            case ACTIVITY:
                serviceMap.put(ServiceType.ACTIVITY, new ActivityServiceImpl());
                break;
        }

    }

    private ServiceFactory() {
    }

}
