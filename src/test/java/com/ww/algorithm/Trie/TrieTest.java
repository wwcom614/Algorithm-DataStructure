package com.ww.algorithm.Trie;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrieTest {

    @Test
    public void testTrie() throws Exception {
        String[] wordList = {"dog", "cat", "panda","dog", "pig"};
        Trie trie = new Trie();
        for(int i=0; i<wordList.length; i++){
            trie.addWord(wordList[i]);
        }
        log.info("【trie.getSize】："+trie.getSize());
        //【trie.getSize】：4

        log.info("【trie.contains(panda)】："+trie.contains("panda"));
        //【trie.contains(panda)】：true

        log.info("【trie.contains(pan)】："+trie.contains("pan"));
        //【trie.contains(pan)】：false

        log.info("【trie.isPrefix(pan)】："+trie.isPrefix("pan"));
        //【trie.isPrefix(pan)】：true

        log.info("【trie.search(p.n.a)】："+trie.search("p.n.a"));
        //【trie.search(p.a.a)】：true
    }


}