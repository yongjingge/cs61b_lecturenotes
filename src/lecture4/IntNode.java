package lecture4;

public class IntNode {

    int item;
    IntNode next;

    public IntNode(int item, IntNode next) {
        this.item = item;
        this.next = next;
    }

    public String toString() {
        return item + " " + next;
    }
}
