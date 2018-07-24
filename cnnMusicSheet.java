int height = 600;
int width = 15;
int channels = 1; // single channel for grayscale images
int outputNum = 2; // 2 class classification
int batchSize = 128;
int nEpochs = 64;
int seed = 1234;
		
Map<Integer, Double> lrSchedule = new HashMap<Integer, Double>();
	lrSchedule.put(0, 0.001); // iteration #, learning rate
	lrSchedule.put(100, 0.0015);
	lrSchedule.put(200, 0.0001);
	lrSchedule.put(400, 0.00015);
	lrSchedule.put(800, 0.00001);
	lrSchedule.put(1200, 0.000015);
	lrSchedule.put(1500, 0.0001);

MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
	.seed(seed)
	.l2(0.0015 * 0.005)
	.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
	.updater(new Nesterovs(new MapSchedule(ScheduleType.ITERATION, lrSchedule)))
	.weightInit(WeightInit.XAVIER)
	.list()
	.layer(0, new ConvolutionLayer.Builder(5, 5)
			.nIn(channels)
			.stride(1, 1)
			.nOut(20)
			.activation(Activation.TANH)
			.build())
	.layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
			.kernelSize(2, 2)
			.stride(2, 2)
			.build())
	.layer(2, new ConvolutionLayer.Builder(1, 1)
			.stride(1, 1) // nIn need not specified in later layers
			.nOut(100)
			.activation(Activation.IDENTITY)
			.build())
	.layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
			.kernelSize(1, 1)
			.stride(1, 1)
			.build())
	.layer(4, new DenseLayer.Builder().activation(Activation.RELU)
			.nOut(500).build())
	.layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
			.nOut(outputNum)
			.activation(Activation.SOFTMAX)
			.build())
	.setInputType(InputType.convolutionalFlat(600, 15, 1)) 
	.backprop(true).pretrain(false).build();