public class NueralNet
{
    int inputNodes;
    int hiddenNodes;
    int outputNodes;
    double[][] inputToHiddenWeights;
    double[][] hiddenToOutputWeights;
    double[] hiddenBiases;
    double[] outputBiases;
    public NueralNet(int IN, int HN, int ON)
    {
        inputNodes = IN;
        hiddenNodes  = HN;
        outputNodes = ON;
        hiddenBiases = new double[hiddenNodes];
        outputBiases = new double[outputNodes];
        inputToHiddenWeights = new double[IN][HN];
        hiddenToOutputWeights = new double[HN][ON];
        inputToHiddenWeights = randomize(inputToHiddenWeights);
        hiddenToOutputWeights = randomize(hiddenToOutputWeights);
    }
    public double[][] randomize(double[][] arr){
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                arr[row][col] = Math.random()*2-1;
            }
        }
        return arr;
    }
    public double[] feedForward(double[] inputs){
        double[] hiddenVals = new double[hiddenNodes];
        double[] results = new double[outputNodes];
        for(int col = 0; col < hiddenNodes; col++){
            double sum = 0;
            for(int row  = 0; row < inputNodes; row++){
                sum += inputs[row] * inputToHiddenWeights[row][col];
            }
            hiddenVals[col] = sigmoid(sum + hiddenBiases[col]);
        }
        for(int col = 0; col < outputNodes; col++){
            double sum = 0;
            for(int row  = 0; row < hiddenNodes; row++){
                sum += hiddenVals[row] * hiddenToOutputWeights[row][col];
            }
            results[col] = sigmoid(sum + outputBiases[col]);
        }
        return results;
    }
    public void backPropagate(double[] guesses, double[] targetAnswers, double learningRate){
        double[] outputError = new double[outputNodes];
        double[] hiddenError = new double[hiddenNodes];
        //finds output layer node errors
        for(int x = 0; x < guesses.length; x++){
            outputError[x] = targetAnswers[x] - guesses[x];
        }
        //finds hidden layer node errors
        int count = 0;
        for(int outputNodeNum = 0; outputNodeNum < outputNodes; outputNodeNum++){
            double sum = 0;
            for(int hiddenNodeNum = 0; hiddenNodeNum < hiddenNodes; hiddenNodeNum++){
                sum += hiddenToOutputWeights[hiddenNodeNum][outputNodeNum];
            }
            hiddenError[count] += hiddenToOutputWeights[count][outputNodeNum];
            if(count < hiddenNodes-1){
                count++;
                outputNodeNum--;
            }
            else{
                count = 0;
            }
        }
        // adjust hidden to output weights 
        for(int outputNodeNum = 0; outputNodeNum < outputNodes; outputNodeNum++){
            double sum = 0;
            for(int hiddenNodeNum = 0; hiddenNodeNum < hiddenNodes; hiddenNodeNum++){
                sum += hiddenToOutputWeights[hiddenNodeNum][outputNodeNum];
            }
            for(int hiddenNodeNum = 0; hiddenNodeNum < hiddenNodes; hiddenNodeNum++){
                hiddenToOutputWeights[hiddenNodeNum][outputNodeNum] += hiddenToOutputWeights[hiddenNodeNum][outputNodeNum]/sum * outputError[outputNodeNum] * learningRate;
            }
        }
        // adjust input to hidden weights
        for(int hiddenNodeNum = 0; hiddenNodeNum < hiddenNodes; hiddenNodeNum++){
            double sum = 0;
            for(int inputNodeNum = 0; inputNodeNum < inputNodes; inputNodeNum++){
                sum += inputToHiddenWeights[inputNodeNum][hiddenNodeNum];
            }
            for(int inputNodeNum = 0; inputNodeNum < inputNodes; inputNodeNum++){
                inputToHiddenWeights[inputNodeNum][hiddenNodeNum] += inputToHiddenWeights[inputNodeNum][hiddenNodeNum]/sum * hiddenError[hiddenNodeNum] * learningRate;
            }
        }
    }
    public double sigmoid(double val){
        return 1 / (1 + Math.exp(-val));
    }
}