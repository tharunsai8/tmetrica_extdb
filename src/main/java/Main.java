import model.dao.impl.ActivityDao;

public class Main {
    public static void main(String[] args) {
        //TODO PAGINATION!!!!!
        ActivityDao activityDao = new ActivityDao();
        System.out.println(activityDao.getPageCountForActiveActivities(3));
    }
}
