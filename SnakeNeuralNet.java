import javax.swing.*;
import cs1.*;
public class SnakeNeuralNet{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setVisible(true);
        Display screen = new Display();
        frame.add(screen);
        NueralNet net = new NueralNet(400,200,3);
        Snake s = new Snake();
        int[][] board = new int[20][20];
        int direction  = 0;
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
        board[board.length/2][board[0].length/2] = 2;
        int[] food = pickRandSpot(board);
        board[food[0]][food[1]] = 3;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        screen.update(board);
        while(true){
            
            //int leftOb = checkLeft(direction,board);
            //int straightOb = checkStraight(direction,board);
            //int rightOb = checkRight(direction,board);
            //double angle = findAngle(direction);
            //int[] headPos = getHeadPos(board);
            //int[] foodPos = getFoodPos(board);
            double[] inputs =  new double[400];
            int count = 0;
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[0].length; col++){
                    inputs[count] = board[row][col];
                    count++;
                }
            }
            double[] decision = net.feedForward(inputs);
            double[] correctAnswer = new double[3];
            for(int r = 0; r < 3; r++){
                
            }
            int largest = 0;
            for(int rep = 0; rep < decision.length; rep++){
                if(decision[rep] > decision[largest]){
                    largest = rep;
                }
            }
            if(largest == 0){
                turnLeft(direction);
            }
            else if(largest == 2){
                turnRight(direction);
            }
            board = s.getBoard();
            board[food[0]][food[1]] = 3;
            s.move(direction);
            screen.update(board);
            if(s.collided()){
                break;
            }
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        screen.update(board);
    }
    public static int[] getHeadPos(int[][] b){
        int[] ret = new int[2];
        for(int row = 0; row < b.length; row++){
            for(int col = 0; col < b[0].length; col++){
                if(b[row][col] == 2){
                    ret[0] = row;
                    ret[1] = col;
                }
            }
        }
        return ret;
    }
    public static int[] getFoodPos(int[][] b){
        int[] ret = new int[2];
        for(int row = 0; row < b.length; row++){
            for(int col = 0; col < b[0].length; col++){
                if(b[row][col] == 3){
                    ret[0] = row;
                    ret[1] = col;
                }
            }
        }
        return ret;
    }
    /*
    public static double findAngle(int[][] b){
        int hr;
        int hc;
        int fr;
        int fc;
        for(int row = 0; row < b.length; row++){
            for(int col = 0; col < b[0].length; col++){
                if(b[row][col] == 2){
                    hr = row;
                    hc = col;
                }
                if(b[row][col] == 3){
                    fr = row;
                    fc = col;
                }
            }
        }
        if(hc == fc){
            if(hr > fr){
                
            }
        }
    }
    */
   
    public static int checkLeft(int d, int[][] b){
        int hr = 0;
        int hc = 0;
        for(int row = 0; row < b.length; row++){
            for(int col = 0; col < b[0].length; col++){
                if(b[row][col] == 2){
                    hr = row;
                    hc = col;
                    break;
                }
            }
        }
        if(d == 0 && b[hr][hc-1] == 1){
            return 1;
        }
        else if(d == 1 && b[hr-1][hc] == 1){
            return 1;
        }
        else if(d == 2 && b[hr][hc+1] == 1){
            return 1;
        }
        else if(b[hr+1][hc] == 1){
            return 1;
        }
        return 0;
    }
    public static int checkStraight(int d, int[][] b){
        int hr = 0;
        int hc = 0;
        for(int row = 0; row < b.length; row++){
            for(int col = 0; col < b[0].length; col++){
                if(b[row][col] == 2){
                    hr = row;
                    hc = col;
                    break;
                }
            }
        }
        if(d == 0 && b[hr-1][hc] == 1){
            return 1;
        }
        else if(d == 1 && b[hr][hc+1] == 1){
            return 1;
        }
        else if(d == 2 && b[hr+1][hc] == 1){
            return 1;
        }
        else if(b[hr][hc-1] == 1){
            return 1;
        }
        return 0;
    }
    public static int checkRight(int d, int[][] b){
        int hr = 0;
        int hc = 0;
        for(int row = 0; row < b.length; row++){
            for(int col = 0; col < b[0].length; col++){
                if(b[row][col] == 2){
                    hr = row;
                    hc = col;
                    break;
                }
            }
        }
        if(d == 0 && b[hr][hc+1] == 1){
            return 1;
        }
        else if(d == 1 && b[hr+1][hc] == 1){
            return 1;
        }
        else if(d == 2 && b[hr][hc-1] == 1){
            return 1;
        }
        else if(b[hr-1][hc] == 1){
            return 1;
        }
        return 0;
    }
    public static int[] pickRandSpot(int[][] b){
        int row = (int)(Math.random()*20); 
        int col = (int)(Math.random()*20);
        while(b[row][col] != 0){
            row = (int)(Math.random()*20);
            col = (int)(Math.random()*20);
        }
        int[] ret = new int[2];
        ret[0] = row;
        ret[1] = col;
        return ret;
    }
    public static int turnRight(int d){
        if(d == 0){
            d = 1;
        }
        else if(d == 1){
            d = 2;
        }
        else if(d == 2){
            d = 3;
        }
        else{
            d = 0;
        }
        return d;
    }
    //public static int[][] move
    public static int turnLeft(int d){
        if(d == 0){
            d = 3;
        }
        else if(d == 1){
            d = 0;
        }
        else if(d == 2){
            d = 1;
        }
        else{
            d = 2;
        }
        return d;
    }
}