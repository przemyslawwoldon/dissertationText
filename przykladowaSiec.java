Map<Integer, Double> lrSchedule = new HashMap<Integer, Double>();
	lrSchedule.put(0, 0.001); // iteracja #, wskaznik uczenia
	lrSchedule.put(100, 0.0015);
	lrSchedule.put(200, 0.0001);
	lrSchedule.put(400, 0.00015);
	lrSchedule.put(800, 0.00001);
	
MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
	.seed(seed) //losowo generowana inicjalizacja wag
	.l2(1e-4) //regularyzacja l2
	.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT) 
	//algorytm optymalizacji
	.updater(new Nesterovs(new MapSchedule(ScheduleType.ITERATION, lrSchedule))) 
	//okreslenie kroku uczenia sie sieci w zaleznosci od iteracji
	.weightInit(WeightInit.XAVIER) //inicjalizacja wag
	.list() //lista kolejnych warstw sieci
	.layer(0, new ConvolutionLayer.Builder(5, 5)
			.nIn(channels) //liczba kanalow obrazu
			.stride(1, 1)
			.nOut(25) 
			.activation(Activation.TANH)
			.build())
	.layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
			.kernelSize(2, 2)
			.stride(2, 2)
			.build())
	.layer(2, new DenseLayer.Builder().activation(Activation.RELU)
			.nOut(500).build())
	.layer(3, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
			.nOut(outputNum)
			.activation(Activation.SOFTMAX)
			.build())
	.setInputType(InputType.convolutionalFlat(600, 15, 1)) 
	//600 - szerokosc, 15 - wysokosc, 1 - liczba kanalow obrazu wejsciowego
	.backprop(true).pretrain(false).build(); 
	//backprop - wlaczenie algorytmu propagacji wstecznej, 
	//pretrain - w przypadku uczenia bez nadzoru sieci specjalnych 
	//Restricted Boltzmann machine, autoencoders wartosc true	
	//w innych przypadkach false