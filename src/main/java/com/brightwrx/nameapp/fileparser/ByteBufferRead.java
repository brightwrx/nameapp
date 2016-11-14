package com.brightwrx.nameapp.fileparser;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ByteBufferRead {

	// public static final String file = "/Users/sudheer/brightwrx/workspace/nameapp/data/names-unique-small.txt";
	public static final String file = "/Users/sudheer/brightwrx/workspace/nameapp/data/names-unique.txt";
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        long counter = 0;

        try {
            RandomAccessFile aFile = new RandomAccessFile(file, "r");
            FileChannel inChannel = aFile.getChannel();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            
            buffer.load();
            
            CharBuffer charBuffer = StandardCharsets.US_ASCII.decode(buffer);
            String read = charBuffer.toString();
            
            //  System.out.println(read); 
            counter += read.split("\n").length;
            
            buffer.clear(); // do something with the data and clear or compact it.
            inChannel.close();
            aFile.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");
        System.out.println("Parsed names: " + counter);
    }
}