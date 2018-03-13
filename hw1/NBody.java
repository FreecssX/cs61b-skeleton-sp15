public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In planetFile = new In(filename);
		int planetNum = planetFile.readInt();
		double radius = planetFile.readDouble();
		Planet[] planets = new Planet[planetNum];
		int index = 0;
		while (index < planetNum) {
			planets[index] = getPlanet(planetFile);
			index += 1;
		}
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(512, 512);
		double time = 0;
		int anotherIndex = 0;
		while (time < T) {
			Planet[] planetsCopy = planets;
			while (anotherIndex < planetNum) {
				planets[anotherIndex].setNetForce(planetsCopy);
				planets[anotherIndex].update(dt);
				anotherIndex += 1;
			}
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, "images/starfield.jpg", 1, 1);
			index = 0;
			while (index < planetNum) {
				planets[index].draw();
				index += 1;
			}
			StdDraw.show();
            StdDraw.pause(20);
			time += dt;
		}
	}
	public static Planet getPlanet(In in) {
		String line = in.readLine();
		double x = Double.parseDouble(in.readString());
		double y = Double.parseDouble(in.readString());
		double xVelocity = Double.parseDouble(in.readString());
		double yVelocity = Double.parseDouble(in.readString());
		double mass = Double.parseDouble(in.readString());
		String img = in.readString();
		return new Planet(x, y, xVelocity, yVelocity, mass, img);
	}
}