package Models;

import enums.Color;
import enums.Form;
import enums.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Class de type visitor, permettant de visiter la partie et de calculer les points
 * @author  Gabriel Duciel
 * @version  1.0
 */
public class Visitor implements VisitorInterface    {
    private HashMap<Color, Integer> PointsByColor;
    private HashMap<Form, Integer> PointsByForm;
    private HashMap<State, Integer> PointsByState;

    /**
     * Map qui associe � chaque type de couleurs, de forme et d'�tat un nombre de point
     * @return
     */

    public Map<Color, Integer> getPointsByColor() {
        return PointsByColor;
    }
    public Map<Form, Integer> getPointsByForm() {
        return PointsByForm;
    }
    public Map<State, Integer> getPointsByState() {
        return PointsByState;
    }
    public Visitor()
    {
        PointsByColor = new HashMap<Color, Integer>();
        PointsByForm = new HashMap<Form, Integer>();
        PointsByState = new HashMap<State, Integer>();

        this.setValueTo0();
    }

    /**
     * Met � 0 l'ensemble des Maps de points
     */
    private void setValueTo0()
    {
        for(State tempState : State.values())
        {
            PointsByState.put(tempState, 0);
        }
        for(Color tempColor : Color.values())
        {
            PointsByColor.put(tempColor, 0);
        }
        for(Form tempForm : Form.values())
        {
            PointsByForm.put(tempForm, 0);
        }
    }

    /**
     * Visite l'ensemble du Board et calcule le nombre de points possibles pour chacune des possibilit�s de forme, couleur ou �tat
     *
     * @param currentBoard le plateau de jeu actuel
     */
    public void visit(Board currentBoard)
    {
        this.setValueTo0();

        // LOOK BY LINE

        Coordinate topLeftCorner = this.getTopLeftCorner(currentBoard);
        Coordinate currentCoordinate = topLeftCorner;

        // LOOK BY COLON
        boolean isLookingByColumn = true;
        Card lastCard = null, beforeLast = null;


        while(isLookingByColumn)
        {
            boolean isLookingByLine = true;

            boolean first3Colors = true;
            boolean first3State = true;


            while(isLookingByLine)
            {

                if(lastCard != null)
                {
                    if(lastCard.getCard_form() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_form() )
                    {
                        PointsByForm.put(lastCard.getCard_form(), PointsByForm.get(lastCard.getCard_form()) +1);
                    }
                    if(beforeLast != null)
                    {
                        if( beforeLast.getCard_color() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_color() &&
                                lastCard.getCard_color() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_color() )
                        {
                            if(first3Colors)
                            {
                                PointsByColor.put(lastCard.getCard_color(), PointsByColor.get(lastCard.getCard_color()) +4);
                                first3Colors = false;
                            }else
                            {

                                PointsByColor.put(lastCard.getCard_color(), PointsByColor.get(lastCard.getCard_color()) +1);
                            }
                        }

                        if(beforeLast.getCard_state() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_state() &&
                                lastCard.getCard_state() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_state() )
                        {
                            if(first3State)
                            {
                                PointsByState.put(lastCard.getCard_state(), PointsByState.get(lastCard.getCard_state()) +3);
                                first3State = false;
                            } else
                            {
                                PointsByState.put(lastCard.getCard_state(), PointsByState.get(lastCard.getCard_state()) +1);
                            }

                        }
                    }
                }

                Coordinate potentielNewPoint = new Coordinate( currentCoordinate.getX() + 1, currentCoordinate.getY());
                Coordinate potentielNewPoint2 = new Coordinate( currentCoordinate.getX() + 2, currentCoordinate.getY());

                if( ! currentBoard.isPlaceAvailable(potentielNewPoint ) )
                {
                    beforeLast = lastCard;
                    lastCard = currentBoard.getCardByCoordinate(currentCoordinate);
                    currentCoordinate = potentielNewPoint;

                } else if( ! currentBoard.isPlaceAvailable(potentielNewPoint2 ) )
                {
                    beforeLast = null;
                    lastCard = null;
                    currentCoordinate = potentielNewPoint2;
                }else
                {
                    isLookingByLine = false;
                }
            }

            Coordinate potentielNewPoint = new Coordinate( topLeftCorner.getX(), currentCoordinate.getY() + 1);
            Coordinate potentielNewPoint2 = new Coordinate( topLeftCorner.getX(), currentCoordinate.getY() + 2);
            if( ! currentBoard.isPlaceAvailable(potentielNewPoint ) )
            {
                lastCard = null;
                beforeLast = null;
                currentCoordinate = potentielNewPoint;
                isLookingByLine = true;
            } else if( ! currentBoard.isPlaceAvailable(potentielNewPoint2 ) )
            {

                lastCard = null;
                beforeLast = null;
                currentCoordinate = potentielNewPoint2;
                isLookingByLine = true;
            }else
            {
                isLookingByColumn = false;
            }
        }


        // LOOK BY LINE

        isLookingByColumn = true;
        lastCard = null;
        beforeLast = null;
        currentCoordinate = topLeftCorner;

        isLookingByColumn = true;
        lastCard = null;
        beforeLast = null;

        while(isLookingByColumn)
        {
            boolean first3Colors = true;
            boolean first3State = true;

            boolean isLookingByLine = true;
            while(isLookingByLine)
            {
                if(lastCard != null)
                {
                    if(lastCard.getCard_form() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_form() )
                    {
                        PointsByForm.put(lastCard.getCard_form(), PointsByForm.get(lastCard.getCard_form()) +1);
                    }
                    if(beforeLast != null)
                    {
                        if( beforeLast.getCard_color() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_color() &&
                                lastCard.getCard_color() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_color() )
                        {
                            if(first3Colors)
                            {
                                PointsByColor.put(lastCard.getCard_color(), PointsByColor.get(lastCard.getCard_color()) +4);
                                first3Colors = false;
                            }else
                            {

                                PointsByColor.put(lastCard.getCard_color(), PointsByColor.get(lastCard.getCard_color()) +1);
                            }
                        }

                        if(beforeLast.getCard_state() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_state() &&
                                lastCard.getCard_state() == currentBoard.getCardByCoordinate(currentCoordinate).getCard_state() )
                        {
                            if(first3State)
                            {
                                PointsByState.put(lastCard.getCard_state(), PointsByState.get(lastCard.getCard_state()) +3);
                                first3State = false;
                            } else
                            {
                                PointsByState.put(lastCard.getCard_state(), PointsByState.get(lastCard.getCard_state()) +1);
                            }

                        }
                    }
                }

                Coordinate potentielNewPoint = new Coordinate( currentCoordinate.getX(), currentCoordinate.getY() + 1);
                Coordinate potentielNewPoint2 = new Coordinate( currentCoordinate.getX(), currentCoordinate.getY() + 2);

                if( ! currentBoard.isPlaceAvailable(potentielNewPoint ) )
                {
                    beforeLast = lastCard;
                    lastCard = currentBoard.getCardByCoordinate(currentCoordinate);
                    currentCoordinate = potentielNewPoint;
                } else if(! currentBoard.isPlaceAvailable(potentielNewPoint2 ))
                {
                    beforeLast = null;
                    lastCard = null;
                    currentCoordinate = potentielNewPoint2;
                }
                {
                    isLookingByLine = false;
                }
            }

            Coordinate potentielNewPoint = new Coordinate(currentCoordinate.getX() + 1,  topLeftCorner.getY() );
            Coordinate potentielNewPoint2 = new Coordinate(currentCoordinate.getX() + 2,  topLeftCorner.getY() );
            if( ! currentBoard.isPlaceAvailable(potentielNewPoint ) )
            {
                lastCard = null;
                beforeLast = null;
                currentCoordinate = potentielNewPoint;
                isLookingByLine = true;
            } else if( ! currentBoard.isPlaceAvailable(potentielNewPoint2 ) )
            {
                lastCard = null;
                beforeLast = null;
                currentCoordinate = potentielNewPoint2;
                isLookingByLine = true;
            }else
            {
                isLookingByColumn = false;
            }
        }

    }


