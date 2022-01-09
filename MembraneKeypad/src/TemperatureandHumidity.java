public class TemperatureandHumidity {

	private String pythonPath;
	String[] commands;
	Runtime rt;

	public TemperatureandHumidity(){
		pythonPath = new File("").getAbsolutePath()+"/src/Temperature.py";

		commands = new String[2];

		commands[0] = "python3";
		commands[1] = pythonPath;
		rt = Runtime.getRuntime();
	}

	@Override
	public String toString() {
		try{
			Process pr = rt.exec(commands);
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

			String line;

			if((line = bfr.readLine()) != null){

				String []tokens = line.split(";");

				String hum = "Hum : "+tokens[0];
				String temp = "Temp: "+tokens[1];

				return hum + temp;

			}
			else return "Reading Error!";
		}
		catch(IOException ex){
			return "Reading Error!";
		}
	}
	public String[] toStr(){
		try{
			Process pr = rt.exec(commands);
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			if((line = bfr.readLine()) != null){
				String []tokens = line.split(";");
				return tokens;
			}
			else throw new IllegalStateException("Reading Error!");
		}
		catch(IOException ex){
			throw new IllegalStateException("Reading Error!");
		}
	}
}
