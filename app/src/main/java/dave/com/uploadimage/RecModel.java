package dave.com.uploadimage;

public class RecModel {
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    String imagePath;
    String imageTag;

    public RecModel(String imagePath, String imageTag) {
        this.imagePath = imagePath;
        this.imageTag = imageTag;
    }
}
