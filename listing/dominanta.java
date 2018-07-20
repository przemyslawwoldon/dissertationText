public List<Double> calculateModeOfStdDev(List<Double> stdDevList) {
	Map<Double, Integer> calculateRepresentationOfValue = new HashMap<Double, Integer>();
	for (double d : stdDevList) {
		calculateRepresentationOfValue.compute(d, (k, v) -> v == null ? 1 : v + 1);
	}
	Map<Double, Integer> modeSpecificScope = calculateRepresentationOfValue.entrySet().stream()
			.filter(map -> map.getKey() < maxStdDev).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

	List<Double> keyModeStdDev = new ArrayList<Double>();
	if (modeSpecificScope.size() > 0 && modeSpecificScope != null) {
		long modeStdDev = modeSpecificScope.values().stream().max(Comparator.naturalOrder()).get();
		keyModeStdDev = modeSpecificScope.entrySet().stream().filter(e -> e.getValue() == modeStdDev)
				.map(Map.Entry::getKey).collect(Collectors.toList());
	}
	return keyModeStdDev;
}