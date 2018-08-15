import java.awt.*;
import javax.swing.*;
public class Display extends JPanel{
    int[][] board;
    public Display(){
        super();
        board = new int[20][20];
    }
    public void update(int[][] b){
        board = b;
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                if(board[row][col] == 0){
                    g.setColor(Color.BLACK);
                }
                else if(board[row][col] == 3){
                    g.setColor(Color.RED);
                }
                else if(board[row][col] == 2){
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(row*50,col*50,50,50);
            }
        }
    }
}