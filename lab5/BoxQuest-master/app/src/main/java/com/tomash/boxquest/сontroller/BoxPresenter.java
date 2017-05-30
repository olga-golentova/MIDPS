package com.tomash.boxquest.сontroller;


public interface BoxPresenter {
    /**
     * Callback for list size for presenter
     * @param size size of list
     */
    void onBoxClick(int size);

    /**
     * Callback for lose state.
     */
    void onLose();

    /**
     * Callback for win state.
     */
    void onWin();
}
