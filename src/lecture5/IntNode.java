package lecture5;

public class IntNode {

    public int item;
    public IntNode prev;
    public IntNode next;

    public IntNode(int item, IntNode prev, IntNode next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public String toString() {
        return item + " " + prev + " " + next;
    }
}
