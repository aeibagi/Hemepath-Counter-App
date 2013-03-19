package edu.sjsu.hemepathcounter;

import java.util.ArrayList;

import edu.sjsu.hemepathcounter.model.CellButton;

public class Parent {
    private String mTitle;
    private ArrayList<CellButton> mArrayChildren;
 
    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<CellButton> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<CellButton> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }
}