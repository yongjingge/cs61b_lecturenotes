package lecture38;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Huffman Encoder implementation from Lab 7: Compression
* Full version https://github.com/yongjingge/cs61b/tree/4607346e108ef2dddb328f3bf14b5b1c9c32e7d3/hw7
* */
public class HuffmanEncoder {

    /* build frequency table based on the input data */
    public static Map<Character, Integer> buildFrequencyTable(char[] input) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char c : input) {
            frequencyTable.compute(c, (key, value) -> (value == null) ? 1 : value + 1);
        }
        return frequencyTable;
    }

    public static void main(String[] args) {
        char[] input = FileUtils.readFile(args[0]);
        /* build frequency table */
        Map<Character, Integer> frequencyTable = buildFrequencyTable(input);
        /* use frequency table to construct a binary decoding trie! */
        BinaryTrie trie = new BinaryTrie(frequencyTable);
        /* write the trie into the .huf file */
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.WriteObject(trie);
        /* also write the number of symbols in input into the .huf file */
        ow.WriteObject(input.length);
        /* use the binary trie to create an encoding lookup table! */
        Map<Character, BitSequence> lookup = trie.buildLookupTable();
        /* create a list of BitSequence */
        List<BitSequence> bsList = new ArrayList<>();
        for (char c : input) {
            BitSequence charBS = lookup.get(c);
            bsList.add(charBS);
        }
        /* assemble all BitSequences into a huge one and write it into the .huf file */
        BitSequence assembled = BitSequence.assemble(bsList);
        ow.WriteObject(assembled);
    }
}
