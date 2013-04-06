package com.ehret.mixit.domain.talk;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ehret_g
 * Date: 27/03/13
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class Conference {
    private long id;
    private String title;
    private String summary;
    private String description;
    private List<Long> interests;
    private List<Long> speakers;
    private Date start;

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    private Date end;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getInterests() {
        return interests;
    }

    public void setInterests(List<Long> interests) {
        this.interests = interests;
    }

    public List<Long> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Long> speakers) {
        this.speakers = speakers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference talk = (Conference) o;

        if (id != talk.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }


}
