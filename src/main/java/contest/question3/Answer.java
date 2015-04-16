package contest.question3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2015/04/10.
 */
public class Answer {
    protected static Logger log = LoggerFactory.getLogger(Answer.class);

    protected static final int LINE_COUNT_ON_ONE_PROCESS = 100;

    public void resolve(String inputFilePath, int k) throws IOException {
        log.debug("select max " + k + " access site from file " + inputFilePath);
        // assume file format:
        // "site url string","access count"
        // for example
        // www.site1.com,283838
        // www.site232.com,500

        if (k < 0 || k > 100) {
            // assume k is far more smaller than file data lines
            throw new IllegalArgumentException("k must be integer between 0 to 100");
        }

        // assume file is very large, read file content partially
        Path inputPath = FileSystems.getDefault().getPath(inputFilePath);

        if (!Files.exists(inputPath) || !Files.isRegularFile(inputPath)) {
            throw new IllegalArgumentException("File " + inputFilePath + " not exists");
        }

        // init heap with first k lines data
        MinHeap heap = new MinHeap(k);

        RandomAccessFile raf = new RandomAccessFile(inputFilePath, "r");

        LoadedData data = loadData(raf, 0L, k);

        if (data != null && data.getNodes() != null && data.getNodes().size() > 0) {
            for(Node node : data.getNodes()) {
                heap.add(node);
            }
        }

        // process remain data on every 100 lines
        while(data != null && !data.isReachEndOfFile()) {
            data = loadData(raf, data.getOffset(), LINE_COUNT_ON_ONE_PROCESS);

            if (data.getNodes() != null && data.getNodes().size() > 0) {
                for(Node node : data.getNodes()) {
                    if (node.getKey() > heap.getRoot().getKey()) {
                        heap.replaceRoot(node);
                    }
                }
            }
        }

        raf.close(); // ignore exception handler

        log.info("max access sites are: ");
        heap.display();
    }

    private LoadedData loadData(RandomAccessFile raf, long offset, int lineCount) throws IOException {
        if (raf == null || lineCount < 1 || offset < 0 || offset > raf.length()) {
            return null;
        }

        raf.seek(offset);

        LoadedData data = new LoadedData();

        String line = null;
        int count = 0;
        String[] lineFields;


        while ((line = raf.readLine()) != null && count < lineCount) {
            lineFields = line.split(",");

            // ignore wrong format check
            data.addNode(new Node(Integer.parseInt(lineFields[1]), lineFields[0]));
            count++;
        }

        data.setOffset(raf.getFilePointer());

        if (line == null || data.getOffset() == raf.length()) {
            data.setReachEndOfFile(true);
            log.debug("reach file end[" + data.getOffset() + "]");
        }

        return data;
    }

}

class LoadedData {
    private boolean reachEndOfFile;
    private long offset;
    private List<Node> nodes;

    public boolean isReachEndOfFile() {
        return reachEndOfFile;
    }

    public void setReachEndOfFile(boolean reachEndOfFile) {
        this.reachEndOfFile = reachEndOfFile;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        if(nodes == null) {
            this.nodes = new ArrayList<>();
        }

        this.nodes.add(node);
    }
}

