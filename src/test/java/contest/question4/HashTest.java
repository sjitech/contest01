package contest.question4;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by wang on 2015/04/17.
 */
public class HashTest {
    protected static Logger log = LoggerFactory.getLogger(HashTest.class);

    @Test
    public void testHash() {
        log.info("start test simple hashmap {}", this.getClass().getName());


        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);

        map.dump(log);

        addDataToHash(map);
        addDataToHash(map);

        map.dump(log);

        addDataToHash(map);

        map.dump(log);

        addDataToHash(map);
        addDataToHash(map);
        addDataToHash(map);

        map.dump(log);
    }

    private void addDataToHash(SimpleHashMap<Integer, String> hashMap) {
        Random random = new Random();
        int seed = random.nextInt(1000);

        hashMap.put(seed, "v" + seed);
    }
}
