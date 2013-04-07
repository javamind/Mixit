/*
 * Copyright 2013 Guillaume EHRET
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ehret.mixit.domain;

/**
 * Differents fichiers Json recuperes sur le site Mix-it
 */
public enum JsonFile {
    FIC0(TypeFile.test, "http://www.mix-it.fr/api/talks/125"),
    FIC1(TypeFile.speaker, "http://www.mix-it.fr/api/members/speakers"),
    FIC2(TypeFile.staff, "http://www.mix-it.fr/api/members/staff"),
    FIC3(TypeFile.members, "http://www.mix-it.fr/api/members"),
    FIC4(TypeFile.talks, "http://www.mix-it.fr/api/talks"),
    FIC5(TypeFile.lightningtalks, "http://www.mix-it.fr/api/lightningtalks"),
    FIC6(TypeFile.sponsor, "http://www.mix-it.fr/api/members/sponsors"),
    FIC7(TypeFile.interests, "http://www.mix-it.fr/api/interests");

    private String url;
    private TypeFile type;

    private JsonFile(TypeFile type, String url) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public TypeFile getType() {
        return type;
    }
}
