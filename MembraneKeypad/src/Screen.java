public class Screen {
public final int ScreenLine1 = 0;
public final int EkranSatir2 = 1;
public final String LineSpace = " ";
final GpioController gpio = GpioFactory.getInstance();
final GpioLcdDisplay lcd = new GpioLcdDisplay(2,    // LCD row Count
                                              16,       // LCD column count
                                                RaspiPin.GPIO_06,  // LCD RS
                                                RaspiPin.GPIO_05,  // LCD E
                                                RaspiPin.GPIO_04,  // LCD D4
                                                RaspiPin.GPIO_00,  // LCD D5
                                                RaspiPin.GPIO_01,  // LCD D6
                                                RaspiPin.GPIO_03); // LCD D7 

public void Display(String str1, String str2,boolean center ,boolean clear){
    try{
        if(clear) lcd.clear();

        Thread.sleep(100);
        String str1Space="",str2Space="";

        if(center){
            str1Space = Space(str1);
            str2Space = Space(str2);
        }
        lcd.write(ScreenLine1,str1Space + str1 + str1Space);
        Thread.sleep(100);
        lcd.write(EkranSatir2,str2Space + str2 + str2Space);
        Thread.sleep(500);
    }
    catch(Exception e){

    }
}

private String Space(String mesaj){
    int space = (16-mesaj.length())/2;
    return LineSpace.substring(0, space);
}
public void Clear(){
    try{
    lcd.clear();
    Thread.sleep(500);
    }
    catch(Exception e){

    }
}
public void Close(){
    try{
        lcd.clear();
        Thread.sleep(500);
    }
    catch(Exception e){

    }
    gpio.shutdown();
}
}
