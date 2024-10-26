public class Barco {
    private int width = 1;
    private int height;
    private int xPosition;
    private int yPosition;
    private int xCord;
    private int yCord;
    private boolean esVertical;
    private String id;
    private boolean Derribado = false;
    private boolean colocado = false;

    public Barco(String id, int height) {
        this.id = id;
        this.height = height;
        this.xPosition = 0;
        this.yPosition = 0;
        this.yCord = 0;
        this.xCord = 0;
        this.colocado = false;
        this.esVertical = true;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {return height;}
    public int getXPosition() {return xPosition;}
    public int getYPosition() {return yPosition;}
    public int getXCord() {return xCord;}
    public int getyCord() {return yCord;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setXPosition(int xPosition) {this.xPosition = xPosition;}
    public void setYPosition(int yPosition) {this.yPosition = yPosition;}
    public void setXCord(int xCord) {this.xCord = xCord;}
    public void setYCord(int yCord) {this.yCord = yCord;}
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


    public int[] obtenerPosicionFinal(){
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

    public int[] obtenerCoordFinal(){
        int x=0,y=0;
        int[] coor = new int[2];
        if(esVertical){
            y = this.height+yCord;
            x = xCord;
        }else{
            x = this.width+xCord;
            y = yCord;
        }
        coor[0] = x;
        coor[1] = y;
        return coor;
    }
    public boolean isDerribado() {return Derribado;}
    public void setDerribado(boolean derribado) {this.Derribado = derribado;}
}
