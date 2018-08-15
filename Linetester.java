
/**
 * Write a description of class dfjb here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Linetester
{
    public static void main(String[] args){
        //determines if a point is above the line x=y
        NueralNet net = new NueralNet(2,2,1);
        double[][] trainingData = new double[100][2];
        double[][] testData = new double[100][2];
        for(int i = 0; i < trainingData.length; i++){
            trainingData[i][0] = Math.random();
            trainingData[i][1] = Math.random();
        }
        for(int i = 0; i < trainingData.length; i++){
            testData[i][0] = Math.random()*500+1;
            testData[i][1] = Math.random()*500+1;
        }
        for(int i = 0; i < trainingData.length; i++){
            double[] guess = net.feedForward(trainingData[i]);
            double[] answer = new double[1];
            if(trainingData[i][0] < trainingData[i][0]){
                answer[0] = 1;
            }
            if((answer[0] == 0 && guess[0] >= 0.5) || (answer[0] == 1 && guess[0] < 0.5)){
                net.backPropagate(guess,answer,.05);
                System.out.println("Incorrect");
            }
            else{
                System.out.println("Correct");
            }
        }
        System.out.println("-----------------------------------------");
        for(int i = 0; i < testData.length; i++){
            double[] guess = net.feedForward(testData[i]);
            double[] answer = new double[1];
            if(testData[i][0] < testData[i][0]){
                answer[0] = 1;
            }
            if(answer[0] == 0 && guess[0] >= 0.5){
                System.out.println("Incorrect");
            }
            else if(answer[0] == 1 && guess[0] < 0.5){
                System.out.println("Incorrect");
            }
            else{
                System.out.println("Correct");
            }
        }
    }
}
