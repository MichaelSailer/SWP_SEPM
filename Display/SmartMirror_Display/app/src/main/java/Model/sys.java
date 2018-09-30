package Model;

public class sys {
    private int type;
    private int id;
    private double message;
    private String country;
    private double sunrse;
    private double sunset;

    public sys(int type, int id, double message, String country, double sunrse, double sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrse = sunrse;
        this.sunset = sunset;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getSunrse() {
        return sunrse;
    }

    public void setSunrse(double sunrse) {
        this.sunrse = sunrse;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }
}
