package ru.clevertec.check.utils;

public enum Pattern {

    PAIR_PATTERN("(\\d+\\-{1}\\d+)"), CARD_PATTERN("(card\\-{1}\\d+)"), FILE_PATTERN("([a-zA-Z0-9.:]+\\\\{1})*(Products){0,1}(Cards){0,1}(\\.{1}\\w+)");

    private String pattern;

    Pattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
