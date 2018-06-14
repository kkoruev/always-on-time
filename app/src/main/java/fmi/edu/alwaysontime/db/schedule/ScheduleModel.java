package fmi.edu.alwaysontime.db.schedule;

public class ScheduleModel {

    String date;
    String time;
    String title;
    String description;


    public ScheduleModel(String date, String time) {
        this.date = checkNull(date);
        this.time = checkNull(time);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = checkNull(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = checkNull(description);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = checkNull(date);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = checkNull(time);
    }

    private String checkNull(String property) {
        return property == null ? "" : property;
    }
}
