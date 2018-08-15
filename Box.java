public class Box{
    private int X;
    private int Y;
    Box connected;
    boolean isConnected;
    public Box(int x,int y){
        int X = x;
        int Y = y;
        isConnected = false;
    }

    public void connect(Box b){
        connected = b;
        isConnected = true;
    }

    public void setLocation(int x,int y){
        if(isConnected)
            connected.setLocation(X,Y);
        X = x;
        Y = y;
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }
}