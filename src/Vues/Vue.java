package Vues;

import Controllers.GameController;

public interface Vue {

    public GameController getCtrl();
    public void occure();
}
