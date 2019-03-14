package com.github.kettoleon.cah.generator;

import java.util.ArrayList;
import java.util.List;

public class CahPage {

    private CahPageType pageType = CahPageType.WHITE;

    private List<CahCard> cards = new ArrayList<CahCard>();

    public CahPageType getPageType() {
        return pageType;
    }

    public void setPageType(CahPageType pageType) {
        this.pageType = pageType;
    }

    public List<CahCard> getCards() {
        return cards;
    }

    public void setCards(List<CahCard> cards) {
        this.cards = cards;
    }

    public enum CahPageType {
        WHITE,
        BLACK
    }

    public static CahPage aWhitePage(){
        return new CahPage();
    }

    public static CahPage aBlackPage(){
        CahPage cahPage = new CahPage();
        cahPage.setPageType(CahPageType.BLACK);
        return cahPage;
    }
}
