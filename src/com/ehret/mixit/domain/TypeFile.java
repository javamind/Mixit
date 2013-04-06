package com.ehret.mixit.domain;

/**
 * Created with IntelliJ IDEA.
 * User: EHRET_G
 * Date: 03/04/13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public enum TypeFile {
    speaker,
    staff,
    talks,
    test,
    workshops,
    members,
    lightningtalks,
    sponsor,
    interests;

    public static TypeFile getTypeFile(String value){
        for(TypeFile typeFile : values()){
            if(typeFile.name().equals(value)){
                return typeFile;
            }
        }
        return null;
    }
}
