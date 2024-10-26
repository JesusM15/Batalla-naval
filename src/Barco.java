public class Barco {
    private int width = 1;
    private int height;
    private int xPosition;
    private int yPosition;
    private boolean esVertical;
    private String id;
    private boolean Derribado = false;
    public Barco(int height, int xPosition, int yPosition, boolean esVertical) {
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.esVertical = esVertical;
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
    public void setEsVertical(boolean esVertical) {this.esVertical = esVertical;}
    public boolean isEsVertical() {return esVertical;}
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public void voltear() {
        int w = this.height;
        this.height = this.width;
        this.width = w;
        this.esVertical = !this.esVertical;
    }
    public void disparar(int x, int y){
        if((x>=0 && x<10)&&(y>=0 && y<10)){
            System.out.println("Disparando "+x+" "+y);
        }
    }
    public int[] posicionFinal(){
        int x=0,y=0;
        int[] coor = new int[2];
        if(esVertical){
            y = this.height+yPosition;
            x = xPosition;
        }else{
            x = this.width+xPosition;
            y = yPosition;
        }
        coor[0] = x;
        coor[1] = y;
        return coor;
    }
    public boolean isDerribado() {return Derribado;}
    public void setDerribado(boolean derribado) {this.Derribado = derribado;}
}
