public int calculateFirstRowOfBlackArea(List<Double> keyModeStdDev,List<Double> stdDevList){
	int k = 0;
	if (!keyModeStdDev.isEmpty()) {
		for (k = stdDevList.size() - 1; k >= 0; k -= 1) {
			if (stdDevList.get(k).equals(keyModeStdDev.get(0))) {
				break;
			}
		}
		for (int a = k; a >= 0; a -= 1) {
			if (!stdDevList.get(a).equals(keyModeStdDev.get(0))) {
				k = a;
				break;
			}
		}
	}
	return k;
}