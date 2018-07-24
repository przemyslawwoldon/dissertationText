public double calculateAngle(double width) {
	double meanRotate = 0;
	int counterLine = 0;
	for(Scalar p : goodLines) {
		meanRotate += Math.abs((p.val[3] - p.val[1]));
		counterLine += 1;
	}
	meanRotate /= counterLine;
	double angleRotTan = (/*-*/1 * meanRotate) / width;
	return Math.toDegrees(Math.atan(angleRotTan));	
}