import model.dao.impl.ActivityDao;
import model.domain.entity.Activity;
import model.domain.entity.ActivityLog;
import model.domain.entity.Order;
import model.domain.enums.ActivityStatus;
import model.factory.ServiceFactory;
import model.factory.ServiceType;
import model.service.ActivityLogService;
import model.service.ActivityService;
import model.service.OrderService;
import model.service.UserService;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        final ActivityService activityService = (ActivityService) ServiceFactory.getService(ServiceType.ACTIVITY);
        final OrderService orderService = (OrderService) ServiceFactory.getService(ServiceType.ORDERS);
        final ActivityLogService activityLogService = (ActivityLogService) ServiceFactory.getService(ServiceType.LOGS);
        final UserService userService = (UserService) ServiceFactory.getService(ServiceType.USERS);


///ORDER TEST
        Order order = orderService.createNewActivityOrder("TestActivity9Feb", "solosuicide133@gmail.com");
        orderService.approveOrder(order);

        System.out.println(order.getStatus().toString());

//LOG TEST
        ActivityLog activityLog = new ActivityLog(order.getActivity(), userService.getById(1), new Date(), new Date());
        activityLogService.create(activityLog);
        activityLog.setEndTime(new Date());
        activityLogService.update(activityLog);
        ActivityDao activityDao = new ActivityDao();
        Activity activity = activityDao.createAndReturn(new Activity("TEST", new Date(), ActivityStatus.SUSPENDED));
        System.out.println(activity);
        System.out.println(activityService.getById(50));


    }
}
