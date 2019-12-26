public class Brain
{
    public int step = 0;
    public double[] directions;
    public double[] magnitudes;
    public Brain(int size) {
        directions = new double[size];
        magnitudes = new double[size];
        randomize();
    }
    
    public void randomize() {
        for (int i = 0; i < directions.length; i++) {
            directions[i] = Math.random() * 2 * Math.PI;
            magnitudes[i] = Math.random() * 5;
        }
    }
    
    public double getDirection(int index) {
        return directions[index];
    }
    public double getMagnitude(int index) {
        return magnitudes[index];
    }
    public Brain clone() {
        Brain brain = new Brain(directions.length);
        for (int i = 0; i < directions.length; i++) {
            brain.directions[i] = directions[i];
            brain.magnitudes[i] = magnitudes[i];
        }
        return brain;
    }
}
