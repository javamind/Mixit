package com.ehret.mixit.domain.talk;

import com.ehret.mixit.domain.people.Link;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ehret_g
 * Date: 27/03/13
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class Talk extends Conference{
    private String format;
    private String level;
    private String room;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }



    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
