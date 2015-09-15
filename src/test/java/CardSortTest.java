import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


import static org.hamcrest.CoreMatchers.*;

/**
 * Tests for {@link CardSort} sorting
 * Author: Brandon Way
 */
public class CardSortTest {

    private Stack<Integer> leftDeck = new Stack<Integer>();

    private Stack<Integer> middleDeck = new Stack<Integer>();

    private Stack<Integer> rightDeck = new Stack<Integer>();

    private Stack<Integer> sortedDeck = new Stack<Integer>();

    @SuppressWarnings("unchecked")
    private List<Stack<Integer>> decks = Arrays.asList(leftDeck, middleDeck, rightDeck);

    @Test
    public void testSortWithThreeCards() throws Exception {

        for (int i = 0; i < 3; i++) {
            sortedDeck.push(3-i);
        }

        leftDeck.push(1);
        middleDeck.push(2);
        rightDeck.push(3);
        CardSort game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        leftDeck.push(1);
        middleDeck.push(3);
        rightDeck.push(2);
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        leftDeck.push(2);
        middleDeck.push(1);
        rightDeck.push(3);
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        leftDeck.push(2);
        middleDeck.push(3);
        rightDeck.push(1);
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        leftDeck.push(3);
        middleDeck.push(1);
        rightDeck.push(2);
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        leftDeck.push(3);
        middleDeck.push(2);
        rightDeck.push(1);
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));
    }

    @Test
    public void testSortWithSixCards() throws Exception {

        for (int i = 0; i < 6; i++) {
            sortedDeck.push(6-i);
        }

        leftDeck.push(1);
        leftDeck.push(4);
        middleDeck.push(2);
        middleDeck.push(5);
        rightDeck.push(3);
        rightDeck.push(6);
        CardSort game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        leftDeck.push(1);
        leftDeck.push(2);
        leftDeck.push(3);
        leftDeck.push(4);
        middleDeck.push(5);
        rightDeck.push(6);
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));
    }

    @Test
    public void testSortWithEmptyDecks() throws Exception {

        for (int i = 0; i < 10; i++) {
            sortedDeck.push(10-i);
            leftDeck.push(10-i);
        }

        CardSort game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        for (int i = 0; i < 10; i++) {
            leftDeck.push(i + 1);
        }
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        for (int i = 0; i < 10; i++) {
            middleDeck.push(i + 1);
        }
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));

        clearDecks();
        for (int i = 0; i < 10; i++) {
            rightDeck.push(i + 1);
        }
        game = new CardSort(leftDeck, middleDeck, rightDeck);
        game.sort();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));
    }

    @Test
    public void testSortWithHighNumberOfCards() throws Exception {
        final int numberOfCards = 10000;
        Stack<Integer> seed = new Stack<Integer>();
        for (int i = 0; i < numberOfCards; i++) {
            sortedDeck.push(numberOfCards - i);
            seed.push(numberOfCards - i);
        }
        Collections.shuffle(seed);
        for (int i = 0; !seed.empty(); i++) {
            if (i%3 == 0) {
                leftDeck.push(seed.pop());
            }
            else if (i%3 == 1) {
                middleDeck.push(seed.pop());
            }
            else {
                rightDeck.push(seed.pop());
            }
        }
        CardSort game = new CardSort(leftDeck, middleDeck, rightDeck);
        long startTime = System.nanoTime();
        game.sort();
        long endTime = System.nanoTime();
        Assert.assertThat(leftDeck, is(equalTo(sortedDeck)));
        System.out.println("Sort function took " + (double)Math.round(((endTime - startTime)/1000000000.0)*1000)/1000
                + " seconds to sort 10000 integers");
    }

    private void clearDecks(){
        for (Stack<Integer> deck : decks) {
            deck.clear();
        }
    }
}
