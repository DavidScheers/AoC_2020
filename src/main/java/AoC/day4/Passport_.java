package AoC.day4;

import java.util.Optional;

import static java.util.Objects.isNull;

public final class Passport_ {

    private final String birthYear;
    private final String issueYear;
    private final String expirationYear;
    private final String height;
    private final String hairColor;
    private final String eyeColor;
    private final String passportID;
    private final String countryID;

    Passport_(String birthYear, String issueYear, String expirationYear, String height, String hairColor, String eyeColor, String passportID, String countryID) {
        this.birthYear = birthYear;
        this.issueYear = issueYear;
        this.expirationYear = expirationYear;
        this.height = height;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.passportID = passportID;
        this.countryID = countryID;
    }

    public static Optional<Passport_> build(String birthYear, String issueYear, String expirationYear, String height,
                                            String hairColor, String eyeColor, String passportID, String countryID) {
        if (isNull(birthYear) || isNull(issueYear) || isNull(expirationYear) || isNull(height) || isNull(hairColor)
                || isNull(eyeColor) || isNull(passportID)) {
            return Optional.empty();
        } else {
            return Optional.of(new Passport_(birthYear, issueYear, expirationYear, height, hairColor, eyeColor,
                    passportID, countryID));
        }
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getIssueYear() {
        return issueYear;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public String getHeight() {
        return height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getPassportID() {
        return passportID;
    }

    public Optional<String> getCountryID() {
        return Optional.ofNullable(countryID);
    }
}
