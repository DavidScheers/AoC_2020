package AoC.day4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static AoC.day4.Passport_.build;
import static AoC.util.AOCFunctions.map8;

public class PassportParser {

    private static final Set<String> fields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid");

    private static final Map<String, String> mutableMap = new HashMap<>();

    public static Optional<Passport_> parse(List<String> input) {
        mutableMap.clear();
        input.forEach(PassportParser::parseLine);

        return build(mutableMap.get("byr"), mutableMap.get("iyr"), mutableMap.get("eyr"), mutableMap.get("hgt"),
                mutableMap.get("hcl"), mutableMap.get("ecl"), mutableMap.get("pid"), mutableMap.get("cid"));
    }

    public static Optional<Passport> parseStrict(List<String> input) {
        mutableMap.clear();
        input.forEach(PassportParser::parseLine);

        final var byr = Year.from(mutableMap.get("byr"));
        final var iyr = Year.from(mutableMap.get("iyr"));
        final var eyr = Year.from(mutableMap.get("eyr"));
        final var hgt = Height.from(mutableMap.get("hgt"));
        final var hcl = HairColor.from(mutableMap.get("hcl"));
        final var ecl = EyeColor.from(mutableMap.get("ecl"));
        final var pid = PassportId.from(mutableMap.get("pid"));
        final var cid = Optional.ofNullable(mutableMap.get("cid"))
                .or(() -> Optional.of(""));

        return map8(byr, iyr, eyr, hgt, hcl, ecl, pid, cid,
                year -> year1 -> year2 -> height -> hairColor -> eyeColor -> passportId -> country ->
                        Passport.build(year, year1, year2, height, hairColor, eyeColor, passportId, country));
    }

    public static void parseLine(String line) {
        final var key = line.substring(0, 3);
        final var value = line.substring(4);
        if (fields.contains(key)) {
            mutableMap.put(key, value);
        }
    }
}
