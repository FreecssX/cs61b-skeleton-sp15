public class Planet{
	static double G = 667E-13;
	double x;
	double y;
	double xVelocity;
	double yVelocity;
	double mass;
	double netForce;
	double xNetForce;
	double yNetForce;
	double xAccel;
	double yAccel;
	String img;
	public Planet(double xPos, double yPos, double xVel, double yVel, 
		          double planetMass, String imgName) {
		x = xPos;
		y = yPos;
		xVelocity = xVel;
		yVelocity = yVel;
		mass = planetMass;
		img = imgName;
	}
	public double calcDistance(Planet p) {
		double xDistanceSquare = (x - p.x) * (x - p.x);
		double yDistanceSquare = (y - p.y) * (y - p.y);
		return Math.sqrt(xDistanceSquare + yDistanceSquare);
	}
	public double calcPairwiseForce(Planet p) {
		double distance = calcDistance(p);
		double F = G * mass* p.mass / (distance * distance);
		return F;
	}	
	public double calcPairwiseForceX (Planet p) {
		double distance = calcDistance(p);
		double xDistance = Math.abs(x - p.x);
		double F = calcPairwiseForce(p);
		double FX = F / distance * xDistance;
		return FX;
	}
	public double calcPairwiseForceY (Planet p) {
		double distance = calcDistance(p);
		double yDistance = Math.abs(y - p.y);
		double F = calcPairwiseForce(p);
		double FY = F / distance * yDistance;
		return FY;
	}
	public void setNetForce(Planet[] ps) {
		int i = 0;
		double xF = 0;
		double yF = 0;
		while (i < ps.length) {
			if (this != ps[i]) {
				xF += calcPairwiseForceX(ps[i]);
				yF += calcPairwiseForceY(ps[i]);
			}
			i += 1;
		}
		xNetForce = xF;
		yNetForce = yF;
		netForce = Math.sqrt(xF * xF + yF * yF);
	}
	public void draw() {
		StdDraw.picture((x + 2.5e+11) / 5e+11, (y + 2.5e+11) / 5e+11, "images/" + img);
	}
	public void update(double dt) {
		xAccel = xNetForce / mass;
		yAccel = yNetForce / mass;
		xVelocity = xVelocity + xAccel * dt;
		yVelocity = yVelocity + yAccel * dt;
		x = x + xVelocity * dt;
		y = y + yVelocity * dt;
	}
}
