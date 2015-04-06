

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A HashTable used to store word frequencies
 * Uses Separate Chaining HashTable method
 * @author Frank Hucek
 */
public class HashTable
{
    /**
     * The array of HashNodes to store the word frequencies
     */
    public LinkedList<HashNode>[] table;
    
    /**
     * Initializes a HashTable of default size initialSize
     * @param initialSize The initial size of the HashTable
     */
    public HashTable(int initialSize)
    {
        this.table = new LinkedList[initialSize];
    }
       
    /**
     * The index in the Table to insert a given key
     * @param key The String to insert into the table
     * @return The index in the table to insert the key into
     */
    private int hashIndex(String key)
    {
        int hashValue = (key.hashCode() % table.length);
        if(hashValue < 0)
            hashValue += table.length;
        return hashValue;
    }
    
    /**
     * Checks if the table needs to be rehashed
     * @return true if rehashing is needed, otherwise returns false 
     */
    private boolean rehashCheck()
    {
        for(int i = 0; i < table.length; i++)
        {
            if(table[i] != null && table[i].size() > this.table.length)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Rehashes the current table, inserting all elements back into it
     * from existing table.
     * Rehashing/expanding occurs when the longest LinkedList in the table
     * is just greater than the table length.
     */
    private void rehash()
    {
        HashTable newTable = new HashTable(this.table.length * 2);
        
        for(int i = 0; i < table.length; i++)
        {
            if(table[i] != null)
            {
                Iterator<HashNode> iter = table[i].iterator();
                while(iter.hasNext())
                {
                    HashNode node = iter.next();
                    newTable.insert(node.word,node.frequency);
                }
            }
        }
        
        this.table = newTable.table;
    }
    
    /**
     * Inserts a new key into the HashTable
     * Or increases an existing key's frequency
     * Also resizes the HashTable if load factor is too great
     * @param key The String to add/increase frequency of within the HashTable
     * @param frequency The frequency of the input key
     */
    public void insert(String key, int frequency)
    { 
        int index = hashIndex(key);
        int position = nodeIndex(key,index);
        
        if(position != -1)
        {
            table[index].get(position).frequency++;
        }
        
        else
        {
            table[index].add(new HashNode(key,frequency));
        }
        
        if(this.rehashCheck())
            this.rehash();
    }
    
    /**
     * Returns the position of the node within the specific list in the table
     * with the matching key value.
     * @param key The key to check for within the given list in the table
     * @param index The index of the LinkedList within the table
     * @return The position of the node in the list
     */
    private int nodeIndex(String key, int index)
    {
        if(table[index] == null)
        {
            table[index] = new LinkedList<>();
            return -1;
        }

        for(int i = 0; i < table[index].size(); i++)
        {
            if(table[index].get(i).word.equals(key))
                return i;
        }
        return -1;
    }
    
    /**
     * Puts all the nodes (words with frequencies) 
     * within the table into a single, simple String
     * @return The String of everything within the table
     */
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (LinkedList<HashNode> list : this.table)
        {
            if (list != null)
            {
                Iterator<HashNode> iter = list.iterator();
                while(iter.hasNext())
                {
                    // calls toString() of HashNode
                    s.append(iter.next());
                }
            }
            // keeps the formatting looking nice
            s.append("\n");
        }
        return s.toString();
    }
    
    /**
     * Returns the table info in the form of a String.
     * Info includes -> total number of words, table length, average
     * length of collision lists
     * @return The table's info
     */
    public String tableInfo()
    {
        int numWords = 0;
        int listSize = 0;
        for (LinkedList<HashNode> list : table)
        {
            if (list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    numWords += list.get(i).frequency;
                }
                listSize += list.size();
            }
        }
        
        String s = "Total number of words -> " + numWords
                + "\nHashTable size -> " + this.table.length
                + "\nAverage length of collision lists -> " 
                + listSize / this.table.length;
        
        return s;
    }
}
