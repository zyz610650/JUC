package com.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class Pipe {
    static class ReadThread implements Runnable{
        public ReadThread(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        private PipedReader pipedReader;

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive=0;
            while (true) {
                try {
                    while ((receive=pipedReader.read())!=-1)
                    {
                        System.out.print((char)receive);
                       // break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class WriteThread implements Runnable
    {
        public WriteThread(PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        private PipedWriter pipedWriter;


        @Override
        public void run() {
            System.out.println("this is writer");
            int receive=0;
            int i=0;
         while(i<5)
         {
             try {
                 pipedWriter.write("test"+i++ +"  \n");
             } catch (IOException e) {
                 e.printStackTrace();
             } finally{
                 try {
                     if (i==5)
                     pipedWriter.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedWriter pipedWriter=new PipedWriter();
        PipedReader pipedReader=new PipedReader();
        pipedWriter.connect(pipedReader);
        new Thread(new ReadThread(pipedReader)).start();
        Thread.sleep(10);
        new Thread(new WriteThread(pipedWriter)).start();
    }

}
