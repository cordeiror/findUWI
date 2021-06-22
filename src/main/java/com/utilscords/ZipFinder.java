package com.utilscords;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Finder implementation that works with zip files
 *
 */
public class ZipFinder implements Finder {
    @Override
    public List<String> find(String directory, String textToFind) {
        List<String> finalResult = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(directory))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());

            result.forEach(zipFilePath -> {
                try {
                    finalResult.addAll(getContentFromZip(zipFilePath, textToFind));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    @Override
    public List<String> findList(String directory, String textToFind) {
        List<String> list = new ArrayList<>(Arrays.asList(textToFind.split(";")));
        List<String> listResult = new ArrayList<>();

        list.forEach((uwIssue) -> {
            find(directory, uwIssue).forEach((result) ->  listResult.add(uwIssue+"\t\t"+" - file: "+Paths.get(result).getFileName().toString()+"\n"));
        });

        return listResult;
    }


    private static List<String> getContentFromZip(String zipFilePath, String textToFind) throws IOException {
        ZipFile zipFile = new ZipFile(zipFilePath);

        List<String> result = new ArrayList<>();

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);
            String content = getStringFromInputStream(stream);

            if (content.contains(textToFind)) {
                result.add(zipFilePath);
            }
        }
        return result;
    }


    private static String getStringFromInputStream(InputStream inputStream) {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }
}
