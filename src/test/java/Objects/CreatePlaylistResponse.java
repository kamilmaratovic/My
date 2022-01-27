package Objects;

public class CreatePlaylistResponse {
    private int id;
    private String name;
    private String[] rules;
    private boolean is_smart;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CreatePlaylistResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
