package edu.cnm.deepdive;

import edu.cnm.deepdive.Suit.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Card implements Comparable<Card> {


  private Rank rank;
  private Suit suit;

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  @Override
  public String toString() {
    return rank.getSymbol() + suit.getSymbol();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return rank == card.rank &&
        suit == card.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, suit);
  }

  @Override
  public int compareTo(Card other) {
    int comparison = this.suit.compareTo(other.suit);
    if (comparison == 0) {
      comparison = this.rank.compareTo(other.rank);
    }
    return comparison;
  }

  public static void main(String[] args) {
    List<Card> deck = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new Card(rank, suit);
        deck.add(card);
      }
    }
    //System.out.println(deck);
    //Collections.shuffle(deck);
    //System.out.println(deck);
    cardTrick(deck);

  }

  static void cardTrick(List<Card> deck) {
    Collections.shuffle(deck);
    int deckSize = deck.size();
    Random rnd = new Random();
    System.out.println(deck);
    List<Card> blackPile = new ArrayList<>();
    List<Card> redPile = new ArrayList<>();
    while (!deck.isEmpty()) {
      Card card = deck.remove(0);
      Color cardColor = card.getSuit().getColor();
      if (cardColor == Color.BLACK) {
        blackPile.add(deck.remove(0));
      } else {
        redPile.add(deck.remove(0));
      }

    }
    assert (redPile.size() + blackPile.size() == deckSize / 2);
    int cardsToSwap = rnd.nextInt(Math.min(redPile.size(), blackPile.size()) + 1);
    List<Card> tempRed = new ArrayList<>();
    List<Card> tempBlack = new ArrayList<>();
    for (int i = 0; i < cardsToSwap; i++) {
      tempBlack.add(blackPile.remove(0));
      tempRed.add(redPile.remove(0));
    }

    redPile.addAll(tempBlack);
    blackPile.addAll(tempRed);
    System.out.printf("Red pile: %s%n", redPile);
    System.out.printf("Black pile: %s%n", blackPile);
    long redCount = redPile.parallelStream()
        .filter((c) -> c.getSuit().getColor() == Color.RED)
        .count();
    long blackCount = blackPile.parallelStream()
        .filter((c) -> c.getSuit().getColor() == Color.BLACK)
        .count();
    if (redCount == blackCount) {
      System.out.println("The plies are equal");
    }
  }
}


