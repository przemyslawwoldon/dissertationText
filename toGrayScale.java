public void toGrayScale(Mat srcImg) {
	for (int y = 0; y < srcImg.rows(); y += 1) {
		for (int x = 0; x < srcImg.cols(); x += 1) {
			double rgb[] = srcImg.get(y, x);
			double greyScale = (rgb[0] + rgb[1] + rgb[2]) / 3;
			srcImg.put(y, x, greyScale, greyScale, greyScale);
		}
	}
}