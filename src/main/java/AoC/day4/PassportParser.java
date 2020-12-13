package AoC.day4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

public class PassportParser {

    private static final Set<String> fields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid");

    private static final Map<String, String> mutableMap = new HashMap<>();

    static Optional<Passport> build(String birthYear, String issueYear, String expirationYear, String height,
                                    String hairColor, String eyeColor, String passportID, String countryID) {
        if (isNull(birthYear) || isNull(issueYear) || isNull(expirationYear) || isNull(height) || isNull(hairColor)
                || isNull(eyeColor) || isNull(passportID)) {
            return Optional.empty();
        } else {
            return Optional.of(new Passport(birthYear, issueYear, expirationYear, height, hairColor, eyeColor,
                    passportID, countryID));
        }
    }

    public static Optional<Passport> parse(List<String> input) {
        mutableMap.clear();
        input.forEach(PassportParser::parseLine);

        return build(mutableMap.get("byr"), mutableMap.get("iyr"), mutableMap.get("eyr"), mutableMap.get("hgt"),
                mutableMap.get("hcl"), mutableMap.get("ecl"), mutableMap.get("pid"), mutableMap.get("cid"));
    }

    public static void parseLine(String line) {
        final var key = line.substring(0, 3);
        final var value = line.substring(4);
        if (fields.contains(key)) {
            mutableMap.put(key, value);
        }
    }
}
