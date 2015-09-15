
import java.util.Stack;

/*
 * Author: Brandon Way
 */
public class CardSort
{
    private Stack<Integer> leftDeck;
    private Stack<Integer> middleDeck;
    private Stack<Integer> rightDeck;

    public CardSort(Stack<Integer> leftDeck, Stack<Integer> middleDeck, Stack<Integer> rightDeck)
    {
        this.leftDeck = leftDeck;
        this.middleDeck = middleDeck;
        this.rightDeck = rightDeck;
    }

    public Stack<Integer> sort()
    {
        primeLists(); // O(n)
        createListofSortedSublists(); //O(n)
        mergeSortedSublists(); //O(n^2) ...?
        return this.leftDeck;
    }

    /**
     * Currently this method just removes the left pile by
     * alternately placing cards on the other piles. This process could
     * likely be optimized.
     */
    private void primeLists()
    {
        int i = 0;
        while (!leftDeck.empty())
        {
            if (i%2 == 0) {
                middleDeck.push(leftDeck.pop());
            }
            else {
                rightDeck.push(leftDeck.pop());
            }
            i++;
        }
    }

    private void createListofSortedSublists()
    {
        while (!(middleDeck.empty() && rightDeck.empty())) {
            if (middleDeck.empty()){
                middleDeck.push(rightDeck.pop());
                if (rightDeck.empty()) {
                    leftDeck.push(middleDeck.pop());
                    return;
                }
            }
            if (rightDeck.empty()){
                rightDeck.push(middleDeck.pop());
                if (middleDeck.empty()) {
                    leftDeck.push(rightDeck.pop());
                    return;
                }
            }
            if ((compare(middleDeck, leftDeck) >= 0 && compare(rightDeck, leftDeck) >= 0)
                    || (compare(middleDeck, leftDeck) < 0 && compare(rightDeck, leftDeck) < 0)){
                if (compare(middleDeck, rightDeck) >= 0) {
                    leftDeck.push(middleDeck.pop());
                } else {
                    leftDeck.push(rightDeck.pop());
                }
            } else {
                if (compare(middleDeck, rightDeck) < 0) {
                    leftDeck.push(middleDeck.pop());
                } else {
                    leftDeck.push(rightDeck.pop());
                }
            }
        }
    }

    private void mergeSortedSublists()
    {
        if (!this.leftDeck.empty()) {
            do {
                this.middleDeck.push(this.leftDeck.pop());
            } while (!this.leftDeck.empty() && this.middleDeck.peek() < this.leftDeck.peek());
        }
        if (!this.leftDeck.empty()) {
            do {
                this.rightDeck.push(this.leftDeck.pop());
            } while (!this.leftDeck.empty() && this.rightDeck.peek() < this.leftDeck.peek());
        }
        if (this.rightDeck.empty())
        {
            while (!this.middleDeck.empty()){
                this.leftDeck.push(this.middleDeck.pop());
            }
            return;
        }
        while (!(this.middleDeck.empty() && this.rightDeck.empty())) {
            if (compare(this.middleDeck, this.rightDeck) <= 0) {
                this.leftDeck.push(this.rightDeck.pop());
            }
            else {
                this.leftDeck.push(this.middleDeck.pop());
            }
        }
        mergeSortedSublists();
    }

    private static int compare(Stack<Integer> deckOne, Stack<Integer> deckTwo)
    {
        if (deckOne.empty() && deckTwo.empty()) {
            throw new IllegalArgumentException("Nothing to compare");
        }
        if (deckOne.empty()) {
            return -1;
        }
        if (deckTwo.empty()) {
            return 1;
        }
        if (deckOne.peek() > deckTwo.peek()) {
            return 1;
        }
        else if (deckOne.peek() == deckTwo.peek()) {
            return 0;
        }
        else {
            return -1;
        }
    }


}
