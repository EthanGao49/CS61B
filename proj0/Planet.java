public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
    }

    public double calcDistance(Planet rocinante){
        double dx = rocinante.xxPos - this.xxPos;
        double dy = rocinante.yyPos - this.yyPos;
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance;
    }

    public double calcForceExertedBy(Planet rocinante){
        double G = 6.67e-11;
        double dis = calcDistance(rocinante);
        return G*this.mass*rocinante.mass/(dis*dis);
    }

    public double calcForceExertedByX(Planet rocinante){
        return calcForceExertedBy(rocinante)*(rocinante.xxPos - this.xxPos)/calcDistance(rocinante);
    }

    public double calcForceExertedByY(Planet rocinante){
        return calcForceExertedBy(rocinante)*(rocinante.yyPos - this.yyPos)/calcDistance(rocinante);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0.0;
        for (Planet planet : allPlanets) {
            if (!(this.equals(planet))) {
                netForceX += this.calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0.0;
        for (Planet planet : allPlanets) {
            if (!(this.equals(planet))) {
                netForceY += this.calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double time, double x_force, double y_force){
        double ax = x_force/this.mass;
        double ay = y_force/this.mass;
        this.xxVel += ax*time;
        this.yyVel += ay*time;
        this.xxPos = this.xxPos + this.xxVel*time;
        this.yyPos = this.yyPos + this.yyVel*time;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }
}
