package model.domain.entity;

import java.time.Duration;

/**
 * The type Statistic.
 */
public class Statistic {
    private Activity activity;
    private Duration duration;

    /**
     * Instantiates a new Statistic.
     *
     * @param activity the activity
     * @param duration the duration
     */
    public Statistic(Activity activity, Duration duration) {
        this.activity = activity;
        this.duration = duration;
    }

    /**
     * Gets activity.
     *
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Sets activity.
     *
     * @param activity the activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "activity=" + activity +
                ", duration=" + duration +
                '}';
    }

    /**
     * Format duration string.
     *
     * @return the string
     */
    public String formatDuration() {
        long days = duration.toDays();
        duration = duration.minusDays(days);
        long hours = duration.toHours();
        duration = duration.minusHours(hours);
        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);
        long seconds = duration.getSeconds();
        return
                (days == 0 ? "" : days + " days ") +
                        (hours == 0 ? "" : hours + " hours ") +
                        (minutes == 0 ? "" : minutes + " minutes ") +
                        (seconds == 0 ? "" : seconds + " seconds");
    }
}
