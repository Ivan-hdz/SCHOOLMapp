package mx.android.schoolapps.schoolmapp.Models;

/**
 * Created by Shiro on 05/12/2017.
 */

public class ClassroomCentralBuilding {
    private String id;
    private String image;
    private String description;

    public ClassroomCentralBuilding(String id, String image, String description) {
        this.id = id;
        this.image = image;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
