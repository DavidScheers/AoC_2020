package AoC.day4;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

public final class Passport {

    private final Year birthYear;
    private final Year issueYear;
    private final Year expirationYear;
    private final Height height;
    private final HairColor hairColor;
    private final EyeColor eyeColor;
    private final PassportId passportID;
    private final String countryID;

    private Passport(Year birthYear, Year issueYear, Year expirationYear, Height height, HairColor hairColor,
                     EyeColor eyeColor, PassportId passportID, String countryID) {
        this.birthYear = birthYear;
        this.issueYear = issueYear;
        this.expirationYear = expirationYear;
        this.height = height;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.passportID = passportID;
        this.countryID = countryID;
    }

    public static Optional<Passport> build(Year birthYear, Year issueYear, Year expirationYear, Height height,
                                           HairColor hairColor, EyeColor eyeColor, PassportId passportID,
                                           String countryID) {
        if (isNull(birthYear) || isNull(issueYear) || isNull(expirationYear) || isNull(height) || isNull(hairColor)
                || isNull(eyeColor) || isNull(passportID)) {
            return Optional.empty();
        } else {
            final var passport = Optional.of(new Passport(birthYear, issueYear, expirationYear, height, hairColor, eyeColor,
                    passportID, countryID));
            return passport.filter(p -> p.getBirthYear().greaterThan(new Year(1919)) && p.getBirthYear().smallerThan(new Year(2003)))
                    .filter(p -> p.getExpirationYear().greaterThan(new Year(2019)) && p.getExpirationYear().smallerThan(new Year(2031)))
                    .filter(p -> p.getIssueYear().greaterThan(new Year(2009)) && p.getIssueYear().smallerThan(new Year(2021)));
        }
    }

    public Year getBirthYear() {
        return birthYear;
    }

    public Year getIssueYear() {
        return issueYear;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public Height getHeight() {
        return height;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public PassportId getPassportID() {
        return passportID;
    }

    public String getCountryID() {
        return countryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(birthYear, passport.birthYear) && Objects.equals(issueYear, passport.issueYear)
                && Objects.equals(expirationYear, passport.expirationYear) && Objects.equals(height, passport.height)
                && Objects.equals(hairColor, passport.hairColor) && eyeColor == passport.eyeColor
                && Objects.equals(passportID, passport.passportID) && Objects.equals(countryID, passport.countryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportID, countryID);
    }
}
