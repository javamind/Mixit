package com.ehret.mixit.domain;

/**
 * Differents fichiers Json recuperes sur le site Mix-it
 */
public enum JsonFile {
    FIC0(TypeFile.test,"http://www.mix-it.fr/api/talks/125"),
    FIC1(TypeFile.speaker,"http://www.mix-it.fr/api/members/speakers"),
    FIC2(TypeFile.staff,"http://www.mix-it.fr/api/members/staff"),
    FIC3(TypeFile.members, "http://www.mix-it.fr/api/members"),
    FIC4(TypeFile.talks,"http://www.mix-it.fr/api/talks"),
    FIC5(TypeFile.lightningtalks, "http://www.mix-it.fr/api/lightningtalks"),
    FIC6(TypeFile.sponsor, "http://www.mix-it.fr/api/members/sponsors"),
    FIC7(TypeFile.interests,"http://www.mix-it.fr/api/interests");

    private String url;
    private TypeFile type;

    private JsonFile(TypeFile type, String url) {
        this.url = url;
        this.type=type;
    }

    public String getUrl() {
        return url;
    }

    public TypeFile getType() {
        return type;
    }
}
