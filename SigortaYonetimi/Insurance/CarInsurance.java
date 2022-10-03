package SigortaYonetimi.Insurance;

import java.util.Calendar;
import java.util.Date;

public class CarInsurance extends Insurance{
    public CarInsurance(){
        this.setName("Kasko");
        this.setPrice(2750);
        this.setStartDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 2);
        this.setEndDate(calendar.getTime());
    }

    @Override
    public void calculate() {
        this.setPrice(this.getPrice() * 1.2);
    }
}
