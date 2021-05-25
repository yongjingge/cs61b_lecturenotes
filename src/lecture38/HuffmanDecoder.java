package lecture38;

/* Huffman Decoder implementation from Lab 7: Compression
 * Full version https://github.com/yongjingge/cs61b/tree/4607346e108ef2dddb328f3bf14b5b1c9c32e7d3/hw7
 * */
public class HuffmanDecoder {

    public static void main(String[] args) {
        /* read the .huf file */
        ObjectReader or = new ObjectReader(args[0]);
        /* read the Huffman coding trie */
        BinaryTrie trie = (BinaryTrie) or.readObject();
        /* read the number of symbols */
        int numberOfSymbols = (Integer) or.readObject();
        /* read the massive bit sequence corresponding to the original txt */
        BitSequence assembled = (BitSequence) or.readObject();

        /* repeat until there are no more symbols:
        * a. perform a longest prefix match on the massive bit sequence.
        * b. record the symbol in some data structure.
        * c. create a new bit sequence containing the remaining unmatched bits.
        * */
        char[] reverse = new char[numberOfSymbols];
        for (int i = 0; i < numberOfSymbols; i += 1) {
            Match match = trie.longestPrefixMatch(assembled);
            reverse[i] = match.getCh();
            assembled = assembled.allButFirstNBits(match.getBs().length());
        }

        FileUtils.writeCharArray(args[1], reverse);
    }
}
