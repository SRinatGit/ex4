package ru.example;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ValidatorComponent {


    @LogTransformation(logFile = "myLog.log")
    public boolean isValidDate(Autorization a){

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            String accessDateStr = a.getaccessDateStr();
            if (StringUtils.hasText(accessDateStr)) {
                startDate = df.parse(accessDateStr);
                a.setAccessDate(startDate);
                return true;
            }
            else {
                System.out.println("НЕ ВЕРНАЯ ДАТА : " + a.getFileName() + " " + a.getUsername() +" "+ a.getFio());

                try { // запись в лог
                    Method method = ValidatorComponent.class.getMethod("isValidDate", Autorization.class);
                    String errString = "Не верный формат даты";
                    LogTransformationClass.logTransformation(a, method, errString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            //e.printStackTrace();
            // преобразовать не удалось, пишем в лог
            //TODO  Если дата не задана, то человек не вносится в базу, а сведения о имени файла и значении человека заносятся в отдельный лог.
            System.out.println("Пустая data: " + a.getFileName() + " " + a.getUsername() +" "+ a.getFio());
            try { // запись в лог
                Method method = ValidatorComponent.class.getMethod("isValidDate", Autorization.class);
                String errString = "Дата пришла пустой";
                LogTransformationClass.logTransformation(a, method, errString);
            } catch (Exception ee) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String isValidateFio(String fio) {
        String fio2 = "";
        fio2 = fio2 + fio.substring(0, 1).toUpperCase(); //первый символ делаем заглавным
        for (int i = 1; i < fio.length(); i++) {
            // смотрим, был ли слева пробел:
            if (" ".equals(fio.substring(i-1, i)))
                fio2 = fio2 + fio.substring(i, i+1).toUpperCase();
            else
                fio2 = fio2 + fio.substring(i, i+1);
        }
        return fio2;
        //this.fio = fio2;
    }
    public String isApplication(String application) {
        //“web”, “mobile”. Если там записано что-либо иное, то оно преобразуется к виду “other:”+значение.
        if (!application.equals("web") && !application.equals("mobile")){
            application = "other " + application;
        }
        return application;
        //this.fio = fio2;
    }

}