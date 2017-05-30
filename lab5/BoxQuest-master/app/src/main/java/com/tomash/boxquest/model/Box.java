package com.tomash.boxquest.model;

public class Box {
    /**
     * if true - contains bomb , else not
     */
    private boolean hasBomb;

    public Box(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Box{");
        sb.append("hasBomb=").append(hasBomb);
        sb.append('}');
        return sb.toString();
    }
}