    /**
     * Identifie la carte dans le coin en haut � gauche, base pour calculer les poins
     *
     * @param board le plateau de jeu actuel
     * @return les coordonn�es de la carte en haut � gauche du plateau
     */
    public Coordinate getTopLeftCorner(Board board)
    {
        Coordinate topLeftCoordinate = new Coordinate(50, 50);
        for (Map.Entry<Coordinate, Card> entry : board.getCurrentCardsOnBoard().entrySet())
        {
           if( entry.getKey().getY() <= topLeftCoordinate.getY() && entry.getKey().getX() <= topLeftCoordinate.getX() )
            {
                topLeftCoordinate = entry.getKey();
            }
        }


        return topLeftCoordinate;
    }


    /**
     * Permet d'obtenir le nombre de points total associ� � une carte victoire
     *
     * @param card     La carte victoire du joueur
     * @return the points total regarding victory card
     */
    public int getPointsTotalRegardingVictoryCard(Card card)
    {
        return PointsByState.get(card.getCard_state()) + PointsByColor.get(card.getCard_color()) + PointsByForm.get(card.getCard_form());
    }

    /**
     * Identifie les valeurs maximales pour chacune des cartes.
     *
     * @return le nombre de point total.
     */
    public int getPointsTotal()
    {
        int points_max = 0;

        for(State tempState : State.values())
        {
           points_max =  PointsByState.get(tempState);
        }
        for(Color tempColor : Color.values())
        {
            points_max =  PointsByColor.get(tempColor);
        }
        for(Form tempForm : Form.values())
        {
            points_max =  PointsByForm.get(tempForm);
        }

        return points_max;
    }

}
