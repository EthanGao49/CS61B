public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int int_planets_num = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int int_planets_num = in.readInt();
        double universe_radius = in.readDouble();
        Planet[] arr_planets = new Planet[int_planets_num];
        for(int i = 0; i < int_planets_num; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            arr_planets[i] = new Planet(xP, yP, xV, yV, mass, img);
        }
        return arr_planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] arr_planets = NBody.readPlanets(filename);
        double universe_radius = NBody.readRadius(filename);
        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-universe_radius, universe_radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        double time = 0.0;
        while (time < T) {
            double[] xForces = new double[arr_planets.length];
            double[] yForces = new double[arr_planets.length];
            for (int i = 0; i < arr_planets.length; i++) {
                xForces[i] = arr_planets[i].calcNetForceExertedByX(arr_planets);
                yForces[i] = arr_planets[i].calcNetForceExertedByY(arr_planets);
            }
            for (int j = 0; j < arr_planets.length; j++) {
                arr_planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : arr_planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", arr_planets.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < arr_planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    arr_planets[i].xxPos, arr_planets[i].yyPos, arr_planets[i].xxVel,
                    arr_planets[i].yyVel, arr_planets[i].mass, arr_planets[i].imgFileName);
        }
    }
}
