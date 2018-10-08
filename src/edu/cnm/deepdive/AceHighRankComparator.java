package edu.cnm.deepdive;

import java.util.Comparator;

public class AceHighRankComparator implements Comparator<Card> {

  @Override
  public int compare(Card card1, Card card2) {
    int comparision = card1.getRank().compareTo(card2.getRank());
    if (comparision < 0 && card1.getRank() == Rank.ACE) {
      comparision *= -1;

    }else if(comparision > 0 && card2.getRank() == Rank.ACE) {
     comparision *= 1;

    }else if(comparision == 0){
      comparision = -card1.getSuit().compareTo(card2.getSuit());
    }
       return comparision;
  }
}
