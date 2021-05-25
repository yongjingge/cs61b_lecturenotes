package lecture38;

public class Match {

    private BitSequence bs;
    private Character ch;
    public Match(BitSequence bs, Character ch) {
        this.bs = bs;
        this.ch = ch;
    }

    public BitSequence getBs() {
        return bs;
    }

    public Character getCh() {
        return ch;
    }
}
