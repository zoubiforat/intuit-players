package com.intuit.demo.service;

import java.io.*;

import com.intuit.demo.enitity.PlayerBuilder;
import com.intuit.demo.enitity.Player;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CSVService {

    @Autowired
    private PlayerService playerService;

//    @PostConstruct
    public void loadLocalResources() throws FileNotFoundException {
        fetchData(ResourceUtils.getFile("classpath:player.csv"));
    }

    @Transactional
    public void fetchData(File file){
        try(CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] headers = csvReader.readNext();
            if (headers != null) {
                String[] nextRecord;

                while ((nextRecord = csvReader.readNext()) != null) {

                        Player p = new PlayerBuilder()
                                .dateOfBirth(
                                    nextRecord[1],
                                    nextRecord[2],
                                    nextRecord[3]
                                )
                                .placeOfBirth(
                                    nextRecord[4],
                                    nextRecord[5],
                                    nextRecord[6]
                                )
                                .dateOfDeath(
                                    nextRecord[7],
                                    nextRecord[8],
                                    nextRecord[9]
                                )
                                .placeOfDeath(
                                    nextRecord[10],
                                    nextRecord[11],
                                    nextRecord[12]
                                )
                                .handOfBat(nextRecord[18])
                                .handOfThrow(nextRecord[19])
                                .weight(nextRecord[16])
                                .height(nextRecord[17])
                                .dateOfDebut(nextRecord[20])
                                .dateOfFinalGame(nextRecord[21])
                                .playerId(nextRecord[0])
                                .firstName(nextRecord[13])
                                .lastName(nextRecord[14])
                                .givenName(nextRecord[15])
                                .retroId(nextRecord[22])
                                .bbrefId(nextRecord[23])
                                .build();

                        playerService.savePlayer(p);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close(); //IOUtils.closeQuietly(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convFile;
    }


    public void importPlayers(MultipartFile file) {
        fetchData(convert(file));
    }

}
