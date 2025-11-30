public class Leaflet {
    private final String id;
    private final String name;
    private final String title;
    private final String body;

    public Leaflet(String id, String name, String title, String body) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.body = body;
    }

    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getTitle() { return title; }
    public String getBody()  { return body; }
}