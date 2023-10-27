package com.skosarev.task01.commandline.parser;

import java.util.Arrays;

public class SimpleCommandParser implements CommandParser {
    private final String[] subStrs;

    public SimpleCommandParser(String commandToParse) {
        this.subStrs = commandToParse.split(" ");
    }

    @Override
    public String getCommand() {
        return subStrs[0];
    }

    @Override
    public String[] getArgs() {
        return Arrays.stream(subStrs).skip(1).toArray(String[]::new);
    }
}
