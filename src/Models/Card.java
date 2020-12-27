package Models;

import enums.Color;
import enums.State;
import enums.Form;

import java.util.ArrayList;
import java.util.List;

public class Card {


    private int x_pos;      //Useless
    private int y_pos;      //Useless
    State card_state;
    Color card_color;
    Form card_form;

    @Override
    public String toString() {

        char stateLetter = '.';
        char colorLetter = '.';
        char formLetter = '.';

        switch(card_state)
        {
            case EMPTY: stateLetter = 'E'; break;
            case FILL: stateLetter = 'F'; break;
        }

        switch(card_color)
        {
            case RED: colorLetter = 'R'; break;
            case GREEN: colorLetter = 'G'; break;
            case BLUE: colorLetter = 'B'; break;
        }

        switch(card_form)
        {
            case RECTANGULAR: formLetter = 'R'; break;
            case TRIANGULAR: formLetter= 'T'; break;
            case CIRCLE: formLetter = 'C'; break;
        }


        return " " + stateLetter + "" + colorLetter + "" + formLetter + " ";

    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public State getCard_state() {
        return card_state;
    }

    public void setCard_state(State card_state) {
        this.card_state = card_state;
    }

    public Color getCard_color() {
        return card_color;
    }

    public void setCard_color(Color card_color) {
        this.card_color = card_color;
    }

    public Form getCard_form() {
        return card_form;
    }

    public void setCard_form(Form card_form) {
        this.card_form = card_form;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public Card(State state, Color color, Form form)
    {
        this.card_color = color;
        this.card_state = state;
        this.card_form = form;
    }

    public List<Player> player = new ArrayList<Player> ();

}
