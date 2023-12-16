package com.intuit.demo.enitity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Date;
import java.time.LocalDate;

public class PlayerBuilder extends Player.PlayerBuilder {

    public PlayerBuilder(){
        super();
    }
    public PlayerBuilder dateOfBirth(String year, String month, String day) {
        super.dateOfBirth(parseDate(year, month, day));
        return this;
    }

    public PlayerBuilder dateOfDeath(String year, String month, String day) {
        super.dateOfDeath(parseDate(year, month, day));
        return this;
    }

    public PlayerBuilder dateOfDebut(String dateOfDebut) {
        super.dateOfDebut(parseDateString(dateOfDebut));
        return this;
    }

    public PlayerBuilder dateOfFinalGame(String dateOfFinalGame) {
        super.dateOfFinalGame(parseDateString(dateOfFinalGame));
        return this;
    }

    public PlayerBuilder placeOfBirth(String country, String state, String city) {
        super.placeOfBirth(parsePlace(country, state, city));
        return this;
    }

    public PlayerBuilder placeOfDeath(String country, String state, String city) {
        super.placeOfDeath(parsePlace(country, state, city));
        return this;
    }

    public PlayerBuilder weight(String weight) {
        super.weight(parseDouble(weight));
        return this;
    }

    public PlayerBuilder height(String height) {
        super.height(parseDouble(height));
        return this;
    }

    public PlayerBuilder handOfBat(String handOfBat) {
        super.handOfBat(parseHandType(handOfBat));
        return this;
    }

    public PlayerBuilder handOfThrow(String handOfThrow) {
        super.handOfThrow(parseHandType(handOfThrow));
        return this;
    }

    private HandType parseHandType(String handType) {
        try {
            return HandType.valueOf(handType);
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate parseDate(String year, String month, String day) {
        return StringUtils.isBlank(year) || StringUtils.isBlank(month) || StringUtils.isBlank(day)?
                null : (LocalDate.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day))
        );
    }

    private Place parsePlace(String country, String state, String city) {
        return StringUtils.isBlank(country) || StringUtils.isBlank(state) || StringUtils.isBlank(city)?
                null : Place.builder().country(country).state(state).city(city).build();
    }

    private Double parseDouble(String doubleString){
        return StringUtils.isBlank(doubleString)? null : Double.parseDouble(doubleString);
    }

    private LocalDate parseDateString(String date) {
        try {
            return new Date(DateUtils.parseDateStrictly(
                    date,
                    "yyyy/MM/dd",
                    "dd/MM/yyyy",
                    "yyyy-MM-dd"
            ).getTime()).toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }
}
