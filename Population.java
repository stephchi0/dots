import java.awt.*;
public class Population
{
    public Dot[] dots;
    public Dot bestDot;
    public boolean extinct = false;
    public int size;
    public int generationNumber = 1;
    
    public Population(int size){
        this.size = size;
        dots = new Dot[size];
        for (int i = 0; i < size; i++) {
            dots[i] = new Dot();
        }
    }
    
    public void show(Graphics g) {
        for (int i = 0; i < dots.length; i++) {
            dots[i].show(g);
        }
    }
    public void update() {
        boolean updated = false;//checks to see if at least one thing updated
        for (int i = 0; i < dots.length; i++) {
            updated = dots[i].update() || updated;
        }
        extinct = !updated;
    }
    public void calculateFitness() {
        for (int i = 0; i < dots.length; i++) {
            dots[i].calculateFitness();
        }
    }
    public double generationFitness() {
        double fitness = 0;
        for (int i = 0; i < dots.length; i++) {
            fitness += dots[i].fitness;
        }
        return fitness;
    }
    public void getBest() {
        bestDot = dots[0];
        bestDot.best = true;
        for (int i = 1; i < dots.length; i++) {
            if (dots[i].fitness > bestDot.fitness) {
                bestDot.best = false;
                bestDot = dots[i];
                bestDot.best = true;
            }
        }
        bestDot.reset();
    }
    public Dot getParent() {
        double random = Math.random() * generationFitness();
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += dots[i].fitness;
            if (sum >= random) {
                return dots[i];
            }
        }
        return new Dot();
    }
    public void cull() {
        calculateFitness();
        Dot[] newPopulation = new Dot[size];
        
        getBest();
        newPopulation[0] = bestDot;
        for (int i = 1; i < size; i++) {
            Brain childBrain = getParent().brain.clone();
            mutateBrain(childBrain);
            Dot child = new Dot();
            child.brain = childBrain;
            
            newPopulation[i] = child;
        }
        dots = newPopulation;
        generationNumber++;
    }
    public void mutateBrain(Brain brain) {
        double directionMutationRate = 0.01;
        for (int i = 0; i < brain.directions.length; i++) {
            if (Math.random() < directionMutationRate) {
                brain.directions[i] = Math.random() * 2 * Math.PI;
            }
        }
        
        double magnitudeMutationRate = 0.05;
        for (int i = 0; i < brain.magnitudes.length; i++) {
            if (Math.random() < magnitudeMutationRate) {
                brain.magnitudes[i] = Math.random() * 5;
            }
        }
    }
}
