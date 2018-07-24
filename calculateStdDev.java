public List<Double> calculateStdDev(Mat srcImg, int width) {
	List<Double> stdDevList = new ArrayList<Double>();
	for (int j = 0; j < srcImg.rows(); j += 1) {
		Mat temp = new Mat(srcImg, new Rect(0, j, width, 1)).clone();
		MatOfDouble mean = new MatOfDouble();
		MatOfDouble stddev = new MatOfDouble();
		Core.meanStdDev(temp, mean, stddev);
		stdDevList.add(stddev.get(0, 0)[0]);
		System.gc();
	}
	return stdDevList;
}