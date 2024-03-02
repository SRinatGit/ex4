package ru.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AutorizationParser {
    public List<Autorization>  parse(String path, String fileName) {
        String pathAndFileName = "";
        if (!fileName.isEmpty()) {
            pathAndFileName = path + fileName;
        }

        //System.out.println("init parse...");
        ArrayList<File> list = getFileAll(path, pathAndFileName); // массив всех файлов csv из каталога path
        //System.out.println(" в каталоге " + path + " нашли " + list.size() + " файлов .csv");

        List<Autorization> autorizationList = new ArrayList<>(); // массив хранения готовых объектов авторизации
        for (File file : list) {
            String nameFile = file.toString();
            try {
                //System.out.println(" parsing file :" + nameFile);
                CSVReader reader =
                        new CSVReaderBuilder(new FileReader(nameFile)).
                                withSkipLines(1) //считываем залоговки
                                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())// установим разделитель, по умолчанию ","
                                .build();

                autorizationList = reader.readAll().stream().map(data -> {
                                                                            Autorization autorizationObj = new Autorization();
                                                                            autorizationObj.setUsername(data[0]);
                                                                            autorizationObj.setFio(data[1]);
                                                                            autorizationObj.setaccessDateStr(data[2]);
                                                                            autorizationObj.setApplication(data[3]);
                                                                            autorizationObj.setFileName(nameFile);
                                                                            System.out.println(autorizationObj);
                                                                            return autorizationObj;
                                                                        }
                                                                 ).toList();
                autorizationList.forEach(System.out::println);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        //System.out.println(autorizationList);
        return autorizationList;
    }

    //нам интересны только файлы с расширением .csv
    public ArrayList<File> getFileAll(String path, String fileName) {
        File folder = new File(path);
        String name;
        String[] pr;
        File[] allFiles = folder.listFiles();
        ArrayList<File> filesList = new ArrayList<>();

        if (allFiles != null) {
            for (File file : allFiles) {
                name = file.toString();
                pr = name.split("\\.");
                if (pr[pr.length - 1].equals("csv")) {
                    if (fileName.isEmpty() || (!fileName.isEmpty() && name.equals(fileName))) {
                        filesList.add(file);
                    }
                }
            }
        } else {
            System.out.printf("Файлы в каталоге'" + path + "' не найдены");
        }

        return filesList;
    }
}