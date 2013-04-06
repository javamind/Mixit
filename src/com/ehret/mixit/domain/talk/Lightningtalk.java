package com.ehret.mixit.domain.talk;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ehret_g
 * Date: 27/03/13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class Lightningtalk extends Conference{

    private int nbVotes;

    public int getNbVotes() {
        return nbVotes;
    }

    public void setNbVotes(int nbVotes) {
        this.nbVotes = nbVotes;
    }
}
