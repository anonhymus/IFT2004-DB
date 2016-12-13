package org.kraken.tp3.application;

import org.kraken.tp3.entity.ArticleEntity;

import javax.swing.*;

/**
 * tp3
 * 12/12/2016.
 *
 * @author Anonhyme
 */

public class SelectionHistory {
    //    private static final int MAX_SIZE = 5;
    private static SelectionHistory INSTANCE = null;
    //    private Stack<Object> objectStack;
    private DefaultListModel<ArticleEntity> lastModel = null;
    private Object[] lastList = null;
    private ArticleEntity lastSelectedArticle = null;
    private int lastIndex = 0;


    private SelectionHistory() {
    }

    public static SelectionHistory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SelectionHistory();
        }
        return INSTANCE;
    }


    public void push(DefaultListModel<ArticleEntity> model, int indexSelection) {
        lastList = model.toArray();
        lastModel = model;
        for(Object article : model.toArray()) {
            lastModel.addElement((ArticleEntity) article);
        }
        lastIndex = indexSelection;
        lastSelectedArticle = model.getElementAt(indexSelection);

    }

    public Object[] getLastList() {
        return lastList;
    }

    public int getLastSelectedArticle() {
        return this.lastIndex;
    }

    public String toString() {
        return "org.kraken.tp3.application.SelectionHistory(lastList=" + this.getLastList() + ", lastSelectedArticle=" + this
                .getLastSelectedArticle() + ")";
    }
}
