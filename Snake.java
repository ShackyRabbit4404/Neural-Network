import java.util.*;
public class Snake{
    ArrayList<Box> tail;
    int headX;
    int headY;
    private int tailX;
    private int tailY;
    boolean growing;
    public Snake(){
        headX = 10;
        headY = 10;
        tailX = 10;
        tailY = 10;
        growing = false;
        tail = new ArrayList<Box>();
        tail.add(new Box(10,10));
    }

    public void move(int direction){
        if(direction == 0){
            headY--;
        }
        else if(direction == 1){
            headX++;
        }
        else if(direction == 2){
            headY++;
        }
        else if(direction == 3){
            headX--;
        }
        tailX = tail.get(tail.size()-1).getX();
        tailY = tail.get(tail.size()-1).getY();
        tail.get(0).setLocation(headX,headY);
        if(growing){
            growing = false;
            grow();
        }
        
    }
    public int[][] getBoard(){
        int[][] board = new int[20][20];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                if(row == 0  || row == board.length-1){
                    board[row][col] = 1;
                }
                else if(col == 0 || col == board[0].length - 1){
                    board[row][col] = 1;
                }
            }
        }
        for(Box b: tail){
            board[b.getY()][b.getX()] = 2;
        }
        return board;
    }
    public boolean contains(int x,int y){
        for(int i = 1; i < tail.size(); i++){
            if(tail.get(i).getX() == x && tail.get(i).getY() == y){
                return true;
            }
        }
        return false;
    }

    public void grow(){
        tail.add(new Box(tailX,tailY));
        tail.get(tail.size()-2).connect(tail.get(tail.size()-1));
    }

    public boolean collided(){
        if(isOnEdge())
            return true;
        for(int i = 1; i < tail.size(); i++){
            if(headX == tail.get(i).getX() && headY == tail.get(i).getY()){
                return true;
            }
        }
        return false;
    }

    public boolean isOnEdge(){
        if(headX == 0 || headX == 19 || headY == 0 || headY == 19){
            return true;
        }
        return false;
    }
}