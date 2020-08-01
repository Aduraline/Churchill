package africa.of.designers.churchill.model;

public class Slide {

    private int Image ;
    private String Title, ButtonTxt;
    private int Caption;


    public Slide(int image, String title, int caption, String ButtonTxt) {
        Image = image;
        Title = title;
        Caption = caption;
        this.ButtonTxt = ButtonTxt;
    }


    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public int getCaption(){
        return Caption;
    }

    public String getButtonTxt(){
        return ButtonTxt;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setCaption(int caption){
        Caption = caption;
    }

    public void setButtonTxt(String txt){
        ButtonTxt = txt;
    }

}
