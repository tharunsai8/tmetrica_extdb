import model.dao.impl.ActivityDao;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //TODO PAGINATION!!!!!
        ActivityDao activityDao = new ActivityDao();
        System.out.println(activityDao.getPageCountForActiveActivities(3));
    }
}
