package lab7;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClass
{
  public static void main(String[] args)
  {
    Deck deck = new Deck();
    Card[] hand = deck.select(5);
    System.out.println(Card.toString(hand));
    ArrayList<String> words = new ArrayList<>();
    words.add("bat");
    words.add("splat");
    words.add("flat");
    words.add("bat");
    words.add("hat");
    ArrayListExample.removeDuplicates(words);
    Object[] noDuplicateWords = words.toArray();
    System.out.println(Arrays.toString(noDuplicateWords));
  }
}