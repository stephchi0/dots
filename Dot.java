import java.awt.*;
public class Dot
{
    public Brain brain = new Brain(300);
    
    public boolean best = false;
    public int status = 0;
    public double fitness = 0;
    public int goalX = 250;
    public int goalY = 45;
    
    public int size = 4;
    
    public double mass = 1;
    public double time = 1;
    public double limit = 50;
    public int xPosition = 0;
    public int yPosition = 0;
    public double velocityMagnitude = 0;
    public double velocityDirection = 0;
    public double xAcceleration = 0;
    public double yAcceleration = 0;
    
    Obstacle[] obstacles = {
        new Obstacle(0, 350, 300, 320),
        new Obstacle(375, 395, 0, 250)
    };
    
    public Dot() {
        xPosition = 250;
        yPosition = 490;
    }
    public Dot reset() {
        status = 0;
        xPosition = 250;
        yPosition = 490;
        velocityMagnitude = 0;
        velocityDirection = 0;
        brain.step = 0;
        return this;
    }
    public int getStatus() {
        if (status == 0) {
            if (Course.collision(xPosition, yPosition)){//collision
                xPosition = (xPosition < 0) ? 0 : xPosition;
                xPosition = (xPosition > 500) ? 500 : xPosition;
                yPosition = (yPosition < 0) ? 0 : yPosition;
                yPosition = (yPosition > 500) ? 500 : yPosition;
                status = -1;
            }
            else if (xPosition >= 240 && xPosition <= 260 &&
                     yPosition >= 25 && yPosition <= 45) {//reaches end
                status = 1;
            }
        }
        return status;
    }
    public void show(Graphics g) {
        if (best) {
            g.setColor(Color.green);
            int size2 = 8;
            g.fillOval(xPosition - (size + size2)/2, yPosition - (size + size2)/2, size + size2, size + size2);
        }
        if (getStatus() > 0) {
            g.setColor(Color.green);
        }
        else if (getStatus() < 0) {
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.black);
        }
        g.fillOval(xPosition - size/2, yPosition - size/2, size, size);
    }
    public boolean update() {
        if (getStatus() == 0) {
            if (brain.step < brain.directions.length) {
                double direction = brain.getDirection(brain.step);
                double magnitude = brain.getMagnitude(brain.step);
                applyForce(direction, magnitude);
                move();
                brain.step++;
                return true;
            }
            else {
                status = -1;
            }
        }
        return false;
    }
    public void applyForce(double direct, double mag) {
        xAcceleration = (mag*Math.cos(direct))/mass;
        yAcceleration = (mag*Math.sin(direct))/mass;
        double xVelocity = velocityMagnitude*Math.cos(velocityDirection) + xAcceleration*time;
        double yVelocity = velocityMagnitude*Math.sin(velocityDirection) + yAcceleration*time;
        
        velocityMagnitude = Math.sqrt(xVelocity*xVelocity + yVelocity*yVelocity);
        velocityMagnitude = (velocityMagnitude > limit) ? limit : velocityMagnitude;
        if (xVelocity == 0) {
            velocityDirection = Math.PI/2;
            if (yVelocity < 0) velocityDirection += Math.PI;
        }
        else {
            velocityDirection = Math.atan(yVelocity / xVelocity);
            if (xVelocity < 0) {
                velocityDirection += Math.PI;
            }
        }
    }
    public void move() {
        if (velocityMagnitude == limit) {//technically not how physics works
            xPosition += velocityMagnitude * Math.cos(velocityDirection);
            yPosition += velocityMagnitude * Math.sin(velocityDirection);
        }
        else {//d = vt + 0.5at^2
            //initial x and y velocities
            double xVelocity = velocityMagnitude*Math.cos(velocityDirection) - xAcceleration*time;
            double yVelocity = velocityMagnitude*Math.sin(velocityDirection) - yAcceleration*time;
            xPosition += xVelocity*time + 0.5*xAcceleration*time*time;
            yPosition += yVelocity*time + 0.5*yAcceleration*time*time;
        }
    }
    public void calculateFitness() {
        double xDistance = xPosition - goalX;
        double yDistance = yPosition - goalY;
        double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance) + 1;
        double steps = brain.step;
        fitness = 1.0/distance/distance;
        if (status == 1) fitness += 1.0/steps/steps;
    }
}
