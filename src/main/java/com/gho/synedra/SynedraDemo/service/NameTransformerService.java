package com.gho.synedra.SynedraDemo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Service
public class NameTransformerService {

    public String transformName(String name) {
        String nameCapitalized = "";
        if (StringUtils.hasText(name)) {
            name = new String(name.getBytes(), 0, name.length(), StandardCharsets.UTF_8);

            nameCapitalized = StringUtils.capitalize(name);
            nameCapitalized = nameCapitalized.replaceAll("AE", "Ä");
            nameCapitalized = nameCapitalized.replaceAll("OE", "Ö");
            nameCapitalized = nameCapitalized.replaceAll("UE", "Ü");
            nameCapitalized = nameCapitalized.replaceAll("SS", "ß");

        }
        return nameCapitalized;
    }

    public Set<String> transformNameFull(String name) {
        Set<String> transformedNames = new HashSet<String>();
        if (StringUtils.hasText(name)) {
            name = new String(name.getBytes(), 0, name.length(), StandardCharsets.UTF_8);

            String originalName = name;
            transformedNames.add(name);
            String nameFullConverted = transformName(name);
            transformedNames.add(nameFullConverted);
            transformedNames.addAll(transformNameLeft(name));
            transformedNames.addAll(transformNameRight(name));

            removeMalformedNames(transformedNames);
        }
        return transformedNames;
    }

    private Set<String> transformNameLeft(String name) {
        String originalName = name;
        Set<String> transformedNames = new HashSet<String>();
        while (name.contains("AE")) {
            name = name.replaceFirst("AE", "Ä");
            transformedNames.add(name);
        }
        name = originalName;
        while (name.contains("OE")) {
            name = name.replaceFirst("OE", "Ö");
            transformedNames.add(name);
        }
        name = originalName;
        while (name.contains("UE")) {
            name = name.replaceFirst("UE", "Ü");
            transformedNames.add(name);
        }
        name = originalName;
        while (name.contains("SS")) {
            name = name.replaceFirst("SS", "ß");
            transformedNames.add(name);
        }
        return transformedNames;
    }

    private Set<String> transformNameRight(String name) {
        String originalName = name;
        Set<String> transformedNames = new HashSet<String>();
        while (name.contains("SS")) {
            name = replaceLast(name, "SS", "ß");
            transformedNames.add(name);
        }
        name = originalName;
        while (name.contains("OE")) {
            name = replaceLast(name, "OE", "Ö");
            transformedNames.add(name);
        }
        name = originalName;
        while (name.contains("AE")) {
            name = replaceLast(name, "AE", "Ä");
            transformedNames.add(name);
        }
        name = originalName;
        while (name.contains("UE")) {
            name = replaceLast(name, "UE", "Ü");
            transformedNames.add(name);
        }
        return transformedNames;
    }

    private String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    private void removeMalformedNames(Set<String> transformedNames) {
        Set<String> malformedNames = new HashSet<String>();
        transformedNames.forEach(name -> {
            if (name.contains("ßCH"))
                malformedNames.add(name);
        });
        transformedNames.removeAll(malformedNames);
    }
}
