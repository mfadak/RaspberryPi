public class Membrane {
	private List listener = new ArrayList();
	public static final String KEY = "key";
	public char pressedKey;
	private static final char keypad[][] = { { '1', '2', '3' },
											 { '4', '5', '6' },
											 { '7', '8', '9' },
											 { '*', '0', '#' } };

	private final GpioController theGpio = GpioFactory.getInstance();

	private static final Pin PIN_1_IN = RaspiPin.GPIO_11;
	private static final Pin PIN_2_IN = RaspiPin.GPIO_10;
	private static final Pin PIN_3_IN = RaspiPin.GPIO_14;
	private static final Pin PIN_4_IN = RaspiPin.GPIO_02;
	private static final Pin PIN_5_OUT = RaspiPin.GPIO_13;
	private static final Pin PIN_6_OUT = RaspiPin.GPIO_12;
	private static final Pin PIN_7_OUT = RaspiPin.GPIO_16;

	private final GpioPinDigitalInput thePin1 = theGpio.provisionDigitalInputPin(PIN_1_IN, PinPullResistance.PULL_UP);
	private final GpioPinDigitalInput thePin2 = theGpio.provisionDigitalInputPin(PIN_2_IN, PinPullResistance.PULL_UP);
	private final GpioPinDigitalInput thePin3 = theGpio.provisionDigitalInputPin(PIN_3_IN, PinPullResistance.PULL_UP);
	private final GpioPinDigitalInput thePin4 = theGpio.provisionDigitalInputPin(PIN_4_IN, PinPullResistance.PULL_UP);
	private final GpioPinDigitalOutput thePin5 = theGpio.provisionDigitalOutputPin(PIN_5_OUT);
	private final GpioPinDigitalOutput thePin6 = theGpio.provisionDigitalOutputPin(PIN_6_OUT);
	private final GpioPinDigitalOutput thePin7 = theGpio.provisionDigitalOutputPin(PIN_7_OUT);
	private final GpioPinDigitalOutput theOutputs[] = { thePin5, thePin6,thePin7};

	private GpioPinDigitalInput theInput;
	private int theLin;
	private int theCol;
	public Membrane(){
		pressedKey='-';
		initListeners();
	}

	private void initListeners() {

	thePin1.addListener(new GpioPinListenerDigital() {

	@Override
	public void handleGpioPinDigitalStateChangeEvent(

		final GpioPinDigitalStateChangeEvent aEvent) {

		if (aEvent.getState() == PinState.LOW) {

		theInput = thePin1;

		theLin = 1;

		findOutput();

		}

		}

	});

	thePin2.addListener(new GpioPinListenerDigital() {
	
	@Override
	public void handleGpioPinDigitalStateChangeEvent(

	final GpioPinDigitalStateChangeEvent aEvent) {

	if (aEvent.getState() == PinState.LOW) {
		theInput = thePin2;
		theLin = 2;

		findOutput();
	}

	}

	});

	thePin3.addListener(new GpioPinListenerDigital() {

	@Override
	public void handleGpioPinDigitalStateChangeEvent(


	final GpioPinDigitalStateChangeEvent aEvent) {

	if (aEvent.getState() == PinState.LOW) {

	theInput = thePin3;
	theLin = 3;
	findOutput();
	}
	}
	});


	thePin4.addListener(new GpioPinListenerDigital() {


	@Override
	public void handleGpioPinDigitalStateChangeEvent(
	final GpioPinDigitalStateChangeEvent aEvent) {
		if (aEvent.getState() == PinState.LOW) {
			theInput = thePin4;
			theLin = 4;
			findOutput();
		}
	}
	});
	}

	private void findOutput() {
		for (int myO = 0; myO &lt; theOutputs.length; myO++) {
		for (final GpioPinDigitalOutput myTheOutput : theOutputs) {
		myTheOutput.high();
	}
	theOutputs[myO].low();
	if (theInput.isLow()) {

		theCol = myO;
		checkPins();
		try {
			Thread.sleep(200);
		} 
		catch (InterruptedException e) {

		}
		break;
	}


	}
	for (final GpioPinDigitalOutput myTheOutput : theOutputs) {
		myTheOutput.low();
	}
	}

	private synchronized void checkPins() {
		notifyListeners(this, KEY, this.pressedKey,
		this.pressedKey = keypad[theLin - 1][theCol]);
	}

	private void notifyListeners(Object object, String property, char oldValue,char newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, property,oldValue, newValue));
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}
}
