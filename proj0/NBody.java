public class NBody {
	public double universeRadius = 0;
	public Planet[] planets = null;

	private void init(String filePath) {
		In in = new In(filePath);

		int num_planets = in.readInt();
		universeRadius = in.readDouble();
		
		Planet[] planets = new Planet[num_planets];

		for (int i = 0; i < num_planets; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double maxx = in.readDouble();
			String path = in.readString();

			Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, maxx, path);
			planets[i] = planet;
		}

		this.planets = planets;
	}

	public static double readRadius(String filePath) {
		NBody nBody = new NBody();
		nBody.init(filePath);

		return nBody.universeRadius;
	}

	public static Planet[] readPlanets(String filePath) {
		NBody nBody = new NBody();
		nBody.init(filePath);

		return nBody.planets;
	}

	public static void main(String[] args) throws Exception {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		
		NBody nBody = new NBody();
		nBody.init(filename);

		StdDraw.picture(0, 0, "images/starfield.jpg", 2, 2);		
	}
}
