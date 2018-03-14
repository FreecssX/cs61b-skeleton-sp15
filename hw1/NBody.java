public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In planetFile = new In(filename);
		int planetNum = planetFile.readInt();
		double radius = planetFile.readDouble();
		Planet[] planets = new Planet[planetNum];
		
		/* store all the planets */
		int index = 0;
		while (index < planetNum) {
			planets[index] = getPlanet(planetFile);
			index += 1;
		}
		

		StdDraw.setCanvasSize(512, 512); //set the size of the universe
		
		double time = 0; //set init time
		
		/* ready to show the solar system */

		StdAudio.loop("audio/2001.mid"); //play sound;
		while (time < T) {
			
			/* update all the planets */
			int anotherIndex = 0;
			while (anotherIndex < planetNum) {
				planets[anotherIndex].setNetForce(planets);
				planets[anotherIndex].update(dt);
				anotherIndex += 1;
			}
			
			/* draw the universe and its planets */ 
			StdDraw.picture(0.5, 0.5, "images/starfield.jpg", 1, 1);
			index = 0;
			while (index < planetNum) {
				planets[index].draw(radius);
				index += 1;
			}
			StdDraw.show(5);
			time += dt;
		}
		StdAudio.close(); //close the sound;
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