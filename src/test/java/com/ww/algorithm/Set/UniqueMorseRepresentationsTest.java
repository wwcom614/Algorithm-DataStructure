package com.ww.algorithm.Set;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class UniqueMorseRepresentationsTest {

    @Test
    public void uniqueMorseRepresentations() throws Exception {
        String[] words = {"gin", "zen", "gig", "msg"};
        log.info("【Different Morse】:" + UniqueMorseRepresentations.uniqueMorseRepresentations(words));
    }//【Different Morse】:2

}