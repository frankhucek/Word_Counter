

/**
 * The Nodes for the HashTable
 * @author Frank Hucek
 */
public class HashNode
{
    /**
     * The word key of this HashNode
     */
    public String word;
    
    /**
     * The frequency that this word occurs
     */
    public int frequency;
    
    /**
     * Constructor setting word and frequency
     * @param word
     * @param frequency 
     */
    public HashNode(String word, int frequency)
    {
        this.word = word;
        this.frequency = frequency;
    }
    
    /**
     * Constructor setting the word and frequency to 0
     * @param word 
     */
    public HashNode(String word)
    {
        this.word = word;
        this.frequency = 0;
    }
    
    /**
     * Constructor not setting any default values
     */
    public HashNode()
    {
        this.word = null;
        this.frequency = 0;
    }
    
    /**
     * Fulfills formatting requirement for project
     * @return The formatted string to match assignment requirements
     */
    @Override
    public String toString()
    {
        return "(" + this.word + ": " + this.frequency + ")";
    }
}
