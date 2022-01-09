public class Distance {

	//GPIO Pins
	private GpioPinDigitalOutput sensorTriggerPin ;
	private GpioPinDigitalInput sensorEchoPin ;
	final GpioController gpio = GpioFactory.getInstance();

	public Distance(){
		sensorTriggerPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28); // Trigger pin as OUTPUT
		sensorEchoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23,PinPullResistance.PULL_UP); // Echo pin as INPUT
	}
	public double calculate() throws InterruptedException{
		try {
			sensorTriggerPin.high();
			Thread.sleep((long) 0.01);
			sensorTriggerPin.low();

			while(sensorEchoPin.isLow()){

			}
			long start= System.nanoTime();
			while(sensorEchoPin.isHigh()){

			}
			long finish= System.nanoTime();
			return ((((finish-start)/1e3)/2) / 29.1);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}

		return -1;
	}	
}
