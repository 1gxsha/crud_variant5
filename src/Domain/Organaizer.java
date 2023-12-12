package Domain;

public class Organaizer {
    private int id;
    private String type;
    private String date;
    private String time;
    private String description;

    public Organaizer() {
    }

    public Organaizer(int id, String type, String date, String time, String description) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setOrganaizerType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nСобытия:{" +
                "id=" + id +
                ",Событие='" + type + '\'' +
                ",Дата='" + date + '\'' +
                ",Время='" + time + '\'' +
                ",Описание='" + description + '\'' +
                "}";
    }
}
