public class Barco {
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    public Barco(int width, int height, int xPosition, int yPosition) {
        this.width = width;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {return height;}
    public int getXPosition() {return xPosition;}
    public int getYPosition() {return yPosition;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setXPosition(int xPosition) {this.xPosition = xPosition;}
    public void setYPosition(int yPosition) {this.yPosition = yPosition;}
}
