package model.domain.entity;

import java.time.Duration;

public class Statistic {
    private Activity activity;
    private Duration duration;

    public Statistic(Activity activity, Duration duration) {
        this.activity = activity;
        this.duration = duration;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Duration getDuration() {
        return duration;
    }

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
