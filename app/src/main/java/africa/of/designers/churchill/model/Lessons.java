package africa.of.designers.churchill.model;

public class Lessons {

    private String title;
    private int description;
    private int thumbnail;
    private int coverPhoto;


    public Lessons(String title, int thumbnail, int description, int coverPhoto) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.description = description;
    }

    public Lessons(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }



    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
