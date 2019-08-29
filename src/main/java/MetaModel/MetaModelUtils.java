package MetaModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MetaModelUtils {
    private static List<String> getSubLevelDirs(String topLevelDir) throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(topLevelDir));
        return walk.filter(Files::isDirectory).
                map(Path::toString).collect(Collectors.toList());
    }

    private static List<String> getSubLevelDirs(String topLevelDir, String filter) throws IOException {
        List<String> filtered = new ArrayList<>();
        for (String dir : getSubLevelDirs(topLevelDir))
            if (!dir.toLowerCase().contains(filter.toLowerCase()))
                filtered.add(dir);
        return filtered;
    }

}
