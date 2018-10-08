package edu.cnm.deepdive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BlackjackDealer implements Comparator<BlackjackHand> {

  private List<Card> shoe;

  public BlackjackDealer(int numDecks, Random rnd) {
    shoe = new ArrayList<>();
    for (int i = 0; i < numDecks; i++) {
      for (Suit suit : Suit.values()) {
        for (Rank rank : Rank.values()) {
          shoe.add(new Card(rank, suit));
        }
      }
    }
    Collections.shuffle(shoe, rnd);
    //TODO Place reshuffle indicator card

  }
  public Card deal(){
    return shoe.remove(0);
  }

  @Override
  public int compare(BlackjackHand hand1, BlackjackHand hand2) {
    return hand1.compareTo(hand2);
  }
}
