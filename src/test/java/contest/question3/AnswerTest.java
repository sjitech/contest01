package contest.question3;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by wang on 2015/04/13.
 */
public class AnswerTest {

    protected static Logger log = LoggerFactory.getLogger(Answer.class);

    @Test
    public void testMinHeap() {
        log.info("start test min heap {}", this.getClass().getName());

        MinHeap heap = new MinHeap(16);

        heap.add(70);
        heap.add(40);
        heap.add(50);
        heap.add(20);
        heap.add(60);
        heap.add(10);
        heap.add(80);
        heap.add(170);
        heap.add(120);

        heap.display();

        heap.replace(0, new Node(100));

        heap.display();
    }

    @Test
    public void testAnswer() {
        log.info("start test question 3 {}", this.getClass().getName());

        try {
            Path inputFile = FileSystems.getDefault().getPath("/Users/wang/tmp/contest01_question3_input.txt");
            createInputFile(inputFile);

            Answer answer = new Answer();

            answer.resolve(inputFile.toString(), 100);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void createInputFile(Path input) throws IOException {
        final int lines = 100000;
        final int maxAccessCount = 10000000;

        if (Files.notExists(input)) {
            Files.createFile(input);
        } else {
            if (!Files.isWritable(input)) {
                throw new IOException("can not write to file : " + input);
            }
        }

        Random random = new Random();
        int accessCount = 0;

        try (FileWriter fw = new FileWriter(input.toFile())) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {

                for (int i = 0; i < lines; i++) {
                    accessCount = random.nextInt(maxAccessCount) + 1;

                    bw.write("www.site" + i + ".com," + accessCount);
                    bw.newLine();
                }
            }
        }
    }
}
