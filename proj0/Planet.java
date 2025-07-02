public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;	
	}		

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet other) {
		double sub_xx = other.xxPos - xxPos;
		double sub_yy = other.yyPos - yyPos;
		return Math.sqrt(sub_xx * sub_xx + sub_yy * sub_yy);		
	}

	public double calcForceExertedBy(Planet other) {
		double distance = this.calcDistance(other);
		return 6.67 * mass * other.mass / (1e11) / distance / distance;
	}

	public double calcForceExertedByX(Planet other) {
		double distance = this.calcDistance(other);
		double force = this.calcForceExertedBy(other);
		double x_distance = other.xxPos - xxPos;

		// if (x_distance < 0) x_distance = -x_distance;
		return force * x_distance / distance;
	}

	public double calcForceExertedByY(Planet other) {
		double distance = this.calcDistance(other);
		double force = this.calcForceExertedBy(other);
		double y_distance = other.yyPos - yyPos;

		// if (y_distance < 0) y_distance = -y_distance;
		return force * y_distance / distance;
	}

	public double calcNetForceExertedByX(Planet[] others) {
		double netForce = 0;

		for (Planet item : others) {
			if (!item.equals(this)) netForce += this.calcForceExertedByX(item);
		}	

		return netForce;
	}

	public double calcNetForceExertedByY(Planet[] others) {
		double netForce = 0;

		for (Planet item : others) {
			if (!item.equals(this)) netForce += this.calcForceExertedByY(item);
		}	

		return netForce;
	}

	public void update(double dt, double fx, double fy) {
		double ax = fx / mass;
		double ay = fy / mass;
		
		xxVel = xxVel + ax * dt;
		yyVel = yyVel + ay * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) return true;
		if (!(other instanceof Planet)) return false;

		return !imgFileName.equals(((Planet) other).imgFileName);
	}
}
