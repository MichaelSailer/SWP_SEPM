package Model;
import  java.util.List;

public class openWeatherMap {
    private  coord coord;
    private List<weather> weather;
    private String base;
    private main main;
    private rain rain;
    private cloud cloud;
    private int dt;
    private sys sys;
    private int id;
    private String name;
    private int cod;

    public openWeatherMap() {
    }

    public openWeatherMap(Model.coord coord, List<Model.weather> weather, String base, Model.main main, Model.rain rain, Model.cloud cloud, int dt, Model.sys sys, int id, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.rain = rain;
        this.cloud = cloud;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Model.coord getCoord() {
        return coord;
    }

    public void setCoord(Model.coord coord) {
        this.coord = coord;
    }

    public List<Model.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Model.weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Model.main getMain() {
        return main;
    }

    public void setMain(Model.main main) {
        this.main = main;
    }

    public Model.rain getRain() {
        return rain;
    }

    public void setRain(Model.rain rain) {
        this.rain = rain;
    }

    public Model.cloud getCloud() {
        return cloud;
    }

    public void setCloud(Model.cloud cloud) {
        this.cloud = cloud;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Model.sys getSys() {
        return sys;
    }

    public void setSys(Model.sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
