package Models;

import enums.Color;
import enums.State;
import enums.Form;

import java.util.ArrayList;
import java.util.List;

/**
 * Class permettant de définir une carte
 * @version  1.0
 * @author Gabriel Duciel, Nicolas Felixine
 */
public class Card {

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


        return "  " + stateLetter + "" + colorLetter + "" + formLetter + "  ";

    }

    /**
     * Methode To String dédiée au graphique, sans les espaces autour du nom de la carte utilisés dans la vue console
     *
     * @return le String correspondant
     */
    public String toStringGraphic() {

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


        return  stateLetter + "" + colorLetter + "" + formLetter ;

    }


    /**
     * Gets card state.
     *
     * @return the card state
     */
    public State getCard_state() {
        return card_state;
    }

    /**
     * Sets card state.
     *
     * @param card_state the card state
     */
    public void setCard_state(State card_state) {
        this.card_state = card_state;
    }

    /**
     * Gets card color.
     *
     * @return the card color
     */
    public Color getCard_color() {
        return card_color;
    }

    /**
     * Gets card form.
     *
     * @return the card form
     */
    public Form getCard_form() {
        return card_form;
    }

    /**
     * Instantiates a new Card.
     *
     * @param state the state
     * @param color the color
     * @param form  the form
     */
    public Card(State state, Color color, Form form)
    {
        this.card_color = color;
        this.card_state = state;
        this.card_form = form;
    }

}
